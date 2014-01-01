<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">

<%@ page import="myutils.StringUtils" %>
<%
	String id = StringUtils.StringEcode(request.getParameter("id"));
%>
</head>
<body background="images/bg.png">
<table cellpadding=0 cellspacing=0  width="100%" align="center">
	<tr>		
		    <table width="90%" border="0" cellspacing="0" cellpadding="0"  align="center">
              <tr>
                <td align="center"><h3>&nbsp;</h3>
                  <h3>&nbsp;</h3>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                 <h3><a href="deleteUser.do?id=<%=id %>" target="mainFrame">确认删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="manage_isManage.jsp" target="mainFrame">返回</a></h3>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p>
                  <p>&nbsp;</p></td>
                <p></p>
              </tr>
            </table></td>
	</tr>
</table>
</body>
</html>
