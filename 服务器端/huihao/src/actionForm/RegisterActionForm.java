package actionForm;

import org.apache.struts.action.ActionForm;

public class RegisterActionForm extends ActionForm {
	String serId,serPassword,serName,serSex,serPhone,medId,serAge,havSignedAgency,havVisited;

	public String getSerId() {
		return  serId;
	}

	public void setSerId(String  serId) {
		this.serId =  serId;
	}

	public String getSerPassword() {
		return serPassword;
	}

	public void setSerPassword(String serPassword) {
		this.serPassword = serPassword;
	}

	public String getSerName() {
		return serName;
	}

	public void setSerName(String serName) {
		this.serName = serName;
	}

	public String getSerSex() {
		return serSex;
	}

	public void setSerSex(String serSex) {
		this.serSex = serSex ;
	}
	public String getSerAge() {
		return serAge;
	}

	public void setSerAge(String serAge) {
		this.serAge = serAge ;
	}
	public String getSerPhone() {
		return serPhone;
	}

	public void setSerPhone(String serPhone) {
		this.serPhone = serPhone ;
	}
	public String getMedId() {
		return medId;
	}
	public void setMedId(String medId) {
		this.medId = medId ;
	}
	public String getHavSignedAgency() {
		return havSignedAgency;
	}
	public void setHavSignedAgency(String havSignedAgency) {
		this.havSignedAgency = havSignedAgency;
	}
	public String getHavVisited() {
		return havVisited;
	}
	public void setHavVisited(String havVisited) {
		this.havVisited = havVisited ;
	}
}
