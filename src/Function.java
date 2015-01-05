import java.io.*;

public class Function implements Sexp {
	Env env;
	Eval eval;

	Function() {
		env = new Env();
		eval = new Eval(env);
		env.put(new Symbol("LAMBDA"));
	}

	Function(Env environment) {
		env = environment;
		eval = new Eval(env);
		env.put(new Symbol("LAMBDA"));
	}

	Function(Env environment, Eval evaluator) {
		env = environment;
		eval = evaluator;
		env.put(new Symbol("LAMBDA"));
	}

	public void print(PrintWriter pw) throws Exception {
		pw.write(this.serialize());
	}

	//追加したgetValue	
	public Object getValue(){ return env;}
	
	public java.lang.String serialize() {
		return "<SystemFunction: " + this.getClass().getName() + ">";
	}

	public Sexp fun(List arguments, int argNum) throws Exception {
		return Nil.NIL;
	}

	public void registSystemFunctions() {
		regist("CAR", new Car());
		regist("CDR", new Cdr());
		regist("QUOTE", new Quote());
		regist("+", new Add());
		regist("-", new Sub());
		regist("*", new Mul());
		regist("/", new Div());
		regist("MODULO", new Modulo());                       //余剰算
		regist("SETQ", new Setq());
		regist("DEFINE", new Define());
		regist(">=", new Ge());
		regist("<=", new Le());
		regist(">", new Gt());
		regist("<", new Lt());
		regist("=", new Eq());
		regist("IF", new If());
		regist("CONS", new Cons());
		regist("READ", new Read());
		regist("READ-FROM-STRING", new ReadFromString());
		regist("EVAL", new EvalFunction());
		regist("LOAD", new Load());
		regist("SQRT", new Sqrt());                          //平方根
	}

	/** regist**/
	void regist(java.lang.String name, Function fun) {
		Symbol sym = new Symbol(name);
		env.put(name, sym);
		sym.setValue(fun);
	}

	/**
	 * CAR
	 */
	class Car extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			return ((List) arg1).car;
		}
	}

	/**
	 * CDR
	 */
	class Cdr extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.cdr);
			return ((List) arg1).cdr;
		}
	}
	
	/**
	 * QUOTE
	 */
	class Quote extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			return arguments.car;
		}
	}

	/**
	 * +
	 */
	class Add extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).add((Integer) arg2);
		}
	}

	/**
	 * -
	 */
	class Sub extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).sub((Integer) arg2);
		}
	}

	/**
	 * *
	 */
	class Mul extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).mul((Integer) arg2);
		}
	}
	
	/**
	 * 割り算
	 */
	class Div extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).div((Integer) arg2);
		}
	}
	
	/**
	 * modulo(余剰算)
	 */
	class Modulo extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).modu((Integer) arg2);
		}
	}
	
	/**
	 * =
	 */
	class Eq extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).eq((Integer) arg2);
		}
	}
	
	
	/**
	 * >=
	 */
	class Ge extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).ge((Integer) arg2);
		}
	}

	
	/**
	 * <=
	 */
	class Le extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).le((Integer) arg2);
		}
	}
	
	/**
	 * >
	 */
	class Gt extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).gt((Integer) arg2);
		}
	}
	
	/**
	 * <
	 */
	class Lt extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return ((Integer) arg1).lt((Integer) arg2);
		}
	}
	
	
	/**
	 * SETQ
	 */
	class Setq extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = arguments.car;
			Sexp arg2 = ((List) arguments.cdr).car;
			Symbol sym = (Symbol) arg1;
			Sexp value = eval.eval(arg2);
			sym.setValue(value);
			return value;
		}
	}

	
	/**
	 * SQRT
	 */
	
	class Sqrt extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			return ((Integer) arg1).sqr((Integer) arg1);
		}
	}
	/**
	 * DEFINE
	 */
	class Define extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = arguments.car;
			Sexp args = arguments.cdr;

			Symbol fun = (Symbol) arg1;
			List lambda = new List();
			lambda.car = env.get("LAMBDA");
			lambda.cdr = args;
			fun.setValue(lambda);
			return fun;
		}
	}

	/**
	 * IF
	 */
	class If extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = arguments.car;
			Sexp args = arguments.cdr;
			Sexp arg2 = ((List) args).car;
			Sexp arg3 = ((List) ((List) args).cdr).car;

			if (eval.eval(arg1) != Nil.NIL)
				return eval.eval(arg2);
			else
				return eval.eval(arg3);
		}
	}

	/**
	 * CONS
	 */
	class Cons extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			Sexp arg2 = eval.eval(((List) arguments.cdr).car);
			return (Sexp) new List(arg1, arg2);
		}
	}

	/**
	 * READ
	 */
	class Read extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			return (Sexp) Lisp.lisp.read.read();
		}
	}

	/**
	 * READ-FROM-STRING
	 */
	class ReadFromString extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			return (Sexp) Lisp.lisp.read.readFromString(
				((String) arg1).valueOf());
		}
	}

	/**
	 * EVAL
	 */
	class EvalFunction extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			return (Sexp) eval.eval(arg1);
		}
	}

	/**
	 * LOAD
	 */
	class Load extends Function {
		public Sexp fun(List arguments, int argNum) throws Exception {
			Sexp arg1 = eval.eval(arguments.car);
			java.lang.String fileName = ((String) arg1).valueOf();
			return Lisp.lisp.read.load(fileName);
		}
	}

}
