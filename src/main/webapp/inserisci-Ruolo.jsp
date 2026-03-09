<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERIRE RUOLO | GESTIONALE</title>
</head>
<body>
 <h2>AGGIUNGI UN NUOVO RUOLO</h2>

  <form id="impiegatoForm" action="<%=request.getContextPath()%>/RuoloSrv" method="post">
    Descrizione:
    <input type="text" id="descrizione" name="descrizione" ><br><br>
    
    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="inserisci"><br>

    <button type="submit">Aggiungi ruolo</button>
  </form>
</body>
</html>