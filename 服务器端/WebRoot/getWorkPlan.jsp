<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ page import="java.io.*,wyy.MyConverter,wyy.DB,java.util.*" %>
     <%@page import="java.sql.*"%>
  <%@page import="myutils.*"%>
  <% 
   String param1=request.getParameter("params1").trim();
   String message=MyConverter.unescape(param1);
    String[] mess=message.split("\\|");
     Random rnd = new Random();
   String serId=mess[0];
		for(int i=1;i<mess.length;i++){ 
		 boolean ok=false;
		String[] mes=mess[i].split(",");
		 String tabelname1="workplan"+serId;
	 String sql1 = " insert into  "+tabelname1+"(ageName,date,visited,preorder,medName,preNumber) values(?,?,?,?,?,?)";
	 List<String> parameters1 = new ArrayList<String>();
	 if(Integer.parseInt(mes[3])==1){
		parameters1.add(mes[0]);
		parameters1.add(mes[1]);
		parameters1.add("ÊÇ");
	parameters1.add("ÊÇ");
		parameters1.add(mes[4]);
		parameters1.add(mes[5]);}
		else
		{
		parameters1.add(mes[0]);
		parameters1.add(mes[1]);
		parameters1.add("·ñ");
		parameters1.add("·ñ");
		parameters1.add(mes[4]);
		parameters1.add(mes[5]);
		}
		SqlUtils.ZSGSql(sql1, parameters1);	  
		Connection conn=ConnectionUtils.getConnection();
Statement stmt =conn.createStatement();
  String sql="select  *from  agency where ageName='"+mes[0]+"' ";
 ResultSet rs=stmt.executeQuery(sql);
 while(rs.next()){
		 UserUtils.updateAgency(mes[0], mes[3], mes[4], serId, mes[1]);
		 ok=true;
		 }
		 if(!ok){
		  String sql2 = " insert into  agency(ageId,ageName,ageAddress,agePhone,ageNumberOfWorker,preNumber,medName,serId,datetime) values(?,?,?,?,?,?,?,?,?)";
	 List<String> parameters = new ArrayList<String>();
		parameters.add((20-rnd.nextInt(10))+"");
		parameters.add(mes[0]);
		parameters.add("ÎäººÊÐ");
		parameters.add((100000000-rnd.nextInt(90000000))+"");
		parameters.add((100-rnd.nextInt(10))+"");
		parameters.add(mes[5]);
		parameters.add(mes[4]);
		parameters.add(serId);
		parameters.add(mes[1]);
		SqlUtils.ZSGSql(sql2, parameters);}
		 
}
		
		 out.println(MyConverter.escape("1"));
		 %>
		