package sudoapache.domain;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class HttpRespond {
	private byte[] data;
	private int statusCode = 200;
	private int dataLength;
	private HashMap<String, String> options;

	private String eol = "\n";
	private ByteArrayOutputStream byteArrayOutputStream;

	public HttpRespond() {
		options = new HashMap<String, String>();
	}

	public OutputStream getOutputStream() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		return byteArrayOutputStream;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void addOption(String option, String value) {
		options.put(option, value);
	}

	public String getOption(String option) {
		return options.get(option);
	}

	private String optionsString() {
		String optionsString = "";
		Object[] keys = options.keySet().toArray();
		for (int i = 0; i < keys.length; i++) {
			optionsString += keys[i] + ": " + options.get(keys[i]) + eol;
		}
		return optionsString;
	}

	@Override
	public String toString() {
		data = byteArrayOutputStream.toByteArray();
		dataLength = data.length;
		String header = "HTTP/1.1"
				+ " "
				+ statusCode
				+ " "
				+ HttpStatusCode.findStatusCode(statusCode)
						.getHumanReadableStatusCode() + eol;
		String contentLength = "Content-Length:" + " " + dataLength + eol;
		String content = new String(data);
		return header + contentLength + optionsString() + eol + content + eol;
	}
}
