package action;
import myutils.StringUtils;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.PillBean;
import myutils.SqlUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeletePillAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		HttpSession session = request.getSession(true);
		String id = StringUtils.StringEcode(request.getParameter("id"));
		String sql = "delete from medicine where medId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(id);
		int count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0){
			PillBean puser = null;
			puser = (PillBean)session.getAttribute("puser");
			int pageSize = puser.getPageSize();
			int currentPage = puser.getCurrentPage();			
			puser = new PillBean();
			puser.setPageSize(pageSize);
			int pageCount = puser.getPageCount();
			if(currentPage>pageCount) puser.setCurrentPage(pageCount);
			else puser.setCurrentPage(currentPage);					
			session.setAttribute("puser", puser);
			return mapping.findForward("success");
		}else{
			return mapping.findForward("error");
		}		
	}
}

