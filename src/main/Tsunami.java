package main;
import static spark.Spark.*;
import spark.*;
public class Tsunami {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		get(TsunamiRoutes.ROUTE_HOME, (Request req, Response res) -> "Hello World");
	}

}
