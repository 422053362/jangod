package net.asfun.jangod.lib.filter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;

public class NumFilter  implements Filter {

	public Object filter(Object object, JangodInterpreter interpreter, String... arg) throws InterpretException {
		if (object == null) {
			return object;
		}
		if (object instanceof Double|| object instanceof Long) { // joda DateTime
			DecimalFormat format = new DecimalFormat(interpreter.evaluateExpressionAsString(arg[0]));
	    	format.setRoundingMode(RoundingMode.DOWN);
			return format.format(object);
		} 
		return object;
	}

	public String getName() {
		return "num";
	}
	
}
