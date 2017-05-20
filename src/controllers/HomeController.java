package controllers;

import org.jtwig.*;

import spark.*;

public class HomeController implements Route{
	String input;
	public Object handle(Request arg0, Response arg1) throws Exception {
		JtwigTemplate template = JtwigTemplate.classpathTemplate("/views/home.html.twig");
		JtwigModel model = JtwigModel.newModel().with("var", "World");
        return template.render(model);
	}
}
