<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Impiegato"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGGIORNA IMPIEGATO | GESTIONALE</title>
</head>
<body>
<h1>AGGIORNA L'IMPIEGATO</h1>

<% Impiegato imp = (Impiegato)request.getSession().getAttribute("impiegatoTrovato"); %>

<form id="impiegatoForm" action="<%= request.getContextPath() %>/ImpiegatoSrv" method="post">

Nome:
<input type="text" id="nome" name="nome" value="<%= imp.getNome()%>"><br><br>
Cognome:
<input type="text" id="cognome" name="cognome" value="<%= imp.getCognome()%>"><br><br>
Codice Fiscale:
<input type="text" id="cf" name="cf" value="<%= imp.getCodicefiscale()%>"><br><br>

<input type="hidden" name="matr" value="<%= imp.getMatricola() %>">
<input type="submit" value="Aggiorna">

<input type="hidden" id="tipoOperazione" name="tipoOperazione" value="aggiorna">


</form>
</body>
</html>