package com.allianz.drdc24.support;

public class ControlledExit extends RuntimeException {
	private static final long serialVersionUID = 5892438426174150703L;
	
	public ControlledExit(String s) {
		super(s);
	}
	
}
