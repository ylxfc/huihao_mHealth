package action;

import myutils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myutils.UserUtils;
import java.sql.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.RegisterActionForm;

public class RegisterAction extends Action {
	
	/**
	 * 注册用户
	 * 先查询用户是否存在
	 * 根据查询结果来处理是否执行注册
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RegisterActionForm raf=(RegisterActionForm)form;
		String serId=StringUtils.StringEcode(raf.getSerId());
		String serPassword=StringUtils.StringEcode(raf.getSerPassword());
		String serName=StringUtils.StringEcode(raf.getSerName());
		 String serSex=StringUtils.StringEcode(raf.getSerSex());
		 String serAge=StringUtils.StringEcode(raf.getSerAge());
		 String serPhone=StringUtils.StringEcode(raf.getSerPhone());
		String medId=StringUtils.StringEcode(raf.getMedId());
		String havSignedAgency=StringUtils.StringEcode(raf.getHavSignedAgency());
		String havVisited=StringUtils.StringEcode(raf.getHavVisited());
		boolean isExistingUser=UserUtils.isExistingUser(serId);
		if(isExistingUser){
			return mapping.findForward("userExisting");
		}else{
			boolean ok = UserUtils.addUser(serId, serPassword,serName,serSex,serAge,serPhone,medId,havSignedAgency,havVisited);
			if(ok){
				 Connection conn=ConnectionUtils.getConnection();
				 Statement stmt =conn.createStatement();
				 String tabelname="workplan"+serId;
				 String sql = "create table "+ tabelname+"(ageName varchar(50),"+"date varchar(20),"+"visited varchar(2),"+"preorder varchar(20),"+"medName varchar(20),"+"preNumber varchar(20))";
			     stmt.executeUpdate(sql);
				 stmt.close();
	      		 conn.close();
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		}
		
		
	}
	

}
