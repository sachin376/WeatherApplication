<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>WeatherReport</title>
<link rel="stylesheet" type="text/css"
	href="/WeatherApplication/resources/style.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="/WeatherApplication/resources/weatherApplicationJavaScript.js"></script>
</head>

<body>
	<h1>Weather Report</h1>
	<form:form action="getWeather.htm" method="GET"
		commandName="weatherModel">
		<form:label path="zipCode" id="label">Zip Code :</form:label>
		<form:input path="zipCode" maxlength="5" />
		<form:errors path="zipCode" cssClass="error" />
		<!-- This will get executed only when user bypass the client side validation -->
		<br>
		<input id="submitId" type="submit" />
	</form:form>

	<div id=errorMsg>
		<h2 class="error">Invalid zip code format : Zipcode can only be
			exact 5 numeric digit</h2>
	</div>
	<div id="exception">
		<h2 class="error">${errMsg}</h2>
	</div>
	<div id=info>
		<c:if test="${showResultInfo}">
			<c:if test="${weatherModel!=null}">
				<h2>Zip Code : ${weatherModel.zipCode}</h2>
				<h2>City : ${weatherModel.city}</h2>
				<h2>State : ${weatherModel.state}</h2>
				<h2>Temperature (f) : ${weatherModel.temp_f}</h2>
			</c:if>
		</c:if>
	</div>
</body>
</html>