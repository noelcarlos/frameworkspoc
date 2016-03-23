package com.allianz.drdc24.support;

public class ExternalExit extends RuntimeException {
	private static final long serialVersionUID = 5892438426174150703L;
	private String url;
	
	public ExternalExit(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
