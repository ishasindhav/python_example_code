/**
 *  
 * 
 * @(#)Laptop.java
 * @author Linda Shepherd and Isha Sindhav
 * @version 1.00 2024/03/29 5:32 PM
 * 
 * PROGRAM PURPOSE: This program allows a user to order one or multiple laptops from a predetermined menu
 * and prints out an order summary at the end of the program.
 */
import java.util.Scanner;   //Importing the Scanner tool to be able to read user input.
import java.util.Calendar;  //Importing the Calendar tool to accurately display the date and time.

public class Laptop
{   
  private Scanner input = new Scanner(System.in);     //private object to capture data entry from keyboard.
  private Calendar dateTime = Calendar.getInstance(); //Object to capture date and time to display to user.
  
  //Stores the header to user's order summary.
  private String orderSummaryHdr = String.format("%n%nLAPTOP ORDER SUMMARY"
                                                   + "%n%nDate:  %tD"
                                                   + "%nTime:  %tr%n", dateTime, dateTime); 
  
  private String orderSummary;  //Stores the user's actual summary.
  private String laptop;  //Stores the user's laptop choice(s).
  private String customerName;  //Store's the customer's name.
  private String data;  //Stores the user's data.
  
  private double subtotal;  //Stores the subtotal shown in Order Summary.
  private static final double TAX_RATE = .0825;  //Static final for tax rate.
  private double price;  //Stores the price shown in Order Summary.
  
  private int choice;  //Stores the user's choice for laptop from the given menu.
  private int trigger;  //Stores the trigger for the program.
  private int qty;  //Stores the quantity of laptops being purchased.  
  
  private char correct;  //Stores the user's answer to verifying questions about the name, choice of laptop, etc.
  
  /**
   * This class sets a default constructor.
   */
  public Laptop() //STUDENT CODES
  {
    
  }//END default constructor  
  
  /**
   * This following method initializes the variables of the main method of the program, Laptop.
   */
  public Laptop(String customerName, String laptop, Double price)//STUDENT CODES
  {
   this.laptop = laptop;
   this.customerName = customerName;  //edited
   this.price = price;
    
     //STUDENT CODES ASSIGNMENT STATEMENT OF PARAMETERS
    //TO FIELDS (LOOK @ CLOSE BRACE LINE COMMENT)
    
  }//END Laptop(customerName, laptop:  String, price:  double)
  
  /**
   * The following method creates the fields of the Laptop method.
   */
  public Laptop(Laptop aLaptop)//STUDENT CODES
  {
    customerName = aLaptop.customerName; //STUDENT CODES STATEMENTS TO ASSIGN
    laptop = aLaptop.laptop; 
    price = aLaptop.price;           //FIELDS FROM INCOMING LAPTOP TO
                                    //THE FIELDS OF THE LAPTOP BEING
                                     //CREATED.    
    
  }//END Laptop(aLaptop:  Laptop)
  
  /**
   * The following method clones Laptop object.
   */
  public Laptop copy() //STUDENT CODES
  {
    Laptop clone = new Laptop(customerName, laptop, price);  
    return clone;

    //STUDENT CODES A Laptop OBJECT CALLED clone USING THE
    //CONSTRUCTOR THAT ACCEPTS THE customerName, laptop,
    //price.  THE LAPTOP OBJECT HAS TO BE RETURNED.
    
  }//END copy():  Laptop
  
  /**
   * The following method sets the customer's name in array list.
   */
  public void setCustomerName(String ordinalSuffix)
  {
    String customerNameCopy = ""; 
    orderSummary = orderSummaryHdr;
    trigger = 1;
    subtotal = 0.0;   
    
    do
    {  
      System.out.printf("%nWhat is the %s customer\'s name?  ", ordinalSuffix);
      customerName = input.nextLine();
      
      customerNameCopy = new String(customerName).replace(" ", "");
      customerNameCopy = new String(customerNameCopy).replace("-", "");
      customerNameCopy = new String(customerNameCopy).replace(",", "");
      customerNameCopy = new String(customerNameCopy).replace(".", "");      
      
      //CODE while HEADER THAT VALIDATES THE COPY IS NOT AN ALPHA BY CALLING THE isAlpha METHOD FROM
      //THE ValidateInput CLASS AND GIVE IT THE COPY OF THE CUSTOMER NAME.
 while(!ValidateInput.isAlpha(customerNameCopy))
      {
        System.out.printf("%nCustomer name not alphabetic!  Please re-enter:  ");
        customerName = input.nextLine();
        customerNameCopy = new String(customerName).replace(" ", "");
        customerNameCopy = new String(customerNameCopy).replace("-", "");
        customerNameCopy = new String(customerNameCopy).replace(",", "");
        customerNameCopy = new String(customerNameCopy).replace(".", "");
        
      }//END Validate employeeName for alpha characters only.
      
      customerName = capitalize(customerName);
      
      correct = ValidateInput.validateYorN(String.format("%n%s is the name entered.  "
                                                       + "Is this correct?  Y or N:  ", customerName));
      
    }while(correct != 'Y');  //END do-while for capturing and validating customer name
    
    orderSummary += String.format("%nCustomer:  %s%n", customerName);    
    
  }//END setCustomerName(ordinalSuffix:  String):  void  
  
