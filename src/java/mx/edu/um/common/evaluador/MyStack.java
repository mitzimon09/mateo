package mx.edu.um.common.evaluador;

import java.math.BigDecimal;

/**
 * @author luishpm
 *
 */
public class MyStack {

	private BigDecimal stckD[];
	private String stckS[];
	private int tos;
	boolean und;

	MyStack(int size) {
		stckD = new BigDecimal[size];
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

	void push(BigDecimal item) {
		stckD[++tos] = item;
	}

	BigDecimal pop() {
		return stckD[tos--];
	}

}
