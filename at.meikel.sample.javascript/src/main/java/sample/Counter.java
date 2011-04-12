package sample;

import org.mozilla.javascript.ScriptableObject;

public class Counter extends ScriptableObject {

	private static final long serialVersionUID = 1L;

	private int count;

	public Counter() {
	}

	public void jsConstructor(int a) {
		count = a;
	}

	public String getClassName() {
		return "Counter";
	}

	public int jsGet_count() {
		return count++;
	}

	public void jsFunction_resetCount() {
		count = 0;
	}
}