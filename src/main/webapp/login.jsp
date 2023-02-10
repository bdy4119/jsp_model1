<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<link rel="stylesheet" href="NewFile.css">
		<script src="https://kit.fontawesome.com/51db22a717.js" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
		<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
	</head>
	<body>
		<div class="main-container">
			<form action="LoginAf.jsp" method="post">
				<div>
				<header>
					<div align="center">
						<h1>로그인</h1>
					</div>
				</header>
				<section class="login-input-section-wrap">
					<!-- 아이디 -->
					<div class="login-input-wrap">	
						<input placeholder="Username" type="text" id="id" name="id">
					</div>
					
					<!-- 비밀번호 -->
					<div class="login-input-wrap password-wrap">	
						<input placeholder="Password" type="password" name="pwd"></input>
					</div>
					<div class="login-button-wrap">
						<button type="submit" value="log-in">Sign in</button>
					</div>
				</section>
				
				<br>
				<input type="checkbox" id="chk_save_id">id저장
				<br>
				<br>
				<a href="regi.jsp">회원가입</a>
				</div>
			</form>
		</div>
		
		<script type="text/javascript">
			/*
				cookie : id, pw저장 == String형태						client에 저장됨
				session : 로그인한 회원에 대한 정보를 저장 == Object형태	server에 저장
			*/
			
			let user_id = $.cookie('user_id');
		
			//저장한 아이디가 있을때 실행
			if(user_id != null) {
				$("#id").val(user_id);
				$("#chk_save_id").prop("checked", true);
			}
			
			$("#chk_save_id").click(function () {
				
				if( $("#chk_save_id").is(":checked") == true ){
					
					//아이디칸이 비어있으면 실행
					if( $("#id").val().trim() == "" ){
						alert('id를 입력해 주십시오');
						$("#chk_save_id").prop("checked", false);
					}else{
						// cookie를 저장
						$.cookie("user_id", $("#id").val().trim(), { expires:7, path:'./' });
					}
					
				}else{
					$.removeCookie("user_id", { path:'./' });
				}	
			});
		</script>
	</body>
</html>