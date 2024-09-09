
# This code was written by Isha Sindhav to demonstrate the creation and 
# navigation of dictionaries, in addition to the pickling of the contents
# of said dictionary. The code involves a menu with several options on 
# navigating a dictionary filled with opposite word pairings.

# Importing the pickle library to create and edit my default file.

import pickle

# Defining the main function of the program.

def main():
    
    print()
    print('*******************************************')
    print()
    print('Welcome to the Oppposite Words Directory Program!')
    
# Opening the pickled file if it exists, and creating the pickle file with
# five default pairings if it does not exist.
    
    file_name = 'oppositeword.pickle'
    
    try:
        with open(file_name, 'rb') as InputFile:
            opp_words = pickle.load(InputFile)
    except FileNotFoundError:
        opp_words = {'Lost': 'Found',
                     'Rain': 'Shine',
                     'Rise': 'Fall',
                     'First': 'Last',
                     'Give': 'Take'}
        
# Defining the variable to hold the user's menu option.
        
    option = 0
    
# Defining a variable to exit or continue the program.
    
    keep_going = 'y'
    
    while keep_going == 'y':
        
# Getting user input to select the menu option, and designating functions to
# each menu option.

        option = menu()
        
        if option == 1:
            display(opp_words)
        elif option == 2:
            display_first_words(opp_words)
        elif option == 3:
            display_second_words(opp_words)
        elif option == 4:
            total(opp_words)
        elif option == 5:
            add(opp_words)
        elif option == 6:
            remove(opp_words)
        elif option == 7:
            change_second_word(opp_words)
        elif option == 8:
            lookup_first_word(opp_words)
        elif option == 9:
            lookup_second_word(opp_words)
        elif option == 10:
            lookup_all_words(opp_words)
        elif option == 11:
            display_first_word_AM(opp_words)
        elif option == 12:
            display_first_word_NZ(opp_words)
        elif option == 13:
            display_second_word_AM(opp_words)
        elif option == 14:
            display_second_word_NZ(opp_words)
        elif option == 15:
            keep_going = exit_program(file_name, opp_words)
            
# Defining the function that will display the startup banner and the menu
# for the user.
            
def menu():
    valid_option = 'True'
    while valid_option == 'True':
        print()
        print()
        print('                MENU OPTIONS:')
        print('***************************************************')
        print()
        print('1: Display all the Opposite Word pairings stored in the dictionary.')
        print('2: Display only the First Words in the dictionary pairs.')
        print('3: Display only the Second Words in the dictionary pairs.')
        print('4: Display how many Opposite Word pairings exist in the dictionary.')
        print('5: Add an Opposite Word pairing to the dictionary.')
        print('6: Remove an Opposite Word pairing from the dictonary.')
        print('7: Change the Second Word in an Opposite Word pairing.')
        print('8: Look up specifically a First Word in the dictionary.')
        print('9: Look up specifically a Second Word in the dictionary.')
        print('10: Look up any word in the entire dictionary (First or Second).')
        print('11: Display the First Words starting with A-M in the dictionary.')
        print('12: Display the First Words starting with N-Z in the dictionary.')
        print('13: Display the Second Words starting with A-M in the dictionary.')
        print('14: Display the Second Words starting with N-Z in the dictionary.')
        print('15: Exit the program.') 
        print()
        print('*****************************************************')
        
# Allowing the user to select a menu option, and assigning it to the variable.

        option = int(input('Select an option number from the menu: '))
                     
# Making sure the user entered a valid menu option.

        if option < 1 or option > 15:
            print()
            print('Invalid response. Returning to main menu...')
        else:
            valid_option = 'False'
            
# Returning the user input.
            
    return option

# This function displays all of the opposite word pairings found in the
# pickle file.

def display(opp_words):
    
# This command alphabetically sorts the dictionary by key.
    
    sorted_words = dict(sorted(opp_words.items()))
    
    print()
    print('First Word\t\t\tSecond Word')
    print('******************************************')
    for item in sorted_words:
        
# Formats the table below.

        print(f'{item:<10}\t\t\t{sorted_words[item]}')
        print()
        
# This function displays only the first words (keys) in the pairing.
        
def display_first_words(opp_words):
    
# Sorting the dictionary by key.

    sorted_first_words = sorted(opp_words.keys())
    print()
    print('First Words')
    print()
    for key in sorted_first_words:
        
# Printing the dictionary keys.  
        
        print(key)
        
# This function displays only the second word (value) of the pairings.
        
def display_second_words(opp_words):
    
# Sorting the dictionary by value.
    
    sorted_second_words = sorted(opp_words.values())
    print()
    print('Second Words')
    print()
    for value in sorted_second_words:
        
# Printing the dictionary values.
        
        print(value)

