package action;

import myutils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myutils.UserUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.RegisterActionForm;

public class updateUserAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm Form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RegisterActionForm raf=(RegisterActionForm)Form;
		String serId=StringUtils.StringEcode(raf.getSerId());
		String serPassword=StringUtils.StringEcode(raf.getSerPassword());
		String serName=StringUtils.StringEcode(raf.getSerName());
		 String serSex=StringUtils.StringEcode(raf.getSerSex());
		 String serAge=StringUtils.StringEcode(raf.getSerAge());
		 String serPhone=StringUtils.StringEcode(raf.getSerPhone());
		String medId=StringUtils.StringEcode(raf.getMedId());
		String havSignedAgency=StringUtils.StringEcode(raf.getHavSignedAgency());
		String havVisited=StringUtils.StringEcode(raf.getHavVisited());
			boolean ok = UserUtils.updateUserMess(serId, serPassword,serName,serSex,serAge,serPhone,medId,havSignedAgency,havVisited);
			if(ok){
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		}
		
		
	}
	

