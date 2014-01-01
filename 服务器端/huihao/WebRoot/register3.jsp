<%@ page language="java" import="java.util.*" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%@page import="java.sql.*"%>
  <%@page import="myutils.ConnectionUtils"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>慧好制药业务管理系统</title>
    
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<script language="javascript">
  function isdate(strDate){
   var strSeparator = "-"; //日期分隔符
   var strDateArray;
   var intYear;
   var intMonth;
   var intDay;
   var boolLeapYear;
   
   strDateArray = strDate.split(strSeparator);
   
   if(strDateArray.length!=3) return false;
   
   intYear = parseInt(strDateArray[0],10);
   intMonth = parseInt(strDateArray[1],10);
   intDay = parseInt(strDateArray[2],10);
   
   if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) return false;
   
   if(intMonth>12||intMonth<1) return false;
   
   if((intMonth==1||intMonth==3||intMonth==5||intMonth==7||intMonth==8||intMonth==10||intMonth==12)&&(intDay>31||intDay<1)) return false;
   
   if((intMonth==4||intMonth==6||intMonth==9||intMonth==11)&&(intDay>30||intDay<1)) return false;
   
   if(intMonth==2){
      if(intDay<1) return false;
      
      boolLeapYear = false;
      if((intYear%100)==0){
         if((intYear%400)==0) boolLeapYear = true;
      }
      else{
         if((intYear%4)==0) boolLeapYear = true;
      }
      
      if(boolLeapYear){
         if(intDay>29) return false;
      }
      else{
         if(intDay>28) return false;
      }
   }
   
   return true;
} 
  function check()
  {
    medid=window.document.form5.medId.value.length;
    mednamelen=window.document.form5.medName.value.length;
    medclasslen=window.document.form5.medClass.value.length;
    medpricelen=window.document.form5.medPrice.value.length;
    meddatelen=window.document.form5.medDate.value.length;
    medspecificationlen=window.document.form5.medSpecification.value.length;
    medintroducationlen=window.document.form5.medIntroducation.value.length;
    medstocklen=window.document.form5.medStock.value.length;
    medbeensoldlen=window.document.form5.medBeenSold.value.length;
    meddiscountlen=window.document.form5.medDiscount.value.length;
    medname=window.document.form5.medName.value;
    medclass=window.document.form5.medClass.value;
    medprice=window.document.form5.medPrice.value;
    meddate=window.document.form5.medDate.value;
    medspecification=window.document.form5.medSpecification.value;
    medintroducation=window.document.form5.medIntroducation.value;
    medstock=window.document.form5.medStock.value;
    medbeensold=window.document.form5.medBeenSold.value;
    meddiscount=window.document.form5.medDiscount.value;
    if(medid==0) alert("请输入批准文号");
    else if(mednamelen==0)  alert("请输入药品名称");
    else if(medclasslen==0) alert("请输入药品类别");
    else if(medpricelen==0) alert("请输入药品单价");
    else if(meddatelen==0) alert("请输入生产日期");
    else if(medspecificationlen==0) alert("请输入药品规格");
    else if(medintroducationlen==0) alert("请输入药品简介");
    else if(medstocklen==0) alert("请输入药品库存");
    else if(medbeensoldlen==0) alert("请输入药品已售出量");
    else if(meddiscountlen==0) alert("请输入药品优惠信息");
    else if(!isNaN(medname)) alert("药品名称不能为数字，请重新输入");
    else if(!isNaN(medclass)) alert("药品类别不能为数字，请重新输入");
    else if(isNaN(medprice)) alert("药品单价必须为数字，请重新输入");
    else if(!isdate(meddate)) alert("日期格式为xxxx-xx-xx，请重新输入");
    else if(!isNaN(medspecification)) alert("药品规格不能为数字，请重新输入");
    else if(!isNaN(medintroducation)) alert("药品简介不能为数字，请重新输入");
    else if(isNaN(medstock)) alert("库存必须为数字，请重新输入");
    else if(isNaN(medbeensold)) alert("已售出量必须为数字，请重新输入");
    else if(!isNaN(meddiscount)) alert("优惠信息不能为数字，请重新输入");
    else form5.submit();
  }
</script>
  </head>
  <%String id=request.getParameter("DI");
  Connection conn=ConnectionUtils.getConnection();
	 Statement stmt =conn.createStatement();
  String sql="select *from medicine";
 ResultSet rs=stmt.executeQuery(sql);
   while(rs.next()){
 if(rs.getString("medId").equalsIgnoreCase(id)){
 String medName=rs.getString("medName");
 String medClass=rs.getString("medClass");
 String medPrice=rs.getString("medPrice");
 String medDate=rs.getString("medDate");
 String medSpecification=rs.getString("medSpecification");
 String medIntroducation=rs.getString("medIntroducation");
 String medStock=rs.getString("medStock");
 String medBeenSold=rs.getString("medBeenSold");
 String medDiscount=rs.getString("medDiscount");%>
 <body background="images/bg.png">
  <table border=0 cellpadding=0 cellspacing=0  width="100%" align="center" >
	<tr>
	  <td >		
		    <table width="90%" border="0" cellspacing="0" cellpadding="0"  align="center">
              <tr>
                <td>
                <form name="form5" method="post" action="updatePill.do">
                <p>&nbsp;</p>
                  <h3 align="center">请认真填写修改信息</h3>
                  <table width="300" border="0" align="center">
                    <tr>
                      <td width="100">批准文号：</td>
                      <td width="184"><label>
                        <input name="medId" value=<%=id %> type="text" id="medId">
                      </label></td>
                    </tr>
                    <tr>
                      <td>药品名称：</td>
                      <td><label>
                        <input name="medName" value=<%=medName %> type="text" id="medName">
                      </label></td>
                    </tr>
					<tr>
                      <td>药品类别:</td>
                      <td><label>
                        <input name="medClass" value=<%=medClass %> type="text" id="medClass">
                      </label></td>
                    </tr>                    
					<tr>
                      <td>药品单价:</td>
                      <td><label>
                        <input name="medPrice" value=<%=medPrice %> type="text" id="medPrice">
                      </label></td>
                    </tr>
                    <tr>
                      <td>生产日期:</td>
                      <td><label>
                        <input name="medDate" value=<%=medDate %> type="text" id="medDate">
                      </label></td>
                    </tr>
                    <tr>
                      <td>药品规格:</td>
                      <td><label>
                        <input name="medSpecification" value=<%=medSpecification %> type="text" id="medSpecification">
                      </label></td>
                    </tr>
                    <tr>
                      <td>药品简介:</td>
                      <td><label>
                        <input name="medIntroducation" value=<%=medIntroducation %> type="text" id="medIntroducation">
                      </label></td>
                    </tr>
                     <tr>
                      <td>库存:</td>
                      <td><label>
                        <input name="medStock" value=<%=medStock %> type="text" id="medStock">
                      </label></td>
                    </tr>
                     <tr>
                      <td>已售出量:</td>
                      <td><label>
                        <input name="medBeenSold" value=<%=medBeenSold %> type="text" id="medBeenSold">
                      </label></td>
                    </tr>
                     <tr>
                      <td>优惠信息:</td>
                      <td><label>
                        <input name="medDiscount" value=<%=medDiscount %> type="text" id="medDiscount">
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
  </body>
  
  <%
 }}
  rs.close();
  stmt.close();
  conn.close(); 
  %> 
</html>