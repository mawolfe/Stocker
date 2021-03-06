/*LICENSING

Copyright (c) 2011, Matthew Wolfe, Andy Gospodnetich

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

public class StockNode {

	// Each node contains a place holder for a ticker Symbol, last trade value, low threshold, high threshold,
	// instance number, emailed fields, and a pointer to the next node
	
	private String tickerSymbol, lastTrade;
    private double threshLow, threshHigh; 
    private int instanceNum;
    private boolean emailed;
    private StockNode next; 
    
    public StockNode(String _tickerSymbol, double _threshLow, double _threshHigh, int _instanceNum, StockNode _next) {
        this.tickerSymbol = _tickerSymbol;
        this.threshLow    = _threshLow;
        this.threshHigh   = _threshHigh;
        this.instanceNum  = _instanceNum;
        this.emailed      = false;
        this.next = _next;            
    }

    public StockNode getNext() { return next; }
    
    // Getters
    public String getTickerSymbol() { return tickerSymbol;}    
    public String getLastTrade()    { return lastTrade;}   
    public double getThreshLow()    { return threshLow;}
    public double getThreshHigh()   { return threshHigh;}
    public int getInstanceNum()     { return instanceNum;}
    public boolean getEmailed()     { return emailed;}
           
    // Setters
    public void setTickerSymbol(String in) { tickerSymbol = in; }    
    public void setLastTrade(String in)    { lastTrade = in;    }
    public void setThreshLow(double in)    { threshLow = in;    }
    public void setThreshHigh(double in)   { threshHigh = in;   }
    public void setInstanceNum(int in)     { instanceNum = in;  }
    public void setEmailed(boolean in)     { emailed = in;      }  
}