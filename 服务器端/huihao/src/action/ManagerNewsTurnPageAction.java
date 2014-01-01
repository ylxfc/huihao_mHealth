package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mybean.PillBean;
import myutils.StringUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManagerNewsTurnPageAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
			PillBean puser = (PillBean)session.getAttribute("puser");
			if(puser==null){			
					puser = new PillBean();
					session.setAttribute("puser", puser);
					return mapping.findForward("success");	
			}else{
				String which = request.getParameter("id");
				which = StringUtils.StringEcode(which);
				if(which.equals("previous")){
					if(puser.getCurrentPage()==1){
						puser.setCurrentPage(puser.getPageCount());
					}else{
						puser.setCurrentPage(puser.getCurrentPage()-1);
					}
				}else if(which.equals("next")){
					if(puser.getCurrentPage()==puser.getPageCount()){
						puser.setCurrentPage(1);
					}else{
						puser.setCurrentPage(puser.getCurrentPage()+1);
					}
				}		
				return mapping.findForward("success");		
			}
		
	
	}
}

