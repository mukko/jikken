package myscheme;

public abstract class SchemeUtils {
	public static final Boolean TRUE = Boolean.TRUE;
	public static final Boolean FALSE = Boolean.FALSE;
	
	public static Object first(Object obj){
		return (obj instanceof Pair) ? ((Pair) obj).first : null;
	}
	
	public static Object rest(Object obj){
		return (obj instanceof Pair) ? ((Pair) obj).rest : null;
	}
}
