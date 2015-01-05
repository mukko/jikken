public class Eval {
	Env env;
	static final int maxStackSize = 65536;
	Sexp[] stack = new Sexp[maxStackSize];
	int stackP = 0;

	public Eval(Env environment) {
		env = environment;
	}

	public Sexp eval(Sexp form) throws Exception {
		if (Lib.symbolp(form)) {
			Sexp symbolValue = (Sexp) ((Symbol) form).getValue();
			if (symbolValue == null) {
				throw new Error(Lib.UNBOUND, form.serialize());
			}
			return symbolValue;
		}

		if (Lib.Atom(form))
			return form;

		Sexp car = (Sexp) ((List) form).car;
		int argNum = ((List) form).size() - 1;
		if (Lib.symbolp(car)) {

			Sexp fun = ((Symbol) car).getValue();
			if (fun == null) {
				throw new Error(Lib.UNDEFINED, car.serialize());
			}

			if (Lib.functionp(fun)) {
				Sexp argumentList = (argNum == 0) ? Nil.NIL : ((List) form).cdr;
				try {
					return ((Function) fun).fun((List) argumentList, argNum);
				} catch (Exception e) {
					throw e;
				}
			}
			
			if (Lib.listp(fun)) {
				List cdr = (List) ((List) fun).cdr;
				List lambdaList = (List) cdr.car;
				List body = (List) cdr.cdr;
				if (lambdaList == (Sexp) Nil.NIL)
					return evalBody(body);
				return sexpEval(lambdaList, body, (List) ((List) form).cdr);
			}
			throw new Error(Lib.NOTFUNCTION, car.serialize());
		}
		throw new Error(Lib.NOTSYMBOL, car.serialize());
	} // end of eval()

	Sexp sexpEval(List lambda, List body, List form) throws Exception {
		List argList = lambda;
		int OldStackP = stackP;
		while (true) {
			Sexp arg = form.car;
			Sexp ret = eval(arg);
			stack[stackP++] = ret;
			if (form.cdr == Nil.NIL)
				break;
			form = (List) form.cdr;
			argList = (List) argList.cdr;
		}
		argList = lambda;
		int sp = OldStackP;
		while (true) {
			Symbol sym = (Symbol) argList.car;
			Sexp swap = sym.getValue();
			sym.setValue(stack[sp]);
			stack[sp++] = swap;
			if (argList.cdr == Nil.NIL)
				break;
			argList = (List) argList.cdr;
		}
		Sexp ret = evalBody(body);
		argList = lambda;
		stackP = OldStackP;
		while (true) {
			Symbol sym = (Symbol) argList.car;
			sym.setValue(stack[OldStackP++]);
			if (argList.cdr == Nil.NIL)
				break;
			argList = (List) argList.cdr;
		}
		return ret;
	}

	Sexp evalBody(List body) throws Exception {
		Sexp ret;
		while (true) {
			ret = eval(body.car);
			if (body.cdr == Nil.NIL)
				break;
			body = (List) body.cdr;
		}
		return ret;
	}

} // end of Eval class
