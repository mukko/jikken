import java.io.*;

public class String extends Atom {
	private java.lang.String value;

	public String() {
		value = "";
	}
	public String(java.lang.String string) {
		value = string;
	}

	// valueOf
	public java.lang.String valueOf() {
		return value;
	}

	//追加したgetValue
	public Object getValue(){ return serialize(); }
	
	public void print(PrintWriter pw) throws Exception {
		pw.write(serialize());
	}

	public java.lang.String serialize() {
		return "\"" + valueOf() + "\"";
	}
	
}
