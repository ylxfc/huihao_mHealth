<%@ page
 contentType="text/html;charset=gbk"
 import="java.io.*,wyy.MyConverter,wyy.DB,java.util.*"
 %>
  <%@page import="java.sql.*"%>
  <%@page import="myutils.ConnectionUtils"%>
<%
   String param1=request.getParameter("params1").trim();
      //System.out.println(",用户数据已经传回客户端！");
   String serId=MyConverter.unescape(param1);
   Connection conn=ConnectionUtils.getConnection();
Statement stmt =conn.createStatement();
  String sql="select  *from  servicer where serId='"+serId+"' ";
 ResultSet rs=stmt.executeQuery(sql);
  while(rs.next()){
	String password ="|"+rs.getString("serName"); 
		password = password + "|" + rs.getString("serSex"); 
		password = password + "|" + rs.getString("serAge"); 
		password = password + "|" + rs.getString("serPhone"); 
		password = password + "|" + rs.getString("medId");
		password = password + "|" + rs.getString("havSignedAgency");
		out.println(MyConverter.escape(password));}
 rs.close();
  stmt.close();
  conn.close();
%>
