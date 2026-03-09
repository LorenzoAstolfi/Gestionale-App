<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="model.Impiegato"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RICERCA IMPIEGATO | GESTIONALE</title>
</head>
<body>
<h1>Ricerca l'impiegato inserendo il cognome</h1>
<form id="impiegatoForm" action="<%=request.getContextPath()%>/ImpiegatoSrv" method="post">
    Cognome:
    <input type="text" id="cognome" name="cognome" ></br><br>
    
    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="ricercaCognome">

    <button type="submit">Ricerca impiegato</button>
</form>
</body>
</html>