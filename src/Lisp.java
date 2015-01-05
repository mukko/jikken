import java.io.*;

import com.apple.jobjc.PrimitiveCoder.SShortCoder;


public class Lisp {
	static Lisp lisp;
	Env env;
	PrintWriter pw;
	Reader read;
	Eval eval;
	final static java.lang.String startLsp = "start.lsp";

	/**
	* init()
	*/
	void init() throws Exception {
		env = new Env();
		new InitLisp(env).init();
		pw = new PrintWriter(System.out);
		read = new Reader(env);
		eval = new Eval(env);
		Function funs = new Function(env, eval);
		funs.registSystemFunctions();

	}

	public static void main(java.lang.String[] args) {
		Sexp sexp; 	// S式
		System.out.println("httmk.Lisp.ver.3");
		System.out.println("  If quit from system, then you type \'quit\'.");

		try {
			lisp = new Lisp();

			lisp.read.load(startLsp);
			while (true) {
				try {
					lisp.pw.print("httmk> ");
					lisp.pw.flush();
					sexp = lisp.read.read();
					if (sexp.serialize().equals("QUIT"))
						break;
					sexp = lisp.eval.eval(sexp);
					System.out.println(sexp.getClass());
					System.out.println(sexp);
					Object ss = sexp.getValue();
					System.out.println(ss.getClass());
					System.out.println(ss);
					sexp.print(lisp.pw);
					lisp.pw.println();
					lisp.pw.flush();
				} catch (Error e) {
					System.out.println(e.message);
					e.print(lisp.pw);
					lisp.pw.println();
					lisp.pw.flush();
				}
			}
			System.out.println("See you again ...");
		} catch (Exception e) {
			System.out.println("Initializing Lisp is failure.");
			e.printStackTrace();
		}
	}

	public Lisp() throws Exception {
		init();
	}
}
