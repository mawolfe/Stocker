import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

/**
 * Periodically fetch stock data.
 */

public class runPeriodicTask {

	public static boolean isRunning=false;
	
	public static Gui gui;
	public static StockLinkedList stockList = new StockLinkedList();
	public static int instanceNum=0;
	
	static protected boolean quit=false;
	//Toolkit toolkit;
	static boolean debug=false;

	static final String myOS = getOS.getOsName();
	static Timer timer;
	static FetchData fetcher = new FetchData();
	static FileIO fio = new FileIO();
    
    public static void main(String[] arg) {
    	gui = new Gui();
    	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gui.setSize(300, 300); // (x,y)
    	gui.setVisible(true);    	
    }
   
//	public void init() {
//        stockList.addStock("GOOG", 536.00, 536.10);	
//        stockList.addStock("YHOO", 15.65, 15.75);
//        stockList.addStock("MENT", 12.60, 12.65);
//        
//	}
		
	public runPeriodicTask(int refreshRate, String tickerSymbol, double hi, double lo) {
		
		//init();									// add stocks
		
		instanceNum = getInstanceNum(tickerSymbol);		
		stockList.addStock(tickerSymbol, lo, hi, instanceNum);
		
	    // toolkit = Toolkit.getDefaultToolkit();
		if (!isRunning) {
			
			timer = new Timer();
			isRunning = true;
			
			if (myOS.equals("windows")) {
	    	    timer.schedule(new periodicMethodWindows(), 0, //initial delay
	    		        refreshRate * 1000); //subsequent rate        	
	        	
	        } else if (myOS.equals("linux")) {
	    	    timer.schedule(new periodicMethodLinux(), 0, //initial delay
	    		        refreshRate * 1000); //subsequent rate
	        } else {
	        	System.out.println(myOS + " operating system not supported.");
	        }
			
		// if it's running, check if any stock ticker symbols are active, if not then quit
		} else if (isRunning) {
			if (!stockList.HasActiveInstanceNum()) {
				System.out.println("No more active ticker symbols. Quitting...");
				quit=true;
			}
		}
	}

	
	class periodicMethodLinux extends TimerTask {
    
		int cnt = 0;
		
		public void run() {
		
			java.util.Date date = new java.util.Date();

			StockNode tempNode;
			String tempLastTrade="";
 		
			if (debug) {System.out.print("quit = " + quit);}			
			if (quit) {System.out.print("Stopped.");	System.exit(0);}
			
			for (int i=0; i<stockList.getNodeCount(); i++) {
				
				tempNode = stockList.getNodeAt(i);
				
				if (tempNode.getInstanceNum()>0) {

					//System.out.print(cnt + " ");
					
					tempLastTrade = fetcher.getLastTrade(tempNode.getTickerSymbol());
	
					gui.setTickerandPrice(tempNode.getTickerSymbol(), tempLastTrade);			
					System.out.print("\n" + new Timestamp(date.getTime()) + ", " + 
											tempNode.getTickerSymbol()+": " + tempLastTrade + 
											" [" + tempNode.getInstanceNum() + "]");		// instance number
					
					fio.writeToFile(
							"//u//agospodn//cs510_OpenSource//" + tempNode.getTickerSymbol() + ".txt", 
							tempLastTrade);		
					
					testThresholds(tempNode, tempLastTrade);
				}
			}
			cnt++;			
		}
	}

	class periodicMethodWindows extends TimerTask {
	    
		int cnt = 0;
		
		public void run() {						

			java.util.Date date = new java.util.Date();

			StockNode tempNode;
			String tempLastTrade="";

			if (debug) {System.out.print("quit = " + quit);}			
			if (quit) {System.out.print("Stopped.");	System.exit(0);}
			
			for (int i=0; i<stockList.getNodeCount(); i++) {
			
				tempNode = stockList.getNodeAt(i);
				
				if (tempNode.getInstanceNum()>0) {
				
					//System.out.print(cnt + " ");
					
					tempLastTrade = fetcher.getLastTrade(tempNode.getTickerSymbol());
	
					gui.setTickerandPrice(tempNode.getTickerSymbol(), tempLastTrade);			
					System.out.print("\n" + new Timestamp(date.getTime()) + ", " + 
											tempNode.getTickerSymbol()+": " + tempLastTrade + 
											" [" + tempNode.getInstanceNum() + "]");		// instance number
					
					fio.writeToFile(
							"C:\\PSU\\cs510_OpenSource\\" + tempNode.getTickerSymbol() + ".txt", 
							tempLastTrade);				
					
					testThresholds(tempNode, tempLastTrade);
				}
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

	public void testThresholds(StockNode _stockNode, String _lastTrade) {

		if (Double.parseDouble(_lastTrade) < _stockNode.getThreshLow()) {
			System.out.println("*** " + _stockNode.getTickerSymbol() + " is lower than low threshold. ***");

			// send email
			if (!_stockNode.getEmailed()) {		// if it hasn't been emailed
				System.out.println("send email...");
				_stockNode.setEmailed(true);	// set emailed in node to true
			}
		
		} else if (Double.parseDouble(_lastTrade) > _stockNode.getThreshHigh()) {
			System.out.println("*** " + _stockNode.getTickerSymbol() + " is higher than high threshold. ***");

			// send email
			if (!_stockNode.getEmailed()) {		// if it hasn't been emailed
				System.out.println("send email...");
				_stockNode.setEmailed(true);	// set emailed in node to true
			}

		} else {
			_stockNode.setEmailed(false);	// set emailed in node to false
		}

	}

	public void disableTickerSymbol(String tickerSymb) {
		
		// find the ticker symbol's node
		StockNode disableNode = stockList.findTickerSymb(tickerSymb);
		
		// if the ticker symbol is found, multiply its instanceNum by -1 // 0 (inactive)
		if (disableNode != null) {
			
			stockList.findTickerSymb(tickerSymb).setInstanceNum(stockList.findTickerSymb(tickerSymb).getInstanceNum()*-1);
			System.out.println("Removed ticker symbol " + tickerSymb);
			
			// if no nodes are active, quit
			if (!stockList.HasActiveInstanceNum()) {
				System.out.println("No more active ticker symbols. Quitting...");
				quit=true;
			}
				
		} else {
			System.out.println(tickerSymb + " is not a ticker symbol being stocked.");
		}
	}

	
	/**
	 * getInstanceNum takes the ticker symbol as an argument and returns
	 * 		the static variable instanceNum incremented by 1.
	 * 	    OR
	 * 		the instanceNum if the ticker symbol has already been added
	 * @param tickerSymb
	 * @return instanceNum
	 */
	public int getInstanceNum(String tickerSymb) {
		
		if (stockList.findTickerSymb(tickerSymb)==null) {
System.out.println("instance num = " + instanceNum);
			return instanceNum+1;
		} else {
System.out.println("instance num = " + instanceNum);			
			return stockList.findTickerSymb(tickerSymb).getInstanceNum();		
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
