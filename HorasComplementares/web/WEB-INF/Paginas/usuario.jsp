<%@page import="java.util.Collection"%>
<%@page import="Bean.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<main>    
        <!--BANNER COM O TITULO-->
        <div class="titulo-pagina">
            <div class="container">
                <div class="row">
                    <div class="col s12">
                        <h2 class="header center-on-small-only">Usuarios</h2>
                        <h4 class="light green-text text-lighten-4 center-on-small-only">Altere os usuarios</h4>
                    </div>
                </div>
            </div>
        </div>
        <!--CONTEÚDO DO PÁGINA-->
        <div class="container">
            <table id="example" class="highlight responsive-table">
                <thead>
                    <tr>
                        <th data-field="usuario">Usuario</th>
                        <th data-field="tipoUsuario">tipo de usuario</th>
                        <th data-field="funcional">Funcional</th>
                        <th data-field="prontuario">Prontuario</th>
                        <th data-field="dataMatricula">data da matricula</th>
                        <th data-field="semestre">Semestre</th>
                        <th data-field="login">Login</th>
                        <th data-field="ativo">Ativo</th>
                        <th data-field="acao">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty listaUsuario}">
                        <c:forEach var="usuario" items="${listaUsuario}">
                            <tr>
                                <td id="descricao-${usuario.codigo}">${usuario.nome}</td>
                                <td id="tipoUsuario-${usuario.codigo}">${usuario.tipoUsuario.descricao}</td>
                                <c:if test="${usuario.tipoUsuario.descricao != 'Aluno'}">
                                    <td id="funcional-${usuario.codigo}">${usuario.funcional}</td>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>-</td>
                                </c:if>
                                <c:if test="${usuario.tipoUsuario.descricao == 'Aluno'}">
                                    <td>-</td>
                                    <td id="prontuario-${usuario.codigo}">${usuario.prontuario}</td>
                                    <td id="dataMatricula-${usuario.codigo}">${usuario.dataMatricula}</td>
                                    <td id="semestre-${usuario.codigo}">${usuario.semestre}</td>
                                </c:if>
                                <td id="login-${usuario.codigo}">${usuario.login}</td>
                                <td id="ativo-${usuario.codigo}">${usuario.ativo}</td>
                                <td class="hide-on-med-and-up">
                                    <a class="botao-alterar-usuario cyan-text text-darken-4" id="${usuario.codigo}"><i class="material-icons yellow-text text-darken-4" style="font-size: 35px">edit</i></a>
                                    <br>
                                    <a class="botao-excluir cyan-text text-darken-4" id="${usuario.codigo}"><i class="material-icons deep-orange-text" style="font-size: 35px">delete</i></a>
                                </td>
                                <td class="hide-on-small-only">
                                    <!-- Dropdown Trigger -->
                                    <a class='dropdown-button btn-floating grey darken-2' href='#' data-constrainwidth="false" data-activates='dropdown${usuario.codigo}'><i class="material-icons">more_horiz</i></a>
                                    <!-- Dropdown Structure -->
                                    <ul id='dropdown${usuario.codigo}' class='dropdown-content'>
                                        <li class="divider"></li>
                                        <li><a class="botao-alterar-usuario cyan-text text-darken-4" id="${usuario.codigo}"><i class="material-icons yellow-text text-darken-4">edit</i>Alterar</a></li>
                                        <li class="divider"></li>
                                        <li><a class="botao-excluir cyan-text text-darken-4" id="${usuario.codigo}"><i class="material-icons deep-orange-text">delete</i>Excluir</a></li>
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
                <h4>Alterar usuario</h4>
                <p>Altere o usuario selecionado:</p>

                <!--Nome das Classes que deverão ser informadas na requisição-->
                <input type="hidden" name="logicaDeNegocio" value="UsuarioServlet">
                <input type="hidden" name="tarefa" value="alterar">
                <input type="hidden" name="codigo" id="codigo-alterar">
                
                
                <div>
                    <label>Tipo de usuario</label>
                    <c:forEach var="tipoUsuario" items="${listaTipoUsuario}">
                        <c:if test="${usuario.tipoUsuario.codigo == tipoUsuario.codigo}">
                            <input name="tipoUsuario-alterar" type="radio" id="tipoUsuario-alterar-${tipoUsuario.codigo}" value="${tipoUsuario.codigo}"  />
                            <label for="tipoUsuario-alterar-${tipoUsuario.codigo}">${tipoUsuario.descricao}</label><br>
                        </c:if>
                        <c:if test="${usuario.tipoUsuario.codigo != tipoUsuario.codigo}">
                            <input name="tipoUsuario-alterar" type="radio" id="tipoUsuario-alterar-${tipoUsuario.codigo}" value="${tipoUsuario.codigo}"  />
                            <label for="tipoUsuario-alterar-${tipoUsuario.codigo}">${tipoUsuario.descricao}</label><br>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="descricao-alterar"></label>
                    <input id="descricao-alterar" placeholder="Insira o nome do usuario aqui..." type="text" class="validate" name="descricao" value="" />
                </div>
                <c:if test="${usuario.tipoUsuario.descricao == 'Aluno'}">
                    <div class="input-field">
                        <i class="material-icons prefix">border_color</i>
                        <label for="prontuario-alterar"></label>
                        <input id="prontuario-alterar" placeholder="Insira o prontuario do usuario aqui..." type="text" class="validate" name="prontuario" value="" />
                    </div>
                    <div class="input-field">
                        <i class="material-icons prefix">border_color</i>
                        <label for="dataMatricula-alterar"></label>
                        <input id="dataMatricula-alterar" placeholder="Insira a data da matricula do usuario aqui..." type="text" class="validate" name="dataMatricula" value="" />
                    </div>
                    <div class="input-field">
                        <i class="material-icons prefix">border_color</i>
                        <label for="semestre-alterar"></label>
                        <input id="semestre-alterar" placeholder="Insira o semestre do usuario aqui..." type="text" class="validate" name="semestre" value="" />
                    </div>
                </c:if>
                <c:if test="${usuario.tipoUsuario.descricao != 'Aluno'}">
                    <div class="input-field">
                        <i class="material-icons prefix">border_color</i>
                        <label for="funcional-alterar"></label>
                        <input id="fincional-alterar" placeholder="Insira o numero de funcionario do usuario aqui..." type="text" class="validate" name="funcional" value="" />
                    </div>
                </c:if>
                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="login-alterar"></label>
                    <input id="login-alterar" placeholder="Insira o login do usuario aqui..." type="text" class="validate" name="login" value="" />
                </div>
                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="senha-alterar"></label>
                    <input id="senha-alterar" placeholder="Insira a senha do usuario aqui..." type="text" class="validate" name="senha" value="" />
                </div>
                <div class="switch">
                    <label for="ativo-alterar">
                        Inativo
                        <input id="ativo-alterar" type="checkbox" class="validate" name="ativo" value="true" />
                        <span class="lever"></span>
                        Ativo
                    </label>
                </div>
                <div class="input-field col s12">
                    <label>Curso</label>
                    <select id="curso-alterar" name="curso">
                        <c:forEach var="curso" items="${listaCurso}">
                            <option value="${curso.codigo}">${curso.nome}</option>
                        </c:forEach>
                    </select>
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
                <h4>Excluir tipo de usuario</h4>
                <p>Confirme a exclusão do tipo de usuario selecionado:</p>

                <!--Nome das Classes que deverão ser informadas na requisição-->
                <input type="hidden" name="logicaDeNegocio" value="UsuarioServlet">
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
    <!-- ABRE MODAL INCLUIR -->
    <div class="fixed-action-btn" style="bottom: 110px; right: 24px;">
        <a class="modal-trigger btn-floating btn-large red" href="#modal-incluir">
            <i class="large material-icons">add</i>
        </a>
    </div>
    <!-- ESTRUTURA DA MODAL INCLUIR -->
    <div id="modal-incluir" class="modal modal-fixed-footer">
        <form method="POST" action="Executa">
            <div class="modal-content">
                <h4>Incluir tipo de usuario</h4>
                <p>Insira abaixo o novo tipo de usuario</p>

                <!--Nome das Classes que deverão ser informadas na requisição-->
                <input type="hidden" name="logicaDeNegocio" value="UsuarioServlet">
                <input type="hidden" name="tarefa" value="incluir">

                <div>
                    <label>Tipo de usuario</label><br>
                    <c:forEach var="tipoUsuario" items="${listaTipoUsuario}">
                        <c:if test="${usuario.tipoUsuario.codigo == tipoUsuario.codigo}">
                            <input name="tipoUsuario-incluir" type="radio" id="tipoUsuario-incluir-${tipoUsuario.codigo}" class="with-gap" value="${tipoUsuario.codigo}" checked />
                        </c:if>
                        <c:if test="${usuario.tipoUsuario.codigo != tipoUsuario.codigo}">
                            <input name="tipoUsuario-incluir" type="radio" id="tipoUsuario-incluir-${tipoUsuario.codigo}" class="with-gap" value="${tipoUsuario.codigo}"  />
                        </c:if>
                        <label for="tipoUsuario-incluir-${tipoUsuario.codigo}">${tipoUsuario.descricao}</label><br>
                        <input type="hidden" value="${tipoUsuario.descricao}" id="tipoUsuario-incluir-descricao-${tipoUsuario.codigo}"></label>
                    </c:forEach>
                </div>
                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="descricao-incluir"></label>
                    <input id="descricao-incluir" placeholder="Insira o nome do usuario aqui..." type="text" class="validate" name="descricao" value="" />
                </div>
                    <div class="input-field">
                        <i class="material-icons prefix">border_color</i>
                        <label for="prontuario-incluir"></label>
                        <input id="prontuario-incluir" placeholder="Insira o prontuario do usuario aqui..." type="text" class="validate" name="prontuario" value="" />
                    </div>
                    <div class="input-field">
                        <i class="material-icons prefix">border_color</i>
                        <label for="dataMatricula-incluir"></label>
                        <input id="dataMatricula-incluir" placeholder="Insira a data da matricula do usuario aqui..." type="text" class="validate" name="dataMatricula" value="" />
                    </div>
                    <div class="input-field">
                        <i class="material-icons prefix">border_color</i>
                        <label for="semestre-incluir"></label>
                        <input id="semestre-incluir" placeholder="Insira o semestre do usuario aqui..." type="text" class="validate" name="semestre" value="" />
                    </div>
                <div id="div-funcional-incluir">
                    <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="funcional-incluir"></label>
                    <input id="fincional-incluir" placeholder="Insira o numero de funcionario do usuario aqui..." type="text" class="validate" name="funcional" value="" />
                </div>
                </div>
                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="login-incluir"></label>
                    <input id="login-incluir" placeholder="Insira o login do usuario aqui..." type="text" class="validate" name="login" value="" />
                </div>
                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="senha-incluir"></label>
                    <input id="senha-incluir" placeholder="Insira a senha do usuario aqui..." type="text" class="validate" name="senha" value="" />
                </div>
                <div class="switch">
                    <label for="ativo-incluir">
                        Inativo
                        <input id="ativo-incluir" type="checkbox" class="validate" name="ativo" value="true" />
                        <span class="lever"></span>
                        Ativo
                    </label>
                </div>
                <br>
                <div class="input-field col s12">
                    <label>Curso</label><br>
                    <select id="curso-incluir" name="">
                        <c:forEach var="curso" items="${listaCurso}">
                            <c:if test="${usuario.curso.codigo == curso.codigo}">
                                <option value="${curso.codigo}" selected="selected">${curso.nome}</option>
                            </c:if>
                            <c:if test="${usuario.curso.codigo != curso.codigo}">
                                <option value="${curso.codigo}">${curso.nome}</option>
                            </c:if>
                        </c:forEach>
                    </select>
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