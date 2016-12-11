<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header_home.jsp" %>
<main class="login">
    <div class="row">
        <div class="col s12 l7">
            <div class="row">
                <div class="col s12">
                    <h1>Acesse</h1>
                </div>
                <div class="col s12 edge">
                    <section class="login">
                        <form method="POST" action="Executa">
                            <div class="row">
                                <input type="hidden" name="logicaDeNegocio" value="LoginServlet">
                            </div>
                            <div class="row">
                                <c:if test="${(not empty usuarioInvalido)}">
                                    <div class="error">
                                        <span class="red-text text-darken-2">Usuário Inválido!</span>
                                    </div>
                                </c:if>
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input placeholder="Informe seu login" id="login" name="login" type="text" class="validate">
                                    <label for="login">Login</label>
                                </div>
                            </div>
                            <c:if test="${(not empty senhaInvalida)}">
                                <div class="error">
                                    <span class="red-text text-darken-2">Senha Inválida!</span>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">lock</i>
                                    <input placeholder="Informe sua senha" id="senha" name="senha" type="password" class="validate">
                                    <label for="senha">Senha</label>
                                </div>
                            </div>
                            <div class="row">
                                <input type="submit" class="btn btn-large green" value="Enviar">
                            </div>
                        </form>
                    </section>
                </div>
            </div>
        </div>
        <div class="col s12 l5 center-align">
            <div class="cadastro card-panel green lighten-4">
                <a href="/HorasComplementares">
                    <img src="images/HorasComplementares_horizontal.png" alt="Horas Complementares">
                </a>
                <h2>Ainda não se cadastrou?</h2>
                <a href="" class="btn orange">Cadastre-se</a>
            </div>
        </div>
    </div>
</main>
<%@ include file="footer_home.jsp" %>