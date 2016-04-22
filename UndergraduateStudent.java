//Name: Michael Chan-Pong
//UFL ID: chenshiming
//Section: 9509
//Project Number: 4
//Brief description of file contents: Undergraduate Student
public class UndergraduateStudent extends Student {
	//Major data field
	String major;
	
	//Constructor
	public UndergraduateStudent(String n, int id, String major){
		super(n, id);
		this.major = major;
	}
	
	//Getter and setter methods
	public String getMajor(){
		return major;
	}
	public void setMajor(String major){
		this.major = major;
	}
	
	//toString override
	public String toString(){
		StringBuilder undergradStudentInfo = new StringBuilder();
		undergradStudentInfo.append(super.toString());
		undergradStudentInfo.insert(0, "Undergraduate Student ");
		undergradStudentInfo.append("\nMajor: " + major);
		return undergradStudentInfo.toString();
	}
}
