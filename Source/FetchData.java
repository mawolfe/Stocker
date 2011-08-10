/*LICENSING

Copyright (c) 2011, Matthew Wolfe, Andy Gospodnetich

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchData {

	// Input: stock ticker symbol
    // Output: Last Trade value       
    protected String getLastTrade(String tickerSymbol) {
        
        String inputLine;
       
        try {
            URL URL_lastTrade = new URL("http://finance.yahoo.com/q?s=" + tickerSymbol);
            BufferedReader in = new BufferedReader(new InputStreamReader(URL_lastTrade.openStream()));               
            
            // Specify before/after items from HTML source
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
 
}
