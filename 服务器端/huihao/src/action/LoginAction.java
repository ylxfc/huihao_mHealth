package action;
import actionForm.LoginActionForm;
import myutils.StringUtils;
import myutils.UserUtils;
import mybean.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginActionForm laf = (LoginActionForm)form;
		String type = StringUtils.StringEcode(laf.getType());
		String username = StringUtils.StringEcode(laf.getUsername());
		String password = laf.getPassword();
		String code = laf.getCode();
		HttpSession session = request.getSession(true);
		String codeTrue=(String)session.getAttribute("code");
		if(!codeTrue.equalsIgnoreCase(code)){
			session.invalidate();
			return mapping.findForward("codeError");
		}else{
			
			
			boolean ok = UserUtils.loginUser(type,username, password);
			if(ok){
				UserLogin login = new UserLogin();
				login.setSerId(username);
				login.setSerPassword(password);
				login.setType(type);
				session.setAttribute("login", login);
				return mapping.findForward("success");
			}else{
				session.invalidate();
				return mapping.findForward("error");
			}
		}
		
	}	
}
