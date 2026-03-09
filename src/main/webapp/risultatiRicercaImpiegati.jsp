<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RISULTATI RICERCA | GESTIONALE</title>
</head>
<body>
<table border="1px">
   
   <tr>
       <th>Matricola</th>
       <th>Nome</th>
       <th>Cognome</th>
       <th>Codice Fiscale</th>
   </tr>
   
   <c:forEach var="r" items="${impiegatiTrovati}">
       <tr>
           <td>${r.matricola}</td>
           <td>${r.nome}</td>
           <td>${r.cognome}</td>
           <td>${r.codicefiscale}</td>
           <td>
               <form action="<%=request.getContextPath()%>/ImpiegatoSrv">
               
               <input type="hidden" id="cf" name="cf" value="${r.codicefiscale}">
               <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="ricercaCF">
               <input type="submit" value="Aggiorna">               
               
               </form>
           </td>
           <td>
               <form action="<%=request.getContextPath()%>/ImpiegatoSrv">
               
               <input type="hidden" id="cf" name="cf" value="${r.codicefiscale}">
               <input type="hidden" id="tipoOperazione" name="tipoOperazione" value="elimina">
               <input type="hidden" id="cognome" name="cognome" value="${r.cognome}">
               <input type="hidden" id="matricola" name="matricola" value="${r.matricola}">
               <input type="submit" value="Elimina">               
               
               </form>
           </td>
       </tr>
   </c:forEach> 

</table>
</body>
</html>