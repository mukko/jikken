<<<<<<< HEAD
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

/*
 * メソッドごとにテストを書くのは難しそうだったので、変な感じのテストになってる
 */
=======
package test;

import static org.junit.Assert.*;

import org.junit.Test;

>>>>>>> test
public class EvalTest {

	@Test
	public void Int型の評価値はInt型() throws Exception{
<<<<<<< HEAD
		Sexp sexp = new Integer(1);
		Env env = new Env();
		Eval eval = new Eval(env);
		Sexp actual = eval.eval(sexp);
		assertThat(actual, is(instanceOf(Integer.class)));
	}
	
	@Test
	public void Int値1の評価値はInt値1() throws Exception{
		Sexp sexp = new Integer(1);
		Env env = new Env();
		Eval eval = new Eval(env);
		Sexp actual = eval.eval(sexp);
		assertThat(actual, is(sexp));
	}
	
	@Test
	public void 加算の評価値が正しい() throws Exception{
		int op1 = 2;
		int op2 = 3;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp ans = new Integer(5);
		Sexp sexp;
		sexp = reader.readFromString("(+ 2 3)");
		Sexp actual = eval.eval(sexp);
		assertEquals(actual,ans);
	}
	
	@Test
	public void 加算の引数が数字でない時のエラー出力() throws Exception{
		try{
		Sexp sexp;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(+ 1 a)");
		eval.eval(sexp);
		}catch(Error e){
			assertEquals("Unbound Variable", e.message);
		}
	}
	
	@Test
	public void 減算の評価値が正しい() throws Exception{
		int op1 = 3;
		int op2 = 2;
		Sexp ans = new Integer(op1-op2);
		Env env = new Env();
		Eval eval = new Eval(env);
		Sexp sexp;
		Reader reader = new Reader(env);
		sexp = reader.readFromString("(- 3 2)");
		Sexp actual = eval.eval(sexp);
		assertThat(actual,is(equalTo(ans)));
	}
	
	@Test
	public void 減算の引数が数字でない時のエラー出力()throws Exception{
		
	}
	
	@Test
	public void 乗算の評価値が正しい() throws Exception{
		
	}
	
	@Test
	public void 乗算の引数が数字でない時のエラー出力()throws Exception{
		
	}
	
	@Test
	public void 除算の評価値が正しい() throws Exception{
		
	}

	@Test
	public void 除算の引数が数字でない時のエラー出力()throws Exception{
		
	}
	
	@Test
	public void sqrtの評価値が正しい()throws Exception{
		
	}
	
	@Test
	public void 変数に代入した値と変数の評価値が等しい() throws Exception{
=======
>>>>>>> test
		
	}

}
