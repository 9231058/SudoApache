package sudoapache.servlet;

import sudoapache.config.ApacheConfig;

public class ServletLoader {
	private static ServletLoader instance;

	public static ServletLoader getInstance() {
		if (instance == null) {
			instance = new ServletLoader();
		}
		return instance;
	}

	private ServletLoader() {
	}

	public Servlet getServlet() {
		Servlet servlet = new DefaultServlet();
		if (ApacheConfig.getInstance().contains("Servlet")) {
			try {
				Class<?> servletClass = Class.forName(ApacheConfig
						.getInstance().getConfig("Servlet"));
				Object object = servletClass.newInstance();
				servlet = (Servlet) object;
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | ClassCastException exception) {
				exception.printStackTrace();
			}
		}
		return servlet;
	}
}
