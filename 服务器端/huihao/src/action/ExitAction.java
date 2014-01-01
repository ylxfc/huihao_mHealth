package action;

import mybean.UserLogin;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExitAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
		UserLogin login = (UserLogin)session.getAttribute("login");
		if(login==null) return mapping.findForward("success");
		else {
			session.invalidate();
			return mapping.findForward("success");
		}
	}	
}
