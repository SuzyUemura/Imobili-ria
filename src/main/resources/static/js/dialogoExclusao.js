$('#confirmacaoExclusao').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	var descricao = button.data('descricao');
	var elemento = button.data('elemento');
	
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	action += elemento;
	
	if(!action.endsWith('/')) {
		action += '/';
	}
	
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' 
	+ descricao + '</strong>?');
	

});

$(function() {
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	});