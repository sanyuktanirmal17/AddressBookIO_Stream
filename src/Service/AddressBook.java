package Service;


	import java.util.ArrayList;
	import java.util.Comparator;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Scanner;
	import java.util.concurrent.atomic.AtomicInteger;
	import java.util.stream.Collectors;

	import Model.Person;

	public class AddressBook {
		
		 private static Scanner sc = new Scanner(System.in);
		 private ArrayList<Person> personList =null;
		 private Map<String,ArrayList<Person>> detail= new HashMap<>();
		 public String city;

		/**
		 *Display welcome message 
		 */
		private void displayWelcome() {
			System.out.println("Welcome to address book");
		}
		
		/**
		 * UC2 
		 * This method adds object person and its fields to ArrayList
		 */
		private void add() {
		
			 System.out.println("Add details in city you want?");
			 city =sc.next();
			 Person person = new Person();
			 System.out.println("First Name :");
			 person.setFirstName(sc.next());
			 System.out.println("Last name : ");
			 person.setLastName(sc.next());
			 System.out.println("Address :");
			 person.setAddress(sc.next());
			 System.out.println("City :");
			 person.setCity(sc.next());
			 System.out.println("State :");
			 person.setState(sc.next());
			 System.out.println("Zip :");
			 person.setZip(sc.next());
			 System.out.println("Phone :");
			 person.setPhone(sc.next());
			 System.out.println("Email :");
			 person.setEmail(sc.next());
			 
			 
			 if(detail.containsKey(city)) {
				 detail.get(city).add(person); 
			 }
			 else {
				 personList = new ArrayList<>();
				 personList.add(person);
				 detail.put(city, personList);
			 }
			 
		 }
		
		/**
		 * UC3
		 *  method finds person according to first name 
		 */
		private void edit() {
			System.out.println("Enter the city to which u want to edit person ");
			city=sc.next();
			String enteredName;
			System.out.println("Enter First name of contact to edit it ");
			enteredName=sc.next();
			for(int i=0;i<detail.get(city).size();i++)
			{
				if(detail.get(city).get(i).getFirstName().equals(enteredName))
				{
					int check=0;
					System.out.println("Person found , what do you want to edit ?");
					System.out.println("Enter\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Zip\n7.Phone\n8.Email");
					check=sc.nextInt();
					switch(check) {
					case 1:
						System.out.println("Enter new first name");
						detail.get(city).get(i).setFirstName(sc.next());
						break;
					case 2:
						System.out.println("Enter new last name");
						detail.get(city).get(i).setLastName(sc.next());
						break;
					case 3:
						System.out.println("Enter new Address");
						detail.get(city).get(i).setAddress(sc.next());
						break;
					case 4:
						System.out.println("Enter new city");
						detail.get(city).get(i).setCity(sc.next());
						break;
					case 5:
						System.out.println("Enter new state");
						detail.get(city).get(i).setState(sc.next());
						break;
					case 6:
						System.out.println("Enter new zip");
						detail.get(city).get(i).setZip(sc.next());
						break;
					case 7:
						System.out.println("Enter new phone number");
						detail.get(city).get(i).setPhone(sc.next());
						break;
					case 8:
						System.out.println("Enter new email");
						detail.get(city).get(i).setEmail(sc.next());
						break;
					default :
						System.out.println("Invalid Entry");
							
					}
				}
			}
			
			
		}
		

		/**
		 * UC4
		 *  removes person from list according to first name.
		 */
		private void delete() {
			System.out.println("Enter First name  to delete  ");
			String enteredName=sc.next();
			for(int i=0;i<detail.get(city).size();i++)
			{
				if(detail.get(city).get(i).getFirstName().equals(enteredName))
					detail.get(city).remove(i);
			}
			System.out.println("Person removed from Address book");
		}
		
		/**
		 * Displays on console
		 */
		private void show() {
			System.out.println(detail);
		}
	
		
		/**
		 * UC8 : Ability to search person across all cities
		 * and Name of cities are stored as key of Hashmap
		 *loop iterate for all keys
		 *  stream to search target person 
		 */
		private void search() {
			System.out.println("Enter name to search across all books");
			String searchKey = sc.next();
			for(String key : detail.keySet()) {
				detail.get(key).stream().filter(personList1->personList1.getFirstName().equals(searchKey)).
					collect(Collectors.toList()).forEach(Person -> System.out.println(Person.toString()));	
			}
		}
		
		
		/**
		 * UC9 : Ability to view persons by city
		 * Gets city name from user 
		 * If Hashmap contains that city as key it will display all contacts in list
		 * using stream..
		 * Use of Atomic integer is to count number of contacts.
		 */
		private void searchBycity() {
			System.out.println("Enter city name to display all contacts");
			String searchKey = sc.next();
			AtomicInteger count = new AtomicInteger(0);
			if(detail.containsKey(searchKey)) {
				detail.get(searchKey).stream().forEach(Person -> {System.out.println(Person.toString());count.incrementAndGet();});
				System.out.println("Number of contacts : "+count.get());
			}
			else {
				System.out.println("City not found");
			}	
			
		}
		
		/**
		 * UC11 Sort by First name 
		 */
		private void sortByFirstName() {
			for(String key : detail.keySet()) {
				detail.get(key).stream().sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList())
				.forEach(Person -> System.out.println(Person.toString()));
			}
		}
		
		/**
		 * UC12 Sort by city name
		 */
		private void sortByCity() {
			for(String key : detail.keySet()) {
				detail.get(key).stream().sorted(Comparator.comparing(Person::getCity)).collect(Collectors.toList())
				.forEach(Person -> System.out.println(Person.toString()));
			}
		}
			
		public static void main(String[] args) {
			AddressBook runner = new AddressBook();
			runner.displayWelcome();
			
			boolean isExit = false;
			while (!isExit) {
				System.out.println("Enter options\n1.Add\n2.Edit\n3.Delete\n4.Show\n5.Search\n6.ShowCity\n7.SortByName\n8.SortByCity\n9.Exit");
				int userInput =sc.nextInt();
				switch (userInput) {
				case 1: 
					runner.add();
					break;
				case 2:
					runner.edit();
					break;
				case 3:
					runner.delete();
					break;
				case 4:
					runner.show();
					break;
				case 5 :
					runner.search();
					break;
				case 6 :
					runner.searchBycity();
					break;
				case 7:
					runner.sortByFirstName();
					break;
				case 8:
					runner.sortByCity();
					break;
				case 9 :
					isExit=true;
					break;
				default :
					System.out.println("Invalid input");
				}
			}
		}

		
	}


