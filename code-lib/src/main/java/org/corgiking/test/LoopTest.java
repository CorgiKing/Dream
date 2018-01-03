package org.corgiking.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class LoopTest {

	public static void main(String[] args) {

		int num = 100000;
		ArrayList<Integer> list = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			list.add(rand.nextInt());
		}

		// for
		long s1 = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			int d = list.get(i);
		}
		long e1 = System.currentTimeMillis();
		System.out.println(e1 - s1);

		// foreach
		long s2 = System.currentTimeMillis();
		for (int d : list) {

		}
		long e2 = System.currentTimeMillis();
		System.out.println(e2 - s2);

		// iterator
		long s3 = System.currentTimeMillis();
		for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
			Integer d = iter.next();
		}
		long e3 = System.currentTimeMillis();
		System.out.println(e3 - s3);

		// stream
		long s4 = System.currentTimeMillis();
		list.stream().forEach(d -> {
		});
		long e4 = System.currentTimeMillis();
		System.out.println(e4 - s4);
	}

}
