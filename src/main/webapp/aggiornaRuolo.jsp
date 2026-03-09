<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Ruolo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AGGIORNA RUOLO | GESTIONALE</title>
</head>
<body>
<h1>AGGIORNA IL RUOLO</h1>

<% Ruolo r = (Ruolo)request.getSession().getAttribute("ruoloTrovato"); %>

<form id="impiegatoForm" action="<%= request.getContextPath() %>/RuoloSrv" method="post">

ID Ruolo: <%= r.getIdRuolo()%> (Non modificabile!)
<input type="hidden" name="id" value="<%= r.getIdRuolo()%>"><br><br>
Descrizione:
<input type="text" id="descrizione" name="descrizione" value="<%= r.getDescrizione()%>"><br><br>

<input type="submit" value="Aggiorna">

<input type="hidden" id="tipoOperazione" name="tipoOperazione" value="aggiorna">


</form>
</body>
</html>