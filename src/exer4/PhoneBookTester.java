package exer4;

public class PhoneBookTester {
  public static void main(String[] args) {
    Database d = new Database();
    d.addEntry("Justin", 14248389631l); // I obviously have many numbers...
    d.addEntry("Justin", 18743961120l);
    d.addEntry("Justin", 24248389631l); // Another country
    
    d.addEntry("Alice", 13235657887l); // Don't know anyone named Alice. 
                                       // I made it up and it just started with an A.
    d.addEntry("Alice", 15736667887l);  
    d.addEntry("Alice", 5736667887l); // No country
    d.addEntry("Alice", 6667887l); // No area code
    
    System.out.println(d.listAllPhoneNumbers());
    System.out.println(d.listAllNames() + "\n");
    
  //...But phone numbers with no area codes are practically useless. Delete that...
    d.delPhoneNumber("Alice", 6667887l);
    
    System.out.println(d.listAllPhoneNumbers());
    System.out.println(d.listAllNames() + "\n");
  }

}




















































































//Not to brag, but this might be the most beautifully made design challenge implementation
//I ever made... Or at least I think so...