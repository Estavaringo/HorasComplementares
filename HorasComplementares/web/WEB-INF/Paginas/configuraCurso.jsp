<%@page import="java.util.Collection"%>
<%@page import="Bean.Curso"%>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<%@ include file="header.jsp" %>
<main>
    <!--BREADCRUMBS QUE MOSTRA O LOCAL ATUAL DO USUARIO-->
    <nav class='nav-breadcrumb green'>
        <div class="nav-wrapper">
            <div class="col s12">
                <a href="#!" class="breadcrumb green-text text-lighten-4">Configurações</a>
                <a href="Executa?logicaDeNegocio=CursoServlet&tarefa=consultarLista" class="breadcrumb green-text text-lighten-4">Cursos</a>
                <a href="Executa?logicaDeNegocio=ConfiguracaoCursoServlet&tarefa=consultar&codigoCurso=${curso.codigo}" class="breadcrumb">${curso.nome}</a>
            </div>
        </div>
    </nav>
    <!--BANNER COM O TITULO-->
    <div class="titulo-pagina-config">
        <div class="container">
            <div class="row">
                <div class="col s12">
                    <h4 class="truncate header center-on-small-only"><i class="centralizar-icons large material-icons">school</i>${curso.nome}</h4>
                </div>
            </div> 
        </div>
    </div>
    <!--CONTEÚDO DO PÁGINA-->
    <div class="container">
        <h5 class="header">Tipo de Relatórios que serão aceitos</h5>
        <form method="POST" action="Executa">

            <!--Nome das Classes que deverão ser informadas na requisição-->
            <input type="hidden" name="logicaDeNegocio" value="CursoServlet">
            <input type="hidden" name="tarefa" value="alterar">
            <input type="hidden" name="codigo" id="codigo-alterar">
            <div class="row">
                <c:forEach var="tipoRelatorio" items="${listaTipoRelatorio}">
                    <div class="col s12 m3">
                        <p>
                            <input type="checkbox" id="test5" />
                            <label for="test5">Red</label>
                        </p>
                    </div>
                </c:forEach>
            </div>

        </form>



    </div>
    <div class="divider"></div>
    <div class="container">
        <h5 class="header">Tipo de Relatórios</h5>



    </div>


    <!-- ESTRUTURA DA MODAL ALTERAR -->
    <div id="modal-alterar" class="modal modal-fixed-footer">
        <form method="POST" action="Executa">
            <div class="modal-content">
                <h4>Alterar Curso</h4>
                <p>Altere a curso selecionada:</p>

                <!--Nome das Classes que deverão ser informadas na requisição-->
                <input type="hidden" name="logicaDeNegocio" value="CursoServlet">
                <input type="hidden" name="tarefa" value="alterar">
                <input type="hidden" name="codigo" id="codigo-alterar">

                <div class="input-field">
                    <i class="material-icons prefix"></i>
                    <label for="descricao-alterar">Nome</label>
                    <input id="descricao-alterar" placeholder="Insira o nome da curso aqui..." type="text" class="validate" name="descricao" value="" />
                </div>

                <div class="input-field">
                    <i class="material-icons prefix">timelapse</i>
                    <label for="cargaHoraria-alterar">Caga Horária Necessária</label>
                    <input id="cargaHoraria-alterar" placeholder="Insira a carga horário necessária para o  respectivo curso..." type="text" name="cargaHoraria" value="" />
                </div>

            </div>
            <div class="modal-footer">
                <button type="submit" class="modal-action waves-effect waves-green btn btn-default cyan" value="Alterar">Alterar</button>
                <a href="#!" class="modal-close waves-effect waves-red btn-flat">Cancelar</a>
            </div>
        </form>
    </div>
    <!-- ESTRUTURA DA MODAL EXCLUIR -->
    <div id="modal-excluir" class="modal modal-fixed-footer">
        <form method="POST" action="Executa">
            <div class="modal-content">
                <h4>Excluir Curso</h4>
                <p>Confirme a exclusão do Curso selecionado:</p>

                <!--Nome das Classes que deverão ser informadas na requisição-->
                <input type="hidden" name="logicaDeNegocio" value="CursoServlet">
                <input type="hidden" name="tarefa" value="remover">
                <input type="hidden" name="codigo" id="codigo-excluir">

                <div class="input-field">
                    <i class="material-icons prefix">description</i>
                    <input disabled class="grey-text text-darken-4" id="descricao-excluir" type="text" name="descricao" value="" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="modal-action waves-effect waves-green btn btn-default cyan" value="Alterar">Confirmar Exclusão</button>
                <a href="#!" class="modal-close waves-effect waves-red btn-flat">Cancelar</a>
            </div>
        </form>
    </div>
    <!-- ESTRUTURA DA MODAL INCLUIR -->
    <div id="modal-incluir" class="modal modal-fixed-footer">
        <form method="POST" action="Executa">
            <div class="modal-content">
                <h4>Incluir Curso</h4>
                <p>Insira abaixo o novo Curso</p>

                <!--Nome das Classes que deverão ser informadas na requisição-->
                <input type="hidden" name="logicaDeNegocio" value="CursoServlet">
                <input type="hidden" name="tarefa" value="incluir">


                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="descricao-incluir">Nome</label>
                    <input id="descricao-incluir" placeholder="Insira o nome do curso aqui..." type="text" class="validate" name="descricao" value="" />
                </div>

                <div class="input-field">
                    <i class="material-icons prefix">timelapse</i>
                    <label for="cargaHoraria-incluir">Caga Horária Necessária</label>
                    <input id="cargaHoraria-incluir" placeholder="Insira a carga horário necessária para o respectivo curso..." type="text" name="cargaHoraria" value="" />
                </div>                
            </div>
            <div class="modal-footer">
                <button type="submit" class="modal-action waves-effect waves-green btn btn-default cyan" value="Incluir">Incluir</button>
                <a href="#!" class="modal-close waves-effect waves-red btn-flat">Cancelar</a>
            </div>
        </form>
    </div>
</main>
<%@ include file="footer.jsp" %>