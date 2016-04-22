//Name: Michael Chan-Pong
//UFL ID: chenshiming
//Section: 9509
//Project Number: 4
//Brief description of file contents: Graduate student class
public class GraduateStudent extends Student{
	//Major and advisor data fields
	String major;
	String advisor;
	
	//Constructor
	public GraduateStudent (String n, int id, String major, String advisor){
		super(n, id);
		this.major = major;
		this.advisor = advisor;
	}
	
	//Getter and setter methods
	public String getMajor(){
		return major;
	}
	public void setMajor(String major){
		this.major = major;
	}
	public String getAdvisor(){
		return advisor;
	}
	public void setAdvisor(String advisor){
		this.advisor = advisor;
	}
	
	//toString override
	public String toString(){
		StringBuilder gradStudentInfo = new StringBuilder();
		gradStudentInfo.append(super.toString());
		gradStudentInfo.insert(0, "Graduate Student ");
		gradStudentInfo.append("\nMajor: " + major + "\nAdvisor: " + advisor);
		return gradStudentInfo.toString();
	}
}
