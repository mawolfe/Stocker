import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;


public class FileIO {

    protected void readTextFile(File aFile, StockLinkedList sLL) {

        //setProgBar("Reading Text file", 0);

    	try {

    		BufferedReader input =  new BufferedReader(new FileReader(aFile));
          
    		try {
	            String line = null; //not declared within while loop
	            /*
	            * readLine is a bit quirky :
	            * it returns the content of a line MINUS the newline.
	            * it returns null only for the END of the stream.
	            * it returns an empty String if two newlines appear in a row.
	            */
	           
	            System.out.println("* ------ START INPUT ------- *");
	           
	            while (( line = input.readLine()) != null) {
	                System.out.println(line);
	                sLL.addStock(line);
	            }
    		} catch (IOException ioe) {
              ioe.printStackTrace();             
    		} finally {
    			System.out.println("* ------  END INPUT  ------- *\n\n");
    		}
    	} catch (FileNotFoundException fnfe ) {
    		fnfe.printStackTrace();
        }
    }
    
 public void writeToFile(String filename, String writeThis) {
        
        BufferedWriter bufferedWriter = null;
        
        try {
            
            //Construct the BufferedWriter object
            bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
            
            //Start writing to the output stream
            bufferedWriter.write(writeThis + "\n");
            //bufferedWriter.newLine();
            //bufferedWriter.write("Writing line two to file");
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedWriter
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
 
 /*
    protected void printHeader(PrintStream p) {
        p.print("Ticker Symbol");
    	p.print(",");
    	p.print("Last Trade");
      	p.print(",");
        p.print("Thresh Low");
    	p.print(",");
        p.print("Thresh High");
    	p.print("\n");
    }
    */    
}
