<%@page import="dto.bbsDTO"%>
<%@page import="dao.Bbsdao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		request.setCharacterEncoding("utf-8");

		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Bbsdao dao = Bbsdao.getInstance();	//싱글톤 받아오기
		
		boolean isS = dao.updateBbs(seq, title, content);
		if(isS) {
		%>
		<script type="text/javascript">
			alert("수정이 완료되었습니다.");
			location.href = "bbsList.jsp";
		</script>
		<%
		} else {
		%>
		<script type="text/javascript">
			alert("수정에 실패하였습니다.");
			let seq = "<%=seq %>"; /* 자바스크립트에서 자바얻어오기 */
			location.href = "bbsupdate.jsp?seq=" + seq;
		</script>
		<%
			}
		%>