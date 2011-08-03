import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData {
	boolean debug=false;

	// Input: stock ticker symbol
    // Output: Last Trade value       
    protected String getLastTrade(String tickerSymbol) {
        if (debug) {System.out.println("Fetching Last Trade...");}
       
        String inputLine;
       
        try {
            URL URL_lastTrade = new URL("http://finance.yahoo.com/q?s=" + tickerSymbol);
            BufferedReader in = new BufferedReader(new InputStreamReader(URL_lastTrade.openStream()));               
            if (debug) {System.out.println(URL_lastTrade);}                                               
       
            // Specify before/after items //
			String beforeLastTrade = "Last Trade:</th><td class=\"yfnc_tabledata1\"><big><b><span id=\"yfs_l10_" + tickerSymbol.toLowerCase() + "\">";
			String afterLastTrade = "</span></b></big></td></tr><tr><th scope=\"row\"";

			while ((inputLine = in.readLine()) != null) {
	
                if (inputLine.contains(beforeLastTrade)) {
                	String LastTrade = inputLine.substring(inputLine.indexOf(beforeLastTrade)+beforeLastTrade.length(), inputLine.indexOf(afterLastTrade));
                    
                    //System.out.print("Current Price: " + LastTrade);
                    return LastTrade;
                }
            }
               
        } catch (MalformedURLException MUE) {
            MUE.printStackTrace();                   
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { } // end try
		return "0";
    }

    protected String getStringFromURL( 
		  	String outputMessage, 
		  	String URLstarter,
		  	String URLkey,
		  	String URLender,
		  	String beforeData, 
		  	String afterData) {
	    System.out.println(outputMessage);
	
	    String inputLine;
	   
	    try {
	       
	        URL myURL = new URL(URLstarter + URLkey + URLender);
	        BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));               
	        System.out.println(myURL);                                               
	        
	        while ((inputLine = in.readLine()) != null) {
	                              
	            if (inputLine.contains(beforeData)) {
	                return inputLine.substring(inputLine.indexOf(beforeData)+beforeData.length(), inputLine.indexOf(afterData,inputLine.indexOf(beforeData)));
	            }
	         
	        }	// end while
	       
	    } catch (MalformedURLException MUE) {
	        MUE.printStackTrace();                   
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } finally {} // end try
		return "";
    }


    protected String getStringFromURL( 
		  	String URLstarter,
		  	String URLkey,
		  	String URLender,
		  	String beforeData, 
		  	String afterData) {

	    String inputLine;
	   
	    try {
	       
	        URL myURL = new URL(URLstarter + URLkey + URLender);
	        BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));               
	        System.out.println(myURL);                                               
	        
	        while ((inputLine = in.readLine()) != null) {
	                              
	            if (inputLine.contains(beforeData)) {
	                return inputLine.substring(inputLine.indexOf(beforeData)+beforeData.length(), inputLine.indexOf(afterData,inputLine.indexOf(beforeData)));
	            }
	         
	        }	// end while
	       
	    } catch (MalformedURLException MUE) {
	        MUE.printStackTrace();                   
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } finally {} // end try
		return "";
	}

    /*
    protected void fetchAll(StockLinkedList _stockList) {
        System.out.println("Node count = " + _stockList.getNodeCount());
        System.out.println("Fetching data...\n\n");

        for (int i=0; i<_stockList.getNodeCount(); i++) {

        	String tickSym = _stockList.getNodeAt(i).getTickerSymbol();
        	StockNode currentStockNode = _stockList.getNodeAt(i); 
        	        	
        	currentStockNode.setLastTrade(Double.parseDouble(getStringFromURL(
        			"last trade", "http://finance.yahoo.com/q?s=", tickSym, "", 
        			"Last Trade:</th><td class=\"yfnc_tabledata1\"><big><b><span id=\"yfs_l10_" + tickSym.toLowerCase() + "\">", 
        			"</span></b></big></td></tr><tr><th scope=\"row\"")));
      

          	currentStockNode.setTotalCashMRQ(getStringFromURL(
 
        			"tcMRQ", "http://finance.yahoo.com/q/ks?s=", tickSym, "", 
        			"Total Cash (mrq):</td><td class=" + "\"" + "yfnc_tabledata1" + "\"" + ">", 
        			"</td></tr><tr><td"));

        	currentStockNode.setTotalDebtMRQ(getStringFromURL(
        			"tdMRQ", "http://finance.yahoo.com/q/ks?s=", tickSym, "", 
        			"Total Debt (mrq):</td><td class=\"yfnc_tabledata1\">", 
        	"</td></tr><tr><td"));
        }	
   
    
    protected void fetchAllExplicitly(StockLinkedList _stockList) {
        System.out.println("Node count = " + _stockList.getNodeCount());
        System.out.println("Fetching data...\n\n");

        for (int i=0; i<_stockList.getNodeCount(); i++) {

        	String tickSym = _stockList.getNodeAt(i).getTickerSymbol();
        	StockNode currentStockNode = _stockList.getNodeAt(i); 
        	
        	currentStockNode.setLastTrade(getLastTrade(tickSym));
        	//currentStockNode.setTotalCashMRQ(getTcMRQ(tickSym));
        	//currentStockNode.setTotalDebtMRQ(getTdMRQ(tickSym));        	
        }	
    }


    protected String getTcMRQ(String tickerSymbol) {
        System.out.println("Fetching Total Cash MRQ...");

        String inputLine;
       
        try {
           
            URL URL_tcMRQ = new URL("http://finance.yahoo.com/q/ks?s=" + tickerSymbol);
            BufferedReader in = new BufferedReader(new InputStreamReader(URL_tcMRQ.openStream()));               
            System.out.println(URL_tcMRQ);                                               
           
            String beforeTotalCashmrq = "Total Cash (mrq):</td><td class=" + "\"" + "yfnc_tabledata1" + "\"" + ">"; 
            String afterTotalCashmrq = "</td></tr><tr><td";
                                  
            while ((inputLine = in.readLine()) != null) {           
                if (inputLine.contains(beforeTotalCashmrq)) {                   
                    return inputLine.substring(inputLine.indexOf(beforeTotalCashmrq)+beforeTotalCashmrq.length(), inputLine.indexOf(afterTotalCashmrq,inputLine.indexOf(beforeTotalCashmrq)));
                }
        	}	// end while
        } catch (MalformedURLException MUE) {
            MUE.printStackTrace();                   
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { } // end try
		return "";
    }
    
    protected String getTdMRQ(String tickerSymbol) {
        System.out.println("Fetching Total Debit MRQ...");

        String inputLine;
       
        try {
           
            URL URL_tdMRQ = new URL("http://finance.yahoo.com/q/ks?s=" + tickerSymbol);
            BufferedReader in = new BufferedReader(new InputStreamReader(URL_tdMRQ.openStream()));               
            System.out.println(URL_tdMRQ);                                               
                   
            String beforeTotalDebtmrq = "Total Debt (mrq):</td><td class=\"yfnc_tabledata1\">";
            String afterTotalDebtmrq = "</td></tr><tr><td";       
           
            while ((inputLine = in.readLine()) != null) {                                 
                if (inputLine.contains(beforeTotalDebtmrq)) {
                	return inputLine.substring(inputLine.indexOf(beforeTotalDebtmrq)+beforeTotalDebtmrq.length(), inputLine.indexOf(afterTotalDebtmrq,inputLine.indexOf(beforeTotalDebtmrq)));
                }             
            }	// end while
           
        } catch (MalformedURLException MUE) {
            MUE.printStackTrace();                   
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {} // end try
    	return "";
    }

*/        	 
}
