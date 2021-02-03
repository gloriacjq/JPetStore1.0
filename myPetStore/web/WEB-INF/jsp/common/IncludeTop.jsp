<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/autostyle.css" type="text/css" media="screen" />

<%--    <meta charset="utf-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>


	<meta name="generator" content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
	<title>JPetStore Demo</title>
	<meta content="text/html; charset=windows-1252" http-equiv="Content-Type" />
	<meta http-equiv="Cache-Control" content="max-age=0" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="Pragma" content="no-cache" />

	<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="js/autoComplete.js"></script>

</head>

<body>
<div id = "page">
	<div id="Header">

		<div id="Logo">
			<div id="LogoContent">
				<a href="main"><img src="images/logo-topbar.gif" /></a>
			</div>
		</div>

		<div id="Menu">
			<div id="MenuContent">
				<a href="viewCart">Cart</a>
<%--				<a href="viewCart"><img align="middle" name="img_cart" src="images/cart.gif" /></a>--%>
<%--				<img align="middle" src="images/separator.gif" />--%>
				<a href="signInForm">Sign In</a>
<%--				<img align="middle" src="images/separator.gif" />--%>
				<a href="signOut">Sign Out</a>
<%--				<img align="middle" src="images/separator.gif" />--%>
				<a href="editAccountForm">Account</a>
<%--				<img align="middle" src="images/separator.gif" />--%>
				<a href="../../../help.html" target="_blank">Help</a>
			</div>
		</div>

		<div id="Search">
			<div id="searchProduct">
				<form action="searchProduct" method="post" onsubmit="return checkSearch()">
                    <div class="ui-widget">
<%--                        <label for="searchKeyword">Search: </label>--%>
                        <input id="searchKeyword" name = "keyword" size="14">
                        <input type="submit" name="searchProducts" value="Search" />
                        <script type="text/javascript" src="js/checkSearch.js"></script>
                    </div>
<%--                    <input type="text" name="keyword" size="14" />--%>
				</form>
			</div>
		</div>

		<div id="QuickLinks">
			<a href="viewCategory?categoryId=FISH"><img src="images/sm_fish.gif" /></a>
			<img src="images/separator.gif" />
			<a href="viewCategory?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
			<img src="images/separator.gif" />
			<a href="viewCategory?categoryId=REPTILES"><img	src="images/sm_reptiles.gif" /></a>
			<img src="images/separator.gif" />
			<a href="viewCategory?categoryId=CATS"><img src="images/sm_cats.gif" /></a>
			<img src="images/separator.gif" />
			<a href="viewCategory?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
		</div>

	</div>

	<div id="Content">