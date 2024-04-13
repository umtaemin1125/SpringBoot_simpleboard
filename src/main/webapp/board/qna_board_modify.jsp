<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mysite.springmemberboard.board.*" %>
<%
	if (session.getAttribute("id") == null){
		out.println("<script>");
		out.println("location.href='loginform.me'");
		out.println("</script>");
	}
	
	String id = (String)session.getAttribute("id");	
	BoardVO board = (BoardVO)request.getAttribute("vo");
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
	<script language ="javascript">
		function modifyboard(){
			modifyform.submit();
		}
	</script>
</head>
<body>
<!--  게시판 등록 -->
<form action="./boardmodify.bo" method="post" name="modifyform">
<input type="hidden" name = "num" value="<%=board.getNum() %>">
<input type="hidden" name = "id" value="<%=id %>">
<table cellpadding="0" cellspacing ="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td style="font-family:돋움; font-size:12" height="16">
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="subject" type ="text" size="50" maxlength="100" value="<%=board.getSubject()%>"/>
		</td> 
	</tr>
	<tr>
		<td style="font-family:돋움; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="content" cols="67" rows="15"><%=board.getContent() %></textarea>
		</td> 
	</tr>
	<%if(!(board.getOrg_file()==null)){%>
	<tr>
		<td style="font-family:돋움; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			&nbsp;&nbsp;<%=board.getOrg_file() %>
		</td>
	</tr>
	<%} %>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height: 1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align = "center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
</table>
</form>
<!-- 게시판 수정  -->
</body>
</html>