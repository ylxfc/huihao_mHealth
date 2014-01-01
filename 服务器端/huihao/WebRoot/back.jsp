<%@ page
 contentType="text/html;charset=gbk"
 import="java.io.*,wyy.MyConverter,wyy.DB,java.util.*"
 %>
 <%@page import="java.sql.*"%>
  <%@page import="myutils.ConnectionUtils"%>
<%

   String param1=request.getParameter("params1").trim();
   String text=MyConverter.unescape(param1);
   Connection conn=ConnectionUtils.getConnection();
Statement stmt =conn.createStatement();
String sql1="insert into feedback(text)values(?)";    
PreparedStatement ps= conn.prepareStatement(sql1);
ps.setString(1,text);
 ps.executeUpdate();
 conn.close();
 out.println(MyConverter.escape("1"));
  
%>