
/**
 * @(#)Sindhav
 * @author Isha Sindhav
 * @version 1.00 2024/04/20 5:00 PM
 *
 * PROGRAM PURPOSE: Allows users to add a laptop or multiple laptops to a file, and edit that file. Users can
 * also search for different laptops in said file.
 */

import java.util.Scanner;     //Importing the Scanner tool to be able to read user input.
import java.util.Calendar;    //Importing the Calendar tool to accurately display the date and time.
import java.util.ArrayList;   //Importing the ArrayList tool to be able to create and edit array lists.
import java.io.File;          //Importing the File tool to accept a file or directory name from user input.
import java.io.PrintWriter;   //Importing the PrintWriter tool to gives Prints formatted representations of 
                              //objects to a text-output stream.
import java.io.IOException;   //Importing the IOException tool to expand input/output operations.

/**
  * 
  * This is the beginning of the LaptopOrders class, which starts with a series of import statements.
  * 
  */

public class LaptopOrders
{
  
  /**
    * 
    * This following method calls the createLaptopOrders method to begin the process of a user's laptop order.
    * 
    */
  
  public void start()  
  {
    createLaptopOrders();  //Calling the createLaptopOrders method.
  }  //END method to start the program.
  
  /**
    * 
    * This following method allows a user to add a laptop to their order by calling several other methods to use.
    * 
    */
  
  public void createLaptopOrders()
  {
    char anotherLaptop;        //Stores user input to add another laptop to files.
    int noCustomers = 0;       //Stores integer to add another customer or exit.
    Laptop aLaptopOrder = new Laptop();     //Initializing aLaptopOrder to variable Laptop.
    String ordinalSuffix = "";    //Stores String value for ordinalSuffix variable.
    
    System.out.printf("%nLAPTOP ORDERS%n");   //Printing LaptopOrders
    
    newCustomer = addNewCustomer();  
    
    while(newCustomer == 'Y')
    {
      switch(noCustomers +1 > 10)    //Beginning of switch for different ordinal suffix options.
      {
        case 1:
          ordinalSuffix = "st";
          break;
          
        case 2:
          ordinalSuffix = "nd";
          break;
          
        case 3:
          ordinalSuffix = "rd";
          break;
            
        default:
            ordinalSuffix = "th";
            
      }   //END switch to assign ordinal suffix.
      
      ordinalSuffix = String.valueOf(noCustomers +1) + ordinalSuffix;   //Assigns ordinalSuffix to the 
      //String.valueOf(noCustomers + 1) plus 
      //ordinalSuffix.
      
      aLaptopOrder.setCustomerName(ordinalSuffix);  //e the Laptop object to call setCustomerName() and send it ordinalSuffix
      
      do
      {
        aLaptopOrder.setLaptopChoice();   //Calling setLaptopChoice method in Laptop.java.
        aLaptopOrder.setLaptopQty();      //Calling setLaptopQyt method in Laptop.java.
        aLaptopOrder.processLaptopTransaction();     //Calling processLaptopTransaction method in Laptop.java.
        anotherLaptop = addLaptop();   //
      } while (anotherLaptop == 'Y');   //END do-while for variable anotherLaptop equaling Y.
        
        noCustomers++;  //post-increment  
      newCustomer = addNewCustomer();   //Calling addNewCustomer() and assigning it to newCustomer.
      
      if(noCustomers > 0)
      {
        laptopOrders.add(aLaptopOrder.getLaptopOrder());
      }      //END if statementfor adding a laptop order to LaptopOrder array list.
    }  //END while newCustomer == Y
    
    laptopOrders.trimToSize();  //trim to size the laptopOrders array list.
    
    if (noCustomers > 0)
    {
      writeLaptopOrders();   //Calling writeLaptopOrders method.
      
      printLaptopOrders();   //Calling printOLaptopOrders method.
      
    }   //END if statement to edit LaptopOrders and print LaptopOrders.
    
    System.out.printf(noCustomers == 0 ? String.format("%nThank you for your patronage!%n") 
                        : String.format("%nThank you for your business!%n"));
    
  }   //END createLaptopOrders method.
  
