package net.asfun.jangod.lib.filter;

import java.util.Map;

import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;

public class KeyFilter implements Filter {

	@SuppressWarnings("rawtypes")
	public Object filter(Object object, JangodInterpreter interpreter,
			String... arg) throws InterpretException {

		if (arg.length != 1) {
			throw new InterpretException("filter multiply expects 1 arg >>> "
					+ arg.length);
		}
		Object key = interpreter.evaluateExpression(arg[0]);
		if (object instanceof Map) {
			Map m = (Map) object;
			return m.get(key);
		}
		
		return object;
	}

	public String getName() {
		return "key";
	}

}
