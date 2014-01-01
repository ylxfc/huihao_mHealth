package myutils;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpSession;

public class UserUtils {
	/**
	 * 查询将要添加的用户在数据库中是否已存在
	 * @param username 查询的用户名
	 * @return 查询结果，是否存在
	 */
	public static boolean isExistingUser(String serId){
		
		boolean ok=true;
		ResultSet rs;
		String sql="select * from servicer where serId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(serId);
		rs = SqlUtils.querySql(sql, parameters);
		if(rs==null){
			ok = false;
		}else{
			try {
				if(rs.next()){
					ok = true;
				}else{
					ok = false;
				}
			} catch (SQLException e) {
				ok = true;
			}
		}		
		return ok;		
	}
public static boolean isExistingPill(String medId){
		
		boolean ok=true;
		ResultSet rs;
		String sql="select * from medicine where medId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(medId);
		rs = SqlUtils.querySql(sql, parameters);
		if(rs==null){
			ok = false;
		}else{
			try {
				if(rs.next()){
					ok = true;
				}else{
					ok = false;
				}
			} catch (SQLException e) {
				ok = true;
			}
		}		
		return ok;		
	}
	/**
	 * 向数据库里添加一个用户
	 * @param username 将要添加的用户名字
	 * @param password 用户的密码
	 * @return 添加结果，是否成功
	 */
	public static boolean addUser(String serId,String  serPassword,String serName,String serSex,String serAge,String serPhone,String medId,String havSignedAgency,String havVisited){
		boolean ok = false;
		int count=0;
		String sql = "insert into servicer (serId,serPassword,serName,serSex,serAge,serPhone,medId,havSignedAgency,havVisited) values(?,?,?,?,?,?,?,?,?)";
		List<String> parameters = new ArrayList<String>();
		parameters.add(serId);
		parameters.add(serPassword);
		parameters.add(serName);
		parameters.add(serSex);
		parameters.add(serAge);
		parameters.add(serPhone);
		parameters.add(medId);
		parameters.add(havSignedAgency);
		parameters.add(havVisited);
		count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0) ok = true;
		
		return ok;		
	}
	public static boolean addPill(String medId,String  medName,String medClass,String medPrice,String medDate,String medSpecification,String medIntroducation,String medStock,String medBeenSold,String medDiscount){
		boolean ok = false;
		int count=0;
		String sql = "insert into medicine (medId,medName,medClass,medPrice,medDate,medSpecification,medIntroducation,medStock,medBeenSold,medDiscount) values(?,?,?,?,?,?,?,?,?,?)";
		List<String> parameters = new ArrayList<String>();
		parameters.add(medId);
		parameters.add(medName);
		parameters.add(medClass);
		parameters.add(medPrice);
		parameters.add(medDate);
		parameters.add(medSpecification);
		parameters.add(medIntroducation);
		parameters.add(medStock);
		parameters.add(medBeenSold);
		parameters.add(medDiscount);
		count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0) ok = true;
		
		return ok;		
	}
	
	public static boolean loginUser(String type,String username,String password){
		boolean ok = false;
		ResultSet rs = null;
		String sql = "";
		if(type.equals("user")){
			sql = "select * from servicer where serId = ? and serPassword = ?";
		}else if(type.equals("admin")){
			sql = "select * from admin where username = ? and password = ?";
		}else{
			ok = false;
			return ok;
		}
		
		List<String> parameters = new ArrayList<String>();
		parameters.add(username);
		parameters.add(password);
		rs = SqlUtils.querySql(sql, parameters);
		try {
			if(rs.next()){
				ok = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return ok;		
	}
	
	public static boolean updateUserMess(String serId,String serPassword,String serName,String serSex,String serAge,String serPhone,String medId,String havSignedAgency,String havVisited){
		int count=0;
		boolean ok = false;
		String sql = "update servicer set serPassword = ?,serName = ?,SerSex = ? ,serAge = ?,serPhone = ?, medId=? , havSignedAgency=?  ,havVisited=? where serId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(serPassword);
		parameters.add(serName);
		parameters.add(serSex);
		parameters.add(serAge);
		parameters.add(serPhone);
		parameters.add(medId);
		parameters.add(havSignedAgency);
		parameters.add(havVisited);
		parameters.add(serId);
		count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0) ok = true;
		
		return ok;	
	}
	public static boolean updateAgency(String serId,String serPassword,String serName,String serSex,String serAge){
		int count=0;
		boolean ok = false;
		String sql = "update agency set preNumber = ?,medName = ?,serId = ?,datetime = ? where ageName = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(serPassword);
		parameters.add(serName);
		parameters.add(serSex);
		parameters.add(serAge);
		parameters.add(serId);
		count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0) ok = true;
		
		return ok;	
	}
	public static boolean updatePill(String medId,String medName,String medClass,String medPrice,String medDate,String medSpecification,String medIntroducation,String medStock,String  medBeenSold,String  medDiscount){
		int count=0;
		boolean ok = false;
		String sql = "update medicine set medName = ?,medClass= ?,medPrice = ? ,medDate = ?, medSpecification=? ,medIntroducation=? ,medStock=? , medBeenSold=? , medDiscount=? where medId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(medName);
		parameters.add(medClass);
		parameters.add(medPrice);
		parameters.add(medDate);
		parameters.add(medSpecification);
		parameters.add(medIntroducation);
		parameters.add(medStock);
		parameters.add(medBeenSold);
		parameters.add(medDiscount);
		parameters.add(medId);
		count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0) ok = true;
		
		return ok;	
	}
	public static boolean updatePassword(String serId,String password){
		int count=0;
		boolean ok = false;
		String sql = "update servicer set  serPassword= ? where serId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(password);
		parameters.add(serId);
		count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0) ok = true;
		
		return ok;	
	}
	public static ResultSet queryUserMess(String username){
		ResultSet rs = null;
		String sql = "select * from user where username = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(username);
		
		rs = SqlUtils.querySql(sql, parameters);
		
		return rs;
	}
}
