<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${bookidforA == bookforA.id }"> 
	  titre livre : ${bookforA.title}
	  <br>
	  		Auteur(s) :		<c:forEach items="${bookforA.authors}" var="author">
		<c:out value="${author.firstname } ${author.lastname}"></c:out>
		<br>
	</c:forEach> 
	  	Prix :${bookforA.price }
	  	<br>
	  	resume : ${bookforA.overview} 
	  	<br>
	  	disponibilite : <c:out
		value="${bookforA.available == 'true' ? 'en stock' : 'indisponible'}" />
	<br>


	<form method="post"
		action="<c:url value='/books/addauthor?id=${bookidforA }'/>"
		id="form-addauthor">
		<input type="text" name="author-firstname" form="form-addauthor" /> <input
			type="text" name="author-lastname" form="form-addauthor" /> <input
			type="text" name="author-country" form="form-addauthor" /> <input
			type="submit" value="Ajouter auteur" form="form-addauthor">
	</form>

</c:if>