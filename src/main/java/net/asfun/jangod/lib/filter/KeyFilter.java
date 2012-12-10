package net.asfun.jangod.lib.filter;

import java.util.Map;

import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;

public class KeyFilter implements Filter {

	
	public Object filter(Object object, JangodInterpreter interpreter, String... arg) throws InterpretException {
		if (object instanceof Map) {
			String value = object.toString();
			return value.toUpperCase();
		}
		return object;
	}

	
	public String getName() {
		return "key";
	}

}