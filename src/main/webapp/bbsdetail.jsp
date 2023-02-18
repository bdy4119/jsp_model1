<%@page import="dao.Bbsdao"%>
<%@page import="dto.bbsDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		int seq = Integer.parseInt(request.getParameter("seq"));
		
	%>
   <%
	MemberDto login = (MemberDto)session.getAttribute("login");
	if(login == null){
	%>
	<script>
		alert('로그인 해 주십시오');
		location.href = "login.jsp";
	</script>
	<%
	}
	Bbsdao dao = Bbsdao.getInstance();
	
	dao.readcount(seq);	//카운트증가
	bbsDTO dto = dao.getBbs(seq);
	%>


<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<!-- 작성자 제목 작성일 조회수 정보 내용
		답글버튼, 삭제 버튼, 수정 버튼 -> 삭제랑 수정은 작성자 본인만 할 수 있게
	-->
		<h1>상세 글보기</h1>
		<div align="center">
			<form>
				<table border="1">
					<col width="200">
					<col width="400">
					<tr>
						<th>작성자</th>
						<td>
							<%=dto.getId() %>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>
							<%=dto.getTitle() %>
						</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>
							<%=dto.getWdate() %>
						</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>
							<%=dto.getReadcount() %>
						</td>
					</tr>
					<tr>
						<th>답글정보</th>
						<td>
							<%=dto.getRef()%>-<%=dto.getStep()%>-<%=dto.getDepth()%>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="20" cols="50px" name="content" readonly="readonly"><%=dto.getContent() %></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" onclick="answerBbs(<%=dto.getSeq() %>)">답글</button>
							<button type="button" onclick="location.href='bbsList.jsp'">글목록</button>
							
							<%
							if(dto.getId().equals(login.getId())) {
								
							%>
							<button type="button" onclick="updateBbs(<%=dto.getSeq() %>)">수정</button>
							<button type="button" onclick="deleteBbs(<%=dto.getSeq() %>)">삭제</button>
							<%
							}
							%>
							
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<script type="text/javascript">
			function answerBbs(seq) {
				//seq를 넘겨주고 있으므로 seq값 가지고 넘어가기
				location.href = "answer.jsp?seq=" + seq;
			}
			
			function updateBbs(seq) {
				location.href = "bbsupdate.jsp?seq=" + seq;
			}
			
			//진짜 지우는 게 아니라 업데이트로 지우기
			//del변수를 1로 바꾸고 보이지 않게 바꾸기
			function deleteBbs(seq) {
				location.href = "bbsdeleteAf.jsp?seq=" + seq;
			}
		</script>
	</body>
</html>