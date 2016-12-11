<%@page import="java.util.Collection"%>
<%@page import="Bean.TipoRelatorio"%>
<%@page contentType="text/html" pageEncoding="latin1"%>
<%@ include file="header.jsp" %>
<main>      
        <!--BANNER COM O TITULO-->
        <div class="titulo-pagina">
            <div class="container">
                <div class="row">
                    <div class="col s12">
                        <h2 class="header center-on-small-only">Tipo de Atividades Complementares</h2>
                        <h4 class="light green-text text-lighten-4 center-on-small-only">Gerencie os tipos de atividades complementares do sistema  </h4>
                    </div>
                </div>
            </div>
        </div>
        <!--CONTE�DO DO P�GINA-->
        <div class="container">
            <table id="example" class="highlight responsive-table">
                <thead>
                    <tr>
                        <th data-field="tipoRelatorio">Atividade</th>
                        <th data-field="acao">A��o</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty listaTipoRelatorio}">
                        <c:forEach var="tipoRelatorio" items="${listaTipoRelatorio}">
                            <tr>
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
                                        <li><a class="botao-excluir cyan-text text-darken-4" id="${tipoRelatorio.codigo}"><i class="material-icons deep-orange-text">delete</i>Excluir</a></li>
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
                <h4>Alterar tipo de atividade</h4>
                <p>Altere o tipo de atividade selecionado:</p>

                <!--Nome das Classes que dever�o ser informadas na requisi��o-->
                <input type="hidden" name="logicaDeNegocio" value="TipoRelatorioServlet">
                <input type="hidden" name="tarefa" value="alterar">
                <input type="hidden" name="codigo" id="codigo-alterar">

                <div class="input-field">
                    <i class="material-icons prefix"></i>
                    <label for="descricao-alterar"></label>
                    <input id="descricao-alterar" placeholder="Insira o nome do tipo de atividade aqui..." type="text" class="validate" name="descricao" value="" />
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
                <h4>Excluir tipo de comprovante</h4>
                <p>Confirme a exclus�o do tipo de comprovante selecionado:</p>

                <!--Nome das Classes que dever�o ser informadas na requisi��o-->
                <input type="hidden" name="logicaDeNegocio" value="TipoRelatorioServlet">
                <input type="hidden" name="tarefa" value="remover">
                <input type="hidden" name="codigo" id="codigo-excluir">

                <div class="input-field">
                    <i class="material-icons prefix">description</i>
                    <input disabled class="grey-text text-darken-4" id="descricao-excluir" type="text" name="descricao" value="" />
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
        <a class="modal-trigger btn-floating btn-large red tooltipped" data-position="top" data-delay="50" data-tooltip="Incluir Tipo de Relat�rio" href="#modal-incluir">
            <i class="large material-icons">add</i>
        </a>
    </div>
    <!-- ESTRUTURA DA MODAL INCLUIR -->
    <div id="modal-incluir" class="modal modal-fixed-footer">
        <form method="POST" action="Executa">
            <div class="modal-content">
                <h4>Incluir tipo de atividade</h4>
                <p>Insira abaixo o novo tipo de atividade</p>

                <!--Nome das Classes que dever�o ser informadas na requisi��o-->
                <input type="hidden" name="logicaDeNegocio" value="TipoRelatorioServlet">
                <input type="hidden" name="tarefa" value="incluir">

                
                <div class="input-field">
                    <i class="material-icons prefix">border_color</i>
                    <label for="descricao-incluir">Nome</label>
                    <input id="descricao-incluir" placeholder="Insira o nome do tipo de atividade aqui..." type="text" class="validate" name="descricao" value="" />
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