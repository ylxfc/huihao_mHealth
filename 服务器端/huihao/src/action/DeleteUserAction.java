package action;
import myutils.StringUtils;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.UserBean;
import myutils.SqlUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeleteUserAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		HttpSession session = request.getSession(true);
		String id = StringUtils.StringEcode(request.getParameter("id"));
		String sql = "delete from servicer where serId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(id);
		int count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0){
			UserBean user = null;
			user = (UserBean)session.getAttribute("user");
			int pageSize = user.getPageSize();
			int currentPage = user.getCurrentPage();			
			user = new UserBean();
			user.setPageSize(pageSize);
			int pageCount = user.getPageCount();
			if(currentPage>pageCount) user.setCurrentPage(pageCount);
			else user.setCurrentPage(currentPage);					
			session.setAttribute("user", user);
			return mapping.findForward("success");
		}else{
			return mapping.findForward("error");
		}		
	}
}
