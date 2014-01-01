package myutils;

import java.sql.*;
import java.util.*;
import com.sun.rowset.CachedRowSetImpl;

public class SqlUtils {
	
	/**
	 * 执行某一查询语句
	 * @param sql 执行的查询语句
	 * @param parameters sql语句要用到的参数
	 * @return 返回的查询结果
	 */
	public static CachedRowSetImpl querySql(String sql,List<String> parameters){
		CachedRowSetImpl cr = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = ConnectionUtils.getConnection();
		if(con!=null){
			try {
				ps = con.prepareStatement(sql);
				cr = new CachedRowSetImpl();
				if(parameters.size()<=0){
					rs = ps.executeQuery();
					cr.populate(rs);
				}else{
					for(int i=1;i<=parameters.size();i++){
						ps.setString(i, parameters.get(i-1));
					}
					rs = ps.executeQuery();
					cr.populate(rs);
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		return cr;
	}
	/**
	 * 增删改操作
	 * @param sql 将要执行的sql语句
	 * @param parameters 增删改的参数
	 * @return 成功执行的个数
	 */
	
	public static int ZSGSql(String sql,List<String> parameters){
		int count=0;
		Connection con = null;
		PreparedStatement ps = null;
		con = ConnectionUtils.getConnection();
		if(con!=null){
			try {
				ps = con.prepareStatement(sql);
				if(parameters.size()<=0){
					count = ps.executeUpdate();
				}else{
					for(int i = 1;i<=parameters.size();i++){
						ps.setString(i, parameters.get(i-1));
					}
					count = ps.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				count = -1;
			}
		}
		
		return count;
	}

}
