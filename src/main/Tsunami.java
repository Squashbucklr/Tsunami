package main;
import static spark.Spark.*;

import controllers.HomeController;
import spark.*;
public class Tsunami {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		get("/", new HomeController());
	}

}
