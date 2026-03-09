<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Storico"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiorna Storico | Gestionale</title>
</head>
<body>
<h1>Aggiorna lo Storico</h1>

<%
    Storico s = (Storico) request.getSession().getAttribute("storicoTrovato");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dataInizio = (s.getDataInizio() != null) ? sdf.format(s.getDataInizio()) : "";
    String dataFine = (s.getDataFine() != null) ? sdf.format(s.getDataFine()) : "";
%>

<form id="impiegatoForm" action="<%= request.getContextPath() %>/StoricoSrv" method="post">

    ID Storico: <%= s.getIdStorico() %> (Non modificabile!)
    <input type="hidden" name="idStorico" value="<%= s.getIdStorico() %>"><br><br>

    ID Ruolo:
    <input type="text" id="idRuolo" name="idRuolo" value="<%= s.getRuolo().getIdRuolo() %>"><br><br>

    Matricola:
    <input type="text" id="matricola" name="matricola" value="<%= s.getImpiegato().getMatricola() %>"><br><br>

    Data di Inizio:
    <input type="date" id="dataInizio" name="dataInizio" value="<%= dataInizio %>"><br><br>

    Data di Fine:
    <input type="date" id="dataFine" name="dataFine" value="<%= dataFine %>"><br><br>

    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="aggiorna">

    <input type="submit" value="Aggiorna">

</form>
</body>
</html>
