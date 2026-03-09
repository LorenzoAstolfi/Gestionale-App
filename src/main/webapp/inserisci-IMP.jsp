<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>INSERIRE IMPIEGATO | GESTIONALE</title>
</head>
<body>
 <h2>AGGIUNGI UN NUOVO IMPIEGATO</h2>

  <form id="impiegatoForm" action="<%=request.getContextPath()%>/ImpiegatoSrv" method="post">
    Nome:
    <input type="text" id="nome" name="nome" ></br><br>

    Cognome:
    <input type="text" id="cognome" name="cognome" ></br><br>

    Codice Fiscale:
    <input type="text" id="cf" name="cf" ><br>
	</br>
    <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="inserisci"><br>

    <button type="submit">Aggiungi impiegato</button>
  </form>
</body>
</html>