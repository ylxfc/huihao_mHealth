<%@ page
 contentType="text/html;charset=gbk"
 import="java.io.*,wyy.MyConverter,wyy.DB,java.util.*"
 %>
 <%@page import="java.sql.*"%>
  <%@page import="myutils.UserUtils"%>
<%

   String param3=request.getParameter("params3").trim();
   String param4=request.getParameter("params4").trim();
   String serId=MyConverter.unescape(param3);
   String password=MyConverter.unescape(param4);
   boolean ok=UserUtils.updatePassword(serId, password);
   if(ok){
    out.println(MyConverter.escape("1"));}
  
%>
 