package in.jdsoft.studentmanagement.model;

public class StudentAcademic {
	public int studentId;
	public int courseId;
	public int semester;
	public String academicStatus;
	public int invoiceStatus;
	public int receiptId;
	public int academicId;
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public int getStudentId() {
		return studentId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setAcademicStatus(String academicStatus) {
		this.academicStatus = academicStatus;
	}
	
	public String getAcademicStatus() {
		return academicStatus;
	}
	
	public void setAcademicId(int academicId) {
		this.academicId = academicId;
	}
	
	public int getAcademicId() {
		return academicId;
	}

	public int getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(int invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceipId(int receiptId) {
		this.receiptId = receiptId;
	}

}
