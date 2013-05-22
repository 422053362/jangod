package net.asfun.jangod.lib.filter;

import javax.servlet.http.HttpSession;
import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;

public class SessionFilter implements Filter {

	public String getName() {
		return "sessionKey";
	}

	public Object filter(Object object, JangodInterpreter interpreter,
			String... arg) throws InterpretException {

		Object sessionKey = interpreter.evaluateExpression(arg[0]);
		
		if (object instanceof HttpSession){
			HttpSession session =(HttpSession)object;
			return session.getAttribute(sessionKey.toString());
		} 

		return object;
	}

}
