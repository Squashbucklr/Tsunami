package main;
import static spark.Spark.*;

import java.io.*;

import javax.servlet.MultipartConfigElement;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import controllers.HomeController;
import spark.*;
import spark.utils.IOUtils; 
public class Tsunami {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		get("/", new HomeController());
		
		post("/", (request, response) -> {
			String s = "";
			OutputStream outputstream;
		    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		    try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
		        // Use the input stream to create a file
		    	File f = new File("src/uploads/" + request.raw().getPart("uploaded_file").getSubmittedFileName());
		    	f.createNewFile();
		    	s = request.raw().getPart("uploaded_file").getSubmittedFileName();
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
		    JtwigTemplate template = JtwigTemplate.classpathTemplate("/views/add.html.twig");
			JtwigModel model = JtwigModel.newModel().with("name", s);
			return template.render(model);
		    
		  
		});
		
		get("/downloads/:file", (request, response) -> {
			String fileName = request.params("file");
			System.out.println(fileName);
			byte[] bz = IOUtils.toByteArray(new FileInputStream("src/uploads/" + fileName));
			return bz;
		});
		
		get("/delete/:file", (request, response) -> {
			String fileName = request.params("file");
			File f = new File("src/uploads/" + fileName);
			f.delete();
			JtwigTemplate template = JtwigTemplate.classpathTemplate("/views/delete.html.twig");
			JtwigModel model = JtwigModel.newModel().with("name", fileName);
			return template.render(model);
		});
		
		get("/img/dn.png", (request, response) -> {
			response.raw().setContentType("image/png");
			byte[] bz = IOUtils.toByteArray(new FileInputStream("src/views/images/download.png"));
			return bz;
		});
		get("/img/dl.png", (request, response) -> {
			response.raw().setContentType("image/png");
			byte[] bz = IOUtils.toByteArray(new FileInputStream("src/views/images/delete.png"));
			return bz;
		});
	}

}
