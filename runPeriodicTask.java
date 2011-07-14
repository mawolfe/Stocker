import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Periodically fetch stock data.
 */

public class runPeriodicTask {

	//Toolkit toolkit;
	boolean debug=true;

	final String myOS = getOS.getOsName();
	Timer timer;
	StockLinkedList stockList = new StockLinkedList();
    FetchData fetcher = new FetchData();
    FileIO fio = new FileIO();
    
	public void init() {
        stockList.addStock("GOOG", 536.00, 536.10);	
        stockList.addStock("YHOO", 15.65, 15.75);
        stockList.addStock("MENT", 12.60, 12.65);        
	}
	
	public runPeriodicTask(int refreshRate) {
		
		init();									// add stocks
	    // toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
	    
        if (myOS.equals("windows")) {
    	    timer.schedule(new periodicMethodWindows(), 0, //initial delay
    		        refreshRate * 1000); //subsequent rate        	
        	
        } else if (myOS.equals("linux")) {
    	    timer.schedule(new periodicMethodLinux(), 0, //initial delay
    		        refreshRate * 1000); //subsequent rate        	
        } else {
        	System.out.println(myOS + " operating system not supported.");
        }

	}

	
	class periodicMethodLinux extends TimerTask {
    
		int cnt = 0;
		
		public void run() {
		
			java.util.Date date = new java.util.Date();
			System.out.print(cnt + " ");
			System.out.println(new Timestamp(date.getTime()));
	 
			StockNode tempNode;
			String tempLastTrade="";
			
			for (int i=0; i<stockList.getNodeCount(); i++) {
				if (debug) {System.out.print(stockList.getNodeAt(i).getTickerSymbol()+": ");}
				
				tempNode = stockList.getNodeAt(i);
				tempLastTrade = fetcher.getLastTrade(stockList.getNodeAt(i).getTickerSymbol());
				
				fio.writeToFile(
						"//u//agospodn//cs510_OpenSource//" + tempNode.getTickerSymbol() + ".txt", 
						tempLastTrade);		
				
				testThresholds(tempNode, tempLastTrade);
			}
			cnt++;
			
			/*
			if (numWarningBeeps > 0) {
				toolkit.beep();
				System.out.println("Beep!");
				numWarningBeeps--;
			} else {
				toolkit.beep();
				System.out.println("Time's up!");
				timer.cancel(); //Not necessary because we call System.exit
				System.exit(0); //Stops the AWT thread (and everything else)
			}
		 	*/			
		}
	}

	class periodicMethodWindows extends TimerTask {
	    
		int cnt = 0;
		
		public void run() {						

			java.util.Date date = new java.util.Date();
			System.out.print(cnt + " ");
			System.out.println(new Timestamp(date.getTime()));

			StockNode tempNode;
			String tempLastTrade="";

			for (int i=0; i<stockList.getNodeCount(); i++) {
				if (debug) {System.out.print(stockList.getNodeAt(i).getTickerSymbol()+": ");}
				
				tempNode = stockList.getNodeAt(i);
				tempLastTrade = fetcher.getLastTrade(stockList.getNodeAt(i).getTickerSymbol());
				
				fio.writeToFile(
						"C:\\PSU\\cs510_OpenSource\\" + tempNode.getTickerSymbol() + ".txt", 
						tempLastTrade);				
				
				testThresholds(tempNode, tempLastTrade);
			}
			cnt++;			
		}
	}

	public void testThresholds(StockNode _stockNode, String _lastTrade) {

		if (Double.parseDouble(_lastTrade) < _stockNode.getThreshLow()) {
			System.out.println("*** " + _stockNode.getTickerSymbol() + " is lower than low threshold. ***");
			// send email
		} else if (Double.parseDouble(_lastTrade) > _stockNode.getThreshHigh()) {
			System.out.println("*** " + _stockNode.getTickerSymbol() + " is higher than high threshold. ***");
			// send email
		}

	}
	/*
  	public static void main(String args[]) {
  		
  		int refreshRate = 1;		// Updates every "refreshRate" seconds
		// what's the fastest the code can execute? 1,2,5 seconds?
  		
	  	System.out.println("About to schedule task.");    
	  	new runPeriodicTask(refreshRate);
	  	System.out.println("Task scheduled.");
	}
	*/
}