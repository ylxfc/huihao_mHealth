<%@ page
 contentType="text/html;charset=gbk"
 import="java.io.*,wyy.MyConverter,wyy.DB,java.util.*"
 %>
  <%@page import="myutils.UserUtils"%>
   <% 
   String param3=request.getParameter("params1").trim();
   String param4=request.getParameter("params2").trim();
   String serId=MyConverter.unescape(param3);
   String password=MyConverter.unescape(param4);
   boolean ok=UserUtils.updatePassword(serId, password);
   if(ok){
    out.println(MyConverter.escape("1"));}%>