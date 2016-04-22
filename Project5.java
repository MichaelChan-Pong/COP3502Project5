//Name: Michael Chan-Pong
//UFL ID: chenshiming
//Section: 9509
//Project Number: 4
//Brief description of file contents: Essay checker main class
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Project5 {
	public static void main(String[] args){
		Scanner console = new Scanner(System.in); //Create Scanner to receive user input
		Scanner input; //Create Scanner to read text files
		ArrayList<String> dictionaryContents = new ArrayList<String>(); //Create ArrayList to store contents of essay file
		//Attempt to open dictionary file and save each word to an ArrayList
		boolean worked = false;
		while(worked == false){
			//Import dictionary file to ArrayList
			System.out.println("Please specify the name of the file that contains the dictionary information.");
			String dicNameNoExt = console.nextLine();
			String dicName = dicNameNoExt + ".txt";
			File file = new File(dicName);
			try{
				input = new Scanner(file);
				while (input.hasNextLine()){
					String line = input.nextLine();
					dictionaryContents.add(line);
				}
				System.out.println("File opened successfully!");
				worked = true;
				input.close();
			}
			catch(FileNotFoundException ex){
				System.out.println("File " + dicNameNoExt + " cannot be found!");
			}
		}
		ArrayList<String> essayContents = new ArrayList<String>(); //Create ArrayList to store contents of essay file
		//Attempt to open essay file and save each word to an ArrayList
		boolean done = false;
		File file = null;
		while(done == false){
			System.out.println("Please specify the ID of the student whose essay will be graded.");
			String uflID = null;
			uflID = console.next();
			if(uflID.equals("Finished") == false){
				//Open essay file
				String essayFile = uflID + ".txt";
				file = new File(essayFile);
				boolean found = false;
				essayContents.clear();
				try{
					input = new Scanner(file);
					while (input.hasNextLine()){
						String line = input.nextLine();
						essayContents.add(line);
					}
					System.out.println("File opened successfully!");
					input.close();
					found = true;
				}
				catch(FileNotFoundException ex){
					System.out.println("File " + uflID + " cannot be found!");
				}
				if(found == true){
					//If file is found, process the essay depending on the type of student
					GraduateStudent gradStudent = null;
					UndergraduateStudent undergradStudent = null;
					HighSchoolStudent highSchoolStudent = null;
					if(essayContents.get(0).equals("Graduate Student")){
						//If student is a graduate student, process the essay as such
						gradStudent = new GraduateStudent(essayContents.get(1), Integer.parseInt(essayContents.get(2)), essayContents.get(3), essayContents.get(4));
						processEssay(dictionaryContents, uflID, essayContents, gradStudent);
					}
					else if(essayContents.get(0).equals("Undergraduate Student")){
						//If student is an undergraduate student, process the essay as such
						undergradStudent = new UndergraduateStudent(essayContents.get(1), Integer.parseInt(essayContents.get(2)), essayContents.get(3));
						processEssay(dictionaryContents, uflID, essayContents, undergradStudent);
					}
					else if(essayContents.get(0).equals("HighSchool Student")){
						//If student is a high school student, process the file as such
						highSchoolStudent = new HighSchoolStudent(essayContents.get(1), Integer.parseInt(essayContents.get(2)), essayContents.get(3));
						processEssay(dictionaryContents, uflID, essayContents, highSchoolStudent);
					}
				}
			}
			else{
				//If "Finished" is entered, end program
				done = true;
			}
		}
		console.close();
	}
	public static ArrayList<String> spellCheck(ArrayList<String> dictionaryContents, ArrayList<String> essayWords){
		//Compare ArrayList of essay's words to the ArrayList of dictionary words, and return an Arraylist of misspelled words
		ArrayList<String> misspelledWords = new ArrayList<String>();
		for(int i = 0; i < essayWords.size(); i++){
			boolean found = false;
			for (int j = 0; j < dictionaryContents.size(); j++){
				if(essayWords.get(i).compareToIgnoreCase(dictionaryContents.get(j)) == 0){
					found = true;
				}
			}
			if(found == false){
				misspelledWords.add(essayWords.get(i));
			}
		}
		return misspelledWords;
	}
	public static StringBuilder modifyWord(StringBuilder essayLine){
		//Strips line from essay of possessive modifiers, commas, and periods
		essayLine = new StringBuilder(essayLine.toString().replaceAll("[^a-zA-Z' ]", ""));
		return essayLine;
	}
	public static ArrayList<String> duplicateChecker(ArrayList<String> misspelledWords){
		for(int i = 0; i < misspelledWords.size(); i++){
			for (int j = i + 1; j < misspelledWords.size(); j++){
				if(misspelledWords.get(i).compareToIgnoreCase(misspelledWords.get(j)) == 0){
					misspelledWords.remove(i);
					i--;
				}
			}
		}
		return misspelledWords;
	}
	public static String listMisspelled(ArrayList<String> misspelledWords){
		//Converts the ArrayList of misspelled words into a string in the form of a list
		StringBuilder list = new StringBuilder();
		for(int i = 0; i < misspelledWords.size(); i++){
			list.append("\n(" + (i + 1) + ")" + misspelledWords.get(i));
		}
		return list.toString();
	}
	public static void processEssay(ArrayList<String> dictionaryContents, String uflID, ArrayList<String> essayContents, Student student){
		//Process the essay according to the type of student and output a text file with the spell check report
		ArrayList<String> essayWords = new ArrayList<String>();
		ArrayList<String> misspelledWords = new ArrayList<String>();
		if (student instanceof GraduateStudent){
			for(int i = 0; i < 5; i++){
				//Remove header for graduate students
				essayContents.remove(0);
			}
		}
		else{
			for(int i = 0; i < 4; i++){
				//Remove header for non-graduate students
				essayContents.remove(0);
			}
		}
		for(int i = 0; i < essayContents.size(); i++){
			//Create ArrayList of words in a line of the essay
			StringBuilder essayLine = new StringBuilder(essayContents.get(i));
			essayLine = modifyWord(essayLine);
			for(int j = 0; j < essayLine.length(); j++){
				if(essayLine.charAt(j) == ' '){
					essayWords.add(essayLine.substring(0, j));
					essayLine.delete(0, j + 1);
					j = 0;
				}
			}
			//Take in remainder of line
			essayWords.add(essayLine.substring(0, essayLine.length()));
			//Check if the words are spelled correctly
			misspelledWords.addAll(spellCheck(dictionaryContents, essayWords));
			misspelledWords = duplicateChecker(misspelledWords);
			essayWords.clear();
		}
		//Calculate grade
		int grade = 100;
		if (student instanceof GraduateStudent)
			grade = grade - misspelledWords.size() * 5;
		else if (student instanceof UndergraduateStudent)
			grade = grade - misspelledWords.size() * 3;
		else if (student instanceof HighSchoolStudent){
			grade = grade - misspelledWords.size();
		}
		//Ensure grade does not fall under 0 points
		if (grade < 0)
			grade = 0;
		//Begin outputting results
		File outputFile = new File(uflID + "_graded.txt");
		if(outputFile.exists()){
			outputFile.delete();
		}
		//Convert ArrayList of misspelled words to a string
		String listMisspelled = listMisspelled(misspelledWords);
		try {
			PrintWriter out = new PrintWriter(new FileWriter(uflID + "_graded.txt", true), true);
			out.print(student + "\nReceived a score of " + grade + " with " + misspelledWords.size() + " misspelled words:" + listMisspelled);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		misspelledWords.clear();
	}
}
