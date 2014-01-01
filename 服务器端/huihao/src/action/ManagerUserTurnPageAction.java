package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mybean.UserLogin;
import mybean.UserBean;
import myutils.StringUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManagerUserTurnPageAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		UserLogin login = (UserLogin)session.getAttribute("login");
		String type = login.getType();
		String t1 = "admin";
		if(type.equals(t1)){
			UserBean user = (UserBean)session.getAttribute("user");
			if(user==null){			
					user = new UserBean();
					session.setAttribute("user", user);
					return mapping.findForward("success");	
			}else{
				String which = request.getParameter("id");
				which = StringUtils.StringEcode(which);
				if(which.equals("previous")){
					if(user.getCurrentPage()==1){
						user.setCurrentPage(user.getPageCount());
					}else{
						user.setCurrentPage(user.getCurrentPage()-1);
					}
				}else if(which.equals("next")){
					if(user.getCurrentPage()==user.getPageCount()){
						user.setCurrentPage(1);
					}else{
						user.setCurrentPage(user.getCurrentPage()+1);
					}
				}		
				return mapping.findForward("success");		
			}
		}else{
			return mapping.findForward("error");
		}
	
	}
}
