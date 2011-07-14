public class StockNode {

	private String tickerSymbol, lastTrade;
    private double threshLow, threshHigh;    
    private StockNode next; 
    
    public StockNode(String _tickerSymbol, double _threshLow, double _threshHigh, StockNode _next) {
        this.tickerSymbol = _tickerSymbol;
        this.threshLow    = _threshLow;
        this.threshHigh   = _threshHigh;
        this.next = _next;            
    }

    public StockNode getNext() { return next; }
    
    public String getTickerSymbol() { return tickerSymbol;}    
    public String getLastTrade()    { return lastTrade;}   
    public double getThreshLow()    { return threshLow;}
    public double getThreshHigh()    { return threshHigh;}
            
    public void setTickerSymbol(String in) { tickerSymbol = in; }    
    public void setLastTrade(String in)    { lastTrade = in;    }
    public void setThreshLow(double in)    { threshLow = in;    }
    public void setThreshHigh(double in)   { threshHigh = in;   }

/*
    public String toString() {
 
        return tickerSymbol + " " + lastTrade + " " + threshLow + " " + threshHigh;
    }
*/    
    
}