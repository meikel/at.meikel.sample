package sample;

import java.lang.reflect.InvocationTargetException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class RhinoSample {

	public static void main(String[] args) {
		RhinoSample rs = new RhinoSample();
		rs.eval("x = 3+1; Math.cos(2 * Math.PI)");
		rs.eval(new String[] { "Math.cos(0 * Math.PI)", "var x = 2 * 3;",
				"Math.cos(0.5 * Math.PI)", "Math.cos(1 * Math.PI)",
				"Math.cos(1.5 * Math.PI)", "Math.cos(2 * Math.PI)" });
		rs.eval("java.lang.System.out.println(3)");
		rs.eval("out.println('Hello world')");
		rs.eval("function f(i) { return i * 2; }");
		rs.eval("out.println('Counter'); x = new Counter(3); out.println(x.count); out.println(x.count);");
	}

	public void eval(String arg) {
		String[] args = new String[1];
		args[0] = arg;
		eval(args);
	}

	public void eval(String[] args) {
		if ((args == null) || (args.length == 0)) {
			return;
		}

		try {
			Context cx = Context.enter();
			Scriptable scope = cx.initStandardObjects();
			addClass(scope);
			fillScriptable(scope);
			for (int i = 0; i < args.length; i++) {
				String arg = args[i];
				Object result = cx.evaluateString(scope, arg, "tralala", i + 1,
						null);
				System.out.println(Context.toString(result));
			}
			printX(scope);
			printF(cx, scope);
		} finally {
			Context.exit();
		}
	}

	private void fillScriptable(Scriptable scope) {
		ScriptableObject.putProperty(scope, "out", Context.javaToJS(System.out,
				scope));
	}

	private void printX(Scriptable scope) {
		Object x = scope.get("x", scope);
		if (x == Scriptable.NOT_FOUND) {
			System.out.println("x is not defined.");
		} else {
			System.out.println("x = " + Context.toString(x));
		}
	}

	private void printF(Context cx, Scriptable scope) {
		Object fObj = scope.get("f", scope);
		if (!(fObj instanceof Function)) {
			System.out.println("f is undefined or not a function.");
		} else {
			Object functionArgs[] = { "4711" };
			Function f = (Function) fObj;
			Object result = f.call(cx, scope, scope, functionArgs);
			String report = "f('4711') = " + Context.toString(result);
			System.out.println(report);
		}
	}

	private void addClass(Scriptable scope) {
		try {
			ScriptableObject.defineClass(scope, Counter.class);
		} catch (IllegalAccessException e) {
			// ignore
		} catch (InstantiationException e) {
			// ignore
		} catch (InvocationTargetException e) {
			// ignore
		}
	}

}
