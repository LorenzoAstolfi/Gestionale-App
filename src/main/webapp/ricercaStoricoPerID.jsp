<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RICERCA UN STORICO | GESTIONALE</title>
</head>
<body>
<body>
 <h2>RICERCA UN NUOVO STORICO</h2>

  <form id="impiegatoForm" action="<%=request.getContextPath()%>/StoricoSrv" method="post">
    ID Storico:
    <input type="text" id="idStorico" name="idStorico" ></br><br>
    
    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="ricercaperid">

    <button type="submit">Ricerca Storico</button>
  </form>
</body>
</html>