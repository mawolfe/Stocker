// Obtain the system's OS

public class getOS {

    public static void main(String[] arg) {
    	
    	//getOS getMyOS = new getOS();

    	final String myOS = getOS.getOsName();
    	  	
    	if (myOS.equals("windows")) {
    		System.out.println("it's windows");
    	} else if (myOS.equals("linux")) {
    		System.out.println("it's linux");
    	} else if (myOS.equals("mac")){
    		System.out.println("it's Mac OS");
    	}
    	
    }

    // System.out.println(myOS.getOsName());
	
	public static String getOsName() {
		  
		String os = "";
		
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
			os = "windows";
		} else if (System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
			os = "linux";
		} else if (System.getProperty("os.name").toLowerCase().indexOf("mac") > -1) {
			os = "mac";
		}
		 
		return os;
	}
}
