import java.util.*;

public class Env extends Hashtable {
  	protected Env () { super(); }

	protected Symbol put (Symbol value) {
	 	put(value.serialize(), value);
	 	return value;
  	}

	protected Symbol put (java.lang.String name, Symbol value) {
	 	super.put(name, value);
	 	return value;
  	}

	protected Sexp get (Symbol sym) throws Exception {
	 	return get(sym.serialize());
  	}

	protected Sexp get (java.lang.String name) {
	 	Symbol sym = (Symbol)super.get(name);
	 	if (sym != null) {
			return sym;
	 	} else {
			return Nil.NIL;
	 	}
  	}

	protected Sexp remove(Symbol sym) {
	 	super.remove(sym.serialize());
	 	return sym;
  	}
}
