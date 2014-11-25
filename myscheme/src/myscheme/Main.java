package myscheme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[])throws IOException
	{
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String string = in.readLine();
			System.out.println(string);
	}catch(Exception e){
		
	}
	}
	
	public void lexer()
	{
		
	}
}
