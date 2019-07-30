$(function() {

	$('body').on('click', '.remove-card-btn', function(e) {
		$.ajax({
			type : 'DELETE',
			url : '/lists/' + $(e.target).data('card-id'),
			success : function() {
				$(e.target).closest('.card').closest('.col-3').remove();
			}
		});
	});

	$('.add-card-btn').on('click', function() {
		$('.new-card-group').show();
	});
	$('.new-card-cancel').on('click', function() {
		$('.new-card-group').hide();
	});
	$('.new-card-confirm').on('click', function() {
		var newTitle = $('.new-card-title').val();
		$.ajax({
			type : 'POST',
			url : '/lists',
			data : {
				name : newTitle
			},
			success : function(data) {
				$('.add-card').before(data);
			}
		});
	});

	$('body').on('click', '.add-todo-btn', function(e) {
		$('.new-todo-group').hide();
		$(e.target).siblings('.new-todo-group').show();
	}).on('click', '.new-todo-cancel', function(e) {
		$(e.target).closest('.new-todo-group').hide();
	}).on(
			'click',
			'.new-todo-confirm',
			function(e) {
				var listId = $(e.target).data('list-id');
				var newTitle = $('.new-todo-title').val();
				$.ajax({
					type : 'POST',
					url : 'lists/' + listId + '/todos',
					data : {
						name : newTitle
					},
					success : function(data) {
						$(e.target).closest('.new-todo-group').siblings(
								'.list-group').append(data);
						$(e.target).siblings('.new-todo-title').val('');
					}
				});
			}).on('click', '.remove-todo-btn', function(e) {
		var todoId = $(e.target).data('todo-id');
		$.ajax({
			type : 'DELETE',
			url : '/todos/' + todoId,
			success : function() {
				$(e.target).closest('li').remove();
			}
		})
	})
});