# This function displays how many word pairings exist in the dictionary.

def total(opp_words):
    
# Getting the length of the dictionary to see how many pairs exist.
    
    num_items = len(opp_words)    
    print()
    
# Printing the number of pairings that exist.
    
    print(f'There are {num_items} pairings in this dictionary.')
    
# This function adds an opposite word pair to the dictionary.
    
def add(opp_words):
    print()    
    
# Asking the user for confirmation that they would like to add a pairing.
    
    user_input = str(input('Are you sure you want to add a First Word/Second Word pairing to the ditionary? (y = yes, n = no): '))
    
# If user replies yes, asking the user for the key and value to add.
    
    if user_input == 'y':
        first_word = str(input('Enter a first word:'))
        second_word = str(input('Enter a second word: '))
        
# Validating if the key already exists in the dictionary.
        
        if first_word not in opp_words:
            print(f'The Opposite Words pairing {first_word} and {second_word} was added to the dictionary.')
            
# Adding the opposite word pairing.

            opp_words[first_word] = second_word
            display(opp_words)
            
# Printing an error message and returning to main menu if the key already
# exists in the dictionary.
            
        else:
            print()
            print('That word already exists in the dictionary, nothing was added.')
            
# If the user replies no to the confirmation, they are taken back to the main
# menu.
    elif user_input == 'n':
        print('Exiting this menu option...')
        
# If the confirmation reply is neither 'y' or 'n', an error message is shown and 
# the user is returned to the main menu.

    else:
        print()
        print('Invalid response - returning to main menu...')
        
# This function allows the user to remove a pair from the dictionary.
        
def remove(opp_words):
    print()
    
# Asking the user for confirmation if they want to remove a pairing.
    
    user_input = str(input('Are you sure you want to remove an Opposite Words pairing? (y = yes, n = no): '))
    
# If the user replies yes, they are asked for the key in the pairing to remove.
    
    if user_input == 'y':
        first_word = str(input('Enter the first word in the pairing: '))
        
# If the key is successfully found, the pair is deleted and a confirmation message
# is shown. The whole dictionary is then also displayed without the pairing.
        
        if first_word in opp_words:
            print(f'\nThe first word {first_word} and second word {opp_words[first_word]} was removed from the dictionary. ')
            del opp_words[first_word]
            display(opp_words)
            
# If the key was not found in the dictionary, an error message is shown to the user.
            
        else:
            print('The pairing was not found, nothing was deleted.')
            
# If user replies 'no' to the initial confirmation, they are taken back to the main menu.
            
    elif user_input == 'n':
        print('Exiting this menu option...')
        
# If user replies something other than 'y' or 'n', an error message is shown and 
# the user is taken back to the main menu.
        
    else:
        print('Invalid response - returning to main menu...')
        
# This function allows the user to change just the second word (value) in the pairing.
        
def change_second_word(opp_words):
    print()
    
# Asking the user for confirmation that they would like to go through with this menu option.
    
    user_input = str(input('Are you sure you want to change the Second Word in an Opposite Words pairing? (y = yes, n = no): '))
    
# If user replies yes, they are asked to enter the key of the pairing.
    
    if user_input == 'y':
        first_word = str(input('Enter the First Word in the pairing: '))
        
# Checking to see if the key exists in the dictionary. If it does, the user is prompted
# to enter the new value (second word) of the pairing.
        
        if first_word in opp_words:
            second_word = str(input('Enter the new Second Word in the pairing: '))
            
# A confirmation message is displayed, showing the new pairing and the whole dictionary as well.
            
            print(f'\nThe First Word {first_word} was found and its Second Word was changed to {opp_words[first_word]}. ')
            opp_words[first_word] = second_word
            display(opp_words)
            
# If the key was not found in the dictionary, an error message is displayed and the 
# user is brought back to the main menu. 
            
        else:
            print('First Word was not found in the dictionary.')
            print()
            
# If the user replies no to the initial confirmation message, they are returned back
# to the main menu.         
            
    elif user_input == 'n':
        print('Exiting this option and returning to main menu...')
        
# If the user replies with anything other than "y" or "n", they are shown an error
# message and returned to the main menu.
        
    else:
        print('Invalid response - returning to main menu...')
        
        
# This function allows user to look up if a first word (key) is in the dictionary.
        
def lookup_first_word(opp_words):
    print()
    
# Getting user input for the key.
    
    first_word = str(input('Enter a First Word: '))
    
# If the key is found in the dictionary, a confirmation message is displayed.
    
    if first_word in opp_words:
        print(f'\nThe First Word {first_word} was found in the dictionary.')
        
# If the key is not found, a message declaring so is displayed.
        
    else: 
        print(f'\nThe First Word {first_word} was not found in the dictionary. ')
        
