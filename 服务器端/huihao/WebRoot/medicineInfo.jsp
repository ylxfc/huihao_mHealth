<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ page import="java.io.*,wyy.MyConverter,wyy.DB" %>
    <%@page import="java.sql.*"%>
  <%@page import="myutils.*"%>
<%
   String param1=request.getParameter("params1").trim();
   String medName=MyConverter.unescape(param1);
   System.out.println(medName);
  Connection conn=ConnectionUtils.getConnection();
Statement stmt =conn.createStatement();
  String sql="select  *from  medicine where medName='"+medName+"' ";
 ResultSet rs=stmt.executeQuery(sql);
 boolean ok=false;
  while(rs.next()){
	String password = medName;
	password = password + "|" + rs.getString("medClass"); 
	password = password + "|" + rs.getString("medSpecification"); 
	password = password + "|" + rs.getString("medIntroducation");
	password = password + "|" + rs.getString("medStock");
	out.println(MyConverter.escape(password));
	ok=true;}
  rs.close();
  stmt.close();
  conn.close();
     if(!ok)
   {
       out.println(MyConverter.escape("药品名不存在"));
   }

%>