//------------------------------------------------------------------------------------------------------------
//
// Name: Joachim Isaac
// Course: CS 2143, Fall 19, Dr. Stringfellow.
// Purpose: To create a class that changes plain text into an encoded text and back to plain text.
// It will have the functionality of encoding and decoding and viewing the message.
//--> This is the implementation of an Interface for that class in JAVA.<--
// Extra Credit done: (I included the default and parameterized constructor)
//
//-----------------------------------------------------------------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Cryptograph implements Crypto_Interface {

    public static void main(String[] args) {

        //The number of times files are read in, to be encrypted and decrypted.
        int iterations = 0;

        //Contains the set we are currently on
        //Set(meaning the set of files we are inputting)
        int set_number = 1;

        //Reads in the number of times files are read in, to encrypt and decrypt.
        //And prints the set the user is currently dealing with.
        iterations = read_in_iterations(iterations, set_number);

        //Loops based on the number of iterations that was inputted.
        while(iterations > 0) {
            File input_file = null;
            File output_file = null;

            //This grabs the name of the input file that was inputted.
            input_file = get_input_File();

            //ArrayList to hold the plaintext.
            ArrayList<String> messages = new ArrayList<String>();

            //Array list to hold the encrypted text.
            ArrayList<String>encrypted_messages = new ArrayList<String>();

            //This grabs the name of the file that was inputted.
            output_file = get_output_File();

            //Reads in the messages from the input file and places them into an
            //ArrayList, it then returns that ArrayList.
            messages = read_in_message(input_file);


            //Takes in the messages array, encrypts the strings in it and
            //returns the array of encrypted messages.
            encrypted_messages = encrypt_messages(messages);


            //Prints the results from the plaintext array and the encrypted array.
            print_results(output_file,messages,encrypted_messages);

            iterations--;
            set_number++;

            //Nicely splits input sequences for the user.
            set_prompt(set_number,iterations);
        }
    }



    //Member variable
    private String message = "";

    //Default constructor.
    public Cryptograph() {
        this.message = null;
    }

    //Parameterized constructor.
   public Cryptograph(String text) {
        this.message = text;
    }







    //Reads in the number of times files are read in, to encrypt and decrypt.
    //And prints the set the user is currently dealing with.
    public static int read_in_iterations(int iterations, int set_number){
        System.out.println("Name: Joachim Isaac\nProgram2: Cryptograph\n");

        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the number of files you want to encrypt:");

        //Reads in the number of times files are read in, to be encrypted and decrypted.
        iterations = input.nextInt();
        if(iterations < 0){
            iterations = 0;
        }

        System.out.println("Set "+ set_number +": ");

        return iterations;
    }


    //Sets the message with a value.
    @Override
    public void set_message(String plain_text) {
        if (plain_text.length() > 0) {
            this.message = plain_text;
        } else {//Think through this later.
            System.out.println("Please enter a valid message");
        }
    }


    //Gets the value that message currently has.
    @Override
    public String get_message() {
        if (this.message == null) {
            return "No current message set.";
        }
        return this.message;
    }

    public abstract void transform();
    public abstract void revert();


    public  void Encrypt(){
        transform();
    }

    public void Decrypt(){
        revert();
    }





//    //Encrypts the message.
//    @Override
//    public void Encrypt() {
//        //This is the value we shift the ASCII by.
//        int k = 1;
//
//        // changed string
//        String encodedS = "";
//
//        // iterates for every characters
//        for (int i = 0; i < this.message.length(); ++i) {
//            // ASCII value
//            int val = this.message.charAt(i);
//
//            // store the duplicate
//            int dup = k;
//
//            // if k-th ahead character exceed 'z'
//            if (val + k > 122) {
//                k -= (122 - val);
//                k = k % 26;
//
//                encodedS += (char) (96 + k);
//            } else {
//                encodedS += (char) (val + k);
//            }
//
//            k = dup;
//        }
//         this.message = encodedS;
//    }


