package humankind.tools;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class RomanNumberConverter {
	private static Map<String, Integer> ROMAN_2_DECIMAL = new HashMap<String, Integer>() {
		{
			put("I", 1);
			put("V", 5);
			put("X", 10);
			put("L", 50);
			put("C", 100);
			put("D", 500);
			put("M", 1000);
		}
	};

	public Integer convert(String romanLiteral) throws ParseException {
		int length = romanLiteral.length();
		if (romanLiteral == null || length == 0)
			return 0;
		if (length == 1)
			return ROMAN_2_DECIMAL.get(romanLiteral);

		return convertString(romanLiteral);
	}

	private Integer convertString(String romanLiteral) throws ParseException {
		int length = romanLiteral.length();
		int accumulator = 0;
		int i = 0;
		while (i < length) {
			int current = ROMAN_2_DECIMAL.get(String.valueOf(romanLiteral.charAt(i)));
			int next = lookahead(romanLiteral, ++i, current);
			if (next > current) {
				accumulator += next - current;
				i++;
			} else {
				accumulator += current;
			}
		}
		return accumulator;
	}

	private int lookahead(String romanLiteral, int index, int current) throws ParseException {
		int next = 0;
		if (index < romanLiteral.length()) {
			next = ROMAN_2_DECIMAL.get(String.valueOf(romanLiteral.charAt(index)));
			if (isSequenceInvalid(current, next))
				throw new ParseException(romanLiteral, index);
		}
		return next;
	}

	private boolean isSequenceInvalid(int current, int next) {
		return next > current && (notAllowedToBeSubtracted(current) || notAllowedToBeSubtractedFrom(next, current));
	}

	private boolean notAllowedToBeSubtractedFrom(int next, int current) {
		return (next - current) / current > 10;
	}

	private boolean notAllowedToBeSubtracted(int current) {
		return current == 5 || current == 50 || current == 500;
	}
}
