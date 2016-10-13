import java.util.Arrays;
import java.util.function.*;

public class SquareSequence implements NumberSequence {

	private int tag = 0; // Tag data is custom
	private int pretag = 0; // Tag usage expression Predicate
	private int funtag = 0; // Tag usage expression Function
	private long record;
	private long args[];
	Predicate<Long> conditions;
	Function<Long, Long> functions;

	/**
	 * Constructor for iterate function
	 * 
	 * @param n
	 * @param function
	 */
	public SquareSequence(int n, Function<Long, Long> function) // 使用Function表达式新建类
	{
		record = n;
		functions = function;
		funtag = 1;
	}

	/**
	 * Constructor
	 * 
	 */
	public SquareSequence() {

	}

	/**
	 * Constructor
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public SquareSequence(long a, long b, long c, long d) {
		tag = 1;
		args = new long[4];
		args[0] = a;
		args[1] = b;
		args[2] = c;
		args[3] = d;
	}

	/**
	 * Constructor
	 * 
	 * @param condition
	 */
	public SquareSequence(Predicate<Long> condition) {
		this.conditions = condition;
		this.pretag = 1;
	}

	/**
	 * get the next number
	 */
	public long next() {
		if (funtag == 1) {
			long ret = record;
			record = functions.apply(record);
			return ret;
		}
		if (pretag == 1) {
			if (conditions.test(record * record)) {
				long ret = record * record;
				record = record + 1;
				return ret;
			} else {
				record = record + 1;
			}
		}
		if (tag == 0) {
			long num = record;
			record++;
			return num * num;
		} else {
			if (record == 4) // 数据到头
			{
				return args[3] * args[3];
			} else {
				long ret = (long) args[(int) record];
				if (record < 4)
					record++;
				return ret;
			}
		}
	}

	/**
	 * Whether there is a next number exist
	 */
	public boolean hasNext() {
		if (tag == 1) {
			if (this.record < 4)
				return true;
			else
				return false;
		}
		return true;
	}

	/**
	 * get a new object
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return NumberSequence
	 */
	public NumberSequence of(int i, int j, int k, int l) {
		return new SquareSequence(i, j, k, l);
	}

	/**
	 * get the average
	 */
	public double average() {
		double ret = ((args[0] + args[1] + args[2] + args[3]) * 1.0) / 4;
		return ret;
	}

	/**
	 * get the average
	 */
	public double average(int n) {
		long add = 0;
		for (int i = 0; i < n; i++) {
			long num = next();
			add = add + num;
		}
		double ret = (add * 1.0) / n;
		return ret;
	}

	/**
	 * get the array
	 */
	public long[] toArray(int n) {
		if (n > args.length) {
			return args;
		} else {
			long[] ret;
			ret = Arrays.copyOfRange(args, 0, n);
			return ret;
		}
	}

	/**
	 * the function of filter
	 * 
	 * @param condition
	 * @return NumberSequence
	 */
	public NumberSequence filter(Predicate<Long> condition) {
		return new SquareSequence(condition);
	}

	/**
	 * the function of iterate
	 * 
	 * @param n
	 * @param condition
	 * @return NumberSequence
	 */
	public NumberSequence iterate(int n, Predicate<Long> condition) {
		return new SquareSequence(condition);
	}

}
