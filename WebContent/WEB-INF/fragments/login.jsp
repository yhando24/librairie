
<div class="row">
	<div class="col-lg-9 description">

		<form method="post" action="<c:url value='/login' />">
			<input type="text" name="pseudo" placeholder="Pseudo" /> <input
				type="password" name="password" placeholder="password" /><br>
			<input type="submit" value="Connection" />

		</form>
		<br> <br>
		<!-- si le parametres de connection sont faux -->
		<c:if test="${!empty usererror }">${usererror }</c:if>
	</div>

	<div class="col-lg-3 acces">
		<h2>Nouveau menbres</h2>
		<a href="<c:url value='/signin'/>">Inscription</a>

	</div>
</div>