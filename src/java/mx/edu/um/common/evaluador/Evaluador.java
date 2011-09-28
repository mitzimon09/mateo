package mx.edu.um.common.evaluador;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 * @author luishpm
 * 
 */ 

/** 
 *La clase Evaluador contiene funciones para evaluar expresiones matematicas
 *sencillas, complejas y ecuaciones con incognitas.
 *
 *@author Luis Perez Meza
 *@version 0.1
 */


public class Evaluador {
	
	MathContext mc = new MathContext(6, RoundingMode.HALF_EVEN);
	
	public Evaluador() {
	}
	
	
	/**
	 * Evalua una funcion con una incognita sustituyendo el valor de la incognita con
	 * el valor pasado como parametro. Luego se llama la funcion {@link #evaluaExpresionCompleja}
	 * 
	 * @param funcion La funcion con incognita a evaluar.
	 * @param valor El valor con que se sustituira a la incognita.
	 * @param variable La variable que se va a sustituir.
	 * @return El valor de la funcion evaluada.
	 */
	public BigDecimal evaluaFuncion(String funcion, BigDecimal valor, String variable) {
		StringBuffer funcionTmp = new StringBuffer(funcion);
		int posicion = -1;
		
		do {
			posicion = funcionTmp.toString().indexOf(variable.charAt(0), posicion + 1);
			if (posicion >= 0) {
				funcionTmp.deleteCharAt(posicion);
				funcionTmp.insert(posicion, valor.toString());
			}
		} while (posicion != -1);
		return evaluaExpresionCompleja(funcionTmp.toString());
	}
	
	
	public BigDecimal evaluaExpresionPostfix(String expr) {
		BigDecimal opnd1, opnd2, value;
		BigDecimal num;
		String c;
		StringTokenizer str = new StringTokenizer(expr, "+-*/^ ", true);
		MyStack opndstk = new MyStack(str.countTokens());
		
		while (str.hasMoreTokens()) {
			c = str.nextToken();
			
			try {
				num = new BigDecimal(c,mc);
				opndstk.push(num);
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
	
	public BigDecimal evaluaExpresion(String expresion) {
		expresion= expresion.replaceAll(" ","");
		return evaluaExpresionPostfix(toPostfix(expresion));
	}
	
	public BigDecimal evaluaExpresionCompleja(String expresionCompleja) {
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
				
				tokenActual = (evaluaExpresionCompleja(strf.toString())).toString();
				expresionSimple.append(evaluaFuncionNumerica(funcion, new BigDecimal(tokenActual,mc)));
				
			} else
				expresionSimple.append(tokenActual);
		}
		
		return evaluaExpresion(expresionSimple.toString());
	}
	
	public BigDecimal evaluaFuncionNumerica(String funcion, BigDecimal num) {
		BigDecimal valor;
		if (funcion.equals("sin")) {
			valor = new BigDecimal(Math.sin(new Double(num.toString())),mc);
		} else if (funcion.equals("cos")) {
			valor = new BigDecimal(Math.cos(new Double(num.toString())),mc);
		} else if (funcion.equals("tan")) {
			valor = new BigDecimal(Math.tan(new Double(num.toString())),mc);
		}
		else if(funcion.equals("sqrt")){
			valor = new BigDecimal(Math.sqrt(new Double(num.toString())),mc);
		}
		else if(funcion.equals("asin")){
			valor = new BigDecimal(Math.asin(new Double(num.toString())),mc);
		}
		else if(funcion.equals("acos")){
			valor = new BigDecimal(Math.acos(new Double(num.toString())),mc);
		}
		else
			valor = new BigDecimal("0.00",mc);
		
		int index = valor.toString().lastIndexOf("E");
		
		if (index > 0)
			return new BigDecimal("0.0",mc);
		
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
	
	public BigDecimal evaluaOperacion(int symb, BigDecimal op1, BigDecimal op2) {
		switch (symb) {
		case '+' :
			return (op1.add(op2));
		case '-' :
			return (op1.subtract(op2));
		case '/' :
			return (op1.divide(op2));
		case '*' :
			return (op1.multiply(op2));
		case '^' :
			return new BigDecimal((Math.pow(new Double(op1.toString()), new Double(op2.toString()))),mc);
		}
		return new BigDecimal("0.0",mc);
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
