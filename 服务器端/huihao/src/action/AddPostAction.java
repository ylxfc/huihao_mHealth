package action;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myutils.SqlUtils;
import myutils.StringUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.AddNewsActionForm;

public class AddPostAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			AddNewsActionForm anaf = (AddNewsActionForm)form;
			String id = StringUtils.StringEcode(anaf.getId());
			String text = StringUtils.StringEcode(anaf.getText());
			String serId = StringUtils.StringEcode(anaf.getSerId());
			String date = "";
			
			//生成SQL格式时间
			Calendar c = Calendar.getInstance();
			long l = c.getTimeInMillis();
			Date d = new Date(l);
			Time t = new Time(l);
			String a=d.toString();
			a=a.substring(5);
			date = a+" "+t.toString();
			date=date.substring(0,11);
			
			String sql = "insert into postinfo (id,text,date,serId)values(?,?,?,?)";
			List<String> parameters = new ArrayList<String>();
			parameters.add(id);
			parameters.add(text);
			parameters.add(date);
			parameters.add(serId);
			int count = SqlUtils.ZSGSql(sql, parameters);
			if(count>0){		
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		}	

}


