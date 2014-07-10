<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 
<form action="write" method="POST" >
 -->
 <form action="write" method="POST" onsubmit="return submit_valid();" >
<input name='h_name' id='h_name' type="text" />
<input type="submit" value='저장'/>
</form>
<%----%> 
<br>
<c:out value="${list.size()}"></c:out>
 
<c:if test="${list.size() >0 }">
<ul class='human_list'>
	<c:forEach var="index" begin="0" end="${list.size()-1}" step="1">
	<li><input type="hidden" value='${index}'>${list[index].getName()}</li>
	</c:forEach>
</ul>
</c:if>


<script>
window.onload = function(){
	document.getElementById("h_name").focus();
}

submit_valid = function(){
	
	if( document.getElementById("h_name").value.length >0 ){
		return true;
	}else{
		return false;
	}
	
}
</script>

<script>
/**
 * index 전송하여 데이터 삭제
 */
var ftn_human_delete = function(){
	
	$.ajax({
			url : "delete",
			type : "POST",
			data : {
				index : $(this).children("input").val()
			},
			success : function(data) {
				location.reload( );
			}
		});

	}
	$("ul.human_list li").bind("click", ftn_human_delete);
	
</script>


</body>
</html>
