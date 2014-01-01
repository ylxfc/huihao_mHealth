<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="mybean.PostBean" %>
<%@ page import="mybean.UserLogin"  %>
<%@ page import="com.sun.rowset.CachedRowSetImpl" %>

<%	String str1="previous";
	String str2="next";
	UserLogin login = null;
	login = (UserLogin)session.getAttribute("login");
		String serId=login.getSerId();
		PostBean post = null;
		post = (PostBean)session.getAttribute("post");
		if(post==null){
			response.sendRedirect("managerPost1.do");
		}else{
			post.setPageSize(8);		
			CachedRowSetImpl row = post.getRow();
			int pageCount = post.getPageCount();
			int currentPage = post.getCurrentPage();
			int rowCount = post.getRowCount();
			int pageSize = post.getPageSize();
%>
<table width="260" height="50" border="0" align="center">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table align="center" border="0" cellspacing="1">
  		<tr>
    		<th width="80">通知编号</th>
    		<th width="80">通知内容</th>
    		<th width="80">通知时间</th>
  		</tr>
  	<%		
			row.absolute((currentPage-1)*pageSize+1);
			for(int i = 1; i<= pageSize;i ++){	if(row.getString("serId").equalsIgnoreCase(serId)){						
	%>
	<tr>
		<td><label><input name="id" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("id") %>></label></td>
	
		<td align="center"> <label> <textarea name="text"  cols="40" rows="4" onfocus=this.blur() ><%=row.getString("text")%></textarea> </label></td>
			<td align="center"><label><input name="serPassword" type="text" maxlength="10" onfocus=this.blur() value=<%=row.getString("date") %>></label></td>
	</tr>				
	<% 		}
				if(!row.next())break;
			}
			
	%>
</table>
	<%}
		
 %>  
</div>
    <br/>	
  
