//Name: Michael Chan-Pong
//UFL ID: chenshiming
//Section: 9509
//Project Number: 4
//Brief description of file contents: Student Class
public class Student {
	//Name and ID number data fields
	String name;
	int idNumber;
	
	//Constructor
	public Student(String n, int id){
		name = n;
		idNumber = id;
	}
	
	//Getter and setter methods
	public String getName(){
		return name;
	}
	public void setName (String n){
		name = n;
	}
	public String getIDNumber(){
		return Integer.toString(idNumber);
	}
	public void setIDNumber(int id){
		idNumber = id;
	}
	
	//toString override
	public String toString(){
		StringBuilder studentInfo = new StringBuilder();
		studentInfo.append(name + "\n" + "Student ID: " + Integer.toString(idNumber));
		return studentInfo.toString();
	}
}