  /**
    * 
    * This following method allows a user to either add a new customer or exit the program.
    * 
    */
  
  public char addNewCustomer()
  {
    return ValidateInput.ValidateYorN(String.format("%nEnter 'Y' to add a new "
                                                      + "customer  or 'N' to exit:  %n"));  
  }   //END addNewCustomer method.
  
  /*
   * This method ADDs laptop orders to a file.
   *
   */
  public char addLaptop() //Method to prompt user to add a laptop or continue to summary.
  {
    return ValidateInput.validateYorN(String.format("%nEnter \'Y\' to add another laptop or \'N\' to go to the next "
                                                      + "step:  %n"));
    
  }  //END while to only accept Y or N values for while-loop cont control variable.
  
  /*
   * This method WRITEs laptop orders to a file.
   *
   */
  public void writeLaptopOrders() 
  {
    
    String laptopOrder = ""; // VARIABLE TO STORE INPUT
    PrintWriter outputFile = null; //  OBJECT TO WRITE TO FILE
    boolean fileError = false; // VARIABLE FOR FILEERRORS
    
    try {
      System.out.printf("%nEnter the file name for the laptop orders with \".txt\" extension."
                          + "%n(WARNING: This will erase a pre-existing file!): ");
      
      fileName = input.nextLine(); //  READS USER INPUT
      
      outputFile = new PrintWriter(fileName); // INITIALIZE PRITWRITER TO FILENAME
      
      for (int i = 0; i < laptopOrders.size(); i++) 
      {
        lapTopOrder = String.format("%s%n", laptopOrders.get(i)); 
        
        outputFile.printf("%s", laptopOrder);  
      } // END FOR LOOP
    } // END TRY BLOCK
    
    catch (IOException e) 
    {
      System.err.printf("%nFile cannot be created.%n");
      
      fileError = true;
    } // END CATCH BLOCK
    
    if (!fileError)
    {
      outputFile.close(); //  CLOSE OUTPUTFILE
      
      System.out.printf("%nLaptop oders have been written to the %s file.%n", fileName);
    } //END IF 
  } // END METHOD +WRITELAPTOPORDER
  
  /*
   * This method PRINTs laptop orders from a file.
   *
   */
  public void printLaptopOrders()
  {
    File file; // VARIABLE FOR FILE 
    Scanner inputFile = null; // OBJECT TO READ FROM FILE
    String fileRecord; // VARIABLE TO STORE INPUT 
    boolean fileNotFound = false; // VARIABLE FOR FILENOTFOUND
    
    try 
    {
      System.outprintf("%nEnter the name for the laptop orders file:  "); //PROMTS USER FOR LAPTOP ORDERS FILE
      
      fileName = input.nextLine(); // READS USER INPUT FOR FILE NAME
      file = new file(fileName); //  CREATES FILE OBJECT 
      inputFile = new Scanner(file); // CREATES SCANNER OBJECT
      while (inputFile.hasNext()); //  WHILE LOOP FOR INPUT FILE
      {
        fileRecord = inputFile.nextLine(); //  READS NEXTLINE
        
        System.out.printf("%n%s", fileRecord); //  PRINTS WHAT WAS INPUT
      } // ENDs while-loop
      
      System.out.println(); // PRINTS BLANK LINE 
      
      
      
    } // ENDs TRY BLOCK 
    
    catch(IOException e)
    {
      System.err.printf("%nFile not found!%n"); // PRINTS ERROR MESSAGE
      
      fileNotFound = true; // SETS FILE TO BE NOT TRUE
    } // ENDS CATCH IOEXCEPTION
    
    catch(NullPointerException e)
    {
      System.err.printf("%nRecord couldn't be accessed or read.%n"); //PRINTS ERROR MESSAGE
        
    } //- ENDs CATCH NULLPOINTEREXCEPTION
    
    if (!FileNotFound) // CHECK FOR FILE
    {
      inputFile.close(); // - CLOSE INPUTFILE
    } // END IF
  }// END OF +PRINTLAPTOPORDERS()
}  //ENDS public class LaptopOrders





