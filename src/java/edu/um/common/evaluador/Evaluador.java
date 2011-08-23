package edu.um.common.evaluador;

import java.util.StringTokenizer;

/**
 * @author luishpm
 * 
 */ 

/** 
 *La clase Evaluador contiene funciones para evaluar expresiones matematicas
 *sencillas, complejas y ecuaciones con inc?gnitas.
 *
 *@author Luis Perez Meza
 *@version 0.1
 */


public class Evaluador {
	
	public Evaluador() {
	}
	
	
	/**
	 * Evalua una funcion con una inc?gnita sustituyendo el valor de la incognita con
	 * el valor pasado como par?metro. Luego se llama la funci?n {@link #evaluaExpresionCompleja}
	 * 
	 * @param funcion La funci?n con inc?gnita a evaluar.
	 * @param valor El valor con que se sustituir? a la inc?gnita.
	 * @param variable La variable que se va a sustituir.
	 * @return El valor de la funci?n evaluada.
	 */
	public double evaluaFuncion(String funcion, double valor, String variable) {
		StringBuffer funcionTmp = new StringBuffer(funcion);
		int posicion = -1;
		
		do {
			posicion = funcionTmp.toString().indexOf(variable.charAt(0), posicion + 1);
			if (posicion >= 0) {
				funcionTmp.deleteCharAt(posicion);
				funcionTmp.insert(posicion, valor);
			}
		} while (posicion != -1);
		return evaluaExpresionCompleja(funcionTmp.toString());
	}
	
	
	public double evaluaExpresionPostfix(String expr) {
		double opnd1, opnd2, value;
		Double num;
		String c;
		StringTokenizer str = new StringTokenizer(expr, "+-*/^ ", true);
		MyStack opndstk = new MyStack(str.countTokens());
		
		while (str.hasMoreTokens()) {
			c = str.nextToken();
			
			try {
				num = new Double(c);
				opndstk.push(num.doubleValue());
			} catch (NumberFormatException e) {
				if (!c.equals(" ")) {
					try{
					opnd2 = opndstk.pop();
					opnd1 = opndstk.pop();
					value = evaluaOperacion(c.charAt(0), opnd1, opnd2);
					opndstk.push(value);
				}catch(Exception ex){
					System.out.println("Excepcion: "+expr);
					}
				}
			}
		}
		return opndstk.pop();
	}
	
	public double evaluaExpresion(String expresion) {
		expresion= expresion.replaceAll(" ","");
		return evaluaExpresionPostfix(toPostfix(expresion));
	}
	
	public double evaluaExpresionCompleja(String expresionCompleja) {
		StringTokenizer token = new StringTokenizer(expresionCompleja, "+-*/()^ ", true);
		StringBuffer expresionSimple = new StringBuffer();
		IsClass probador = new IsClass();
		String tokenActual;
		
		while (token.hasMoreTokens()) {
			tokenActual = token.nextToken();
			if (probador.isFuncion(tokenActual)) {
				
				StringBuffer strf = new StringBuffer();
				int cont = 0;
				String funcion = tokenActual;
				
				do {
					tokenActual = token.nextToken();
					if (tokenActual.equals("("))
						cont++;
					else if (tokenActual.equals(")"))
						cont--;
					strf.append(tokenActual);
				} while (cont != 0);
				
				tokenActual = Double.toString(evaluaExpresionCompleja(strf.toString()));
				expresionSimple.append(evaluaFuncionNumerica(funcion, Double.parseDouble(tokenActual)));
				
			} else
				expresionSimple.append(tokenActual);
		}
		
		return evaluaExpresion(expresionSimple.toString());
	}
	
	public double evaluaFuncionNumerica(String funcion, double num) {
		double valor;
		
		if (funcion.equals("sin")) {
			valor = Math.sin(num);
		} else if (funcion.equals("cos")) {
			valor = Math.cos(num);
		} else if (funcion.equals("tan")) {
			valor = Math.tan(num);
		}
		else if(funcion.equals("sqrt")){
			valor = Math.sqrt(num);
		}
		else if(funcion.equals("asin")){
			valor = Math.asin(num);
		}
		else if(funcion.equals("acos")){
			valor = Math.acos(num);
		}
		else
			valor = 0;
		
		int index = Double.toString(valor).lastIndexOf("E");
		
		if (index > 0)
			return 0;
		
		return valor;
	}
	
	public String toPostfix(String infix) {
		String topsymb = "+";
		String symb, lastSymb = "+";
		String postr = "";
		StringTokenizer str = new StringTokenizer(infix, "+-*/()^ ", true);
		MyStack opndstk = new MyStack(str.countTokens(), 1);
		
		while (str.hasMoreTokens()) {
			symb = str.nextToken();
			
			if ((char) symb.charAt(0) == '-' && isOperand(lastSymb) && !lastSymb.equals(")")) {
				postr += "0 ";
				symb = str.nextToken();
				postr += symb + " " + "-";
				lastSymb = symb;
			} else if (Character.isDigit((char) symb.charAt(0)))
				postr += symb + " ";
			else {
				topsymb = opndstk.popAndTest();
				while (!opndstk.und && (prcd2(topsymb, symb))) {
					postr += topsymb;
					topsymb = opndstk.popAndTest();
				}
				if (!opndstk.und)
					opndstk.push2(topsymb);
				if (opndstk.und || !(symb.equals(")")))
					opndstk.push2(symb);
				else
					topsymb = opndstk.popAndTest();
				
			}
			lastSymb = symb;
			
		}
		while (!opndstk.empty())
			postr += opndstk.popAndTest();
		return postr;
	}
	
	public double evaluaOperacion(int symb, double op1, double op2) {
		switch (symb) {
		case '+' :
			return (op1 + op2);
		case '-' :
			return (op1 - op2);
		case '/' :
			return (op1 / op2);
		case '*' :
			return (op1 * op2);
		case '^' :
			return (Math.pow(op1, op2));
		}
		return 0;
	}
	
	public boolean isOperand(String oper) {
		if (oper.equals("+") || oper.equals("-") || oper.equals("*") || oper.equals("/") || oper.equals("(") || oper.equals(")") || oper.equals("^"))
			return true;
		else
			return false;
	}
	
	private boolean prcd2(String symb, String symb2) {
		
		if (symb.equals("("))
			return false;
		else if (symb2.equals("(") && !symb.equals(")"))
			return false;
		else if (symb2.equals(")") && !symb.equals("("))
			return true;
		else if (symb.equals("^"))
			return true;
		else if ((symb.equals("*") || symb.equals("/")) && !symb2.equals("^"))
			return true;
		else if(symb.equals(symb2))
			return true;
		
		else
			return false;
	}
}
