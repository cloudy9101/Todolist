package com.cloudy9101.todolist.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
 
    @Test
    public void whenFindByEmail_thenReturnUser() {
        // given
        User user = new User();
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setPassword("test");
        entityManager.persist(user);
        entityManager.flush();
     
        // when
        User found = userRepository.findByEmail(user.getEmail());
     
        // then
        assertThat(found.getEmail())
          .isEqualTo(user.getEmail());
    }
}