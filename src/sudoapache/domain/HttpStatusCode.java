package sudoapache.domain;

public enum HttpStatusCode {
	OK(200, "OK"), NotFound(404, "Not Found"), Forbidden(403, "Forbidden");

	int statusCode;
	String humanReadableStatusCode;

	private HttpStatusCode(int statusCode, String humanReadableStatusCode) {
		this.statusCode = statusCode;
		this.humanReadableStatusCode = humanReadableStatusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getHumanReadableStatusCode() {
		return humanReadableStatusCode;
	}

	public static HttpStatusCode findStatusCode(int statusCode) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].getStatusCode() == statusCode) {
				return values()[i];
			}
		}
		return null;
	}
}
