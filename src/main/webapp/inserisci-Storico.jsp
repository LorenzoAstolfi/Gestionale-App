<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERIRE STORICO | GESTIONALE</title>
</head>
<body>
 <h2>AGGIUNGI UN NUOVO STORICO</h2>

  <form id="impiegatoForm" action="<%=request.getContextPath()%>/StoricoSrv" method="post">
    ID Ruolo:
    <input type="text" id="idRuolo" name="idRuolo" ><br><br>
    Matricola:
    <input type="text" id="matricola" name="matricola" ></br><br>
    Data di Inizio:
    <input type="date" id="dataInizio" name="dataInizio" ><br><br>
    Data di Fine:
    <input type="date" id="dataFine" name="dataFine" ><br><br>
    
    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="inserisci"><br>

    <button type="submit">Aggiungi Storico</button>
  </form>
</body>
</html>