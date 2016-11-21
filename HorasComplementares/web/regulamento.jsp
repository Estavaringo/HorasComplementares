<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header_home.jsp" %>
<main>  
    <!--BANNER DA PÁGINA-->
    <div class="col m3">
        <div id="index-banner" class="parallax-container">
            <div class="section no-pad-bot">
                <div class="container">
                    <br><br>
                    <h1 class="header center white-text text-lighten-2">Horas<br>Complementares</h1>
                    <div class="row center">
                        <h5 class="header col s12 light">As Atividades Complementares são um conjunto de atividades, habilidades, experiências e conhecimentos que devem ser adquiridos fora do ambiente escolar pelos alunos desta Instituição.</h5>
                    </div>
                    <div class="row center">
                        <a href="/HorasComplementares/login.jsp" id="download-button" class="btn-large waves-effect waves-light green lighten-1">Acesse Aqui</a>
                    </div>
                    <br><br>

                </div>
            </div>
            <div class="parallax"><img src="images/clock.jpg" alt="Clock background"></div>
        </div>

        <!--CONTEÚDO DO PÁGINA-->
        <div class="container">
            <div class="section">

                <!--   Icon Section   -->
                <div class="row">
                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center green-text"><i class="material-icons">forum</i></h2>
                            <h5 class="center">Quadro de Avisos</h5>

                            <p class="light">No quadro de avisos você poderá visualizar as atividades recomendadas pelo seu coordenador, informações úteis e outras publicações.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center green-text"><i class="material-icons">timelapse</i></h2>
                            <h5 class="center">Gerenciar as Horas</h5>

                            <p class="light">Você poderá visualizar as atividades que já foram lançadas, o status da das suas solicitações e enviar as atividades realizadas.</p>
                        </div>
                    </div>

                    <div class="col s12 m4">
                        <div class="icon-block">
                            <h2 class="center green-text"><i class="material-icons">settings</i></h2>
                            <h5 class="center">Regulamento</h5>

                            <p class="light">Aqui, você pode acessar o regulamento, entender quais as regras para o seu curso, ver o passo a passo de como acessar o ambiente.</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
</main>
<%@ include file="footer_home.jsp" %>