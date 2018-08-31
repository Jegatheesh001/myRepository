package com.warehouse.boot.test.javafeatures;

public class JavaProcessAPI {

	public static void main(String[] args) {
		ProcessHandle.allProcesses().forEach(System.out::println);
	}

}
