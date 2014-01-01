package actionForm;

import org.apache.struts.action.ActionForm;

public class AddNewsActionForm extends ActionForm {
	String id,text,serId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSerId() {
		return serId;
	}

	public void setSerId(String serId) {
		this.serId = serId;
	}

	
	
}
