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
                    <h4 class="truncate header center-on-small-only"><i class="centralizar-icons medium material-icons">school</i>${curso.nome}</h4>
                </div>
            </div> 
        </div>
    </div>
    <!--CONTEÚDO DO PÁGINA-->
    <div class="container">
        <div class="row">
            <div class="col s12">
                <div id="status" class="section scrollspy">
                    <h5>Section 1</h5>
                    <p>Content </p>
                </div>
                <div class="divider"></div>
                <div id="introduction" class="section scrollspy">
                    <h5 class="header">Tipo de Relatórios que serão aceitos</h5>
                    <table id="example" class="highlight responsive-table">
                        <thead>
                            <tr>
                                <th data-field="nome">Atividade</th>
                                <th data-field="descricao">Descrição</th>
                                <th data-field="acao">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty listaTipoRelatorio}">
                                <c:forEach var="tipoRelatorio" items="${listaTipoRelatorio}">
                                    <tr>
                                        <td id="nome-${tipoRelatorio.codigo}">${tipoRelatorio.nome}</td>
                                        <td id="descricao-${tipoRelatorio.codigo}">${tipoRelatorio.descricao}</td>
                                        <td class="hide-on-med-and-up">
                                            <a class="botao-alterar-tipoRelatorio cyan-text text-darken-4" id="${tipoRelatorio.codigo}"><i class="material-icons yellow-text text-darken-4" style="font-size: 35px">edit</i></a>
                                            <br>
                                            <a class="botao-excluir cyan-text text-darken-4" id="${tipoRelatorio.codigo}"><i class="material-icons deep-orange-text" style="font-size: 35px">delete</i></a>
                                        </td>
                                        <td class="hide-on-small-only">
                                            <!-- Dropdown Trigger -->
                                            <a class='dropdown-button btn-floating grey darken-2' href='#' data-constrainwidth="false" data-activates='dropdown${tipoRelatorio.codigo}'><i class="material-icons">more_horiz</i></a>
                                            <!-- Dropdown Structure -->
                                            <ul id='dropdown${tipoRelatorio.codigo}' class='dropdown-content'>
                                                <li class="divider"></li>
                                                <li><a class="botao-alterar-tipoRelatorio cyan-text text-darken-4" id="${tipoRelatorio.codigo}"><i class="material-icons yellow-text text-darken-4">edit</i>Alterar</a></li>
                                                <li class="divider"></li>
                                                <li><a class="botao-excluir-nome cyan-text text-darken-4" id="${tipoRelatorio.codigo}"><i class="material-icons deep-orange-text">delete</i>Excluir</a></li>
                                            </ul>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <div class="divider"></div>

                <div id="structure" class="section scrollspy">
                    <h5>Section 1</h5>
                    <p>Content </p>
                </div>
                <div class="divider"></div>

                <div id="initialization" class="section scrollspy">
                    <p>Content </p>
                </div>
                <div class="divider"></div>

            </div>
            <div class="target acesso-rapido-lateral">
                <div class="col hide-on-small-only">
                    <ul class="section table-of-contents">
                        <li><a href="#status">Status</a></li>
                        <li><a href="#introduction">Introduction</a></li>
                        <li><a href="#structure">Structure</a></li>
                        <li><a href="#initialization">Intialization</a></li>
                    </ul>
                </div>
            </div>
        </div>"


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