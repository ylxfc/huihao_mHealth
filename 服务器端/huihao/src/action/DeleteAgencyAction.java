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

public class DeleteAgencyAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		HttpSession session = request.getSession(true);
		String id = StringUtils.StringEcode(request.getParameter("id"));
		String sql = "delete from preorder where ageId = ?";
		List<String> parameters1 = new ArrayList<String>();
		parameters1.add(id);
		int count1 = SqlUtils.ZSGSql(sql, parameters1);
		String sql1 = "delete from agency where ageId = ?";
		List<String> parameters = new ArrayList<String>();
		parameters.add(id);
		int count = SqlUtils.ZSGSql(sql1, parameters);
		if(count>0&&count1>0){
			return mapping.findForward("success");
		}else{
			return mapping.findForward("error");
		}		
	}
}
