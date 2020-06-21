package br.com.codenation.desafioexe;

import java.util.*;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		return new Fibonacci(377).getSequence();
	}

	public static Boolean isFibonacci(Integer a) {
		return new Fibonacci(a).isInSequence(a) >= 0;
	}

	public static class Fibonacci {

		private List<Integer> sequence;

		public Fibonacci(int lastNumber) {
			this.sequence = calculateSequence(lastNumber);
		}

		private List<Integer> calculateSequence(int lastNumber) {
			if(Objects.isNull(sequence)) {
				sequence = new ArrayList<>();
				sequence.addAll(Arrays.asList(0, 1));
			}

			int nextNumber = sequence.get(sequence.size() -1) + sequence.get(sequence.size() -2);
			if(nextNumber <= lastNumber) {
				sequence.add(nextNumber);
				this.calculateSequence(lastNumber);
			}
			return sequence;
		}

		public int isInSequence(int number) {
			return Collections.binarySearch(sequence, number);
		}

		public List<Integer> getSequence() {
			return sequence;
		}
	}
}