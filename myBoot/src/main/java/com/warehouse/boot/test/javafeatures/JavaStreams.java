package com.warehouse.boot.test.javafeatures;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JavaStreams {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10,20,30);
		list.forEach(System.out::println);
		Stream<Integer> stream = list.stream();
		stream.filter(i -> i > 10).forEach(System.out::println);
	}

}
