<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="Latin1"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Horas Complementares</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/horas_complementares.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body>
        <header>
            <!-- BARRA SUPERIOR DO SITE -->
            <div class="navbar-fixed">
                <nav class="grey darken-3">
                    <div class="nav-wrapper">
                        <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
                        <a id="logo-container" href="/HorasComplementares" class="brand-logo"><img src="images/HorasComplementares.png" alt="Horas Complementares"></a>
                        <ul class="right">
                            <!-- Dropdown Trigger -->
                            <li>
                                <a class="dropdown-button" href="#!" data-activates="dropdown-user">
                                    <b class="hide-on-med-and-down">Olá ${usuarioLogado.nome}</b><i class="material-icons right">account_circle</i><i class="material-icons right hide-on-med-and-down">arrow_drop_down</i>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>

            <!-- MENU DE LOGOUT DO USUÁRIO -->
            <ul id="dropdown-user" class="dropdown-content">
                <li class="divider"></li>
                <li><a href="Executa?logicaDeNegocio=Logout" class="cyan-text"><i class="material-icons left">close</i>Sair</a></li>
            </ul>

            <!-- MENU LATERAL DO SITE -->
            <ul id="slide-out" class="side-nav fixed">
                <li class="green lighten-2">
                    <div class="area-do-usuario center-align">
                        <a href="/HorasComplementares"><img src="images/HorasComplementares_logo.png" alt="Horas Complementares"></a>
                    </div>
                </li>
                <li class="truncate"><a class="waves-effect" href="Executa?logicaDeNegocio=LoginServlet"><i class="material-icons">dashboard</i><span class="gray-text text-darken-2"><b>Dashboard</b></span></a></li>
                <li class="truncate"><div class="divider"></div></li>
                <li class="truncate"><a class="waves-effect modal-trigger" href="#modal-upload"><i class="icon-horas-complementares"></i><span class="gray-text text-darken-2"><b>Nova Atividade</b></span></a></li>
                <li class="truncate"><a class="waves-effect" href="#!"><i class="material-icons">swap_vertical_circle</i><span class="gray-text text-darken-2"><b>Entregas</b></span></a></li>
                <li class="truncate"><div class="divider"></div></li>
                <li class="truncate"><a class="waves-effect" href="#!"><i class="material-icons">forum</i><span class="gray-text text-darken-2"><b>Quadro de Avisos</b></span></a></li>
                <li class="truncate"><a class="waves-effect" href="#!"><i class="material-icons">info</i><span class="gray-text text-darken-2"><b>Regulamento</b></span></a></li>
                    <%--:if test="${usuarioLogado.moderador}"--%>
                    <c:if test="${true}">
                    <li><div class="divider"></div></li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header">Configurações<i class="material-icons">arrow_drop_down</i></a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li class="truncate"><a href="Executa?logicaDeNegocio=CursoServlet&tarefa=consultarLista">Cursos</a></li>
                                        <li class="truncate"><a href="Executa?logicaDeNegocio=UsuarioServlet&tarefa=consultarLista">Usuários</a></li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li><div class="divider"></div></li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header">Parâmetros<i class="material-icons">arrow_drop_down</i></a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li class="truncate"><a href="Executa?logicaDeNegocio=TipoComprovanteServlet&tarefa=consultarLista">Tipos de Comprovantes</a></li>
                                        <li class="truncate"><a href="Executa?logicaDeNegocio=TipoRelatorioServlet&tarefa=consultarLista">Tipos de Atividades</a></li>
                                        <li class="truncate"><a href="Executa?logicaDeNegocio=TipoContatoServlet&tarefa=consultarLista">Tipos de Contatos</a></li>
                                        <li class="truncate"><a href="Executa?logicaDeNegocio=StatusRelatorioAtividadeServlet&tarefa=consultarLista">Status de Atividades</a></li>
                                        <li class="truncate"><a href="Executa?logicaDeNegocio=TipoNotificacaoServlet&tarefa=consultarLista">Tipos de Notificação</a></li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </header>
