<%-- 
    Document   : index
    Created on : 15/10/2016, 10:00:03
    Author     : flaviosampaioreisdelima
--%>
<!-- ABRE MODAL INCLUIR -->
<div class="fixed-action-btn horizontal" style="bottom: 45px; right: 24px;">
    <a class="modal-trigger btn-floating btn-large green darken-4" href="#modal-upload">
        <i class="icon-horas-complementares"></i>
    </a>
    <ul>
      <li><a class="modal-trigger btn-floating red darken-3 tooltipped" data-position="top" data-delay="50" data-tooltip="Nova Atividade" href="#modal-upload">
              <i class="material-icons">add_circle</i>
          </a>
      </li>
      <li><a class="btn-floating blue tooltipped" data-position="top" data-delay="50" data-tooltip="Ver Histórico"><i class="material-icons">swap_vertical_circle</i></a></li>
    </ul>
</div>
<!-- ESTRUTURA DA MODAL INCLUIR -->
<div id="modal-upload" class="modal modal-fixed-footer">
    
    <div class="stepwizard-row setup-panel">
        <div class="stepwizard-step">
            <a href="#step-1" type="button" class="btn-floating btn-primary btn-circle">1</a>
            <p>Step 1</p>
        </div>
        <div class="stepwizard-step">
            <a href="#step-2" type="button" class="btn-floating btn-default btn-circle" disabled="disabled">2</a>
            <p>Step 2</p>
        </div>
        <div class="stepwizard-step">
            <a href="#step-3" type="button" class="btn-floating btn-default btn-circle" disabled="disabled">3</a>
            <p>Step 3</p>
        </div>
    </div>
    
    
    <form method="POST" action="Executa">
        <div class="modal-content">
            <h4>Nova Atividade</h4>
            <p>Siga o passo a passo para incluir uma nova atividade:</p>

            <!--Nome das Classes que deverão ser informadas na requisição-->
            <input type="hidden" name="logicaDeNegocio" value="RelatorioAtividadeServlet">
            <input type="hidden" name="tarefa" value="incluir">

            <div class="input-field">
                <i class="material-icons prefix">border_color</i>
                <label for="nome-incluir" class="active">Título</label>
                <input id="nome-incluir" placeholder="Insira um título para a sua publicação aqui..." type="text" class="validate" name="nome" value="" />
            </div>

            <div class="input-field">
                <i class="material-icons prefix">description</i>
                <label for="descricao-incluir" class="active">Descrição</label>
                <input id="descricao-incluir" placeholder="Insira uma descrição para a sua foto..." type="text" name="descricao" value="" />
            </div>
            
            <!--Campo de Upload do Comprovante-->
            <div class="file-field input-field">
                <div class="btn">
                    <span>Selecione o Comprovante</span>
                    <input type="file">
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text">
                </div>
            </div>

        </div>
        <div class="modal-footer">
            <button type="submit" class="modal-action waves-effect waves-green btn btn-default cyan" value="Incluir">Incluir</button>
            <a href="#!" class="modal-close waves-effect waves-red btn-flat">Cancelar</a>
        </div>
    </form>
</div>
<!-- RODAPÉ DA PÁGINA -->
<footer class="page-footer green darken-1">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Horas Complementares</h5>
                <p class="grey-text text-lighten-4">Sistema para gestão do processo das Atividades Complementares.</p>
            </div>
            <div class="col l3 s12">
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Conectar</h5>
                <ul>
                    <li><a class="white-text" href="http://www.ifsp.edu.br">IFSP</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container truncate">
            Elaborado por 
            <a class="light-green-text text-lighten-3" href="#">Flávio Sampaio</a>, 
            <a class="light-green-text text-lighten-3" href="#">Gabriel Estavaringo</a> e 
            <a class="light-green-text text-lighten-3" href="#">Lucas Tortelli</a>
        </div>
    </div>
</footer>
<!--  Scripts-->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>
</body>
</html>

