public class SubClass extends Cryptograph {



    public void transform(){
                //This is the value we shift the ASCII by.
        int k = 1;

        // changed string
        String encodedS = "";

        // iterates for every characters
        for (int i = 0; i < this.message.length(); ++i) {
            // ASCII value
            int val = this.message.charAt(i);

            // store the duplicate
            int dup = k;

            // if k-th ahead character exceed 'z'
            if (val + k > 122) {
                k -= (122 - val);
                k = k % 26;

                encodedS += (char) (96 + k);
            } else {
                encodedS += (char) (val + k);
            }

            k = dup;
        }
         this.message = encodedS;
    }


    public void revert(){
    //This is the value we shift the ASCII by.
        int k = -1;

        //Changed string
        String encodedS = "";

        //iterates for every characters
        for (int i = 0; i < this.message.length(); ++i) {

            //ASCII value
            int val = this.message.charAt(i);

            //store the duplicate
            int dup = k;

            //if k-th ahead character exceed 'z'
            if (val + k > 122) {
                k -= (122 - val);
                k = k % 26;

                encodedS += (char) (96 + k);
            } else {
                encodedS += (char) (val + k);
            }

            k = dup;
        }
        this.message = encodedS;

    }


}
