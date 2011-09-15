/*
 * Created on 25/09/2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package edu.um.common.evaluador;

/**
 * @author luishpm
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class IsClass {
	
	public boolean isConstante(String symb){
		if(symb.equals("pi"))
			return true;
		else return false;
	}
	
	public boolean isFuncion(String num){
		if(num.equals("cos")||num.equals("sin")||num.equals("tan")||num.equals("pi")||num.equals("sqrt")
			||num.equals("asin")||num.equals("acos"))
			return true;
		else 
			return false;
	}
	
	public boolean isOperador(String oper){
		if(oper.equals("+")||oper.equals("-")||oper.equals("*")||oper.equals("/")||oper.equals("(")||oper.equals(")")||oper.equals("^"))
			return true;
		else
			return false;
	}


}
