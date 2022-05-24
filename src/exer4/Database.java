package exer4;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
public class Database {
  /* Hold two maps, mapping from a lookup-friendly item to the adjacent objects. Each person found will have
   * phone numbers, and each phone number found will have people associated with it. TreeMaps will print
   * their objects in order. */
  private TreeMap<String, Person> people;
  private TreeMap<Long, PhoneNumber> numbers;
  
  public Database() {
    people = new TreeMap<String, Person>();
    numbers = new TreeMap<Long, PhoneNumber>();
  }
  
  /* === Entry Management === */
  
  // Retrieves a person if they already exist in the database, or adds and returns a new person if not.
  private Person ensurePerson(String name) {
    return people.computeIfAbsent(name, k -> new Person(name));
  }
  
  // Same, but with a phone number.
  private PhoneNumber ensurePhoneNumber(long num) {
    return numbers.computeIfAbsent(num, k -> new PhoneNumber(num));
  }
  
  /* Adds a mapping from a Person to a PhoneNumber, adding the required data if absent.
   * If the person already exists, it will add another phone number to that person. */
  public void addEntry(String name, long phoneNumber) {
    Person person = ensurePerson(name);
    PhoneNumber number = ensurePhoneNumber(phoneNumber);
    person.addUndirectedEdge(number);
  }
  
  // This person lost his rights to phones. Removes all data associated with his name, if it exists.
  public void delEntry(String name) {
    people.remove(name);
  }
  
  // Removes a phone number from one's name.
  public void delPhoneNumber(String name, long phoneNumber) {
    Person p = people.get(name);
    if(p == null) return;
    p.delUndirectedEdge(p);
    numbers.remove(phoneNumber);
  }
  
  /* Helper functions (security) */
  
  // Converts a Set of Persons into a List of Strings so that rather than
  // going to someone's house and messing with their phones, you are simply observing harmless data.
  private List<String> stripDataFromPersons(Set<Person> pSet) {
    List<String> l = new LinkedList<String>();
    for(Person p : pSet)
      l.add(p.getName());
    return l;
  }
  
  // Same with a Set of PhoneNumbers
  private List<String> stripDataFromPhoneNumbers(Set<PhoneNumber> pSet) {
    List<String> l = new LinkedList<String>();
    for(PhoneNumber p : pSet)
      l.add(""+p);
    return l;
  }
  
  /* === Search Functions === */
  
  // Returns a Set of all PhoneNumbers that person is associated with, if so.
  public List<String> lookupNumbers(String name) {
    Person p = people.get(name);
    if(p != null) {
      Set<PhoneNumber> s = p.getConnections(); // See UnweightedGraphNode
      return stripDataFromPhoneNumbers(s);
    } else {
      return null;
    }
  }
  
  // Returns a Set of all Persons that phone number (long) is associated with, if so.
  public List<String> lookupPeople(long number) {
    PhoneNumber p = numbers.get(number);
    if(p != null) {
      Set<Person> s = p.getConnections(); // See UnweightedGraphNode
      return stripDataFromPersons(s);
    } else {
      return null;
    }
  }
  
  /* Listing functions */
  
  public List<String> listAllNames() { // stripData only accepts sets
    List<String> l = new LinkedList<String>();
    for(Person p : people.values())
      l.add(p.getName());
   return l;
  }
  
  public List<String> listAllPhoneNumbers() {
    List<String> l = new LinkedList<String>();
    for(PhoneNumber p : numbers.values())
      l.add(""+p);
    return l;
  }
  
}
