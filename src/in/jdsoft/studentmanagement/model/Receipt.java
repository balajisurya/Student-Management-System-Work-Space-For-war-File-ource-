package in.jdsoft.studentmanagement.model;

import java.sql.Timestamp;

public class Receipt {

	public int receiptId;
	public int studentId;
	public int semester;
	public double totalFees;
	public double pendingFees;
	public int status;
	public Timestamp createdDate;
	
	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
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
	public double getTotalFees() {
		return totalFees;
	}
	public void setTotalFees(double totalFees) {
		this.totalFees = totalFees;
	}
	public double getPendingFees() {
		return pendingFees;
	}
	public void setPendingFees(double pendingFees) {
		this.pendingFees = pendingFees;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
