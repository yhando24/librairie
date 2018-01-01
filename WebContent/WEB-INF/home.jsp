
<!-- jsp principale contenant tout les autres en fonction de l'actionname -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/ressources/css/style.css'/>" />
<title>${title}</title>
</head>
<body>



	<c:import url="fragments/header.jsp"></c:import>



	<!-- import en cas de connection ou de creation de compte -->
	<c:if test="${actionname == 'login'}">
		<c:import url="fragments/login.jsp"></c:import>
	</c:if>

	<c:if test="${actionname =='signin'}">
		<c:import url="fragments/signin.jsp"></c:import>
	</c:if>




	<c:choose>


		<c:when test="${actionname != 'home'  &&  !empty user}">
			<c:import url="fragments/books.jsp"></c:import>
		</c:when>
		<c:when test="${actionname == 'books'  && empty user}">
			<h1>Connection demandée</h1>

			<c:import url="fragments/login.jsp"></c:import>
		</c:when>
	</c:choose>



	<c:if test="${actionname =='addauthor'}">
		<c:import url="fragments/addauthor.jsp"></c:import>
	</c:if>

	<c:if test="${actionname =='home'}">
		<div class="row">
			<div class="col-lg-9 description">
				<p>
				<h2>BIBLIOTHÈQUE PAUL LANGEVIN</h2>
				La bibliothèque Paul Langevin est située dans les bâtiments de la
				Maison Pour Tous Albert Dubout. Paul Langevin.jpg Que peut-on y
				trouver? Adultes Romans, bandes dessinées, livres documentaires,
				livres sur Montpellier et le Languedoc, revues.. dictionnaires et
				encyclopédies en consultation. Enfants Romans, contes, albums,
				bandes dessinées, livres pour tout petits, livres-cassettes, livres
				documentaires... Activités Aide à la recherche documentaire,
				conseils de lecture, animations avec les classes en accord avec les
				enseignants.

				</p>
			</div>



			<div class="col-lg-3 acces">
				<h2>ACCÈS</h2>


				Adresse: Quartier Hôpitaux Facultés 1071, avenue de la Justice de
				Castelnau 34090 Montpellier Téléphone : 04 67 72 58 76 Accès: Tram
				ligne 2, arrêt Aiguelongue Localisation et itinéraire <br> <br>
				<br>
				<p>
				<h2>HORAIRES</h2>
				Mardi :15h-18h30 Mercredi :10 h-12 h30 et 15 h-18 h 30 Jeudi :/
				Vendredi :15h-18h30 Samedi: 10h-12h30 Les inscriptions sont closes
				15 min avant la fermeture.
				</p>

			</div>

		</div>
	</c:if>






</body>