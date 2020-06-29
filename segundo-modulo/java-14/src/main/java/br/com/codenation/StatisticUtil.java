package br.com.codenation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StatisticUtil {

	public static int average(int[] elements) {
		return IntStream.of(elements).sum() / elements.length;
	}

	public static int mode(int[] elements) {
		Map<Integer, Integer> ocurrency = new HashMap<>();

		for(int element : elements) {
			if(ocurrency.containsKey(element)) {
				ocurrency.put(element, ocurrency.get(element) +1);
			} else {
				ocurrency.put(element, 1);
			}
		}
		return ocurrency.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(ocurrency.size() -1);
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int par = (elements[elements.length/2] + elements[(elements.length/2) -1]) / 2;
		return elements.length % 2 == 0	? par : elements[elements.length/2];
	}
}