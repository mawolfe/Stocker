/*LICENSING

Copyright (c) 2011, Matthew Wolfe, Andy Gospodnetich

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

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
	        
        // if ticker symbol does exist, ensure it's sign is postive (active) 
    	} else {
    		
    		StockNode currNode = findTickerSymb(tickerSymbol); 

    		currNode.setThreshLow(threshLow); 
    		currNode.setThreshHigh(threshHigh); 
    		currNode.setEmailed(false);

    		if (currNode.getInstanceNum() < 0) {
        		currNode.setInstanceNum(Math.abs(currNode.getInstanceNum()));		 

    			System.out.println(tickerSymbol + " reactivated.");

    		} else {
    			System.out.println(tickerSymbol + " is already active.  Updated thresholds.");
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
}