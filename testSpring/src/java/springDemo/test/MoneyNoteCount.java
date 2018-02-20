package springDemo.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MoneyNoteCount {

	Map<Integer, Integer> counts = new LinkedHashMap<Integer, Integer>();
	List<Integer> moneType;

	public static void main(String[] args) {
		new MoneyNoteCount().getAllCounts(2789);
		new MoneyNoteCount().getAllCounts(5500);
		new MoneyNoteCount().getAllCounts(900);
		new MoneyNoteCount().getAllCounts(58);
		new MoneyNoteCount().getAllCounts(5517);
	}

	final int[] values = { 1000, 500, 200, 100, 50, 20, 10, 5, 1 };
	void getMoneyTypes() {
		moneType = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			moneType.add(values[i]);
		}
		Collections.sort(moneType, Collections.reverseOrder());
	}

	Integer remaining = 0;

	void getAllCounts(Integer amount) {
		getMoneyTypes();
		remaining = amount;
		Integer moneyType = 0;
		for (int i = 0; i < moneType.size(); i++) {
			moneyType = moneType.get(i);
			getCount(moneyType);
		}
		System.out.println(counts);

	}

	void getCount(Integer moneyType) {
		Integer count = 0;
		if (remaining != 0 && remaining >= moneyType) {
			count = remaining / moneyType;
			remaining = remaining % moneyType;
		}
		counts.put(moneyType, count);
	}

}
