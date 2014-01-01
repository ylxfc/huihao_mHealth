<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ page import="java.io.*,wyy.MyConverter,wyy.DB" %>
    <%@page import="java.sql.*"%>
  <%@page import="myutils.*"%>
  <%String param1=request.getParameter("params1").trim();
   String medName=MyConverter.unescape(param1);
   System.out.println(medName);
 Connection conn=ConnectionUtils.getConnection();
Statement stmt =conn.createStatement();
String password="";
  String sql="select  *from discount";
 ResultSet rs=stmt.executeQuery(sql);
  while(rs.next()){
   password +="|"+ rs.getString("discount");} 
    rs.close();
  stmt.close();
  conn.close();
   if(medName!=null)
{
Connection conn1=ConnectionUtils.getConnection();
Statement stmt1 =conn1.createStatement();
  String sql1="select  *from  medicine where medName='"+medName+"'";
 ResultSet rs1=stmt1.executeQuery(sql1);
   while(rs1.next()){
   password +="|"+ rs1.getString("medDiscount");}
    rs1.close();
  stmt1.close();
  conn1.close();}
  out.println(MyConverter.escape(password));
  %>