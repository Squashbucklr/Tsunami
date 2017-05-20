package main;
import static spark.Spark.*;

import java.io.*;

import javax.servlet.MultipartConfigElement;

import controllers.HomeController;
import spark.*; 
public class Tsunami {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		get("/", new HomeController());
		
		post("/", (request, response) -> {
			OutputStream outputstream;
		    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		    try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
		        // Use the input stream to create a file
		    	File f = new File("src/uploads/idk.test");
		    	f.createNewFile();
		    	outputstream = new FileOutputStream(f);
		    	int read = 0;
		    	byte[] bytes = new byte[1024];
		    	while ((read = is.read(bytes)) != -1){
		    		outputstream.write(bytes, 0, read);
		    	}
		    	System.out.println("done!");
		    	is.close();
		    	outputstream.close();
		    	
		    } catch (IOException e){
		    	e.printStackTrace();
		    } 
//		    finally {
//		    	if(is != null){
//		    		try{
//		    			is.close();
//		    		} catch (IOException e){
//		    			e.printStackTrace();
//		    		}
//		    	}
//		    	if(outputstream != null){
//		    		try{
//		    			outputstream.close();
//		    		}catch (IOException e){
//		    			e.printStackTrace();
//		    		}
//		    	}
//		    }
		    return "File uploaded";
		});
	}

}
