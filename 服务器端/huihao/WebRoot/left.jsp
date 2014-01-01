<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page import="mybean.UserLogin"%>
<%
	UserLogin log = null;
	String t1="admin";
	try{
		log = (UserLogin) session.getAttribute("login");
	}catch(Exception e){}
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>慧好制药业务管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta name="author" content="Ian Main" />
		<meta name="Copyright"
			content="Creative Commons - http://creativecommons.org/licenses/by/2.0/" />
		<link href="css/left.css" rel="stylesheet"> 
<script language="javascript">
  function showm()
  {
    document.getElementById("layer1").style.display="inline";
  }
  function showu()
  {
    document.getElementById("layer2").style.display="inline";
  }
</script>
</head>
	<body>
	<center>
		<div id="menu3">
			<ul>
				<!-- CSS Tabs -->
				
              <%  if(log!=null){
			String type = log.getType();
	    	if(type.equals(t1)) {%>
				<li>
					<a href="productList.jsp" target="mainFrame">药品管理</a>
				</li>
				<li>
					<a href="manage_isManage.jsp" target="mainFrame">业务员管理</a>
				</li>
				<li>
					<a href="Agencyis.jsp" target="mainFrame">经销商管理</a>
				</li>
				<li>
					<a href="managePlan.jsp?id=012111" target="mainFrame">工作计划</a>
				</li>
				<li>
					<a href="Content.jsp" target="mainFrame" onclick="showm()">信息分析</a>
					<div id="layer1" style="display: none;padding-top: 0;margin: 0">
					<ul style="padding-top: 0;margin: 0">
					  <li style="width: 140px;margin: 5px"><a href="information_is.jsp" target="mainFrame">业务员分析</a></li>
					  <li style="width: 140px;margin: 5px"><a href="informationis.jsp" target="mainFrame">经销商分析</a></li>
					</ul>
					</div>
				</li>
				<li>
					<a href="transparent_isManager.jsp" target="mainFrame">消息推送</a>
				</li>
				<%}else { %>
				<li>
					<a href="productList1.jsp" target="mainFrame">药品信息</a>
				</li>
				<li>
					<a href="manage_notManage.jsp" target="mainFrame">个人信息管理</a>
				</li>
				<li>
					<a href="Agencynot.jsp" target="mainFrame">经销商信息</a>
				</li>
				<li>
					<a href="serPlan.jsp" target="mainFrame">个人工作计划</a>
				</li>
				<li>
					<a href="Content.jsp" target="mainFrame" onclick="showu()">工作业绩</a>
					<div id="layer2" style="display: none;padding-top: 0;margin: 0">
					<ul style="padding-top: 0;margin: 0">
					  <li style="width: 140px;margin: 5px"><a href="information_not.jsp" target="mainFrame">业绩分析</a></li>
					  <li style="width: 140px;margin: 5px"><a href="informationnot.jsp" target="mainFrame">经销商分析</a></li>
					</ul>
					</div>
				</li>
				<li>
					<a href="transparent_notManager.jsp" target="mainFrame">推送信息</a>
				</li>
				<%}} %>
			</ul>
		</div>
		</center>
	</body>
</html>
