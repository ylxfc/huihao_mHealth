<%@ page language="java" import="java.util.*"  contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="myutils.ConnectionUtils" %>
 <%@page import="java.sql.*"%>
 <%@page import="mybean.UserLogin"%>
 <%@page import="myutils.StringUtils"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>慧好制药业务管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body background="images/bg.png">
  <%String id= request.getParameter("id");
  Connection conn=ConnectionUtils.getConnection();
	 Statement stmt =conn.createStatement();
	 String work="workplan"+id;
  String sql="select *from "+work;
 ResultSet rs=stmt.executeQuery(sql);
   while(rs.next()){
 String serPassword=rs.getString("ageName");
 String serName=rs.getString("date");
 String serSex=rs.getString("visited");
 String serAge=rs.getString("preorder");
 String medId=rs.getString("medName");
 String havSignedAgency=rs.getString("preNumber");
%>
<b>业务员<%=id%></b>
  <table align="center" border="0" cellspacing="1">
  		<tr>
    		<th width="100">经销商名称</th>
    		<th width="80" valign="bottom">日期</th>
    		<th width="80">是否拜访</th>
    		<th width="80">是否预购</th>
    		<th width="80">药品名称</th>
    		<th width="80">预购数量</th>
    	</tr>
    	<tr>
		<td align="center"><label><input name="serPassword" type="text" maxlength="10" onfocus=this.blur() value=<%=serPassword%>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=serName%>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=serSex%>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=serAge %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=medId%>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=havSignedAgency %>></label></td>
	</tr>		
 </table> 
  
  <%
 }
  rs.close();
  stmt.close();
  conn.close();
   boolean ok=false;
    Connection conn1=ConnectionUtils.getConnection();
	 Statement stmt1 =conn1.createStatement();
	  String sql1="select *from servicer ";
	   ResultSet rs1=stmt1.executeQuery(sql1);
	    String idnext;
	    String idbefore="012111";
         while(rs1.next()){
	     idnext=rs1.getString("serId");
	    if(ok){%> <div algin="center"><table align="center" border="0"><tr valign="bottom"><td colspan="2">&nbsp;</td></tr><tr><td><a href='managePlan.jsp?id=<%=idnext%>' target="mainFrame"> 下一页 |</a></td>
  <%
	    break;}
	     if(rs1.getString("serId").equalsIgnoreCase(id)){ok=true;%><td><a href='managePlan.jsp?id=<%=idbefore%>' target="mainFrame"> 上一页</a></td></tr></table></div><%}
	      else{ idbefore=rs1.getString("serId");}} rs1.close();
  stmt1.close();
  conn1.close();%>  
</body>
</html>