  /**
   *  The following method prints the laptop menu and stores user input about laptop choices.
   */
  public void setLaptopChoice()  
  {
    //For the proper alignment of the laptops and their prices.
    String laptopMenu = String.format("%nTOP LAPTOPS OF %tY"
                                    + "%n%n1.  %-23s %7s $%,9.2f" 
                                    + "%n2.  %-23s %8s %,9.2f"
                                    + "%n3.  %-23s %8s %,9.2f" 
                                    + "%n4.  %-23s %8s %,9.2f" 
                                    + "%n%nEnter your choice:  ", 
                                      dateTime,
                                      "HP Pavilion Plus 14", " ", 529.99, 
                                      "HP Spectre x360 2-in-1", " ", 1099.99, 
                                      "Dell XPS 15", " ", 1499.99, 
                                      "Zenbook Pro 14 Duo OLED", " ", 1999.00);   
    
    do
    {    
      System.out.printf("%s", laptopMenu);
      data = input.nextLine();
      
     while(!ValidateInput.isNumeric(data)) // while HEADER THAT VALIDATES THE DATA IS NOT NUMERIC.
      {          
        System.out.printf("%nNot a valid number!  Please re-enter your choice:"                            
                        + "%n%s", laptopMenu);
        data = input.nextLine();
      }//END while NOT a number
      
      choice = Integer.parseInt(data);     
      
      if(choice <= 0 || choice > 4)
      {
        System.out.printf("%s", getChoiceErrorMsg()); 
      }//END if choice is outside of 1-4 range
      else
      {
        setLaptopName();
        setLaptopPrice();        
        correct = ValidateInput.validateYorN(String.format("%nYou chose the %s.  Enter Y or N:  ", laptop));
        
        if(correct == 'N')
        {
          choice = 0;  //To allow for re-entry into the loop.
        }//END if not correct
      }//END if choice is in range of 1-4
      
    }while(choice <= 0 || choice > 4);  //Validates the range for choice.  
    
  }//END setLaptopChoice():  void
  
  /**
   * The following method sets the laptop name according to user's choice in switch.
   */  
  private void setLaptopName()  
  {
    switch(choice)
    {
      case 1:  
        laptop = "HP Pavilion Plus 14";     
        break;
      case 2:
        laptop = "HP Spectre x360 2-in-1";       
        break;
      case 3:
        laptop = "Dell XPS 15";    
        break;
      case 4:
        laptop = "Zenbook Pro 14 Duo OLED";   
    }//END switch on choice to determine laptop and price     
    
  }//END setLaptopName():  void
  
  /**
   * The following method stores laptop price according to user's choice on laptop.
   */
  private void setLaptopPrice()  
  {
    switch(choice)
    {
      case 1:    
        price = 529.99; 
        break;
      case 2: 
        price = 1099.99;             
        break;
      case 3:  
        price = 1499.99;  
        break;
      case 4:  
        price = 1999.00;
    }//END switch on choice to determine laptop price     
    
  }//END setLaptopPrice():  void
  
  /**
   * The following method sets the quantity of laptops in the laptop order with user input.
   */
  public void setLaptopQty()
  {
    do
    { 
      System.out.printf("%nEnter the quantity for the %s:  ", laptop);
      data = input.nextLine();
      
      while(!ValidateInput.isNumeric(data))
      {        
        System.out.printf("%nNot an integer!%n"
                        + "%nPlease re-enter the quantity for %s:  ", laptop);
        data = input.nextLine();
      }//END while NOT a number
      
      qty = Integer.parseInt(data);             
      
      //Validation of purchase. 
      correct = ValidateInput.validateYorN(String.format(qty > 1 ? 
                String.format("%nYou\'re purchasing %d %ss.  Is this correct?  "
                            + "Enter Y or N:  ", qty, laptop) 
                : 
                String.format("%nYou\'re purchasing %d %s.  Is this correct?  "
                            + "Enter Y or N:  ", qty, laptop)));   
      
    }while(correct != 'Y');  //END do-while for verifying the quantity purchased.    
    
  }//END setLaptopQty():  void
  
  /**
   * The following method processes the order summary and total.subtotal of the purchase.
   */
  public void processLaptopTransaction()
  {
    double lineItem = qty * price;
    subtotal += lineItem;
    
    switch(trigger)
    {
      case 0: //For no $ sign. 
        orderSummary += String.format("%n%,-9d %-40s %5s %,17.2f",                         
                                      qty, String.format(laptop + " @ $%,.2f ea.", price), 
                                      " ", lineItem);
        break;
      case 1:  //For $ sign.
        orderSummary += String.format("%n%,-9d %-40s %4s $%,17.2f",                         
                                      qty, String.format(laptop + " @ $%,.2f ea.", price), 
                                      " ", lineItem);
        trigger = 0;  //Reset to trigger else in next iteration.
    }//END switch on trigger to determine when to include $ sign        
    
  }//END processLaptopTransaction():  void
  
