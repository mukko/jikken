import java.io.*;

/**
 * Error
 */
public class Error extends Exception implements Sexp {
	java.lang.String message;
	java.lang.String errorInformation;
	int errorNo;
	static final java.lang.String errorMessages[] = { 
		"Undefined Error", 		// 0
		"Unbound Variable", 	// 1
		"Undefined Function", 	// 2
		"Not Function", 		// 3
		"Not Symbol", 			// 4
	};


	Error() {
		new Error(0, "");
	}

	Error(int err) {
		new Error(err, "");
	}

	Error(int err, java.lang.String errorInfo) {
		message = getMessage(err);
		errorInformation = errorInfo;
		errorNo = err;
	}

	java.lang.String getMessage(int err) {
		return errorMessages[err];
	}

	public void print(PrintWriter pw) throws Exception {
		pw.print(serialize());
	}
	
	public java.lang.String serialize() {
		return "Error: " + message + " --- " + errorInformation;
	}
	
	//追加したgetValue
	public Object getValue(){ return message;}
}
