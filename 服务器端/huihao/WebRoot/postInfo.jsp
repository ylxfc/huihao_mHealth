<%@ page
 contentType="text/html;charset=gbk"
 import="java.io.*,wyy.MyConverter,wyy.DB,java.util.*"
 %>
 <%@page import="java.sql.*"%>
  <%@page import="myutils.ConnectionUtils"%>
<%

   String param1=request.getParameter("params1").trim();
   String param2=request.getParameter("params2").trim();
   String serId=MyConverter.unescape(param1);
   String id1=MyConverter.unescape(param2);
   int id2=Integer.parseInt(id1);
   String a = "";
   Connection conn=ConnectionUtils.getConnection();
Statement stmt =conn.createStatement();
  String sql="select  *from  postinfo where serId='"+serId+"' or serId='0'";
 ResultSet rs=stmt.executeQuery(sql);
   while(rs.next()){
   if(Integer.parseInt(rs.getString("id")) > id2){
   a=a+rs.getString("text")+"|";
   
    a=a+rs.getString("date")+"|";}}
     conn.close();
    int m = a.length();
    a = a.substring(0, m-1);
    out.println(MyConverter.escape(a));
  
%>
