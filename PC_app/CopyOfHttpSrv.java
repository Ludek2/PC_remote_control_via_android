import java.net.*;
import java.awt.AWTException;
import java.io.*;
import java.util.regex.*;
import java.util.concurrent.*;

public class CopyOfHttpSrv {
	
	public static void main( String argv[] ) throws IOException, AWTException {
		
		//Receiver Rcv= new Receiver("X=100&Y=100&MOUSE_MOVE");
		Executor executor = Executors.newFixedThreadPool(3);
		ServerSocket ss = new ServerSocket( 80 );
		while ( true )
			executor.execute( new HttpdConnection( ss.accept() ) );
	}
	  
}

class HttpdConnection implements Runnable {
  
  Socket client;
  
  HttpdConnection ( Socket client ) throws SocketException {
    this.client = client;
  }
  
  public void run() {
    
	  try {
      
    	BufferedReader in = new BufferedReader(
        new InputStreamReader(client.getInputStream(), "8859_1" ) );
      OutputStream out = client.getOutputStream();
      PrintWriter pout = new PrintWriter(new OutputStreamWriter(out, "8859_1"), true );
      String request = in.readLine();
      System.out.println( "Request: "+request);
      pout.println( "I am server, I received: " +request);
      
      if(request!=null){ // sometimes
    	  Receiver Rcv= new Receiver( request );
      }
      else{
    	 System.out.println( "null received???? " );
      }
      
      client.close();
    } catch ( IOException e ) {
      System.out.println( "I/O error " + e ); } catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
  }
   
}
