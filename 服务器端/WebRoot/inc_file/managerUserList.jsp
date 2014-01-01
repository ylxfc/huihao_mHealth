<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="mybean.UserBean" %>
<%@ page import="mybean.UserLogin"  %>
<%@ page import="com.sun.rowset.CachedRowSetImpl" %>

<%	String str1="previous";
	String str2="next";

	UserLogin login = null;
	login = (UserLogin)session.getAttribute("login");
		
		UserBean user = null;
		user = (UserBean)session.getAttribute("user");
		if(user==null){
			response.sendRedirect("managerUser.do");
		}else{
			user.setPageSize(8);		
			CachedRowSetImpl row = user.getRow();
			int pageCount = user.getPageCount();
			int currentPage = user.getCurrentPage();
			int rowCount = user.getRowCount();
			int pageSize = user.getPageSize();
%>
<table width="260" height="50" border="0" align="center">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table align="center" border="0" cellspacing="1">
  		<tr>
    		<th width="120">业务人员编号</th>
    		<th width="80">密码</th>
    		<th width="80">姓名</th>
    		<th width="80">性别</th>
			<th width="80">年龄</th>
			<th width="80">电话</th>
			<th width="160">销售药品批准文号</th>
			<th width="160">已签约经销商数量</th>
			<th width="160">已拜访经销商数量</th>
            <th width="50">删除</th>
  		</tr>
  	<%		
			row.absolute((currentPage-1)*pageSize+1);
			for(int i = 1; i<= pageSize;i ++){							
	%>
	<tr>
		<td><label><input name="serId" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("serId") %>></label></td>
		<td align="center"><label><input name="serPassword" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("serPassword") %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("serName") %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("serSex") %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("serAge") %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("serPhone") %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("medId") %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("havSignedAgency") %>></label></td>
		<td align="center"><label><input name="serName" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("havVisited") %>></label></td>
		<td align="center"><label><a href='confirmDeleteUser1.jsp?id=<%=row.getString("serId")%>' target="mainFrame" >删除</a></label></td>
	</tr>				
	<% 		
				if(!row.next())break;
			}
			
	%>
	
</table>
	<br>
    <br/> <form action="register1.jsp" method="get" name="myform">
请输入要管理的业务员编号 ：<input name="ID" type="text" maxlength="5">
<input name="sure" type ="submit"  value="提交">
    </form>			
    <div align="center"><a href='register.jsp?' target="mainFrame">添加业务员 |</a><a href='managerUser.do?id=<%=str1 %>' target="mainFrame">上一页 |</a><a href='managerUser.do?id=<%=str2 %>' target="mainFrame"> 下一页</a>&nbsp;&nbsp; 用户列表共<%=pageCount %>页&nbsp; <%=rowCount %>个用户&nbsp; 当前是第<%=currentPage %>页
  <%  	}
		
 %>  
</div>
