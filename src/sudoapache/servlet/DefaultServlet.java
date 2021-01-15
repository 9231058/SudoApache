package sudoapache.servlet;

import java.io.PrintWriter;

import sudoapache.domain.HttpRequest;
import sudoapache.domain.HttpRespond;

public class DefaultServlet implements Servlet {

	@Override
	public void doGet(HttpRequest httpRequest, HttpRespond httpRespond) {
		System.out.println(httpRequest.getPath());
		String name = httpRequest.getPath();
		PrintWriter writer = new PrintWriter(httpRespond.getOutputStream());
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>Parham Web Page</title>");
		writer.println("</head>");
		writer.println("<body>");
		if (name.split("/").length == 3) {
			String firstName = name.split("/")[1];
			String lastName = name.split("/")[2];
			writer.println("<h1>");
			writer.println("Hello " + firstName + " " + lastName);
			writer.println("</h1>");
		} else {
			httpRespond.setStatusCode(404);
			writer.println("<h1>");
			writer.println("What is your name ... enter in url please ...");
			writer.println("</h1>");
		}
		writer.println("</body>");
		writer.println("</html>");
		writer.flush();
		httpRespond.addOption("Server", "Parham Server");
	}

}
