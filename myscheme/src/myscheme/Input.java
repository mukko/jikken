package myscheme;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.lang.model.element.Element;

public class Input extends SchemeUtils{
	static String EOF = "#!EOF";
	boolean isPushedToken = false;
	boolean isPushedChar = false;
	Object pushedToken = null;
	int pushedChar = -1;
	Reader in;
	StringBuffer buff = new StringBuffer();
	
	public Input(InputStream in){
		this.in = new InputStreamReader(in);
	}
	
	public Input(Reader in){
		this.in = in;
	}
	
	int popChar(){
		isPushedChar = false;
		return pushedChar;
	}
	
	int pushChar(int ch){
		isPushedChar = true;
		return pushedChar = ch;
	}
	
	public Object nextToken() throws IOException{
		int ch;
		
		if(isPushedToken){
			isPushedToken = false;
			return pushedToken;
		}else if(isPushedChar){
			ch = popChar();
		}else{
			ch = in.read();
		}
		
		//空白部分を読み捨てる
		while (Character.isWhitespace((char) ch)) ch = in.read();
		
		switch (ch) {
		case -1: return EOF;
		case '(': return "(";
		case ')': return ")";
		case '\'': return "'";
		case ';': while(ch != -1 && ch != '\n') ch = in.read();
				return nextToken();
		case '"': buff.setLength(0);
				while((ch = in.read()) != '"' && ch != -1){
					buff.append((char) ((ch == '\\') ? in.read() : ch));
				}
				return buff.toString().toCharArray();
		case '#':
			switch (ch = in.read()) {
			case 't':
			case 'T':	return TRUE;
			
			case 'f':
			case 'F':	return FALSE;
			
			default: return nextToken();
			}

		default:
			buff.setLength(0);
			int c = ch;
			do{
				buff.append((char) ch);
				ch = in.read();
			}while(!Character.isWhitespace((char)ch) && ch != -1 &&
					ch != '(' && ch != ')' && ch != '\'' && ch != ';' &&
					ch != '"');
			pushChar(ch);
			if(c == '.' || c == '+' || c == '-' || (c >= '0' && c <= '9')){
				try {
					return new Double(buff.toString());
				}catch(NumberFormatException e){
					;
				}
			}
			//小文字でinternする
			return buff.toString().toLowerCase().intern();
		}
	}
	
	public Object read(){
		try{
			Object token = nextToken();
			if(token == "(") return readTail(false);
			else if(token == ")"){
				warn("Extra ) ignored.");
				return read();
			}else if(token == "."){
				warn("Extra . ignored.");
				return read();
			}else if(token == "'") return list("quote", read());
			else return token;
		}catch(IOException e){
			warn("On input, exception: " + e);
			return EOF;
		}
	}
	
	Object readTail(boolean dotOK) throws IOException{
		Object token = nextToken();
		if(token == EOF) return error("EOF during read.");
		else if(token == ")") return null;
		else if(token =="."){
			Object result = read();
			token = nextToken();
			if(token != ")") warn("Whare's the ')' ? Got" + token + "after .");
			return result;
		}else{
			isPushedToken = true;
			pushedToken = token;
			return cons(read(), readTail(true));
		}
	}
	}
