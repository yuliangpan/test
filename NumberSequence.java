import java.util.function.Function;

public interface NumberSequence {
	/**
	 * get the next number
	 * @return long
	 */
	long next();

	/**
	 * Whether there is a next number exist
	 * @return boolean
	 */
	default boolean hasNext() {
		return true;
	}
	/**
	 * computes the average of the first n elements
	 * @return double
	 */
	default double average() {
		return 0;
	}
	/**
	 * computes the average of the first n elements,n is divisor
	 * @param n is divisor
	 * @return
	 */
	default double average(int n) {
		return 0;
	}
	/**
	 * one can construct an instance of a finite sequence by specifying the elements
	 * @param i long
	 * @param j long
	 * @param k long
	 * @param l long
	 * @return NumberSequence
	 */
	public static NumberSequence of(long i, long j, long k, long l) {
		return new SquareSequence(i, j, k, l);
	}
	/**
	 * provide a default method toArray that yields an array 
	 * of the first n elements of the sequence
	 * @param n is the length of array
	 * @return long array[]
	 */
	default long[] toArray(int n) {
		return null;
	}
	/**
	 * provide a method default NumberSequence filter(LongPredicate p) 
	 * that yields a sequence of numbers fulfilling the predicate
	 * @param k
	 * @param function
	 * @return NumberSequence
	 */
	public static NumberSequence iterate(int k, Function<Long, Long> function) {
		return new SquareSequence(k, function);
	}
	/**
	 * produce a static method random(long seed) that creates random numbers
	 * @param i is seed
	 * @return NumberSequence
	 */
	static NumberSequence random(int i) {
		long a = 1103515245;
		long c = 12345;
		long t1 = 32768;
		long t2 = 65536;
		return NumberSequence.iterate(i, n -> (a * n + c) % (t1 * t2));
	}
}