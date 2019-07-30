$(function() {

  $('body').on('click', '.remove-card-btn', function(e) {
	 console.log("test");
    $.ajax({
      type: 'DELETE',
      url: '/lists/' + $(e.target).data('card-id'),
      success: function() {
        $(e.target).closest('.card').closest('.col-3').remove();
      }
    });
  });

	$('.add-card-btn').on('click', function() {
		$('.new-card-group').show();
	})
	$('.new-card-cancel').on('click', function() {
		$('.new-card-group').hide();
	})
	$('.new-card-confirm').on('click', function() {
    var newTitle = $('.new-card-title').val();
		$.ajax({
      type: 'POST',
      url: '/lists',
      data: { name: newTitle },
      success: function(data) {
        $('.add-card').before(data);
      }
    });
	})
});
