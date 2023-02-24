import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Addressbook {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String inputName =  "null";
        String inputEmail = "null";
        String inputPhoneNumber = "null";
        ArrayList<Person> Addressbook = new ArrayList<Person>();
        String inputNumber = "0";
        String searchTerm = "";
        while(true) {
            System.out.println("Press 1 for adding a new person.");
            System.out.println("Press 2 for getting the size of the addressbook.");
            System.out.println("Press 3 for finding person(s) in the addressbook");
            System.out.println("Press 4 for deleting a person with a specified email address.");
            System.out.println("Press 5 to end the programm");
            inputNumber = scanner.nextLine();
            if(inputNumber.equals("1")){
                System.out.println("Type in the name:");
                inputName = scanner.nextLine();
                System.out.println("Type in the email-address:");
                inputEmail = scanner.nextLine();
                System.out.println("Type in the phone number");
                inputPhoneNumber = scanner.nextLine();
                Collections.copy(Addressbook, addNewPerson(Addressbook, inputName, inputEmail, inputPhoneNumber));
            }
            else if(inputNumber.equals("2"))
                printAddressbookLength(Addressbook); //prints length of Addressbook
            else if(inputNumber.equals("3")){
                System.out.println("Type in the name of the person you want to search");
                searchTerm = scanner.nextLine();
                printPersonsWithName(Addressbook, searchTerm);
            }
            else if(inputNumber.equals("4")){
                System.out.println("Type in the email address of the person you want to delete");
                searchTerm = scanner.nextLine();
                deletedPersonsWithEmail(Addressbook, searchTerm);
            }
            else if(inputNumber.equals("5"))
                break;
            else
                System.out.println("Input needs to be either 1, 2, 3 or 4");
        }
    }
    public static ArrayList<Person> addNewPerson(ArrayList<Person> Addressbook, String inputName, String inputEmail, String inputPhoneNumber){
        for(Person person : Addressbook){
            if(person.email.equals(inputEmail)){
                System.out.println("Email is already used in the Addressbook. Try another one");
                return Addressbook;
            }
        }
        Addressbook.add(new Person(inputName, inputEmail, inputPhoneNumber));
        System.out.println(inputName + " has been successfully added");
        return Addressbook;
    }
    public static void printAddressbookLength(ArrayList<Person> Addressbook){
        System.out.println(Addressbook.size());
    }
    public static void printPersonsWithName(ArrayList<Person> Addressbook, String searchName){
        boolean beenFound = false;
        for(Person person : Addressbook){
           if(person.name.equals(searchName)){
               beenFound = true;
               System.out.println(person.name);
               System.out.println(person.email);
               System.out.println(person.phoneNumber);
               System.out.println();
           }
       }
        if(!beenFound){
           System.out.println("Nobody has been found.");
       }
    }
    public static void deletedPersonsWithEmail(ArrayList<Person> Addressbook, String searchEmail){
        boolean beenDeleted = false;
        for(Person person : Addressbook){
            if(person.email.equals(searchEmail)){
                beenDeleted = true;
                System.out.println(person.name + " has been successfully removed");
                Addressbook.remove(person);
            }
        }
        if(!beenDeleted){
            System.out.println("Nobody has been deleted. The Email was not found");
        }
    }
}
class Person{
    String name = "";
    String email = "";
    String phoneNumber = "0";
    public Person(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
