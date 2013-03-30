package net.asfun.jangod.lib.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import net.asfun.jangod.interpret.InterpretException;
import net.asfun.jangod.interpret.JangodInterpreter;
import net.asfun.jangod.lib.Filter;

public class CookieFilter implements Filter {

	public String getName() {
		return "cookieName";
	}

	public Object filter(Object object, JangodInterpreter interpreter,
			String... arg) throws InterpretException {

		Object cookieName = interpreter.evaluateExpression(arg[0]);
		if (object instanceof Cookie[]) {
			Cookie[] cs = (Cookie[]) object;
			for (Cookie c : cs) {
                if(c.getName().equals(cookieName)){
                	String value = c.getValue();
                	if(arg.length==1){return value;}
                	if(arg.length==2){
                		String enc = (String) interpreter.evaluateExpression(arg[1]);
                		try {
							return java.net.URLDecoder.decode(value, enc);
						} catch (UnsupportedEncodingException e) {
							return value;
						}
                	}
                }
			}
			return null;
		}

		return object;
	}

}
