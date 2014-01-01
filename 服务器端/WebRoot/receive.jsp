<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ page import="java.io.*,wyy.MyConverter,wyy.DB" %>
<% 
   String param1=request.getParameter("params1").trim();
   String param2=request.getParameter("params2").trim();
   
   String serId=MyConverter.unescape(param1);
   String serPassword=MyConverter.unescape(param2);
   
   String sqla="select serPassword from servicer where serId='"+serId+"'";
   if(DB.isExist(sqla)){
				String sql = "select serPassword from servicer where serId='"+serId+"'";
				String password=DB.getInfo(sql).trim();//从数据库得到密码
				if(serPassword.equals(password)){
				   out.println(MyConverter.escape("登录成功"));	
				}
				else{
				   out.println(MyConverter.escape("登录失败"));	 
				}
	}
   else
   {
       out.println(MyConverter.escape("用户不存在，请重新输入"));
   }

%>

    