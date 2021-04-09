package application;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class sockServer implements Runnable
{
	   Socket csocket;
	   String ipString;
	   char threadType;

	   static Vector<String> vec = new Vector<String>(5);
	   
	   static final String newline = "\n";
	   static int first_time = 1;
	   
	   static int port_num = 3333;
	   
	   static int numOfConnections = 0;
	   static int numOfMessages = 0;
	   static int max_connections = 5;
	   static int numOfTransactions = 0; 

	   sockServer(Socket csocket, String ip)
	   {
	      this.csocket  = csocket;
	      this.ipString = ip;
	   } 

	   public static void runSockServer()   // throws Exception
	   {
	     boolean sessionDone = false;
	  
	     ServerSocket ssock = null;
	   
	     try
	     {
		   ssock = new ServerSocket(port_num);
	     }
	     catch (BindException e)
	     {
		    e.printStackTrace();
	     }
	     catch (IOException e)
	     {
		    e.printStackTrace();
	     }
	 
	     // update the status text area to show progress of program
	     try 
	     {
		     InetAddress ipAddress = InetAddress.getLocalHost();
		     sss5.textArea.appendText("IP Address : " + ipAddress.getHostAddress() + newline);
	     }
	     catch (UnknownHostException e1)
	     {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
	     }
	 
	     sss5.textArea.appendText("Listening on port " + port_num + newline);
	 
	     //
	     // initialize the hash table to the following keys or if file hash table data exists, then use it
	     //
	     try
         {
     	      File f = new File("hashTableData.txt");
     	      if (f.exists())
     	      {
                 FileReader reader = new FileReader("hashTableData.txt");
                 BufferedReader br = new BufferedReader(reader);
               
                 String line = br.readLine();
                 while (line != null)
                 {
                	String args[]   = line.split("\\,");
                	
                	String key     = args[0];
                	int transCount = Integer.parseInt(args[1]);
                	int tickets    = Integer.parseInt(args[2]);
                	double dollars = Double.parseDouble(args[3]);
                	
 					clients.put(key, new kiosk(key,
 					    	            transCount,
 						                   tickets,
 				                           dollars));
 					
                 	line = br.readLine();
                 }
                 
                 br.close();
                
                 //
                 // add homemade key to HASHTABLE data structure
                 //
                 int    transCount=0, ticketCount=0;
                 double dollarCount=0.0;
                 
                 List<String> v = new ArrayList<String>(clients.keySet());
         	     for (String key : v)
        	     {
        	        transCount  = transCount  + clients.get(key).getTransactions();
        	        ticketCount = ticketCount + clients.get(key).getTickets();
        	        dollarCount = dollarCount + clients.get(key).getDollars();
        	     }        
         	    
         	     
                 int currentSize     = clients.size();
         	     sss5.textArea_3.setText("Total# : " + currentSize + newline);
         	    
         	     clients.put("totalKiosk", new kiosk("totalKiosk",
		    	               transCount,
			                   ticketCount,
	                           dollarCount));
     	      }
     	      else
     	      {
     	    	 //
     	    	 // add default kiosks to start out with
     	    	 //
     		     clients.put("kiosk#001", new kiosk("kiosk#001", 0, 0, 0.0));
     		     clients.put("kiosk#002", new kiosk("kiosk#002", 0, 0, 0.0));
     		     clients.put("kiosk#003", new kiosk("kiosk#003", 0, 0, 0.0));
     		     
     		     
     		     //
                 // add homemade key, "totalKiosk", to HASHTABLE data structure
                 //
                 int    transCount=0, ticketCount=0;
                 double dollarCount=0.0;
                 
                 List<String> v = new ArrayList<String>(clients.keySet());
         	     for (String key : v)
        	     {
        	        transCount  = transCount  + clients.get(key).getTransactions();
        	        ticketCount = ticketCount + clients.get(key).getTickets();
        	        dollarCount = dollarCount + clients.get(key).getDollars();
        	     }        
         	     
         	     
         	     int currentSize     = clients.size();
        	     sss5.textArea_3.setText("Total# : " + currentSize + newline);
        	     
         	     clients.put("totalKiosk", new kiosk("totalKiosk",
		    	               transCount,
			                   ticketCount,
	                           dollarCount));   
     	      }
     	 }
         catch(Exception e2)
         {   
     	    e2.printStackTrace(); 
         }		
	     
	     sessionDone = false;
	     while (sessionDone == false)
	     {
	        Socket sock = null;
		    try
		    {
		       //	
  	     	   // blocking system call
		       //	
			   sock = ssock.accept();
		    }
		    catch (IOException e)
		    {
			   e.printStackTrace();
		    }
		 
		    // update the status text area to show progress of program
	        sss5.textArea.appendText("Client Connected : " + sock.getInetAddress() + newline);
	        
	        //
	        // start a NEW THREAD process
	        //
	        new Thread(new sockServer(sock, sock.getInetAddress().toString())).start();
	     }
	 
	     try 
	     {
		    ssock.close();
	     }
	     catch (IOException e) 
	     {
		    e.printStackTrace();
	     }
	}	  

	   
	static synchronized void hashOperation(char type, String key, String ticks, String d)
	{
		switch (type)
		{
			case 'T':
				long start = System.nanoTime();
				
				if (clients.containsKey(key) == true)
		        {
					clients.get(key).incrementTrans();
					clients.get(key).addTickets(Integer.parseInt(ticks));
					clients.get(key).addDollars(Double.parseDouble(d));
					
					long finish = System.nanoTime();
					
					long timeElapsed = finish - start;
					sss5.textArea_3.appendText(" Time Nano-Seconds : " + timeElapsed + newline);
					sss5.textArea_3.appendText("Time Milli-Seconds : " + timeElapsed / 1000000 + newline);
					
					if (clients.containsKey("totalKiosk") == true)
					{
						clients.get("totalKiosk").incrementTrans();
						clients.get("totalKiosk").addTickets(Integer.parseInt(ticks));
						clients.get("totalKiosk").addDollars(Double.parseDouble(d));
					}
		        }	
			break;
		}
	}

	//
	// add a new KIOSK entry and number to the hash table
	//
	public static void createNewKiosk()
	{
		int nextKioskNumber, currentSize = 0;
		String kioskString;
		
		currentSize     = clients.size();
		nextKioskNumber = currentSize + 1;
		
		nextKioskNumber = nextKioskNumber - 1;
		kioskString     = "kiosk#" + String.format("%03d", nextKioskNumber);
				
		clients.put(kioskString, new kiosk(kioskString, 0, 0, 0.0));
	}

	//
	// method to write out hash table data
	//
	public static void writeHashTableData()
	{
		FileWriter fwg = null;
        try 
        {
        	// open the file in append write mode
        	fwg = new FileWriter("hashTableData.txt");
        }
        catch (IOException e)
        {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
   	    
        List<String> v = new ArrayList<String>(clients.keySet());
	    Collections.sort(v);
	    
        BufferedWriter bwg = new BufferedWriter(fwg);
        PrintWriter outg   = new PrintWriter(bwg);
		
	    for (String key : v)
	    {
	    	if (key.equals("totalKiosk") == true)
	    		continue;
	    	
	        outg.println(clients.get(key).forFileOutput());
	    }
	        
        outg.close();
	}
	
	
	//
	// get all transaction data from the hash table keys
	//
	public static String getAllTransactions()
	{
		String rs="";
		
		List<String> v = new ArrayList<String>(clients.keySet());
	    Collections.sort(v);
		
	    for (String str : v) 
	        rs = rs + clients.get(str.toString()) + "\r\n";
				
		return rs;
	}

	
	//
	// CLIENT THREAD CODE - This is the thread code that ALL clients will run()
	//
	public void run()
	{
	   try
	   {
		  boolean session_done = false; 
	      long threadId;
	      String clientString;
	      String keyString = "";
	    
	      threadId = Thread.currentThread().getId();
	      
	      numOfConnections++;
	      
	      sss5.textArea.appendText("Num of Connections = " + numOfConnections + newline);
	      
	      keyString = ipString + ":" + threadId;
	      
	      if (vec.contains(keyString) == false)
	      {
	    	    int counter = 0;
	        	vec.addElement(keyString);
	        	
	        	sss5.textArea_2.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{
	        		sss5.textArea_2.appendText(en.nextElement() + " || ");
	        		
	        		if (++counter >= 6)
	        		{
	        			sss5.textArea_2.appendText("\r\n");
	        			counter = 0;
	        		}
	        	}
	      }
	       
	      PrintStream pstream = new PrintStream (csocket.getOutputStream());
	      BufferedReader rstream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
	       
	      while (session_done == false)
	      {
	       	if (rstream.ready())   // check for any data messages
	       	{
	              clientString = rstream.readLine();

	              
	              //
	              // write to transaction log
	              //
	              fileIO transLog = new fileIO();
	              transLog.wrTransactionData("SERVER : " + clientString);
	              
	              	              
	              // update the status text area to show progress of program
	   	           sss5.textArea.appendText("RECV : " + clientString + newline);
	     	       
	     	       // update the status text area to show progress of program
	     	       sss5.textArea.appendText("RLEN : " + clientString.length() + newline);
	              
	              if (clientString.length() > 128)
	              {
	           	   session_done = true;
	           	   continue;
	              }

	              if (clientString.contains("quit"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("QUIT"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("Quit"))
	              {
	                 session_done = true;
	              }
	              else if (clientString.contains("Query>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  
	            	  if (clients.containsKey(tokens[1]) == true)
	            	  {
	            		  pstream.println(clients.get(tokens[1]).toString());  
	            	  }
	            	  else
	            	  {
	            		  pstream.println("NACK : ERROR : No such kiosk number!");
	            	  }
	              }
	              else if (clientString.contains("Transaction>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  String args[]   = tokens[1].split("\\,");
	            	  
	            	  if (clients.containsKey(args[0]) == true)
	            	  {
	            		  hashOperation('T', args[0], args[1], args[2]);
	            		  
	            		  pstream.println("ACK");
	            	  }
	            	  else
	            	  {
	            		  pstream.println("NACK : ERROR : No such kiosk number!");
	            	  }
	              }
	              else if (clientString.contains("Configure>"))
	              {
	            	  String tokens[] = clientString.split("\\>");
	            	  
	            	  if (tokens.length == 2)
	            	  {
	            	     clients.put(tokens[1], new kiosk(tokens[1], 0, 0, 0.0));
	            	     
	            	     pstream.println("ACK");
	            	  }
	            	  else
	            	  {
	            		  pstream.println("NACK : ERROR : Invalid number of parameters!");
	            	  }
	              }
	              else if (clientString.contains("Date>"))
	              {
	            	numOfMessages++;
	            	  
	            	// Create an instance of SimpleDateFormat used for formatting 
	            	// the string representation of date (month/day/year)
	            	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	            	// Get the date today using Calendar object.
	            	Date today = Calendar.getInstance().getTime();
	            	
	            	// Using DateFormat format method we can create a string 
	            	// representation of a date with the defined format.
	            	String reportDate = df.format(today);

	            	//
	            	// Print what date is today! Send to the individual THREAD
	            	//
	            	pstream.println("Num Of Messages : " + numOfMessages + "   Simple Date: " + reportDate);
	              }
	              else
	              {
	            	  pstream.println("NACK : ERROR : No such command!");
	              }
	       	   }
	         			    		        	
	           Thread.sleep(500);
	           
	        }    // end while loop
	
            keyString = ipString + ":" + threadId;
	      
	        if (vec.contains(keyString) == true)
	        {
	        	int counter = 0;
	        	vec.removeElement(keyString);
	        	
	        	sss5.textArea_2.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{        		     		
                    sss5.textArea_2.appendText(en.nextElement() + " || ");
	        		
	        		if (++counter >= 6)
	        		{
	        			sss5.textArea_2.appendText("\r\n");
	        			counter = 0;
	        		}
	        	}

  	            //sss5.textArea_2.repaint();
	        }
	      
	        numOfConnections--;

	        // close client socket
	        csocket.close();
	       
	        // update the status text area to show progress of program
		     sss5.textArea.appendText("Child Thread : " + threadId + " : is Exiting!!!" + newline);
		     sss5.textArea.appendText("Num of Connections = " + numOfConnections);
		     
	     } // end try  
	 
	     catch (SocketException e)
	     {
		  // update the status text area to show progress of program
	      sss5.textArea.appendText("ERROR : Socket Exception!" + newline);
	     }
	     catch (InterruptedException e)
	     {
		  // update the status text area to show progress of program
	      sss5.textArea.appendText("ERROR : Interrupted Exception!" + newline);
	     }
	     catch (UnknownHostException e)
	     {
		  // update the status text area to show progress of program
	      sss5.textArea.appendText("ERROR : Unkonw Host Exception" + newline);
	     }
	     catch (IOException e) 
	     {
	     // update the status text area to show progress of program
	      sss5.textArea.appendText("ERROR : IO Exception!" + newline);
	     }     
	     catch (Exception e)
	     { 
		  numOfConnections--;
		  
		  // update the status text area to show progress of program
	      sss5.textArea.appendText("ERROR : Generic Exception!" + newline);
	     }
	   
	  }  // end run() thread method
}
