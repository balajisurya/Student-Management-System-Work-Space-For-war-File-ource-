package in.jdsoft.studentmanagement.model;

import java.sql.Timestamp;

public class StudentFeesHistory {

	public int feesHistoryId;
	public int studentId;
	public int semester;
	public int receiptId;
	public  String templateItemName;
	public double templateItemAmount;
	public Timestamp createdDate;
	
	public int getFeesHistoryId() {
		return feesHistoryId;
	}
	public void setFeesHistoryId(int feesHistoryId) {
		this.feesHistoryId = feesHistoryId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	public String getTemplateItemName() {
		return templateItemName;
	}
	public void setTemplateItemName(String templateItemName) {
		this.templateItemName = templateItemName;
	}
	public double getTemplateItemAmount() {
		return templateItemAmount;
	}
	public void setTemplateItemAmount(double templateItemAmount) {
		this.templateItemAmount = templateItemAmount;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
