import java.io.*;


public class Nil extends Atom implements Sexp{
	static Nil NIL = new Nil();
	private Nil() {}

	public void print(PrintWriter pw) throws Exception {
		pw.print("#f");
	}
	
	//追加したgetValue
	//返せる値を保持していないから、文字列を返すことにした
	public Object getValue(){ return "message : this is NIL";}
	
	public java.lang.String serialize(){
		return "#f";
	}
}
