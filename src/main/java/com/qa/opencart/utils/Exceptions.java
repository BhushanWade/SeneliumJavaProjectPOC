package com.qa.opencart.utils;

public class Exceptions {
	
	public class FrameworkException extends RuntimeException {
		
		public FrameworkException(String message) {
			super(message);
		}
	}
}
