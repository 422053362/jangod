package net.asfun.jangod.lib.filter;

import java.math.BigDecimal;

import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;

public class GtFilter implements Filter {
 
	public Object filter(Object object, JangodInterpreter interpreter, String... arg) throws InterpretException {
		if (arg.length != 1) {
			throw new InterpretException("filter add expects 1 arg >>> " + arg.length);
		}
		Object toGt = interpreter.evaluateExpressionAsString(arg[0]);
		Number num;
		if (toGt instanceof String) {
			try {
				num = new BigDecimal(toGt.toString());
			} catch (Exception e) {
				throw new InterpretException("filter add arg can't cast to number >>> " + toGt);
			}
		} else if (toGt instanceof Number) {
			num = (Number) toGt;
		} else {
			return object; 
		}
		
		if (object instanceof Integer) {
			return (Integer) object > num.intValue();
		}
		if (object instanceof Float) {
			return (Float) object >num.floatValue();
		}
		if (object instanceof Long) {
			return (Long) object > num.longValue();
		}
		if (object instanceof Short) {
			return (Short) object > num.shortValue();
		}
		if (object instanceof Double) {
			return (Double) object > num.doubleValue();
		}
		if (object instanceof String) {
			try {
				return new BigDecimal(object.toString()).abs();
			} catch (Exception e) {
				throw new InterpretException(object + " can't be dealed with gt filter");
			}
		}
		return object;
	}

	
	public String getName() {
		return "gt";
	}

}
