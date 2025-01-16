<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	var el = $("#btn-check");
	el.click(function() {
		var id = $("#id").val();
		if (id == ""){
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath }/api/user/checkid?id=" + id,
			typea: "get",
			dataType: "json",
			success: function(response) {
				if (response.result != "success"){
					console.error(response.message);
					return;
				}
				
				if (response.data.exist){
					alert("해당 아이디가 이미 존재합니다. 다른 이메일을 사용하세요.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				$("#img-check").show();
				$("#btn-check").hide();
			},
			error: function(xhr, status, err){
				console.error(err);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/menu.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="color: red;">
				<form:errors path="name" />
			</p>

			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" />
			<input id="btn-check" type="button" value="id 중복체크" method="get" action="${pageContext.request.contextPath }/user?a=duplication" style="display:;">
			<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="vertical-align:bottom; width:24px; display:none;" />
			<p style="color: red;">
				<form:errors path="id" />
			</p>
			
			<label class="block-label" for="password">패스워드</label>
			<form:password path="password" />
			<p style="color: red;">
				<form:errors path="password" />
			</p>

			
			<fieldset>
				<legend>약관동의</legend>
				<form:checkbox path="agreeProv" id="agree-prov" name="agreeProv"/>
				<label class="l-float">서비스 약관에 동의합니다.</label>
				<p style="color: red;">
      				<form:errors path="agreeProv" />
  				</p>
			</fieldset>

			<input type="submit" value="가입하기">
		</form:form>
	</div>
</body>
</html>
