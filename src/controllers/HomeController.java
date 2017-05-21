package controllers;

import java.io.*;

import org.jtwig.*;

import spark.*;

public class HomeController implements Route{
	public Object handle(Request arg0, Response arg1) throws Exception {
		String input;
		JtwigTemplate template = JtwigTemplate.classpathTemplate("/views/home.html.twig");
		JtwigModel model = JtwigModel.newModel().with("var", "World");
		File dir = new File("src/uploads");
		File[] files = dir.listFiles();
		String[] fnames;
		if (files == null){
			fnames = new String[0];
		}else{
			fnames= new String[files.length];
			for (int i = 0; i < files.length; i++) {
				fnames[i] = files[i].getName();
			}
		}
		
		model.with("files", fnames);
        return template.render(model);
	}
}
