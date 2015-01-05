/*
 * Created on 2005/06/15
 */


import java.io.*;

import sun.org.mozilla.javascript.internal.ast.NewExpression;


/**
* ���X�g�N���X�̒�`
*/
public class List extends Cell implements Sexp {
	/**
	* car, cdr �� NIL �̃Z���𐶐�
	*/
	protected List() {
		super();		// �Z���̐���
	} 

	/**
	* Cons
	*/
	protected List(Sexp kar, Sexp kdr) {
		super();
		super.car = kar;
		super.cdr = kdr;
	}

	/**
	* car �����Z�b�g
	*/
	protected Sexp setCar(Sexp sexp) {
		return car = sexp;
	}

	/**
	* cdr �����Z�b�g
	*/
	protected Sexp setCdr(Sexp sexp) {
		return cdr = sexp;
	}

	/**
	* size = (system (length list))
	*/
	protected int size() {
		List list = this;
		for (int i = 1;; i++) {
			if (Lib.Atom(list.cdr))
				return i;
			list = (List) list.cdr;
		}
	}

	/**
	* List �̃v�����g
	*/
	public void print(PrintWriter pw) throws Exception {
		List list = this;
		pw.write("("); 				// �J������ Open "("
		for (;;) {
			list.car.print(pw);		// Car ��
			if ((Sexp) list.cdr == Nil.NIL) {
				pw.write(")");		// ������ Close ")"
				break;
			} else if (Lib.Atom((Sexp) list.cdr)) {
				pw.write(" . ");	// �h�b�g��
				list.cdr.print(pw); // Cdr ��
				pw.write(")");		// ������ Close ")"
				break;
			} else {
				pw.write(" ");		// ��
				list = (List) list.cdr; // ���� Cdr ����
			}
		} // end of for
	}

	/**
	* List �̃V���A���C�Y�i�����񉻁j
	*/
	public java.lang.String serialize() {
		StringBuffer str = new StringBuffer();
		List list = this;
		str.append("("); 			// Open "("
		for (;;) {
			str.append(list.car.serialize()); // Car ��
			if ((Sexp) list.cdr == Nil.NIL) {
				str.append(")");	// Close ")"
				break;
			} else if (Lib.Atom((Sexp) list.cdr)) {
				str.append(" . ");	// �h�b�g��
				str.append(list.cdr.serialize()); // Cdr ��
				str.append(")");	// Close ")"
				break;
			} else {
				str.append(" ");	// ��
				list = (List) list.cdr; // ���� Cdr ����
			}
		} // end of for
		return "" + str;
	}
	
	//追加したgetValue
	//listの中身car,cdrを文字列で返す
	public Object getValue() { 
		return serialize();
	}
}
