import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class SpecialFormTest {
//if quote lambda define set!
	@Test
	public void ifが正常に処理される() throws Exception {
		int ans = 1;
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
		Sexp sexp;
		sexp = reader.readFromString("(if (= 1 1) 1 3)");
		Sexp actual = eval.eval(sexp);
		assertEquals(ans, (int)actual.getValue());
	}
	
	@Test
	public void quoteが正常に処理される() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
	}
	
	@Test
	public void lambdaが正常に処理される() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
	}
	
	@Test
	public void defineが正常に処理される() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
	}
	
	//set!
	@Test
	public void setびっくりが正常に処理される() throws Exception{
		Env env = new Env();
		Eval eval = new Eval(env);
		Reader reader = new Reader(env);
	}

}