# This function allows a user to look up if a value (second word) is in the dictionary.
        
def lookup_second_word(opp_words):
    print()
    
# Getting user input for the second word.
    
    second_word = str(input('Enter a Second Word: '))
    
# Creating a list of just the values in the dictionary.
    
    opp_values = dict.values(opp_words)
    
# Checking to see if the value exists in the dictionary. If it does, a message
# saying so is depicted. If not, a message saying so is depicted.
    
    if second_word in opp_values:
        print(f'\nThe Second Word {second_word} was found in the dictionary.')
    else:
        print(f'\nThe Second Word {second_word} was not found in the dictionary. ')
        
# This function allows user to look up a word in both the keys and the values.
        
def lookup_all_words(opp_words):
    print()
    
# Getting user input for the word to look up, and assigning it to a variable.
    
    word = str(input('Enter a Word to look up: '))
    
# Creating two lists: one with the dictionary's keys and the other with
# the dictionary's values.
    
    opp_keys = dict.keys(opp_words)
    opp_values = dict.values(opp_words)
    
# Checking to see if the word is in the keys of the dictionary.
    
    if word in opp_keys:
        print(f'\nThe word {word} was found in the dictionary.')
        
# Checking to see if the word is in the values of the dictionary.
        
    elif word in opp_values:
        print(f'\nThe word {word} was found in the dictionary.')
        
# The message below is printed if the word is found in neither the keys or the values.
        
    else:
        print(f'\nThe word {word} was not found in the entire dictionary. ')
    
# This function displays the first words (keys) beginning with letters A through M.
    
def display_first_word_AM(opp_words):
    
# Sorting the dictionary alphabetically.
    
    sorted_first_word_keys = sorted(opp_words.keys())
    print()
    print('First Words A-M in the Dictionary:')
    print('*********************************************')
    
# Restricting the keys printed using an if statement.
    
    for key in sorted_first_word_keys:
        if key >= 'A' and key <= 'M':
            
# Printing the applicable keys.
            
            print(key)
            
# This function displays the first words (keys) beginning with letters N through Z.
            
def display_first_word_NZ(opp_words):
    
# Sorting the dictionary alphabetically.
    
    sorted_first_word_keys = sorted(opp_words.keys())
    print()
    print('First Words N-Z in the Dictionary:')
    print('*********************************************')
    
# Restricting the keys printed using an if statement.
    
    for key in sorted_first_word_keys:
        if key >= 'N' and key <= 'Z':
            
# Printing the applicable keys.
            
            print(key)
            
# This function displays the second words (values) starting with the letters A through M.
            
def display_second_word_AM(opp_words):
    
# Sorting the values in the dictionary alphabetically.
    
    sorted_second_word_values = sorted(opp_words.values())
    print()
    print('Second Words A-M in the Dicionary: ')
    print('*********************************************')
    
# Restricting the values printed using an if statement.
    
    for value in sorted_second_word_values:
        if value >= 'A' and value <= 'M':
            
# Printing the applicable values.
            
            print(value)
            
# This function displays the second words (values) starting with letters N through Z.            
            
def display_second_word_NZ(opp_words):
    
# Sorting the values of the dictionary alphabetically.
    
    sorted_second_word_values = sorted(opp_words.values())
    print()
    print('Second Words N-Z in the Dictionary: ')
    print('*********************************************')
    
# Restricting the values printed using an if statement.
    
    for value in sorted_second_word_values:
        if value >= 'N' and value <= 'Z':
            
# Printing the applicable values.
            
            print(value)
            
# This function allows the user to exit the program.

def exit_program(file_name, opp_words):
    print()
    
# Getting confirmation from the user to exit the program.
    
    user_input = str(input('Are you sure you want to exit the program? (y= yes, n = no) '))
    
# If user replies 'y', a file is created and the pairings are saved in a pickle file.
    
    if user_input == 'y':
        output_file = open(file_name, 'wb')
        pickle.dump(opp_words, output_file)
        
# Closing the output file.
        
        output_file.close()
        
# A confirmation message of the saved file, and the name of the file, is printed.
        
        print()
        print(f'Your data has been saved to the file: {file_name}.')
        print()
        print('Thank you for using the Opposite Words Directory Program!')
        return 'n'
    
# If user replies 'n' to the confirmation message, they are returned to the main menu.
    
    elif user_input == 'n':
        print()
        print('Returning to main menu...')
        return 'y'
    
# If user replies with anything other than "y" or "n", an error message is displayed
# and the user is returned to the main menu.
    
    else:
        print()
        print('Invalid option - returning to main menu...')
        return 'y'

# The main function is called.

if __name__ == '__main__':
    main()            
            
            
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    