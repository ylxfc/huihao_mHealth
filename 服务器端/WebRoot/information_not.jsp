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
 <%@ page
     import="org.jfree.chart.*,org.jfree.chart.plot.PiePlot,
     org.jfree.data.general.DefaultPieDataset,
     org.jfree.chart.servlet.ServletUtilities,
     java.awt.*,org.jfree.chart.title.TextTitle"%>
 <%@ page
     import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
 <%@ page import="java.text.NumberFormat"%>
 <%@page import="java.text.DecimalFormat"%>
 
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
  <table border=0 cellpadding=0 cellspacing=0  width="100%" align="center">
	<tr><td align="center">
  <%UserLogin login = null;
	login = (UserLogin)session.getAttribute("login");
	String id= StringUtils.StringEcode(login.getSerId());
  Connection conn=ConnectionUtils.getConnection();
	 Statement stmt =conn.createStatement();
  String sql="select *from servicer where serId = '"+id+"'";
 ResultSet rs=stmt.executeQuery(sql);
   while(rs.next()){
 String havSignedAgency=rs.getString("havSignedAgency");
 String havVisited=rs.getString("havVisited"); 
 double a=Double.parseDouble(havSignedAgency);//设置数据集
 double b=Double.parseDouble(havVisited);//设置数据集
 double c=a/b;
     DefaultPieDataset dataset = new DefaultPieDataset();
     dataset.setValue("成功率",c);
     dataset.setValue("失败率", 1-c);
     //通过工厂类生成JFreeChart对象
     JFreeChart chart = ChartFactory.createPieChart3D("业务人员推销成功率分布图",
             dataset, true, false, false);
 
     PiePlot pieplot = (PiePlot) chart.getPlot();
     //一块突出的饼图，在网上搜了好久也没找到可行的实现方式，欢迎大侠指导 <?后期如果找到解决方法再来做修改?>
 //pieplot.setExplodePercent("A",0.3D);//炸开的饼图，目前实现还有问题
 
 //DecimalFormat:
 //NumberFormat:
 //StandardPieSectionLabelGenerator:
 //setLabelGenerator():    
     DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
     NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
     StandardPieSectionLabelGenerator sp = new StandardPieSectionLabelGenerator(
             "{0}{2}", nf, df);//获得StandardPieSectionLabelGenerator对象
     pieplot.setLabelGenerator(sp);//设置饼图显示百分比
 
 //没有数据的时候显示的内容
     pieplot.setNoDataMessage("无数据显示");
     pieplot.setCircular(false);
     pieplot.setLabelGap(0.02D);
 
     pieplot.setIgnoreNullValues(true);//设置不显示空值
     pieplot.setIgnoreZeroValues(true);//设置不显示负值
 
 //标题文字乱码  IT行业职业分布图
     TextTitle textTitle = chart.getTitle();
     textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
 
     //饼上的文字乱码
     PiePlot plot = (PiePlot) chart.getPlot();
     plot.setLabelFont(new Font("宋体", Font.PLAIN, 12));
 
     //图例文字乱码 饼图下面的5个说明
     chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
 
     String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300,
             null, session);
     String graphURL = request.getContextPath()
             + "/DisplayChart?filename=" + filename;
 
 %> <img src="<%=graphURL%>" width=500 height=300 border=0
             usemap="#<%=filename %>"> </td><%
  
 }
  rs.close();
  stmt.close();
  conn.close();
  %>  
</table>
</body>
</html>


