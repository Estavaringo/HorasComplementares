/**
 * 
 */

$(document).ready(function () {

    //Inicializa o Paralex (imagem usada na Home)
    $('.parallax').parallax();

    // Initialize collapse button
    $(".button-collapse").sideNav();
    
    // Initialize collapsible (uncomment the line below if you use the dropdown variation)
    $('.collapsible').collapsible();

    $(".dropdown-user").dropdown({
        inDuration: 300,
        outDuration: 225,
        constrain_width: false, // Does not change width of dropdown to that of the activator
        hover: false, // Activate on hover
        gutter: 0, // Spacing from edge
        belowOrigin: true, // Displays dropdown below the button
        alignment: 'right' // Displays dropdown with edge aligned to the left of button
    }
    );

    /*Função que habilita o modal. Deve ser colocado no href o id da div que estiver
    com a classe .modal-trigger que será aberta por está função*/
    $('.modal-trigger').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .5, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    });
    
    /*FUNÇÕES DAS PÁGINAS DE CADASTRO*/
    $(".botao-excluir").click(function () {

        var codigo = this.id;

        var descricao = $('#descricao-' + codigo).text();

        $('#codigo-excluir').val(codigo);
        $('#descricao-excluir').val(descricao);

        $('#modal-excluir').openModal();
    });
    
    $(".botao-excluir-nome").click(function () {

        var codigo = this.id;

        var nome = $('#nome-' + codigo).text();

        $('#codigo-excluir').val(codigo);
        $('#nome-excluir').val(nome);

        $('#modal-excluir').openModal();
    });
    
    $(".botao-alterar-curso").click(function () {
                
        var codigo = this.id;
        var nome = $('#nome-' + codigo).text();
        var descricao = $('#descricao-' + codigo).text();

        $('#codigo-alterar').val(codigo);
        $('#nome-alterar').val(nome);
        $('#descricao-alterar').val(descricao);

        $('#modal-alterar').openModal('');
    });
    
    $('select').material_select();
    
    $(".botao-alterar-descricao").click(function (){
       
        var codigo = this.id;
        var descricao = $('#descricao-' + codigo).text();
        
        $('#codigo-alterar').val(codigo);
        $('#descricao-alterar').val(descricao);
        
        $('#modal-alterar').openModal('');
    });    
    
    $(".botao-alterar-tipoRelatorio").click(function (){
       
        var codigo = this.id;
        var nome = $('#nome-' + codigo).text();
        var descricao = $('#descricao-' + codigo).text();
        
        $('#codigo-alterar').val(codigo);
        $('#nome-alterar').val(nome);
        $('#descricao-alterar').val(descricao);
        
        $('#modal-alterar').openModal('');
    });
    
    $('.dropdown-button').dropdown({
      hover: true // Activate on hover
    }
  );  
    
  $('input[name="tipoUsuario-incluir"]:radio').change(function (){
    
    var parts;
    parts = Array[3];
    var codigo = String.valueOf(this.id);
        parts = codigo.split("-",3);
        
    var descricao =  $('#tipoUsuario-incluir-descricao-' + parts[3]).text();
    
    switch (descricao){
        case "Aluno":
            $('#div-funcional-incluir').addClass("hide");
            
            break;
        default:
            break;
    }
            
        
      
      
  });
  
});
