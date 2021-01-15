package sudoapache.servlet;

import sudoapache.domain.HttpRequest;
import sudoapache.domain.HttpRespond;

public interface Servlet {
	void doGet(HttpRequest httpRequest, HttpRespond httpRespond);
}
