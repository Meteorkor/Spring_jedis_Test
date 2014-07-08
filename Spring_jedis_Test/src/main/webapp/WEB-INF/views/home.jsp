<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<%
request.setCharacterEncoding("UTF-8");
%>

<form action="write" method="POST" >

<input name='h_name' id='h_name' type="text" />
<input type="submit" value='저장'/>
</form>
<%----%> 
<br>
<c:out value="${list.size()}"></c:out>
 
<c:if test="${list.size() >0 }">
<ul>
	<c:forEach var="index" begin="0" end="${list.size()-1}" step="1">
	<li>${list[index].getName()}</li>
	</c:forEach>
</ul>
</c:if>


<script>
window.onload = function(){
	//alert( document.h_name.name );
	
	document.getElementById("h_name").focus();
	
	/* 
	var form = document.h_name;
	form.focus();
	 */
}

</script>


</body>
</html>
