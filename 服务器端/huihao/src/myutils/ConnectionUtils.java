package myutils;
import java.sql.*;

public class ConnectionUtils {
	
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			//注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//数据库的位置字符串
			String url = "jdbc:mySql://127.0.0.1:3306/huihao";
			//数据库用户名
			String user = "root";
			//数据库密码
			String password = "123456";
			//得到数据库连接
			con = DriverManager.getConnection(url,user,password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con = null;
		}
		
		return con;
	}

}
