import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
/*
 * メソッドごとにテストを書くのは難しそうだったので、変な感じのテストになってる
 */
public class EvalTest {

	@Test
	public void HInteger型の評価値はHInteger型() throws Exception{
		Sexp sexp = new HInteger(1);
		Env env = new Env();
		Eval eval = new Eval(env);
		Sexp actual = eval.eval(sexp);
		assertThat(actual, is(instanceOf(HInteger.class)));
	}
	
	@Test
	public void HInteger値1の評価値はHInteger値1() throws Exception{
		Sexp sexp = new HInteger(1);
		Env env = new Env();
		Eval eval = new Eval(env);
		Sexp actual = eval.eval(sexp);
		assertEquals(actual, sexp);
	}
	
	@Test
	public void 加算の評価値が正しい() throws Exception{
		int ans = 5;Env env = new Env();
		
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(+ 2 3)");
		sexp = eval.eval(sexp);
		int actual = new Integer(sexp.getValue().toString()).intValue();
		assertEquals(actual,ans);
	}
	
	@Test(expected=Error.class)
	public void 加算の引数が数字でない時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(+ 1 a)");
		eval.eval(sexp);
	}
	
	@Test
	public void 減算の評価値が正しい() throws Exception{
		int op1 = 3;
		int op2 = 2;
		int ans = op1-op2;
		Env env = new Env();
		Eval eval = new Eval(env);
		Sexp sexp;
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(- 3 2)");
		sexp = eval.eval(sexp);
		int actual = new Integer(sexp.getValue().toString()).intValue();
		assertEquals(actual,ans);
	}
	
	@Test(expected=Error.class)
	public void 減算の引数が数字でない時のエラー出力()throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(- 1 a)");
		eval.eval(sexp);
	}
	
	@Test
	public void 乗算の評価値が正しい() throws Exception{
		int ans = 2;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(* 1 2)");
		sexp = eval.eval(sexp);
		int actual = new Integer(sexp.getValue().toString()).intValue();
		assertEquals(ans, actual);
	}
	
	@Test(expected=Error.class)
	public void 乗算の引数が数字でない時のエラー出力()throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(* 1 a)");
		eval.eval(sexp);
	}
	
	@Test
	public void 除算の評価値が正しい() throws Exception{
		int ans = 1;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("/ 2 2");
		sexp = eval.eval(sexp);
		int actual = new Integer(sexp.getValue().toString()).intValue();
		assertEquals(ans, actual);
	}

	@Test(expected=Error.class)
	public void 除算の引数が数字でない時のエラー出力()throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(/ 1 a)");
		eval.eval(sexp);
	}
	
	@Test
	public void 剰余演算の評価値が正しい() throws Exception{
		int ans = 0;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(modulo 4 2)");
		sexp = eval.eval(sexp);
		int actual = new Integer(sexp.getValue().toString()).intValue();
		assertEquals(ans, actual);
	}
	
	@Test(expected=Error.class)
	public void 剰余演算の引数が未定義時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(modulo 1 a)");
		eval.eval(sexp);
	}
	
	@Test
	public void sqrtの評価値が正しい()throws Exception{
		int ans = 1;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(sqrt 2)");
		sexp = eval.eval(sexp);
		int actual = new Integer(sexp.getValue().toString()).intValue();
		assertEquals(ans, actual);
	}
	
	@Test(expected=Error.class)
	public void sqrtの引数が未定義時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(sqrt a)");
		eval.eval(sexp);
	}
	
	@Test
	public void 変数に束縛した値と変数の評価値が等しい() throws Exception{
		java.lang.String varName = "a";
		int ans = 3;
		Env env = new Env();
		env.put(varName,ans);
		Object actual = env.get(varName);
		actual = (int)actual;
		assertEquals(ans, actual);
	}

	//=
	@Test
	public void 等号演算の評価値が正しい() throws Exception{
		T ans ;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(= 1 1)");
		sexp = eval.eval(sexp);
		T actual = (T)sexp.getValue();
		
	}
	
	@Test(expected=Error.class)
	public void 等号演算の引数が未定義時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(= 1 a)");
		eval.eval(sexp);
	}
	
	//<
	@Test
	public void 大なり演算の評価値が正しい() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(> 3 1)");
	}
	
	@Test(expected=Error.class)
	public void 大なり演算の引数が数字でない時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(> 1 a)");
		eval.eval(sexp);
	}
	//<=	
	@Test
	public void 大なりイコール演算の評価値が正しい() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("");
	}
	
	@Test(expected=Error.class)
	public void 大なりイコール演算の引数が数字でない時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(<= 1 a)");
		eval.eval(sexp);
	}
	//>
	@Test
	public void 小なり演算の評価値が正しい() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("");
	}
	
	@Test(expected=Error.class)
	public void 小なり演算が数字でない時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(< 1 a)");
		eval.eval(sexp);
	}
	//>=
	@Test
	public void 小なりイコール演算の評価値が正しい() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("");
	}
	
	@Test(expected=Error.class)
	public void 小なりイコール演算が数字でない時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(<= 1 a)");
		eval.eval(sexp);
	}
	//integer?
	@Test
	public void 整数かどうかの評価値が正しい() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(integer? 10)");
		sexp = eval.eval(sexp);
		//Tの#Tが欲しい
		assertEquals(#t, sexp.getValue());
	}
	
	@Test(expected=Error.class)
	public void 整数かどうかの値が未定義時のエラー出力() throws Exception{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(integer? a)");
		eval.eval(sexp);
	}
}
