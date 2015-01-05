import java.io.*;

public class T extends Atom {
  static T T = new T();
  private T() {}

  public void print(PrintWriter pw) throws Exception {
	pw.print("#t");
  }

  public java.lang.String serialize(){
	return "#t";
  }
  
  public Object getValue(){ return T;}
}