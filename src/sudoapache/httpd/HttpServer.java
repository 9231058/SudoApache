package sudoapache.httpd;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sudoapache.config.ApacheConfig;

public class HttpServer extends Thread {

	private volatile boolean run = true;
	private int port = ApacheConfig.getInstance().contains("Port") ? Integer
			.parseInt(ApacheConfig.getInstance().getConfig("Port")) : 1373;
	private ExecutorService executorService;
	private ServerSocket serverSocket;

	public HttpServer() {
		super("SudoApache");
		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(port);
			while (run) {
				Socket socket = serverSocket.accept();
				executorService.submit(new HttpHandler(socket));
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void setRun(boolean run) {
		this.run = run;
	}

}
