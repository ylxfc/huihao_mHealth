package actionForm;

import org.apache.struts.action.ActionForm;

public class RegisterPill extends ActionForm {
	String medId,medName,medClass,medPrice,medDate,medSpecification,medIntroducation,medStock,medBeenSold,medDiscount;

	public String getMedId() {
		return  medId;
	}

	public void setMedId(String  medId) {
		this.medId =  medId;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getMedClass() {
		return medClass;
	}

	public void setMedClass(String medClass) {
		this.medClass = medClass;
	}

	public String getMedPrice() {
		return medPrice;
	}

	public void setMedPrice(String medPrice) {
		this.medPrice = medPrice ;
	}
	public String getMedIntroducation() {
		return medIntroducation;
	}

	public void setMedIntroducation(String medIntroducation) {
		this.medIntroducation = medIntroducation ;
	}
	
	public String getMedSpecification() {
		return medSpecification;
	}
	public void setMedSpecification(String medSpecification) {
		this.medSpecification = medSpecification;
	}
	public String getMedDate() {
		return medDate;
	}

	public void setMedDate(String medDate) {
		this.medDate = medDate ;
	}
	public String getMedStock() {
		return medStock;
	}

	public void setMedStock(String medStock) {
		this.medStock = medStock ;
	}
	public String getMedBeenSold() {
		return medBeenSold;
	}

	public void setMedBeenSold(String medBeenSold) {
		this.medBeenSold = medBeenSold ;
	}
	public String getMedDiscount() {
		return medDiscount;
	}

	public void setMedDiscount(String medDiscount) {
		this.medDiscount =  medDiscount;
	}
}

