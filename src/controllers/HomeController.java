package controllers;

import spark.*;

public class HomeController implements Route{
	@Override
	public Object handle(Request arg0, Response arg1) throws Exception {
		// TODO Auto-generated method stub
		return "hello world";
	}
}
