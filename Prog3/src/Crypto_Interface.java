
//------------------------------------------------------------------------------------------------------------
//
// Name: Joachim Isaac
// Course: CS 2143, Fall 19, Dr. Stringfellow.
// Purpose: To create a class that changes plain text into an encoded text.
// we wil be able to encode and decode and view the message.
//-->This is the implementation of an Interface for that class in JAVA.<--
//
//-----------------------------------------------------------------------------------------------------------
public interface Crypto_Interface{



    // 1. Takes in a message an sets it.
    public void set_message(String plain_text);


    // 2. Gets message and returns that value.
    public String get_message();


    // 3. Encrypts message.
    public  void Encrypt();


    // 4. Decrypts message.
    public void Decrypt();

}