//    //Decrypts the message.
//    @Override
//    public void Decrypt() {
//        //This is the value we shift the ASCII by.
//        int k = -1;
//
//        //Changed string
//        String encodedS = "";
//
//        //iterates for every characters
//        for (int i = 0; i < this.message.length(); ++i) {
//
//            //ASCII value
//            int val = this.message.charAt(i);
//
//            //store the duplicate
//            int dup = k;
//
//            //if k-th ahead character exceed 'z'
//            if (val + k > 122) {
//                k -= (122 - val);
//                k = k % 26;
//
//                encodedS += (char) (96 + k);
//            } else {
//                encodedS += (char) (val + k);
//            }
//
//            k = dup;
//        }
//        this.message = encodedS;
//    }


    //Reads in the file name and returns it as a File variable.
    public static File get_input_File() {
        System.out.println("Please enter your input file's name:");
        Scanner input = new Scanner(System.in).useDelimiter("\\s*fish\\s*");
        String file_name = input.nextLine();
        File c_file = new File(file_name.strip());
        return c_file;
    }


    //Reads in the file name and returns it as a File variable.
    public static File get_output_File() {
        System.out.println("Please enter your output file's name:");
        Scanner input = new Scanner(System.in).useDelimiter("\\s*fish\\s*");
        String file_name = input.nextLine();
        File c_file = new File(file_name.strip());
        return c_file;
    }


    //Out-of-place implementation rather than in-place:
    //Takes in a file and reads in the text line by line.
    //It returns an ArrayList containing each line of text.
    public static ArrayList<String>read_in_message(File input_file_name) {
        try {
            //Another array is declared(avoided an in-place algorithm)
            ArrayList<String> words = new ArrayList<String>();

            //Works with file object -->input_file_name<--
            //In this case it will be reading
            //from the input file rather than from the console/terminal.
            Scanner infile = new Scanner(input_file_name);

            //String which holds each line.
            String line = "";

            //Loops each time while there is a next line to read.
            while (infile.hasNextLine()) {

                //Reads in a line from the file and stores it into line.
                line = infile.nextLine();

                //Adds that line into the ArrayList words.
                words.add(line);

            }//End of while loop.

            return words;

            //Catch exception: if the file name was not found.
            //It prints "File not found".
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return null;
    }


    //This takes in an ArrayList of plaintext and encrypts it.
    //Then it returns the encrypted list.(not an in-place algorithm)
    public static ArrayList<String>encrypt_messages(ArrayList<String> messages){
        Cryptograph crypto = new Cryptograph();
        ArrayList<String> Encrypted_list = new ArrayList<String>();

        for(int i = 0; i < messages.size(); i++){
            crypto.set_message(messages.get(i));
            crypto.Encrypt();
            Encrypted_list.add(crypto.get_message());
        }
    return Encrypted_list;
    }

    //Takes in the plain-text array and the encrypted array and prints both the results.
    public static void print_results(File output_file_name,ArrayList<String> plaintext,
        ArrayList<String> encrypted_text){
            try {

                PrintWriter output = new PrintWriter(output_file_name);

                output.println("Name: Joachim Isaac\nProgram2: Cryptograph\n");

                for(int i =0; i < plaintext.size();i++ ){
                    output.println("Plain text: " + plaintext.get(i) +
                        "\n"+"Encrypted text: " + encrypted_text.get(i) + "\n");
                }

                //Close file.
                output.close();

            } catch (IOException ex) {
                System.out.println("File not found");
            }
    }

    //Nicely splits input sequences for the user.
    public static void set_prompt(int set_number, int iterations){
        //Prevents it from printing when iterations equals 0.
        if(iterations > 0) {
            System.out.println("######################################"+"\n");
            System.out.println("Set " + set_number + ": ");
        }
        if(iterations <= 0 ){
            System.out.println("DONE!\nTIME TO CHECK THE OUTPUT!");
        }
    }





}

