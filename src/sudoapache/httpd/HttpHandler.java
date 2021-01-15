package sudoapache.httpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import sudoapache.domain.HttpMethod;
import sudoapache.domain.HttpRequest;
import sudoapache.domain.HttpRespond;
import sudoapache.servlet.Servlet;
import sudoapache.servlet.ServletLoader;

public class HttpHandler implements Runnable {

	private Socket socket;
	private Servlet servlet;

	public HttpHandler(Socket socket) {
		this.socket = socket;
		servlet = ServletLoader.getInstance().getServlet();
	}

	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String header = reader.readLine();
			String[] headers = header.split(" ");
			HttpMethod method = HttpMethod.valueOf(headers[0]);
			String path = headers[1];

			HttpRequest httpRequest = new HttpRequest(method, path);
			HttpRespond httpRespond = new HttpRespond();

			String optionLine = reader.readLine();
			while (optionLine.length() != 0) {
				String value = optionLine.split(":")[1].trim();
				String option = optionLine.split(":")[0];
				httpRequest.addOption(option, value);
				optionLine = reader.readLine();
			}

			servlet.doGet(httpRequest, httpRespond);

			writer.println(httpRespond.toString());
			writer.flush();

			socket.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
