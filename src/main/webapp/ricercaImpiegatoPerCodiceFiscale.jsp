<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RICERCA UN IMPIEGATO | GESTIONALE</title>
</head>
<body>
<body>
 <h2>RICERCA UN NUOVO IMPIEGATO</h2>

  <form id="impiegatoForm" action="<%=request.getContextPath()%>/ImpiegatoSrv" method="post">
    Codice Fiscale:
    <input type="text" id="cf" name="cf" ></br><br>
    
    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="ricercaCF">

    <button type="submit">Ricerca impiegato</button>
  </form>
</body>
</html>