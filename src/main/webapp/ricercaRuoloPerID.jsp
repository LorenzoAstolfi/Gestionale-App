<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RICERCA UN RUOLO | GESTIONALE</title>
</head>
<body>
<body>
 <h2>RICERCA UN NUOVO RUOLO</h2>

  <form id="impiegatoForm" action="<%=request.getContextPath()%>/RuoloSrv" method="post">
    ID Ruolo:
    <input type="text" id="id" name="id" ></br><br>
    
    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="ricercaperid">

    <button type="submit">Ricerca Ruolo</button>
  </form>
</body>
</html>