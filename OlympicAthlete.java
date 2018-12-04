/*
 * 
 * Sunny Kalsi					Lab 4
 * This class contains the sorted list, it holds all the information for
 * the athletes, allows private methods that lets the client code add
 * remove, and print from the list.
 */
import java.util.*;   // Iterator, Comparator

public class OlympicAthlete {
	SortedABList<Athlete> aList = new SortedABList<Athlete>();
	//base list with natural order
	SortedABList<Athlete> eventList = new SortedABList<Athlete>(Athlete.eventComparator());
	//event list that is ordered by the event name
	SortedABList<Athlete> nameList = new SortedABList<Athlete>(Athlete.nameComparator());
	//name list that is ordered by the last name and the first name
	 Iterator<Athlete> athleteIter;
	 
	 //adds the athlete object passed. also adds them to the event list and name list
	 public void add(Athlete a){
	  aList.add(a);
	  eventList.add(a);
	  nameList.add(a);
	  }
	  //removed the athlete object passed from all three lists.
	 public void remove(Athlete a){
	  aList.remove(a);
	  eventList.remove(a);
	  nameList.remove(a);
	  }
	  //prints out the default "alist" in the natural order
	 public void printList(){
		 for(Athlete item : aList){
		 	System.out.println(item);
		 }
	 }
	 //iterates through the alist and finds a particular athlete
	 //keeps going until the end of the list.
	 public void printAthlete(Athlete a){
		 for(Athlete item : aList){
			 if(item.compareTo(a) == 0){
			 	System.out.println(item);
			 }
		 }
	 }
	 //iterates through the alist and trys to match the event
	 //if it matches it's printed out
	 public void printEvent(String event){
		 for(Athlete item : aList){
			 if(item.compareEvent(event)==0){
				 System.out.println(item);
			 }
		 }
	 }
	 //iterates through the alist and searches for a particular country
	 //if the country is matched it checks if the athlete was in the top 3
	 //if they were it prints them out
	public void printCountryWinners(String country){
		for (Athlete item: aList){
		if(item.compareCountry(country)==0) {
			if(item.getResult()<4) {
				System.out.println(item);
			}
		}
		}
	}
	//iterates through the alist and searches for a particular event
	//if the country is matched it checks if the athlete was in the top 3
	//if they were it prints them out
	public void printEventWinners(String event){
		for(Athlete item: aList){
		if(item.compareEvent(event)==0){
			if(item.getResult() <4){
			System.out.println(item);
			}
		}
		}
	}
	//iterates through the alist and searches for a particular result
	//if it matches it's printed out
	
	public void printMedals(int medal){
		for(Athlete item: aList){
			if(item.getResult() == medal){
				System.out.println(item);
			}
		}
	}
	//prints out the nameList which is ordered by last name and first name
	public void printByName(){
		for(Athlete item: nameList){
			System.out.println(item);
		}
	}
	//prints out the eventList which is ordered by event name
	public void printByEvent(){
		for(Athlete item: eventList){
			System.out.println(item);
		}
	}
}