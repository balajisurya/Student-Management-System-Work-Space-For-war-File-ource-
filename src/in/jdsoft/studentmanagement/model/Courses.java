package in.jdsoft.studentmanagement.model;

public class Courses {

	public  String courseName=null;
	public  int durationInYears;
	public   int durationInSemester;
	public  int courseId;
	public String courseDescription=null;
	public String courseImage=null;
	
	
	
	public  int getCourseId() {
		return courseId;
	}
	
	public  void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public   String getCourseName() {
		return courseName;
	}
	
	public  void  setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	public String getCourseDescription() {
		return courseDescription;
	}
	
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	
	public  int getDurationInSemester() {
		return durationInSemester;
	}
	
	public  void setDurationInSemester(int durationInSemester) {
		this.durationInSemester = durationInSemester;
	}
	
	public  int getDurationInYears() {
		return durationInYears;
	}
	
	public  void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}
	
     public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}
     
     public String getCourseImage() {
		return courseImage;
	}
}
