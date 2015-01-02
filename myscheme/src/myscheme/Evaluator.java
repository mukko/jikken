package myscheme;

public class Evaluator extends SchemeUtils{
	public Object eval(Object obj, Environment env) {
		while (true){
			if(obj instanceof String){	//String
				
			}else if(!(obj instanceof Pair)){	//Pairではないとき
				return obj;
			}else{	//Pairなので
				Object fn = first(obj);
				Object args = rest(obj);
				if(fn == "quote" || fn == "'"){
					return first(args);
				}
			}
		}
	}
}
