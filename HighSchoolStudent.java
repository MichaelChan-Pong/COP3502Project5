//Name: Michael Chan-Pong
//UFL ID: chenshiming
//Section: 9509
//Project Number: 4
//Brief description of file contents: High school student class
public class HighSchoolStudent extends Student {
	//School name data field
	String nameOfSchool;
	
	//Constructor
	public HighSchoolStudent(String n, int id, String schoolName){
		super(n, id);
		nameOfSchool = schoolName;
	}
	
	//Getter and setter methods
	public String getNameOfSchool(){
		return nameOfSchool;
	}
	public void setNameOfSchool(String schoolName){
		nameOfSchool = schoolName;
	}
	
	//toString override
	public String toString(){
		StringBuilder highSchoolStudentInfo = new StringBuilder();
		highSchoolStudentInfo.append(super.toString());
		highSchoolStudentInfo.insert(0, "High School Student ");
		highSchoolStudentInfo.append("\nName of School: " + nameOfSchool);
		return highSchoolStudentInfo.toString();
	}
}
