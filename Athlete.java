/*
 * Sunny Kalsi					Lab 4
 * This class is to create the athlete object. it is then used by the olympicAthlete list
 * this class has many comparators that sort athlete by different means
 */
import java.util.*;
public class Athlete implements Comparable<Athlete> {


		private String firstName;		//first name
		private String lastName;		//last name
		private String country;			//country
		private String sport;			//sport
		private String event;			//event
		private int result;				//result
		
		// default constructor
		Athlete() {}

		// initializing constructor
		Athlete(String first, String last, String country, String sport, String event,
				int result) {
			firstName = first;
			lastName = last;
			this.country = country;
			this.sport = sport;
			this.event = event;
			this.result = result;
		}
		
		//default compareTo method that compares by country and then by name
		//since this class implements comparable we have to have a compareTo method.
		public int compareTo(Athlete o){
				Athlete athlete = (Athlete) o;
				if(this.country.equals(o.country)) {
					if(this.lastName.equals(athlete.lastName)){
						if(this.firstName.equals(athlete.firstName)){
							return 0;
						}
						else return this.firstName.compareTo(athlete.firstName);
					}
					else return this.lastName.compareTo(athlete.lastName);
				}else return this.country.compareTo(o.country);	
		}
		//compares two events
		public int compareEvent(String o) {
			return this.event.compareTo(o);
		}
		//compares two countries
		public int compareCountry(String o) {
			return this.country.compareTo(o);
		}
		//returns the results
		public int getResult() {
			return this.result;
			
		}
		//to string to make it look nice and to break each Athlete into their own block
		public String toString(){
			String a = ("Name: "+firstName+" "+lastName+
					"\nCountry: "+country+"\nSport: "+sport+"\nEvent: "+event
					+"\nResult: "+result+"\n_______________________________");
			return a;
		}
		//comparator that sorts by last name first and then by first name
		public static Comparator<Athlete> nameComparator(){
			return new Comparator<Athlete>(){
				public int compare(Athlete a1, Athlete a2){
					if(a1.lastName.equals(a2.lastName)){
						if(a1.firstName.equals(a2.firstName)){
							return 0;
						}
						else return a1.firstName.compareTo(a2.firstName);
					}
					else return a1.lastName.compareTo(a2.lastName);
				}
			};
			
		}
		//comparator that sorts by event
		public static Comparator<Athlete> eventComparator(){
			return new Comparator<Athlete>(){
				public int compare(Athlete a1, Athlete a2) {
					if(a1.event.equals(a2.event)){
							return 0;
					}
						else return a1.event.compareTo(a2.event);
				}
			
			};
		}
		
	}



