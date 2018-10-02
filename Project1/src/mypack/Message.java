package mypack;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
// parts of the message
 public String   theMessage;  // a string
 public String[] someLines;   // an array
 public int      someNumber;  // a primitive


 // So clients can say System.out.println( msg )
 public String toString() {
     String s = "Message: " + theMessage +
                "\nwith an array: ";
     for ( int i = 0 ; i < someLines.length ; i++ ) {
         s += someLines[i] + " ";
     } // end FOR(i)

     s += "\nand a magic #: " + someNumber;

		return s;
	} // end METHOD toString

} // end CLASS Message
