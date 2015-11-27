package in.jdsoft.studentmanagement.model;

public class FeesTemplate {

	 public int templateId;
	 public String templateName;
	 public String templateItemIds;
	 public double templateFee;
	 
	 public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	 
	 public int getTemplateId() {
		return templateId;
	}
	 
	 public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	 
	 public String getTemplateName() {
		return templateName;
	}
	 
	 public void setTemplateItemIds(String templateItemIds) {
		this.templateItemIds = templateItemIds;
	}
	 
	 public String getTemplateItemIds() {
		return templateItemIds;
	}
	 
	 public void setTemplateFee(double templateFee) {
		this.templateFee = templateFee;
	 }
	 
	 public double getTemplateFee() {
		return templateFee;
	}

}
