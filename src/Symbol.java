import java.io.*;

public class Symbol extends Atom {
  	private java.lang.String name = null;
  	private Sexp value = null;

  	protected Symbol() {}

  	protected Symbol(java.lang.String s) {
		name = new java.lang.String(s);
  	}

  	protected Sexp setValue(Sexp val) {
		value = val;
		return value;
  	}

  	public Sexp getValue() { return value; }

  	protected Sexp unbound() {
		value = null;
		return this;
  	}

  	protected Sexp intern(Env env) {
		return env.put(this);
  	}

  	protected Sexp unintern(Env env) {
		return env.remove(this);
  	}

	public void print(PrintWriter pw) throws Exception {
		pw.print(name);
	}

	public java.lang.String serialize() { return name; }

}