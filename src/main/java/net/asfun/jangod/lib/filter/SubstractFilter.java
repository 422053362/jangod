package net.asfun.jangod.lib.filter;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;

public class SubstractFilter implements Filter {

	
	public Object filter(Object object, JangodInterpreter interpreter,
			String... arg) throws InterpretException {
		if (arg.length != 1) {
			throw new InterpretException("filter add expects 1 arg >>> "
					+ arg.length);
		}
		Object toSubstract = interpreter.evaluateExpressionAsString(arg[0]);
		Number num;
		if (toSubstract instanceof String) {
			try {
				num = new BigDecimal(toSubstract.toString());
			} catch (Exception e) {
				throw new InterpretException(
						"filter add arg can't cast to number >>> "
								+ toSubstract);
			}
		} else if (toSubstract instanceof Number) {
			num = (Number) toSubstract;
		} else {
			return object;
		}
		if (object instanceof Integer) {
			return 0L + (Integer) object - num.intValue();
		}
		if (object instanceof Float) {
			return 0D + (Float) object - num.floatValue();
		}
		if (object instanceof Long) {
			return (Long) object - num.longValue();
		}
		if (object instanceof Short) {
			return (short) (0 + (Short) object - num.shortValue());
		}
		if (object instanceof Double) {
			return (Double) object - num.doubleValue();
		}
		if (object instanceof BigDecimal) {
			return ((BigDecimal) object).subtract(BigDecimal.valueOf(num
					.doubleValue()));
		}
		if (object instanceof BigInteger) {
			return ((BigInteger) object).subtract(BigInteger.valueOf(num
					.longValue()));
		}
		if (object instanceof Byte) {
			return (byte) ((Byte) object - num.byteValue());
		}
		if (object instanceof String) {
			try {
				String sv = (String) object;
				if (sv.contains(".")) {
					return Double.valueOf(sv) - num.doubleValue();
				} else {
					return Long.valueOf(sv) - num.longValue();
				}
			} catch (Exception e) {
				throw new InterpretException(object
						+ " can't be dealed with add filter");
			}
		}
		return object;
	}

	
	public String getName() {
		return "substract";
	}

}
