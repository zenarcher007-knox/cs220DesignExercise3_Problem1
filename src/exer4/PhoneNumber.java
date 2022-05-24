package exer4;

import java.util.HashSet;
import java.util.Set;

/* Just a simple phone number. Supports only 7, 10, or 11-18 digit numbers. */
public class PhoneNumber extends UnweightedGraphNode implements Comparable<PhoneNumber> {
  private long number; // I chose a long instead of a string because phone numbers are usually
  // no more than 11 or 12 digits, and it is also easily comparable with other phone numbers.
  // It may also be more compact in memory. However, the main reason I chose this is for ease of use: 
  // it can be created quickly without verbose setAreaCode(), etc... functions.
  
  private HashSet<Person> people;
  
  // Constructor
  public PhoneNumber(long number) {
    super();
    setPhoneNumber(number);
    people = new HashSet<Person>();
  }
  
  
  /* Setter / getter Functions */
  
  // Sets the phone number, but I don't think people should be able to reset this; just make a new
  // phone number... Throws an exception if it is an invalid phone number.
  private void setPhoneNumber(long number) throws IllegalArgumentException {
    int l = (""+number).length();
    if( !( l == 7 || l == 10 || (l >= 11 && l <= 18) ) ) { // Range check
      throw new IllegalArgumentException("Phone number must be a 7, 10, or 11-18 digit number.");
    }
    this.number = number;
  }
  
  /* Returns a phone number, represented as an int. If you want a more formal form, call the toString method. */
  public long getPhoneNumber() {
    return number; 
  }
  
  /* Returns a Set of all people associated with this PhoneNumber. */
  public Set<Person> getPeople() {
    return people;
  }
  
  @Override
  public int compareTo(PhoneNumber other) {
    /* Yes, there may be a loop-over, but... How else would I compare how many phone
     * numbers away from another phone number this is? At least the sign should be correct. */
    return (int)(this.number - other.number) % Integer.MAX_VALUE;
  }
  
  @Override
  public boolean equals(Object other) {
    if( !(other instanceof PhoneNumber) )
      return false;
    PhoneNumber o = (PhoneNumber) other;
    return number == o.number;
  }
  
  @Override
  public String toString() {
    String s = ""+number;
    int l = s.length();
    switch(l) {
      case 7: return s.substring(0,3) + "-" + s.substring(3,7);// 7-digit phone number
      case 10: return "("+s.substring(0,3)+") " + s.substring(3,6) + "-" + s.substring(6,10); // with area code
      default: 
        if(l >= 11) return "+" + s.substring(0,l-4-3-3) + " ("+s.substring(l-4-3-3,l-4-3)+") " + s.substring(l-4-3,l-4) + "-" + s.substring(l-4,l); // with country codereturn s;
        else
          return s; // Will never happen, but Java doesn't know this...
    }
  }
}
