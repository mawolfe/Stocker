public class StockNode {

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
    
    public String getTickerSymbol() { return tickerSymbol;}    
    public String getLastTrade()    { return lastTrade;}   
    public double getThreshLow()    { return threshLow;}
    public double getThreshHigh()   { return threshHigh;}
    public int getInstanceNum()     { return instanceNum;}
    public boolean getEmailed()     { return emailed;}
           
    public void setTickerSymbol(String in) { tickerSymbol = in; }    
    public void setLastTrade(String in)    { lastTrade = in;    }
    public void setThreshLow(double in)    { threshLow = in;    }
    public void setThreshHigh(double in)   { threshHigh = in;   }
    public void setInstanceNum(int in)     { instanceNum = in;  }
    public void setEmailed(boolean in)     { emailed = in;      }

/*
    public String toString() {
 
        return tickerSymbol + " " + lastTrade + " " + threshLow + " " + threshHigh;
    }
*/    
    
}