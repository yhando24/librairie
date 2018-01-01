<jsp:include page="find.jsp">
	<jsp:param value="${actionname}" name="actionname" />
</jsp:include>

<c:if test="${actionname == 'books'}">
	<h1>Liste complete des livres</h1>

	<form method="get" action="<c:url value='/books' />">
		trier par : <select name="tri">
			<option value="firstname">prenom</option>
			<option value="lastname">nom</option>
			<option value="title">titre</option>
			<option value="price">Prix</option>
		</select> <input type="submit" value="go" />

	</form>


	<form method="POST" action="<c:url value='/books/add'/>"
		id="form-addbook"></form>
	<form method="post" action="<c:url value='/books/edit?id=${bookid }'/>"
		id="form-editbook"></form>

	<%-- ${book.title} --%>

	<table>
		<tr>
			<c:if test="${ user.role=='admin' }">
				<th class="class-id-livre">Id</th>
			</c:if>
			<th class="class-nom-auteur">Nom Auteur</th>
			<th class="class-penom-auteur">Prenom Auteur</th>
			<th class="class-pays-auteur">Pays d'origine Auteur</th>
			<th class="class-titre-livre">Titre Livre</th>
			<th class="class-prix-livre">Prix Livre</th>
			<th class="class-dispo-livre">Disponibilite</th>
			<th class="class-resume-livre">Resume</th>
			<c:if test="${ user.role=='admin' }"><th>Action</th></c:if>

		</tr>
		<tr>
		<c:if test="${ user.role=='admin' }">	<td></td> 
			<td><input type="text" name="author-firstname"
				form="form-addbook" /></td>
			<td><input type="text" name="author-lastname"
				form="form-addbook" /></td>
			<td><input type="text" name="author-country" form="form-addbook" /></td>
			<td><input type="text" name="book-title" form="form-addbook" /></td>
			<td><input type="text" name="book-price" form="form-addbook" /></td>
			<td><input type="checkbox" name="book-avaibility"
				form="form-addbook" /></td>
			<td><input type="text" name="book-overview" form="form-addbook" /></td>
			<td><input type="submit" value=" ajouter livre"
				form="form-addbook" /></td>

		</tr>
		</c:if>
		</c:if>
		<c:forEach items="${books}" var="book">
			<c:choose>
				<c:when test="${bookid == book.id}">
					<tr>
						<td></td>
						<td><input type="text" value="${book.authors[0].firstname}"
							name="author-firstname" form="form-editbook" /></td>
						<td><input type="text" value="${book.authors[0].lastname}"
							name="author-lastname" form="form-editbook" /></td>
						<td>country</td>

						<td><input type="text" value="${book.title}"
							name="book-title" form="form-editbook" /></td>
						<td><input type="text" value="${book.price} €"
							name="book-price" form="form-editbook" /></td>
						<td>avaibility</td>
						<td><input type="text" value="${book.overview}"
							name="book-overview" form="form-editbook" /></td>
						<td><input type="submit" value="go" form="form-editbook" /></td>
					</tr>


				</c:when>
				<c:otherwise>
					<tr>
						<c:if test="${ user.role=='admin' }">
							<td class="class-id-livre">${book.id}</td>
						</c:if>
						<td><c:forEach items="${book.authors}" var="author">

								<p>${author.lastname}</p>
							</c:forEach></td>

						<td class="class-penom-auteur"><c:forEach
								items="${book.authors}" var="author">
								<p>${author.firstname}</p>
							</c:forEach></td>
						<td><c:forEach items="${book.authors}" var="author">
								<p>${author.nativeCountry}</p>
							</c:forEach></td>

						<td class="class-titre-livre">${book.title}</td>
						<td>${book.price}</td>
						<td class="class-dispo-livre">${book.available}</td>
						<td>${book.overview}</td>
						<c:if test="${ user.role=='admin' }">
						<td><a href="<c:url value='/books/edit?id=${book.id }'/>">&#10003;
						</a> <a href="<c:url value='/books/delete?id=${book.id }'/>">&#128686;
						</a> <a href="<c:url value='/books/addauthor?id=${book.id }'/>">Rajouter
								auteur</a></td>
						</c:if>



					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>








	</table>