import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class FileIO {
    
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
}