package action;
import myutils.StringUtils;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.PostBean;
import myutils.SqlUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeletePostAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		HttpSession session = request.getSession(true);
		String id = StringUtils.StringEcode(request.getParameter("id"));
		String sql = "delete from postinfo where id = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(id);
		int count = SqlUtils.ZSGSql(sql, parameters);
		if(count>0){
			PostBean post = null;
			post = (PostBean)session.getAttribute("post");
			int pageSize = post.getPageSize();
			int currentPage = post.getCurrentPage();			
			post = new PostBean();
			post.setPageSize(pageSize);
			int pageCount = post.getPageCount();
			if(currentPage>pageCount) post.setCurrentPage(pageCount);
			else post.setCurrentPage(currentPage);					
			session.setAttribute("post", post);
			return mapping.findForward("success");
		}else{
			return mapping.findForward("error");
		}		
	}
}
