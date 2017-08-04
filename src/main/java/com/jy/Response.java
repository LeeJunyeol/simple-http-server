package com.jy;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Response {
	public final static String httpVersion = "HTTP/1.1";
	private Map<String, String> headers = new HashMap<String, String>();

	private String statusLine;
	private OutputStream outputStream;
	private String headerString;
	private String statusCode;
	private String reasonPhrase;

	public void addHeader(String header) {
		int end = header.indexOf(":");
		String key = header.substring(0, end);
		String value = header.substring(end + 1);

		headers.put(key, value);
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	public String getStatusLine() {
		statusLine = String.join(" ", httpVersion, getStatusCode(), getReasonPhrase());
		return statusLine;
	}

	public String getHeaderString() {
		for (String key : headers.keySet()) {
			headerString += key + ":" + headers.get(key) + "\n";
		}

		return headerString;
	}

	@Override
	public String toString() {
		return "Response [statusLine=" + getStatusLine() + ", statusCode=" + getStatusCode() + ", reasonPhrase="
				+ getReasonPhrase() + ", headers=[" + headers.toString() + "]";
	}

}
