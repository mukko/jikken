package myscheme;

import java.io.PrintWriter;

public abstract class SchemeUtils {
	public static final Boolean TRUE = Boolean.TRUE;
	public static final Boolean FALSE = Boolean.FALSE;
	
	public static Object first(Object obj){
		return (obj instanceof Pair) ? ((Pair) obj).first : null;
	}
	
	public static Object rest(Object obj){
		return (obj instanceof Pair) ? ((Pair) obj).rest : null;
	}
	
	public static int length(Object obj){
		int length = 0;
		while(obj instanceof Pair){
			length++;
			obj = ((Pair) obj).rest;
		}
		return length;
	}
	
	public static char chr(Object obj){
		if(obj instanceof Character) return ((Character) obj).charValue();
		else return chr(error("expected a char, got: " + obj));
	}
	
	public static Character chr(char ch){
		return new Character(ch);
	}
	
	public static Object error(String message){
		System.err.println("**** ERROR: " + message);
		throw new RuntimeException(message);
	}
}
