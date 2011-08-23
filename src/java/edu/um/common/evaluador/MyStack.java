package edu.um.common.evaluador;

/**
 * @author luishpm
 *
 */
public class MyStack {

	private double stckD[];
	private String stckS[];
	private int tos;
	boolean und;

	MyStack(int size) {
		stckD = new double[size];
		tos = -1;
	}
	MyStack(int size, int x) {
		stckS = new String[size];
		tos = -1;
	}

	boolean empty() {
		if (tos == -1)
			return true;
		else
			return false;
	}

	String popAndTest() {
		if (empty()) {
			und = true;
			return "-1";
		}
		und = false;
		return stckS[tos--];

	}

	void push2(String item) {
		stckS[++tos] = item;
		tos++;
		tos--;
	}

	void push(double item) {
		stckD[++tos] = item;
	}

	double pop() {
		return stckD[tos--];
	}

}
