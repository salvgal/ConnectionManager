package mypack;
import java.io.*;

public class ConnectionManager {

	//Message passing in a centralized environment.
	//It is an synchronous message passing.
	
	
   static private PipedInputStream   pis1;
   static private PipedOutputStream  pos1;

   static private PipedInputStream   pis2;
   static private PipedOutputStream  pos2;

   static private ObjectOutputStream oos;
   static private ObjectInputStream  ois;

   public static void main ( String argv[] ) {
      try {

      // set up a pipe - It is just connected in one side, not two sides. It is enough one way.
      System.out.println( "Pipe setup" );
      pos1 = new PipedOutputStream( );  //with no argument means not connected
      pis1 = new PipedInputStream ( pos1 ); //with one argument means the corresponding end of the pipe that is connected

      pos2 = new PipedOutputStream( );
      pis2 = new PipedInputStream ( pos2 );

      //It has two pipes: one from sender to receiver, the other from the receiver to the sender
      System.out.println( "Object creation" );
      Receiver r = new Receiver( pis2, pos1, ois, oos );
      Sender s   = new Sender  ( pis1, pos2, ois, oos );

      System.out.println( "Thread execution" );
      r.start(); s.start();
      } // end TRY
      catch ( Exception exc ) {
            System.out.println( exc );
      } // end CATCH
   }

} // end CLASS ConnectionManager