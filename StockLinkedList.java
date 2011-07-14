public class StockLinkedList {
    
    private StockNode head = null;
    
    public void addStock(String tickerSymbol, double threshLow, double threshHigh) {    
        
        if(head == null) { //If head = null then create the first node
            head = new StockNode(tickerSymbol,threshLow, threshHigh, null);
        
        } else { //If there are more than 1 node            
        	head = new StockNode(tickerSymbol, threshLow, threshHigh, head);
        }                
    }

    public int getNodeCount() {
    	
    	int cnt=0;
    	StockNode current = head; 
    	
        while (current != null) {
    		current = current.getNext();
    		cnt++;
    	}        
    	
    	return cnt;
    }

    public StockNode getNodeAt(int index) {
        StockNode current = head;    // handle to the head node
        
    	while(index > 0) {
    		current = current.getNext();
    		index--;
    	}        
    	return current;
    }

    /*
    public void sortLastTradeDesc() {
        
        boolean sorted = false;
        
        while(!sorted) {
            
            sorted = true;
            
            for (StockNode cursor = head ; cursor.getNext() != null ; cursor = cursor.getNext()) {
                if (cursor.getLastTrade() < cursor.getNext().getLastTrade()) {
                    String ts = cursor.getTickerSymbol();
                    Double lt = cursor.getLastTrade();
                    Double tl = cursor.getThreshLow();
                    Double th = cursor.getThreshHigh();
                                        
                    cursor.setTickerSymbol(cursor.getNext().getTickerSymbol());
                    cursor.setLastTrade(cursor.getNext().getLastTrade());
                    
                    cursor.setThreshLow(cursor.getNext().getThreshLow());
                    cursor.setThreshHigh(cursor.getNext().getThreshHigh());
                                       
                    cursor.getNext().setTickerSymbol(ts);
                    cursor.getNext().setLastTrade(lt);
                    cursor.getNext().setThreshLow(tl);
                    cursor.getNext().setThreshLow(th);
                                                        
                    sorted = false;        
                }                    
            }                 
        }        
    }

    public void sortLastTradeAsc() {
        
        boolean sorted = false;
        
        while(!sorted) {
            
            sorted = true;
            
            for (StockNode cursor = head ; cursor.getNext() != null ; cursor = cursor.getNext()) {
                if (cursor.getLastTrade() > cursor.getNext().getLastTrade()) {
                    String ts = cursor.getTickerSymbol();
                    Double lt = cursor.getLastTrade();
                    Double tl = cursor.getThreshLow();
                    Double th = cursor.getThreshHigh();
                                        
                    cursor.setTickerSymbol(cursor.getNext().getTickerSymbol());
                    cursor.setLastTrade(cursor.getNext().getLastTrade());
                    
                    cursor.setThreshLow(cursor.getNext().getThreshLow());
                    cursor.setThreshHigh(cursor.getNext().getThreshHigh());
                                       
                    cursor.getNext().setTickerSymbol(ts);
                    cursor.getNext().setLastTrade(lt);
                    cursor.getNext().setThreshLow(tl);
                    cursor.getNext().setThreshLow(th);
                                                        
                    sorted = false;        
                }                    
            }                 
        }        
    }
    
    public String viewAll() {
            
        StringBuffer str = new StringBuffer();    

        for(StockNode cursor = head ; cursor != null ; cursor = cursor.getNext()) {
            //Appending car by car until there are no more cars
            str.append(cursor+"\n");
        }
        return new String(str);        
    }
*/
   
}