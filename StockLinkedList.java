public class StockLinkedList {
    
    private StockNode head = null;
    
    public void addStock(String tickerSymbol, double threshLow, double threshHigh, int instanceNum) {    
        
    	// if ticker symbol does not exist, add ticker symbol
    	if (findTickerSymb(tickerSymbol)==null) {
    	
	        if (head == null) { //If head = null then create the first node
	            head = new StockNode(tickerSymbol,threshLow, threshHigh, instanceNum, null);
	        
	        } else { //If there are more than 1 node            
	        	head = new StockNode(tickerSymbol, threshLow, threshHigh, instanceNum, head);
	        }                
	        
        // if ticker symbol does exist, flip its sign 
    	} else {
    		
    		StockNode currNode = findTickerSymb(tickerSymbol); 
    		currNode.setInstanceNum(findTickerSymb(tickerSymbol).getInstanceNum()*-1); 

    		currNode.setThreshLow(threshLow); 
    		currNode.setThreshHigh(threshHigh); 
    		currNode.setEmailed(false);

    		if (findTickerSymb(tickerSymbol).getInstanceNum() < 0) {
        		System.out.println(tickerSymbol + " deactivated.");    	
    		} else {
    			System.out.println(tickerSymbol + " reactivated.");
    		}    		
    	}
    	
    }

    public StockNode getHead() {return head;}

    
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

    public StockNode findTickerSymb(String tickerSymb) {
    	
    	StockNode current = head;    // handle to the head node

        while(current != null) {
    		
    		// if ticker symbol exists in linked list
    		if (current.getTickerSymbol().equals(tickerSymb)) {return current;}    		// return node
    		
    		current = current.getNext();
    	}        
    	return null;		// ticker symbol does not exist
    }

    public boolean HasActiveInstanceNum() {

    	StockNode current = head;    // handle to the head node

        while(current != null) {
    		
    		// if at least 1 node has an active instance, return true
    		if (current.getInstanceNum()>0) {return true;}    		// return node
    		
    		current = current.getNext();
    	}        
    	return false;
    	
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