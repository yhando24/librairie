<section id="find">
<!-- QUESTION -->

	<!-- CHERCHER PAR ID-->

	
	<article>
	<c:if test="${ user.role=='admin' }">
		<form method="post"
			action="<c:url value='/findbyid?bookid=${bookbyid.id }'/>"
			id="form-findid">
			<input type="text" name="book-by-id" form="form-findid"
				placeholder="Chercher par id" /> <input type="submit"
				value="Rechercher">
		</form>
	</c:if>
	
	<!-- CHERCHER PAR TITRE -->
	
	
		<form method="post"
			action="<c:url value='/findbytitle?booktitle=${bookbytitle.title }'/>"
			id="form-findtitle">
			<input type="text" name="book-by-title" form="form-findtitle"
				placeholder="Chercher par titre" /> <input type="submit"
				value="Rechercher" form="form-findtitle" />
		</form>


	<!-- CHERCHER PAR AUTEUR -->
		<form method="post"
			action="<c:url value='/findbyauthor?bookauthor=${bookby.author.lastname }'/>"
			id="form-findauthor">
			<input type="text" name="book-by-author"
				placeholder="Chercher par auteur" form="form-findauthor" /> <input
				type="submit" value="Rechercher" form="form-findauthor" />
		</form>
	</article>

	
	
	
	
	<!-- REPONSE -->



	<!-- PAR ID -->

	<c:if test="${!empty bookbyid && actionname == 'findbyid' }">


		<table>
			<th>Titre</th>
			<th>Prix</th>
			<th>Disponibilite</th>
			<th>Resume</th>
			<th>Action</th>
			<tr>
				<td>${bookbyid.title}</td>
				<td>${bookbyid.price}</td>
				<td>${bookbyid.available}</td>
				<td>${bookbyid.overview}</td>
				<c:if test="${ user.role=='admin' }">
					<td><a href="<c:url value='/books/edit?id=${bookbyid.id }'/>">&#10003;
					</a> <a href="<c:url value='/books/delete?id=${bookbyid.id }'/>">&#128686;
					</a> <a href="<c:url value='/books/addauthor?id=${bookbyid.id }'/>">Rajouter
							auteur</a></td>
					</c:if>
			</tr>


		</table>
	</c:if>

	<c:if test="${empty bookbyid && actionname=='findbyid'}">
		<h2>Aucun livre trouvé</h2>
	</c:if>







	<!-- PAR TITRE -->



	<c:if test="${!empty booksbytitle && actionname=='findbytitle'}">

		<table>
			<tr>

				<th>Titre</th>
				<th>Prix</th>
				<th>Disponibilite</th>
				<th>Resume</th>
				<th>Action</th>
			</tr>

			<c:forEach items="${booksbytitle}" var="bookbytitle" varStatus="last">

				<c:if test="${last.last }">${last.count } livre(s) trouvé(s)</c:if>

				<tr>
					<td>${bookbytitle.title}</td>
					<td>${bookbytitle.price}</td>
					<td>${bookbytitle.available}</td>
					<td>${bookbytitle.overview}</td>
					<c:if test="${ user.role=='admin' }">
					<td><a
						href="<c:url value='/books/edit?id=${bookbytitle.id }'/>">&#10003;
					</a> <a href="<c:url value='/books/delete?id=${bookbytitle.id }'/>">&#128686;
					</a> <a href="<c:url value='/books/addauthor?id=${bookbytitle.id }'/>">Rajouter
							auteur</a></td>
					</c:if>
				</tr>

			</c:forEach>
		</table>
	</c:if>

	<c:if test="${empty booksbytitle && actionname=='findbytitle'}">
		<h2>Aucun livre trouvé</h2>
	</c:if>







	<!-- PAR AUTEUR -->

<!-- 	
AFFICHAGE DES AUTEUR ET DES LIVRE PAR AUTEUR -->

	<c:if test="${!empty booksbyauthor && actionname=='findbyauthor'}">


		<c:forEach items="${booksbyauthor}" var="author"
			varStatus="countauthor">

			<c:if test="${countauthor.first }">   ${fn:length(booksbyauthor)} auteur(s) trouvé(s) </c:if>
			<h2>Auteur : ${author.lastname } ${author.firstname }</h2>



			<c:if test="${ !empty author.books}">
				<table>

					<tr>
						<th>Titre</th>
						<th>Prix</th>
						<th>Disponibilite</th>
						<th>Resume</th>
						<th>Action</th>
					</tr>

					<c:forEach items="${author.books}" var="bookauthor"
						varStatus="countbook">
						<c:if test="${countbook.first }">${fn:length(author.books)} livre(s) trouvé(s)</c:if>
						<tr>
							<td>${bookauthor.title}</td>
							<td>${bookauthor.price}</td>
							<td>${bookauthor.available}</td>
							<td>${bookauthor.overview}</td>
							<c:if test="${ user.role=='admin' }">
							<td><a
								href="<c:url value='/books/edit?id=${bookauthor.id }'/>">&#10003;
							</a> <a href="<c:url value='/books/delete?id=${bookauthor.id }'/>">&#128686;
							</a> <a href="<c:url value='/books/addauthor?id=${bookauthor.id }'/>">Rajouter
									auteur</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</c:if>

			<c:if test="${ empty author.books}">
				<h2>Desolé, cette auteur n'a ecrit aucun livre.</h2>
			</c:if>
		</c:forEach>


	</c:if>
</section>