package main;
import static spark.Spark.*;

import java.io.InputStream;

import javax.servlet.MultipartConfigElement;

import controllers.HomeController;
import spark.*;
public class Tsunami {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		get("/", new HomeController());
		
		post("/", (request, response) -> {
		    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		    try (InputStream is = request.raw().getPart("uploaded_file").getInputStream()) {
		        // Use the input stream to create a file
		    }
		    return "File uploaded";
		});
	}

}
