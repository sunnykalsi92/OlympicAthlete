/*
 * Sunny Kalsi		Lab 4
 * This purpose of this program is to work with a list of athlete objects
 * This client code does the following
 * asks the user for the file name that it's going to be working with
 * then it gives them a menu of options from which the user can choose from
 * after completing the menu option the program gives a prompt to ask if 
 * the user wants to end the program or not, if not the menu reruns
 * but the user does not have to re-enter the filename and doesn't lose any
 * information that they either added or removed.
 */


import java.util.*;
import java.io.*;

public class clientCode {
	
	public static void main(String[] args) throws IOException {
		String fileName;	//stores the name of the file we will be working with
		int counter = 2;	//counter to make sure the user enters a valid file
		String response = "n";	//need to make response n to start so the loop works.	
		
		//our base list, when we add or remove from the list, the program does it to this list
		//and then it can be coppied to whatever order the user chooses.
		OlympicAthlete aList = new OlympicAthlete();
		
		Scanner in = new Scanner(System.in);
		//get the file name, make sure it works
		while(counter ==2) {
			try {
				System.out.println("Enter the file name");
				fileName = in.next();
				File file = new File(fileName);
		   		Scanner reader = new Scanner(file);
				if (reader != null) {
					//if the reader isn't null, we start building the first list
					build(aList, reader);
					//make counter = 1 so we can leave this loop.
					counter =1;
					//start of the main "menu" loop
					while (response.equalsIgnoreCase("n")) {
						//prompts menu method
						menuOptions(aList);
						//asks the user if they want to terminate the program.
						System.out.println("Do you want to exit the program? (y/n)");
						response=in.next();
						//if the user types anything other then "n" the loop ends and the program terminates
						if(!response.equalsIgnoreCase("n") && !response.equalsIgnoreCase("y")){
							System.out.println("**You've entered an invalid response, the program will now terminate.**");
							response = "y";
						}
					}
				}
				//catch any errors while reading the file.
			}catch (Exception E) {
				System.out.println("You entered an invalid file, please try again.");
			}
			
				
		}
		
	}
	/*
	 * This is the menu method, it holds all the options for the menu so
	 * and holds all the code that will be triggered once the user selects
	 * a particular option. 	
	 */
	public static void menuOptions(OlympicAthlete list) {
		int error = 2;			//variable for the while loop
		while (error ==2) {		//while loop makes sure that the user doesn't select a non int input
		try {
			Scanner in = new Scanner(System.in);
			int menuOpt;		//holds user input
			@SuppressWarnings("unused")
			String dummy;		//dummy is here so we can empty out the line after the int is entered.
				System.out.println("Please select from the following options."
						+ "\n1) Add an athlete.\n2) Delete an athlete."
						+"\n3) Print all athletes. \n4) Print a particular athlete."
						+"\n5) Print a event.\n6) Print medal winners by country."
						+"\n7) Print medal winners of an event.\n8) Print medal winners by medal"
						+"\n9) Print all Athletes in the List in alphabetical order by Athlete name."
						+"\n10) Print all Athletes in the List in alphabetical order by event."
						+"\nEnter option 1-10");
				menuOpt = in.nextInt();
				error =1;		//adjusted variable for the while loop so we can exit the loop.
				dummy = in.nextLine();
				if(menuOpt == 1) {
					//this option is for when the user wants to add an athlete to the list
					//calls the list that we took in and runs the add method from the OlympicAthlete class
					//also uses the createAthlete function to get all the info to create the Athlete object
					list.add(createAthlete());
				}else if(menuOpt ==2) {
					//This option is for when the user wants to delete an athlete from the list
					//calls the list to run the default remove methods, and passes an Athlete object
					//that is return by the athleteName function which creates the Athlete by asking
					//us for the first and last name
					list.remove(athleteName());
				}else if(menuOpt ==3) {
					//iterates through the list and prints it
					list.printList();
				}else if(menuOpt ==4) {
					//this part searches for a particular athlete which we create by asking for the first name
					//last name and the country, then searches for the athlete throughout the list.
					//this can print multiple times because we can have more then one entry per Athlete.
					list.printAthlete(athleteCountry());
				}else if(menuOpt ==5) {
					//asks for the name of the event and prints all athletes that took part in that event
					System.out.println("Enter the name of the event.");
					String dummy1 = in.nextLine();
					list.printEvent(dummy1);
				}else if(menuOpt ==6) {
					//Asks for the name of the country and prints all athletes from that country that won a medal
					System.out.println("Enter the name of the country.");
					String dummy1 = in.nextLine();
					list.printCountryWinners(dummy1);
				}else if(menuOpt ==7) {
					//Prints all the medal winners for a certain event.
					System.out.println("Enter the name of the event.");
					String dummy1 = in.nextLine();
					list.printEventWinners(dummy1);
				}else if(menuOpt ==8) {
					//asks for the type of medal and searches for all athletes that won that medal.
					System.out.println("Enter the medal you want to search for."
							+"\n1) Gold\n2) Silver\n3) Bronze\nEnter 1 2 or 3.");
					int result = in.nextInt();
					list.printMedals(result);
					//ask the user what medal, and print the winners of those medals
					
				}else if(menuOpt ==9) {
					//prints out a list in order of last name and first name
					list.printByName();
				}else if(menuOpt ==10) {
					//prints out the list in order of event.
					list.printByEvent();
					
				}else System.out.println("You selected an invalid option.");
		}
		catch(Exception e) {
			//if the user didn't enter an int at all, give them this error message
			System.out.println("**You've entered an invalid option, please try again.**");
		}
		}
		}
	//to save time this method takes in a list and an Scanner object and creates Athlete objects and adds them to the list
	public static void build(OlympicAthlete list,Scanner reader) {
		String first;		//first name
		String last;		//last name
		String country;		//Country
		String sport;		//sport
		String event;		//Event
		int result;			//results
		@SuppressWarnings("unused")
		String dummy;		//dummy value to reader after reading an int
		Athlete athlete;	//actual object
		
		//read the first line like any normal document
		if(reader.hasNextLine()) {
			first = reader.nextLine();
			last = reader.nextLine();
			country = reader.nextLine();
			sport = reader.nextLine();
			event = reader.nextLine();
			result = reader.nextInt();
			//creates the Athlete object
			athlete = new Athlete(first,last,country,sport,event,result);
			//adds the athlete to the list
			list.add(athlete);
		}
		//now because the last part we read was a int, there is a empty line in the reader
		//so the remainder of the document will be read by this loop.
		while(reader.hasNextLine()){
			dummy = reader.nextLine();	//clear out the empty line
			first = reader.nextLine();	//everything else runs as normal
			last = reader.nextLine();
			country = reader.nextLine();
			sport = reader.nextLine();
			event = reader.nextLine();
			result = reader.nextInt();
			athlete = new Athlete(first,last,country,sport,event,result);
			list.add(athlete);
		}
		
	}
	
	//asks the user for all the info the athlete then returns it as an athlete object
	public static Athlete createAthlete() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first name");
		String first = in.nextLine();
		System.out.println("Enter the last name.");
		String last = in.nextLine();
		System.out.println("Enter the country.");
		String country = in.nextLine();
		System.out.println("Enter the sport.");
		String sport = in.nextLine();
		System.out.println("Enter the event.");
		String event = in.nextLine();
		System.out.println("Enter the result.");
		int result = in.nextInt();
		return new Athlete(first,last,country,sport,event,result);
	}
	//returns an Athlete object with just the name.
	public static Athlete athleteName() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first name.");
		String first= in.nextLine();
		System.out.println("Enter the last name.");
		String last= in.nextLine();
		return new Athlete(first,last,"","","",0);
	}
	
	//returns an athlete object with the name and country.
	public static Athlete athleteCountry() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first name.");
		String first= in.nextLine();
		System.out.println("Enter the last name.");
		String last= in.nextLine();
		System.out.println("Enter the country");
		String country = in.nextLine();
		return new Athlete(first,last,country,"","",0);
	}
}
