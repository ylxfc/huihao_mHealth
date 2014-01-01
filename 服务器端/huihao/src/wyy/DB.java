package wyy;

import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
public class DB
{
	private static Connection con=null;//声明Connection引用
	private static Statement stat=null;//声明Statement引用
	private static ResultSet rs=null;//声明ResultSet引用
//*****************************数据库连接和关闭操作*************************
	public static Connection getCon(){//得到数据库连接的方法	
		
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost/huihao";
		con = DriverManager.getConnection(url,"root","123456");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e1){
				e1.printStackTrace();
				}
	
		return con;
	}
	public static void closeCon(){//关闭数据库连接方法
		try	{
			  if(rs!=null){rs.close();}
			  if(stat!=null){stat.close();}
			  if(con!=null){con.close();}
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
//*******************对分组的操作******************************************
	public static Vector<String[]> getGroup(){
		Vector<String[]> v =new Vector<String[]>();//创建返回向量对象
		try{
			 con = DB.getCon();//得到数据库连接
			 stat = con.createStatement();//创建语句对象
			 String sql = "select gName,gImg,gDetail,gId,gOrderDet from rgroup";
			 rs = stat.executeQuery(sql);
			 while(rs.next()){//遍历结果集得到分组信息		    
			    String group[] = new String[5];
			    for(int i=0;i<group.length;i++){
			      group[i] = //将信息添加到数组
			    	new String(rs.getString(i+1).getBytes("iso8859-1"),"gbk");
			    }			
				v.add(group);//将信息数组添加到返回的向量里
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		finally
		{DB.closeCon();}	
		return v;
	}
	public static Vector<String> getGroupInfo(int gId){
		Vector<String> v =new Vector<String>();//创建返回信息向量	
		try{
			 con = DB.getCon();//得到数据库连接
			 stat = con.createStatement();//创建语句对象
			 String sql = "select gId,gName,gOrderDet,gImg,gDetail from"+
			  				" rgroup where gId="+gId;
			 rs = stat.executeQuery(sql);//执行SQL查询
			 if(rs.next()){//将结果集信息添加到返回向量中			  
				v.add(new String(rs.getString(1).getBytes("iso8859-1"),"gbk"));
				v.add(new String(rs.getString(2).getBytes("iso8859-1"),"gbk"));
				v.add(new String(rs.getString(3).getBytes("iso8859-1"),"gbk"));
				v.add(new String(rs.getString(4).getBytes("iso8859-1"),"gbk"));
				v.add(new String(rs.getString(5).getBytes("iso8859-1"),"gbk"));								
			 }
		}
		catch(Exception e){e.printStackTrace();}
		finally	{DB.closeCon();}//关闭数据库连接
		return v;//返回分组信息
	}
	public static String getOrderDet(int gId)
	{
		String msg="";
		try
		{
			con=DB.getCon();
			stat=con.createStatement();
			String sql="select gOrderDet from rgroup where gId="+gId;
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				msg=new String(rs.getString(1).getBytes("iso8859-1"),"gbk");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return msg;
	}
	
//******************分页操作**************************************************
public static int getTotal(int span,int group){
	int result=0;//初始化返回页数
	String sql = "";//声明sql引用
	try{
		con = DB.getCon();
		stat = con.createStatement();
		//得到相关记录的总条数
		if(group==0)//0代表所有分组
		{sql = "select count(*) from resource";}
		else{
			sql = "select count(*) from resource "+"where rgroup='"+group+"'";
		}
		rs = stat.executeQuery(sql);//执行sql语句			
	    rs.next();
	    int rows=rs.getInt(1);//得到记录条数
	    result=rows/span+((rows%span==0)?0:1);//计算出总页数
	}
	catch(Exception e){e.printStackTrace();}
	finally{DB.closeCon();}//关闭数据库连接		
	return result;//返回结果
}
public static Vector<String[]> getResource(String rlevel)
	{
		Vector<String[]> v=new Vector<String[]>();
		
		String sql;
		try
		{
			con=DB.getCon();
			stat=con.createStatement();
			String rlevell = new String(rlevel.getBytes("gbk"),"iso8859-1");
			sql="select rgid,rlevel,rmoney,rstatus from resource where rlevel='"+rlevell+"'";
			rs=stat.executeQuery(sql);
            while(rs.next()){//遍历结果集得到分组信息		    
			    String group[] = new String[4];
			    for(int i=0;i<group.length;i++){
			      group[i] = //将信息添加到数组
			    	new String(rs.getString(i+1).getBytes("iso8859-1"),"gbk");
			    }			
				v.add(group);//将信息数组添加到返回的向量里
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return  v;
	}
	public static Vector<String[]> getResource1()
	{
		Vector<String[]> v=new Vector<String[]>();
		try
		{
			con=DB.getCon();
			stat=con.createStatement();
			String sql="select rgroup,rgid,rlevel,rmoney,rstatus from resource";
			rs=stat.executeQuery(sql);
            while(rs.next()){//遍历结果集得到分组信息		    
			    String group[] = new String[4];
			    for(int i=0;i<group.length;i++){
			      group[i] = //将信息添加到数组
			    	new String(rs.getString(i+2).getBytes("iso8859-1"),"gbk");
			    }			
				v.add(group);//将信息数组添加到返回的向量里
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return  v;
	}
	
public static Vector<String[]> getPageContent(int page,int span,int group){
	Vector<String[]> v = new Vector<String[]>();//声明返回向量集合
	String sql = "";//声明sql语句引用
	int startRow = 	(page-1)*span;//计算出起始记录行数
	try{
		con = DB.getCon();
		stat = con.createStatement();
		if(group==0){//group参数为零，则是对所有的分组进行分页显示
			sql = "select rgid,rlevel,rmoney,rdetail,rstatus,rid,gName from "+
			       "resource,rgroup where resource.rgroup=rgroup.gId order "+
			        "by rgroup, rgid, rid";
		}
		else{//对具体分组进行分页显示
			sql = "select rgid,rlevel,rmoney,rdetail,rstatus,rid,gName "+
			 	   "from resource,rgroup where resource.rgroup=rgroup.gId "+
			 	   "and rgroup='"+group+"' order by rgid";
		}
		rs = stat.executeQuery(sql);//执行sql语句，拿到结果集
		if(startRow!=0)//如果其实行不是第零行
		{rs.absolute(startRow);}//结果集滚动到起始行
		int c=0;//控制读取的记录条数
		while(c<span&&rs.next()){//从其实行读每页显示的记录条数
			String s[] = new String[7];
			for(int i=0;i<s.length;i++){
		      s[i] = //遍历结果集将信息添加到数组
		    	new String(rs.getString(i+1).getBytes("iso8859-1"),"gbk");
		    }							
			v.add(s);//将数组添加到返回向量
			c++;
		}
	}
	catch(Exception e){e.printStackTrace();}
	finally{DB.closeCon();}//关闭数据库连接	
	return v;//返回结果
}
//*******************得到某张表某一列的最大值并加1***************************
	public static int getId(String table,String row){//得到一个表主键ID+1值
		int id = 0;
		try	{
			con = DB.getCon();//得到数据库连接
			stat = con.createStatement();//创建语句对象
			rs = stat.executeQuery("select count(*) from "+table);
			rs.next();
			if(rs.getInt(1)==0)	{ id = 1; }//如果表中没有记录，则将id置为1
			else{
				rs = stat.executeQuery("select max("+row+") from "+table);
				rs.next();
				id = Integer.parseInt(rs.getString(1))+1;//将其值加一
			}						
		}
		catch(Exception e){e.printStackTrace();}
		finally	{DB.closeCon();}//关闭数据库连接
		return id;//返回结果
	}
//********************某条记录是否存在**************************************
	public static boolean isExist(String sqla){//查看此条记录是否存在
		boolean flag = false;			
		try{			
			String sql = new String(sqla.getBytes("gbk"),"iso8859-1");//转码	
			con = DB.getCon();//得到数据库连接
			stat = con.createStatement();//创建语句对象
			rs = stat.executeQuery(sql);//执行查询
			if(rs.next()) {flag = true;}
		}
		catch(Exception e)	{e.printStackTrace();}
		finally	{DB.closeCon();}//关闭数据库连接		
		return flag;//返回结果
	}
//*********************更新数据库*****************************************
	public static int update(String sqla){
		int changedCount=0;
		try{
			String sql = new String(sqla.getBytes(),"iso8859-1");//转码
			con = DB.getCon();//得到数据库连接
			stat = con.createStatement();//创建语句对象
			changedCount = stat.executeUpdate(sql);//进行更新
		}
		catch(Exception e)	{e.printStackTrace();}
		finally {DB.closeCon();}//关闭数据库连接 		
		return changedCount;//返回跟新记录条数
	}
	public static boolean update(String sqla,String sqlb){//事务处理
		boolean b = false;//记录是否更新成功
		try{
			con = DB.getCon();//得到数据库连接
			con.setAutoCommit(false);//禁止自动提交，开始一个事务
			stat = con.createStatement();
			String sql = new String(sqla.getBytes(),"iso8859-1");//转码
			stat.executeUpdate(sql);//执行更新
			sql = new String(sqlb.getBytes(),"iso8859-1");//转码
			stat.executeUpdate(sql);//执行更新			
			con.commit();//将事务提交			
			con.setAutoCommit(true);//恢复自动提交模式
			b = true;//设置更新成功
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				con.rollback();//出现问题，事务回滚
				b = false;
			}
			catch(Exception ea){ea.printStackTrace();}
		}
		finally{DB.closeCon();}//关闭数据库连接
		return b;//返回更新成功或者失败标志
	}
		public static boolean updatea(String sqla)//我添加的
	{
		boolean b=false;
		try{
			String sql = new String(sqla.getBytes(),"iso8859-1");//转码
			con = DB.getCon();//得到数据库连接
			con.setAutoCommit(false);//禁止自动提交，开始一个事务
			stat = con.createStatement();//创建语句对象
			stat.executeUpdate(sql);//进行更新
			con.commit();//将事务提交			
			con.setAutoCommit(true);//恢复自动提交模式
			b = true;//设置更新成功
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				con.rollback();//出现问题，事务回滚
				b = false;
			}
			catch(Exception ea){ea.printStackTrace();}
		}
		finally{DB.closeCon();}//关闭数据库连接
		return b;//返回更新成功或者失败标志
	}
//********************根据一条SQL得到数据库中信息****************************
	public static String getInfo(String sqla){
		String Info=null;
		try{			
			String sql = new String(sqla.getBytes(),"iso8859-1");//SQL转码
			con = DB.getCon();//得到数据库连接
			stat = con.createStatement();//创建语句对象
			rs = stat.executeQuery(sql);//执行查询
			if(rs.next())
			{Info=new String(rs.getString(1).getBytes("iso8859-1"),"gbk");}
		}
		catch(Exception e)	{e.printStackTrace();}
		finally {DB.closeCon();}		
		return Info;
	}
	public static String getDetail(String rgid)
	{
		String s=null;
		try
		{
			String sql="select rdetail from resource where rgid="+rgid;
			con=DB.getCon();
			stat=con.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next())
			{
				s=new String(rs.getString(1).getBytes("iso8859-1"),"gbk");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DB.closeCon();
		}
		return s;
	}
	
//***********************得到用户的详细信息********************************
	public static Vector<String> getUserInfo(String uname1){
		Vector<String> userInfo=new Vector<String>();
		try{
			String serId = new String(uname1.getBytes("gbk"),"iso8859-1");//转码
			con = DB.getCon();//得到数据库连接
			stat = con.createStatement();//创建语句对象
			rs = stat.executeQuery("select serPassword,serName,serSex,serAge,medId,"+
									"havSignedAgency from user where serId='"+serId+"'");
			if(rs.next()){//将用户信息添加到向量中
			    userInfo.add(new String(rs.getString(1).getBytes("iso8859-1"),"gbk"));
				userInfo.add(new String(rs.getString(2).getBytes("iso8859-1"),"gbk"));
				userInfo.add(new String(rs.getString(3).getBytes("iso8859-1"),"gbk"));
				userInfo.add(new String(rs.getString(4).getBytes("iso8859-1"),"gbk"));
				userInfo.add(new String(rs.getString(5).getBytes("iso8859-1"),"gbk"));
				userInfo.add(new String(rs.getString(6).getBytes("iso8859-1"),"gbk"));
			}
		}
		catch(Exception e) {e.printStackTrace();}
		finally	{DB.closeCon();}//关闭数据库连接		
		return userInfo;//返回用户信息
	}
//******************得到资源的详细信息***************************
	public static Vector<String[]> getResInfo(String sqla){
		Vector<String []> v = new Vector<String[]>();
		try{
			con = DB.getCon();//得到数据库连接
			stat = con.createStatement();//创建语句对象			
			String sql = new String(sqla.getBytes(),"iso8859-1");//转码
			rs = stat.executeQuery(sql);//执行查询
			while(rs.next()){
				String s[] = new String[8];
				for(int i=0;i<s.length-1;i++){//将资源信息添加到数组
					s[i] = new String(rs.getString(i+1).getBytes("iso8859-1"),"gbk");
				}				
				v.add(s);//将数组添加到返回向量中
			}
			for(String s[]:v){//根据分组ID得到分组名				
				String sqlb = "select gName from rgroup where gId='"+s[5]+"'";
				rs = stat.executeQuery(sqlb);
				rs.next();//结果集游标向后移一位
				s[7] = new String(rs.getString(1).getBytes("iso8859-1"),"gbk");
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally{DB.closeCon();}//关闭数据库连接
		return v;//返回查询结果
	}
	
//*****************得到管理员详细信息********************************
	public static Vector<String[]> getAdminInfo(){
		Vector<String[]> v = new Vector<String[]>();
		try{
			con = DB.getCon();//得到数据库连接
			stat = con.createStatement();//创建语句对象
			rs = stat.executeQuery("select adname,adlevel from adinfo");//执行查询
			while(rs.next()){
				String s[] = new String[2];
				s[0] = new String(rs.getString(1).getBytes("iso8859-1"),"gbk");
				s[1] = new String(rs.getString(2).getBytes("iso8859-1"),"gbk");				
				v.add(s);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{DB.closeCon();}		
		return v;
	}
		public static boolean isDelete(String rid1){
		boolean b = false;
		int count=0;
		try{
			String orid=new String(rid1.getBytes("iso8859-1"),"gbk");
			System.out.println(orid);
			String sqla="delete from olist where oid="+orid;
			String sqlb="delete from oinfo where orid="+orid;
		    count+=stat.executeUpdate(sqla);
		    count+=stat.executeUpdate(sqlb);
		    if(count==2){b=true;}
		}
		catch(Exception e){e.printStackTrace();}
		finally{DB.closeCon();}
		return b;
	}
}