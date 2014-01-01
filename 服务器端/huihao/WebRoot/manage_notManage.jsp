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
    
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<script language="javascript">
  function check()
  {
    seridlen=window.document.form1.serId.value.length;
    serpassword=window.document.form1.serPassword.value.length;
    sername=window.document.form1.serName.value.length;
    sersexlen=window.document.form1.serSex.value.length;
    seragelen=window.document.form1.serAge.value.length;
    serphonelen=window.document.form1.serPhone.value.length;
    medidlen=window.document.form1.medId.value.length;
    havsignedagencylen=window.document.form1.havSignedAgency.value.length;
    havvisitedlen=window.document.form1.havVisited.value.length;
    serid=window.document.form1.serId.value;
    sersex=window.document.form1.serSex.value;
    serage=window.document.form1.serAge.value;
    serphone=window.document.form1.serPhone.value;
    medid=window.document.form1.medId.value;
    havsignedagency=window.document.form1.havSignedAgency.value;
    havvisited=window.document.form1.havVisited.value;
    if(seridlen==0) alert("请输入业务员编号");
    else if(serpassword==0)  alert("请输入业务员密码");
    else if(sername==0) alert("请输入业务员姓名");
    else if(sersexlen==0) alert("请输入业务员性别");
    else if(seragelen==0) alert("请输入业务员年龄");
    else if(serphonelen==0) alert("请输入业务员联系方式");
    else if(medidlen==0) alert("请输入药品种类");
    else if(havsignedagencylen==0) alert("请输入签约经销商数量");
    else if(havvisitedlen==0) alert("请输入已拜访经销商数量");
    else if(isNaN(serid))  alert("业务员编号必须为数字，请重新输入");
    else if(sersex!="男"&&sersex!="女")  alert("性别输入无效，请输入男或女");
    else if(isNaN(serage))  alert("年龄必须为数字，请重新输入");
    else if(isNaN(serphone)) alert("联系方式必须为数字，请重新输入");
    else if(!isNaN(medid))  alert("药品种类不能为数字，请重新输入");
    else if(isNaN(havsignedagency))  alert("签约经销商数量必须为数字，请重新输入");
    else if(isNaN(havvisited))  alert("已拜访经销商数量必须为数字，请重新输入");
    else form1.submit();
  }
</script>
  </head>
  <%UserLogin login = null;
	login = (UserLogin)session.getAttribute("login");
	String id= StringUtils.StringEcode(login.getSerId());
  Connection conn=ConnectionUtils.getConnection();
	 Statement stmt =conn.createStatement();
  String sql="select *from servicer";
 ResultSet rs=stmt.executeQuery(sql);
   while(rs.next()){
 if(rs.getString("serId").equalsIgnoreCase(id)){
 String serPassword=rs.getString("serPassword");
 String serName=rs.getString("serName");
 String serSex=rs.getString("serSex");
 String serAge=rs.getString("serAge");
 String serPhone=rs.getString("serPhone");
 String medId=rs.getString("medId");
 String havSignedAgency=rs.getString("havSignedAgency");
 String havVisited=rs.getString("havVisited");%>
 <body background="images/bg.png">
  <table border=0 cellpadding=0 cellspacing=0  width="100%" align="center" >
	<tr><td>
	 <br><table cellpadding=0 cellspacing=0  width="1000" align="center" >
	<tr>	
	  <td>	
		    <table width="90%" border="0" cellspacing="0" cellpadding="0"  align="center">
              <tr>
                <td>
                <form name="form1" method="post" action="updateUser.do" >
                <p>&nbsp;</p>
                  <h3 align="center">请认真填写修改信息</h3>
                  <table width="300" border="0" align="center">
                    <tr>
                      <td width="100">业务员编号：</td>
                      <td width="184"><label>
                        <input name="serId" value=<%=id %> readonly type="text" id="serId">
                      </label></td>
                    </tr>
                    <tr>
                      <td>密码：</td>
                      <td><label>
                        <input name="serPassword" type="text" value=<%=serPassword %> id="serPassword">
                      </label></td>
                    </tr>
					<tr>
                      <td>姓名：</td>
                      <td><label>
                        <input name="serName" type="text" value=<%=serName%> id="serName">
                      </label></td>
                    </tr>                    
					<tr>
                      <td>性别：</td>
                      <td><label>
                        <input name="serSex" type="text" value=<%=serSex %> id="serSex">
                      </label></td>
                    </tr>
                    <tr>
                      <td>年龄：</td>
                      <td><label>
                        <input name="serAge" type="text" value=<%=serAge %> id="serAge">
                      </label></td>
                    </tr>
                    <tr>
                      <td>电话</td>
                      <td><label>
                        <input name="serPhone" type="text" value=<%=serPhone %> id="serAge">
                      </label></td>
                    <tr>
                      <td>销售药品批准文号：</td>
                      <td><label>
                        <input name="medId" type="text" value=<%=medId%> id="medId">
                      </label></td>
                    </tr>
                    <tr>
                      <td>已签约经销商数量：</td>
                      <td><label>
                        <input name="havSignedAgency" type="text" value=<%=havSignedAgency%> id="havSignedAgency">
                      </label></td>
                    </tr>
                      <tr>
                      <td>已拜访经销商数量：</td>
                      <td><label>
                        <input name="havVisited" type="text" value=<%=havVisited%> id="havVisited">
                      </label></td>
                    </tr>
				    <tr>
                      <td colspan="2" align="center"><label>
                        <input type="button" name="sub" value="提交" onclick="check()">
                        <input name="reset" type="reset" id="reset" value="重置">
                      </label></td>
                    </tr>
                  </table>
                </form>
                </td>
              </tr>
            </table></td>
	</tr>
</table>
</table>
  </body>
  
  <%
 }}
  rs.close();
  stmt.close();
  conn.close();
  %> 
  
</html>
