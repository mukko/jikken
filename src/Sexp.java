import java.io.*;

interface Sexp {
	void print(PrintWriter pw) throws Exception;
	java.lang.String serialize();
	//値を取得するために、getValueメソッドを実装。すべてのクラスで実装したいのでObject型
	//setterはみなくていいのでつけないよ
	java.lang.Object getValue();
}