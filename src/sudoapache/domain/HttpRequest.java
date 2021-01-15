package sudoapache.domain;

import java.util.HashMap;

public class HttpRequest {
	private HttpMethod method;
	private String path;
	private HashMap<String, String> options;

	public HttpRequest(HttpMethod method, String path) {
		this.path = path;
		this.method = method;
		options = new HashMap<String, String>();
	}

	public HttpMethod getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public void addOption(String option, String value) {
		options.put(option, value);
	}

	public String getOption(String option) {
		return options.get(option);
	}

}
