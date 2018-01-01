



<header>
	<!-- le '/' reprensente le depart de webcontent -->
	<img src="<c:url value='/ressources/images/logo.png'/>" alt="le logo">


	<nav>
		<a href="<c:url value='home'/>"><input type="button"
			value="Acceuil" /></a>
		<c:if test="${!empty user }">
			<a href="<c:url value='/books'/>"><input type="button"
				value="Livres" /></a>
		</c:if>



		<c:choose>
			<c:when test="${!empty user }">
				<p class="role">
					Bonjour ${user.firstname}
					<c:if test="${user.role == 'admin' }">
						<br>  STATUT : Administrateur </c:if>
				</p>
				<a href="<c:url value='/logout'/>"><input type="button"
					value="Deconnection" /></a>

			</c:when>

			<c:otherwise>
				<a href="<c:url value='/login'/>"><input type="button"
					value="Connection" /></a>
			</c:otherwise>
		</c:choose>
	</nav>
</header>
