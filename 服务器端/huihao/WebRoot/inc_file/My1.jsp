<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ page import="org.jfree.chart.ChartFactory, 
org.jfree.chart.JFreeChart, 
org.jfree.chart.plot.PlotOrientation, 
org.jfree.chart.servlet.ServletUtilities, 
org.jfree.data.category.DefaultCategoryDataset"%><% 
DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
dataset.addValue(410, "广州", "猪肉"); 
dataset.addValue(220, "广州", "牛肉"); 
dataset.addValue(330, "广州", "鸡肉"); 
dataset.addValue(340, "广州", "鱼肉"); 
JFreeChart chart = ChartFactory.createBarChart3D("业务员成功率统计图", 
"业务员ID", 
"成功率", 
dataset, 
PlotOrientation.VERTICAL, 
false, 
false, 
false); 
String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session); 
String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename; 
%> 
<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= filename %>"> 
