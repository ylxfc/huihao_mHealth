package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mybean.PostBean;
import myutils.StringUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManagePostAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(true);
			PostBean post = (PostBean)session.getAttribute("post");
			if(post==null){			
					post = new PostBean();
					session.setAttribute("post", post);
					return mapping.findForward("success");	
			}else{
				String which = request.getParameter("id");
				which = StringUtils.StringEcode(which);
				if(which.equals("previous")){
					if(post.getCurrentPage()==1){
						post.setCurrentPage(post.getPageCount());
					}else{
						post.setCurrentPage(post.getCurrentPage()-1);
					}
				}else if(which.equals("next")){
					if(post.getCurrentPage()==post.getPageCount()){
						post.setCurrentPage(1);
					}else{
						post.setCurrentPage(post.getCurrentPage()+1);
					}
				}		
				return mapping.findForward("success");		
			}
		
	
	}
}

