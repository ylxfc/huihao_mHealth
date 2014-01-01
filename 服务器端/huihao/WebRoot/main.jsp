<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/main.css" rel="stylesheet">
<title>慧好制药业务管理系统</title>
</head>

<frameset rows="110*,368*" cols="*" framespacing="0" frameborder="NO" border="0">
  <frame src="title.jsp" name="topFrame" scrolling="NO" noresize>
  <frameset rows="*" cols="272,*" frameborder="NO" border="0" framespacing="0">
		<frame src="left.jsp" name="leftFrame" scrolling="NO" noresize>
		<frameset rows="34*,308*" cols="*" framespacing="0" frameborder="NO" border="0">
			<frame src="DocMain.jsp" name="topMenuFrame" scrolling="NO" noresize>
			<frame src="Content.jsp" name="mainFrame">
		</frameset>
  </frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>

