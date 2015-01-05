import java.io.*;

public class Integer extends Number {
	private java.lang.Integer value;
	//private double Dvalue;

	public Integer() { value = 0; }
	public Integer(int i) { value = i; }
	public Integer(long l) { value = (int)l; }
  
	// valueOf
	public int valueOf() { return value; }
	public Object getValue(){ return value; }

	public Integer add(Integer i)  {
		return new Integer(value + i.valueOf());
	}

	public Integer sub(Integer i)  {
		return new Integer(value - i.valueOf());
	}

	public Integer mul(Integer i)  {
		return new Integer(value * i.valueOf());
	}

	public Integer div(Integer i)  {
		return new Integer(value / i.valueOf());
	}
	
	public Integer modu(Integer i)  {
		return new Integer(value % i.valueOf());
	}
	
	public  Integer sqr(Integer i)  {
		return new Integer((int)Math.sqrt(value));
	}
	
	/**
	 * >=
	 */
	public Sexp eq(Integer i)  {
		return (value == i.valueOf()) ? (Sexp)T.T : (Sexp)Nil.NIL;
	}

	/**
	 * >=
	 */
	public Sexp ge(Integer i)  {
		return (value >= i.valueOf()) ? (Sexp)T.T : (Sexp)Nil.NIL;
	}

 	/**
 	 * <=
 	 */
 	public Sexp le(Integer i)  {
		return (value <= i.valueOf()) ? (Sexp)T.T : (Sexp)Nil.NIL;
	}
	
	/**
	 * >
  	 */
	public Sexp gt(Integer i)  {
		return (value > i.valueOf()) ? (Sexp)T.T : (Sexp)Nil.NIL;
  	}

  	/**
  	 * <
  	 */
  	public Sexp lt(Integer i)  {
		return (value < i.valueOf()) ? (Sexp)T.T : (Sexp)Nil.NIL;
  	}

  	/**
  	 * =
  	 */
  	public Sexp equal(Integer i)  {
		return (value == i.valueOf()) ? (Sexp)T.T : (Sexp)Nil.NIL;
  	}


  	public void print(PrintWriter pw) throws Exception {
		pw.write(serialize());
  	}

  	public java.lang.String serialize() { return "" + value; }
}
