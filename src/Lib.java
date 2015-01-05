/*
 * Created on 2005/06/15
 */


/**
 * static Library class
 */
public abstract class Lib {
   static final Class list = new List().getClass();
   static final Class symbol = new Symbol().getClass();
   static final Class function = new Function().getClass();

   public static boolean Atom(Sexp sexp) {
      return !list.isInstance(sexp);
   }

   public static boolean listp(Sexp sexp) {
      return list.isInstance(sexp);
   }

   public static boolean symbolp(Sexp sexp) {
      return symbol.isInstance(sexp);
   }

   public static boolean functionp(Sexp sexp) {
      return function.isInstance(sexp);
   }
   
   static final int UNBOUND = 1;
   static final int UNDEFINED = 2;
   static final int NOTFUNCTION = 3;
   static final int NOTSYMBOL = 4;

}
