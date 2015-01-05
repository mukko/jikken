import java.io.*;

public class Reader {
	private final static int CharBuffSize = 256;
	private char[] charBuff = null;
	private char ch;
	private java.lang.String line;
	private int indexOfLine = 0;
	private int lineLength = 0;
	private BufferedReader br = null;
	private Env env = null;

	protected Reader() {
		env = new Env();
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
	}

	protected Reader(Env environment) {
		env = environment;
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
	}

	private void init() {
		charBuff = new char[CharBuffSize];
	}

	protected Sexp read() throws IOException {
		getSexpPrepare(br.readLine());
		return getSexp();
	}

	protected Sexp readFromString(java.lang.String input) throws IOException {
		getSexpPrepareString(input);
		return getSexp();
	}

	private void getSexpPrepare(java.lang.String str) throws IOException {
		line = str;
		gSP();
	}
	// getSexp
	private void gSP() {
		indexOfLine = 0;
		lineLength = line.length();
		line.getChars(0, lineLength, charBuff, 0);
		charBuff[lineLength] = '\0';
		getChar();
	}

	private void getSexpPrepareString(java.lang.String input) {
		line = input;
		gSP();
	}

	private void getChar() {
		ch = charBuff[indexOfLine++];
	}

	private char nextChar() {
		char nch = charBuff[indexOfLine];
		while (Character.isWhitespace(ch)) {
			if (nch == ')') {
				getChar();
				break;
			};
			if (indexOfLine >= lineLength)
				break; // suddenly EOF 
			ch = nch;
			nch = charBuff[++indexOfLine];
		}
		return nch;
	}

	private Sexp getSexp() throws IOException {
		for (; indexOfLine <= lineLength; getChar()) {
			switch (ch) {
				case '(' :
					return makeList();
				case '\'' :
					return makeQuote();
				case '-' :
					return makeMinusNumber();
				case '\"' :
					return makeString();
				default :
					if (Character.isWhitespace(ch))
						break;
					if (Character.isDigit(ch))
						return makeNumber();
					return makeSymbol();
			} // end of switch
		} // end of for
		return Nil.NIL;
	}

	private Sexp makeNumber() throws IOException {
		StringBuffer str = new StringBuffer();
		for (; indexOfLine <= lineLength; getChar()) {
			if (ch == '(' || ch == ')')
				break;
			if (Character.isWhitespace(ch))
				break;
			if (!Character.isDigit(ch)) {
				indexOfLine--;
				return makeSymbolInternal(str);
			}
			str.append(ch);
		}
		int value = new java.lang.Integer("" + str).intValue();
		return (Sexp) new Integer(value);
	}

	private Sexp makeMinusNumber() throws IOException {
		char nch = charBuff[indexOfLine];
		if (Character.isDigit(nch) == false)
			return makeSymbolInternal(new StringBuffer().append(ch));
		return makeNumber();
	}

	private Sexp makeSymbol() throws IOException {
		ch = Character.toUpperCase(ch);
		StringBuffer str = new StringBuffer().append(ch);
		return makeSymbolInternal(str);
	}

	private Sexp makeSymbolInternal(StringBuffer str) throws IOException {
		while (indexOfLine < lineLength) {
			getChar();
			if (ch == '(' || ch == ')')
				break;
			if (Character.isWhitespace(ch))
				break;
			ch = Character.toUpperCase(ch);
			str.append(ch);
		}
		java.lang.String symStr = "" + str;

		if (symStr.equals("#T"))
			return T.T;
		if (symStr.equals("#F"))
			return Nil.NIL;

		Sexp sym = env.get(symStr);
		if (sym == Nil.NIL)
			return env.put(new Symbol(symStr));
		return sym;
	}

	private Sexp makeList() throws IOException {
		getChar();
		nextChar();
		if (ch == ')') {
			getChar();
			return Nil.NIL;
		};
		List top = new List();
		List list = top;
		while (true) {
			list.setCar(getSexp());
			nextChar();
			if (ch == ')')
				break;
			if (indexOfLine == lineLength)
				return Nil.NIL;
			if (ch == '.') {
				getChar();
				list.setCdr(getSexp());
				getChar();
				return top;
			}
			list.setCdr((Sexp) new List());
			list = (List) list.cdr;
		}
		getChar();
		return top;
	}

	private Sexp makeQuote() throws IOException {
		List top = new List();
		List list = top;
		list.setCar((Symbol) env.get("QUOTE"));
		list.setCdr((Sexp) new List());
		list = (List) list.cdr;
		getChar();
		list.setCar(getSexp());
		return top;
	}

	private Sexp makeString() throws IOException {
		StringBuffer str = new StringBuffer();
		while (indexOfLine < lineLength) {
			getChar();
			if (ch == '\"')
				break;
			str.append(ch);
		}
		String lispStr = new String("" + str);
		return lispStr;
	}

	/**
	 *  LOAD
	 */
	protected Sexp load(java.lang.String fileName) throws Exception {
		BufferedReader oldBr = br;
		try {
			FileInputStream in =
				new FileInputStream(new java.io.File(fileName));
			br = new BufferedReader(new InputStreamReader(in));
			init();
			for (;;) {
				java.lang.String str = br.readLine();
				if (str == null)
					break;
				Sexp sexp = readFromString(str);
				Sexp ret = Lisp.lisp.eval.eval(sexp);
			}
			br.close();
			br = oldBr;
			return T.T;
		} catch (IOException e) {
			br = oldBr;
			return Nil.NIL;
		}
	}

}
