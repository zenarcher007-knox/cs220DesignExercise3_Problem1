package exer4;

import java.util.HashSet;
import java.util.Set;

public class Person extends UnweightedGraphNode implements Comparable<Person> {
  private String name;
  private HashSet<PhoneNumber> phoneNumbers;
  public Person(String name) {
    super();
    this.name = name;
    this.phoneNumbers = new HashSet<PhoneNumber>();
  }

  /* Getters (I'm assuming that noone is getting their name legally changed. It may also violate
   * the functionality of a Map, or result in odd behavior) */
  public String getName() {return name;}
  
  // Retrieves a set of all phone numbers associated with this person
  public Set<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }
  
  /* Fundamentals */
  @Override
  public int compareTo(Person o) {
    return name.compareTo(o.name);
  }
  
  @Override
  public String toString() {
    return "Person(\"" + name + "\")";
  }
}
