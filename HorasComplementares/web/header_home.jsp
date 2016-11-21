<%-- 
    Document   : index
    Created on : 15/10/2016, 10:00:03
    Author     : flaviosampaioreisdelima
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Horas Complementares</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style_home.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body>
        <header>
            <!-- BARRA SUPERIOR DO SITE -->
            <div class="navbar-fixed">
                <nav class="grey darken-3">
                    <div class="nav-wrapper">
                        <a href="#" data-activates="slide-out" class="button-collapse show-on-large"><i class="material-icons">menu</i></a>
                        <a id="logo-container" href="/HorasComplementares" class="brand-logo"><img src="images/HorasComplementares.png" alt="Horas Complementares"></a>
                    </div>
                </nav>
            </div>

            <!-- MENU DE LOGOUT DO USUÁRIO -->
            <ul id="dropdown-user" class="dropdown-content">
                <li class="divider"></li>
                <li><a href="Executa?logicaDeNegocio=Logout" class="cyan-text"><i class="material-icons left">close</i>Sair</a></li>
            </ul>

            <!-- MENU LATERAL DO SITE -->
            <ul id="slide-out" class="side-nav">
                <li class="green lighten-2">
                    <div class="area-do-usuario center-align">
                        <a href="/HorasComplementares"><img src="images/HorasComplementares_logo.png" alt="Horas Complementares"></a>
                        <p class="white-text truncate">${usuarioLogado.nome}</p>
                    </div>
                </li>
                <li><a class="waves-effect" href="/HorasComplementares/quadro.jsp"><i class="material-icons">forum</i><span class="gray-text text-darken-2"><b>Quadro de Avisos</b></span></a></li>
                <li><a class="waves-effect" href="/HorasComplementares/regulamento.jsp"><i class="material-icons">settings</i><span class="gray-text text-darken-2"><b>Regulamento</b></span></a></li>
                <li><a class="waves-effect" href="/HorasComplementares/login.jsp"><i class="material-icons">launch</i><span class="gray-text text-darken-2"><b>Acessar</b></span></a></li>

            </ul>
        </header>
