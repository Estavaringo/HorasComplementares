<%@page import="java.util.Collection"%>
<%@page import="Bean.Curso"%>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<%@ include file="header.jsp" %>
<main>    
        <!--BANNER COM O TITULO-->
        <div class="titulo-pagina">
            <div class="container">
                <div class="row">
                    <div class="col s12">
                        <h2 class="header center-on-small-only">Curso</h2>
                        <h4 class="light green-text text-lighten-4 center-on-small-only">Altere os cursos cadastrados</h4>
                    </div>
                </div>
            </div>
        </div>
        <!--CONTE�DO DO P�GINA-->
        <div class="container">
            <table id="example" class="highlight responsive-table">
                <thead>
                    <tr>
                        <th data-field="curso">Nome do Curso</th>
                        <th data-field="descricao">Descri��o do Curso</th>
                        <th data-field="acao">A��o</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty listaCurso}">
                        <c:forEach var="curso" items="${listaCurso}">
                            <tr>
                                <td id="nome-${curso.codigo}">
                                    <a class="btn-floating green darken-2 tooltipped" data-position="bottom" data-delay="50" data-tooltip="Clique aqui para configurar." href="Executa?logicaDeNegocio=ConfiguracaoCursoServlet&tarefa=consultar&codigoCurso=${curso.codigo}" class="waves-effect waves-light">
                                        <i class="large material-icons left">settings</i>
                                    </a>${curso.nome}</td>
                                <td id="descricao-${curso.codigo}"> ${curso.descricao}</td>
                                <td class="hide-on-med-and-up">
                                    <a class="botao-alterar-curso cyan-text text-darken-4" id="${curso.codigo}"><i class="material-icons yellow-text text-darken-4" style="font-size: 35px">edit</i></a>
                                    <br>
                                    <a class="botao-excluir-nome cyan-text text-darken-4" id="${curso.codigo}"><i class="material-icons deep-orange-text" style="font-size: 35px">delete</i></a>
                                </td>
                                <td class="hide-on-small-only">
                                    <!-- Dropdown Trigger -->
                                    <a class='dropdown-button btn-floating grey darken-2' href='#' data-constrainwidth="false" data-activates='dropdown${curso.codigo}'><i class="material-icons">more_horiz</i></a>
                                    <!-- Dropdown Structure -->
                                    <ul id='dropdown${curso.codigo}' class='dropdown-content'>
                                        <li class="divider"></li>
                                        <li><a class="botao-alterar-curso cyan-text text-darken-4" id="${curso.codigo}"><i class="material-icons yellow-text text-darken-4">edit</i>Alterar</a></li>
                                        <li class="divider"></li>
                                        <li><a class="botao-excluir-nome cyan-text text-darken-4" id="${curso.codigo}"><i class="material-icons deep-orange-text">delete</i>Excluir</a></li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
        
    <!-- ESTRUTURA DA MODAL ALTERAR -->
    <div id="modal-alterar" class="modal modal-fixed-footer">
        <form method="POST" action="Executa">
            <div class="modal-content">
                <h4>Alterar Curso</h4>
                <p>Altere a curso selecionada:</p>

                <!--Nome das Classes que dever�o ser informadas na requisi��o-->
                <input type="hidden" name="logicaDeNegocio" value="CursoServlet">
                <input type="hidden" name="tarefa" value="alterar">
                <input type="hidden" name="codigo" id="codigo-alterar">

                <div class="input-field">
                    <i class="material-icons prefix">edit</i>
                    <label for="nome-alterar">Nome</label>
                    <input id="descricao-alterar" placeholder="Insira o nome do curso aqui..." type="text" class="validate" name="nome" value="" />
                </div>
                
                <div class="input-field">
                    <i class="material-icons prefix">description</i>
                    <label for="descricao-alterar">Descri��o do Curso</label>
                    <input id="cargaHoraria-alterar" placeholder="Insira a descri��o do curso..." type="text" name="descricao" value="" />
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
                <p>Confirme a exclus�o do Curso selecionado:</p>

                <!--Nome das Classes que dever�o ser informadas na requisi��o-->
                <input type="hidden" name="logicaDeNegocio" value="CursoServlet">
                <input type="hidden" name="tarefa" value="remover">
                <input type="hidden" name="codigo" id="codigo-excluir">

                <div class="input-field">
                    <i class="material-icons prefix">edit</i>
                    <input disabled class="grey-text text-darken-4" id="nome-excluir" type="text" name="nome" value="" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="modal-action waves-effect waves-green btn btn-default cyan" value="Alterar">Confirmar Exclus�o</button>
                <a href="#!" class="modal-close waves-effect waves-red btn-flat">Cancelar</a>
            </div>
        </form>
    </div>
    <!-- ABRE MODAL INCLUIR -->
    <div class="fixed-action-btn" style="bottom: 110px; right: 24px;">
        <a class="modal-trigger btn-floating btn-large red tooltipped" data-position="top" data-delay="50" data-tooltip="Incluir Curso" href="#modal-incluir">
            <i class="large material-icons">add</i>
        </a>
    </div>
    <!-- ESTRUTURA DA MODAL INCLUIR -->
    <div id="modal-incluir" class="modal modal-fixed-footer">
        <form method="POST" action="Executa">
            <div class="modal-content">
                <h4>Incluir Curso</h4>
                <p>Insira abaixo o novo Curso</p>

                <!--Nome das Classes que dever�o ser informadas na requisi��o-->
                <input type="hidden" name="logicaDeNegocio" value="CursoServlet">
                <input type="hidden" name="tarefa" value="incluir">

                
                <div class="input-field">
                    <i class="material-icons prefix">edit</i>
                    <label for="nome-incluir">Nome</label>
                    <input id="descricao-incluir" placeholder="Insira o nome do curso aqui..." type="text" class="validate" name="nome" value="" />
                </div>
                                
                <div class="input-field">
                    <i class="material-icons prefix">description</i>
                    <label for="descricao-incluir">Descri��o do Curso</label>
                    <input id="cargaHoraria-incluir" placeholder="Insira uma descri��o para o curso..." type="text" name="descricao" value="" />
                </div>                
            </div>
            <div class="modal-footer">
                <button type="submit" class="modal-action waves-effect waves-green btn btn-default cyan" value="Incluir">Incluir e Configurar</button>
                <a href="#!" class="modal-close waves-effect waves-red btn-flat">Cancelar</a>
            </div>
        </form>
    </div>
</main>
<%@ include file="footer.jsp" %>