  /**
   * The following method calculates the tax amount of the order.
   */
  public double calcTax()
  {
    return subtotal * TAX_RATE;
  }//END calcTax():  double  
  
  /**
   * The following method prints the order summary.
   */
  public String getLaptopOrder() //printOrderSummary()
  {
    double tax = calcTax();  
    
    double total = subtotal + tax;
    
    orderSummary += String.format("%n%n%40s Subtotal %6s %,17.2f"
                                    + "%n%37s Tax @ 8.25%% %6s %,17.2f"                    
                                    + "%n%n%43s TOTAL %5s $%,17.2f%n", 
                                  " ", " ", subtotal,  
                                  " ", " ", tax, 
                                  " ", " ", total);
    
    return orderSummary;
  }//END getLaptopOrder():  String
  
  /**
   * The following method returns customerName.
   */
  public String getCustomerName()
  {
    return customerName;
    
  }//END getCustomerName():  String
  
  /**
   * The following method returns Laptop.
   */
  public String getLaptop()
  {
    return laptop;
  }//END getLaptop():  String
  
  /**
   * The following method returns the price variable.
   */
  public double getPrice()
  {
    return price;
  }//END getPrice():  double
  
  /**
   * The following method returns several variables in a print statement. 
   */
  public String getObjectState()
  {
    return String.format("%nCustomer Name:  %s"
                           + "%nLaptop:  %s"
                           + "%nPrice:  $%,.2f"
                           + "%nQuantity:  %,d%n", 
                         customerName, laptop, price, qty);
  }//END getObjectState():  String
  
  /**
   * The following method prints an error message.
   */
  private String getChoiceErrorMsg()
  {
    return String.format("%nOut of range!  Try again.%n");
  }//END getChoiceErrorMsg():  String    
  
  /**
   * The incoming string is split into a String array of words. 
   * Each word has its first letter capitalized.  Location
   * of dashes are determined, then they are stripped and then
   * re-instated after proper capitalization in the string.
   */
  public static final String capitalize(String str)
  { 
    boolean found = false;  //Variable to determine if a dash is in the string.
    int dash1Ordinal = 0;   //Variable for the location of the 1st dash.
    int dash2Ordinal = 0;   //Variable for the location of the 2nd dash.
    
    //Block for determining where the dash(es) are.
    {
      if(str.indexOf("-") > 0)  //Does the 1st dash exist?
      {
        dash1Ordinal = str.indexOf("-");  //The location of the 1st dash.
        found = true;  //The 1st dash does exist.
      }//END if there is a 1st dash
      
      if(str.indexOf("-", dash1Ordinal + 1) > 0)  //Does the 2nd dash exist?
      {
        dash2Ordinal = str.indexOf("-", dash1Ordinal + 1);  //The location of the 2nd dash.
        found = true;  //The 2nd dash does exist.        
      }//END if there is a 2nd dash
      
      str = str.replace("-", " ");  //Replace the dashes with spaces.
    }//END block for the location of the dash(es)   
    
    String words[] = str.split("\\s");  //Each word in str is an element in the array.    
    String nameSuffix = words[words.length - 1];  //Isolate suffix such as Jr., II, etc.  
    
    String capitalized = "";  //Stores what came in the str with correct capitalization.
    
    for(int i = 0; i < words.length; i++)  //Navigate to each word of the str in the array.    
    {   
      if(i == words.length - 1)  //If it's the last word.
      {     
        if(words[i].substring(0, 1).equalsIgnoreCase("i"))  //Is the first letter an "i"?
        {
          capitalized += nameSuffix.substring(0).toUpperCase();  //Uppercase the suffix.          
        }
        else  
        {
          capitalized += words[i].substring(0, 1).toUpperCase();  //Get the first character.
          capitalized += words[i].substring(1).toLowerCase() + " ";  //Get the rest of the characters starting at the 2nd.      
        }//END if suffix starts with "I" else process as usual.
      }
      else
      {
        capitalized += words[i].substring(0, 1).toUpperCase();  //Get the first character.
        capitalized += words[i].substring(1).toLowerCase() + " ";     //Get the rest of the characters starting at the 2nd.      
      }//if last word in array process as suffix else process as usual.    
      
    }//for each word from a String in the words array, capitalize the first letter. 
    
    if(words[0].length() == 2)
    {
      capitalized = capitalized.substring(0, 2).toUpperCase() +
        capitalized.substring(2);
    }//if 1st word is 2 letters, capitalize all 
    
    if(found)  
    {     
      if(dash1Ordinal > 0)
      {
        capitalized = capitalized.substring(0, dash1Ordinal) + "-"
          + capitalized.substring(dash1Ordinal + 1);
      }//if there is a 1st dash put back.
      
      if(dash2Ordinal > 0)
      {        
        capitalized = capitalized.substring(0, dash2Ordinal) + "-"
          + capitalized.substring(dash2Ordinal + 1);
      }//if there is a 2nd dash put back.
      
    }//END if there are dashes put them back.  
    
    return capitalized.trim();  //Return the string with the first letters all capitalized.
  }//END capitalize(str:  String):  static final String  
  
}//END CLASS Laptop