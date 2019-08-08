package com.cloudy9101.todolist.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryIntegrationTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private TodoRepository todoRepository;
 
    @Test
    public void whenFindByListId_thenReturnTodos() {
        // given
    	int listId = 1;
        Todo todo1 = new Todo();
        todo1.setName("todo1");
        todo1.setListId(listId);
        Todo todo2 = new Todo();
        todo2.setName("todo2");
        todo2.setListId(listId);
        entityManager.persist(todo1);
        entityManager.persist(todo2);
        entityManager.flush();
     
        // when
        ArrayList<Todo> found = todoRepository.findByListId(listId);
     
        // then
        assertThat(found.get(0)).isEqualTo(todo1);
        assertThat(found.get(1)).isEqualTo(todo2);
    }
}