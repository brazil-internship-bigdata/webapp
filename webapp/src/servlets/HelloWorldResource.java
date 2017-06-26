package servlets;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("helloworld")
public class HelloWorldResource {

	@GET
	@Produces("text/plain")
	public String getHello() {
		return "Hello World! (GET)";
	}

	@POST
	@Produces("text/plain")
	public String postHelloHello() {
		return "Hello World! (POST)";
	}

}