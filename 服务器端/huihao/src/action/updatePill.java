package action;

import myutils.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myutils.UserUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import actionForm.RegisterPill;

public class updatePill extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm Form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RegisterPill raf=(RegisterPill)Form;
		String medId=StringUtils.StringEcode(raf.getMedId());
		String medName=StringUtils.StringEcode(raf.getMedName());
		String medClass=StringUtils.StringEcode(raf.getMedClass());
		 String medPrice=StringUtils.StringEcode(raf.getMedPrice());
		 String medDate=StringUtils.StringEcode(raf.getMedDate());
		String medSpecification=StringUtils.StringEcode(raf.getMedSpecification());
		String medIntroducation=StringUtils.StringEcode(raf.getMedIntroducation());
		String medStock=StringUtils.StringEcode(raf.getMedStock());
		String medBeenSold=StringUtils.StringEcode(raf.getMedBeenSold());
		String medDiscount=StringUtils.StringEcode(raf.getMedDiscount());
			boolean ok = UserUtils.updatePill(medId, medName,medClass,medPrice,medDate,medSpecification,medIntroducation,medStock,medBeenSold,medDiscount);
			if(ok){
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		}
		
		
	}
	

