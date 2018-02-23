package springDemo.test.java8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeBasics {

	public static void main(String[] args) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDate start = LocalDate.from(dtf.parse("01-01-2018"));
		System.out.println(start);
		
		LocalDate now = LocalDate.now();
		System.out.println(now);
		
		System.out.println(ChronoUnit.DAYS.between(start, now));
		
	}

}
