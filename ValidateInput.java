/**
 * @(#) ValidateInput.java
 * @author Linda Shepherd
 * @version 1.00 2021/03/18 10:23 AM
 * 
 * PROGRAM PURPOSE:  This is a utility class that serves to 
 * validate integer, double, boolean, character, and String 
 * input.  There is also a method to remove all whitespaces
 * from a String so proper validation can be performed.
 */

/********************************************************************
  * The following class imports are part of a group of classes for  
  * regular expressions.  A regular expression is a pattern of 
  * characters that are to be matched in a String.  The pattern is
  * first compiled into a Pattern object, which is then used to
  * create a Matcher object that subsequently searchs a String for
  * the given pattern or sequence of characters to be found.
  *******************************************************************/
/* Allows for a pattern in the form of a special sequence of characters 
 * to be applied in the search of a String.*/
import java.util.regex.Pattern;  

/*Allows for the interpretation of and matching of a pattern in a String.*/
import java.util.regex.Matcher; 

public class ValidateInput
{ 
  /**
   * Assures default constructor remains as an option
   * for declaring an object of this class with fields
   * initialized to their default values.
   */
  public ValidateInput()
  {
  }//END Default Constructor
  
  /**
   * Using regex (regular expression) to determine 
   * whether an input value is a positive or negative 
   * integer or floating point.
   * 
   * @param input is the incoming String value to test.
   * @return is true when the input is an integer/floating
   *  point or false when it isn't.
   */
  public static final boolean isNumeric(String input) 
  {
    /* Local Pattern object that contains the expression/
     * pattern to look for in a String.
     * -? determines if a value is negative.
     * \d+ searches for one or more digits in the value.
     * (\.\d+)? determines if a value is a float by
     * searching for one or more digits with a period
     * following the digit(s). 
     * 
     * The ? mark indicates that the pattern is optional.
     */
    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    
    //Local boolean variable to determine if the input is or is not a number.
    boolean isNumber = false;  
    
    if(input != null) 
    {
      //Creates a Matcher object to match the input to the pattern.
      isNumber = pattern.matcher(input).matches();
      
    }//if input is NOT empty
    
    return isNumber;
    
  }//END isNumeric(String):  static final boolean
  
  /**
   * Using regex to determine whether data is a boolean.
   * 
   * @param data is the incoming String value to test.
   * @return is true when data is a boolean or false 
   *  when it isn't.
   */
  public static final boolean isBoolean(String data)
  {
    //Local variable to determine if data is or is not a boolean.
    boolean isBoolean = false; 
    
    //Specify what to look for.
    Pattern pattern = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);
    
    //Interpret the pattern by applying it to data.
    Matcher matcher = pattern.matcher(data);
    
    /* Now see if there is a match.
     * If there isn't a match, the if body is by-passed and false
     * remains in the isBoolean variable.
     */
    if(matcher.matches()) 
    {
      isBoolean = true;  //True when the String object contains the pattern.
    }//END if data is a boolean
    
    return isBoolean;  //Returns either true or false.
    
  }//END isBoolean(String):  static final boolean
  
  /**
   * Determines whether a character is a letter.
   * 
   * @param aChar is the incoming char value to test.
   * @return is true when aChar is a letter or false 
   *  when it isn't.
   */
  public static final boolean aLetter(char aChar)
  {
    //Returns a true when the char is a letter; else a false is returned.
    return Character.isLetter(aChar);  
                                       
  }//END aLetter(char):  static boolean
  
  /**
   * Using lambda expression to check for words that are alphabetic.
   * The :: tells the compiler to call the isLetter method from
   * the Character class.  
   * 
   * @param word is the incoming String value to test.
   * @return is true when the word is not empty and is alphabetic 
   *  or false when it isn't.
   */
  public static final boolean isAlpha(String word)
  {    
    /* Test to see if the word is not empty AND if each letter
     * in a word is an alphabetic character.  
     */
    return word != null && word.chars().allMatch(Character :: isLetter);
    
  }//END isAlpha(String):  static final boolean
  
  /**
   * chars() returns the integer values of the characters in word.  
   * 
   * allMatch determines whether the integer values for each character 
   * matches the predicate (criterion) that each character is a letter.  
   * 
   * The :: is a method reference operator for calling isLetter from
   * the Character class.
   * 
   * @param word is the incoming String value to test.
   * @return is true when the word is not empty and is alphabetic 
   *  or false when it isn't.
   */
  public static final boolean isDigits(String word)
  {    
    /* Test to see if the word is not empty AND if each 
     * character in the word is a digit.  
     */
    return word != null && word.chars().allMatch(Character :: isDigit);
    
  }//END isAlpha(String):  static final boolean
  
  /**
   * Using regex to determines whether a  String is alphanumeric, 
   * i.e., contains letters and digits.
   * 
   * @param word is the incoming String value to test.
   * @return is true when word is alphanumeric or false 
   *  when it isn't.
   */
  public static final boolean isAlphaNumeric(String word)
  {
    /* From the beginning of the String ^ to the end $,
     * search one or more characters + for upper A-Z or 
     * lowercase a-z letters and digits 0-9.
     */
    String alphaNumericPattern = "^[a-zA-Z0-9]+$";
    
    /*
     * Returns true when the word matches to the pattern
     * or false when it doesn't.  The matches() from the
     * String class produces the same result as using
     * Pattern and Matcher objects.
     */
    return word.matches(alphaNumericPattern);
    
  }//END isAlphaNumeric(String):  static final boolean
  
  /**
   * All whitespace characters in a String are removed:  tab,
   * space along with
   * 
   * - carriage return (goes to beginning of current line,
   *   no line advance), 
   * - line feed (advances to the next line, is a newline), 
   * - form feed (advances to next page) plus     
   * - file separator for separating files.
   * - group separator for separating tables.
   * - record separator for separating records in a table.
   * - unit separator for separating fields in a record. 
   * 
   * \s+ is a regex (regular expression) to search for 
   *     multiple whitespaces.  
   * o The \s is a meta character for a common matching 
   *   pattern.  This meta character actually represents
   *   the regular expression for whitespaces which is 
   *   [\t\n\x0B\f\r].
   * o The + is a quantifier for one or more occurences.  
   * o The replaceAll() of the String class accepts regular 
   *   expressions.
   * o The first \ in \\s+ is a lead-in to the regex since
   *   a single \ is already a special character in Java for
   *   escape sequences that perform specific tasks, such as
   *   inserting a tab, a double quote, etc.  
   */
  public static final String removeAllWhiteSpaces(String item)
  {
    return item.replaceAll("\\s+", "");
    
  }//END removeAllWhiteSpaces(String):  static final String
  
}//END CLASS ValidateInput