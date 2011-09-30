/*
 * Created on 21-oct-2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.edu.um.common.evaluador;

import java.util.TreeMap;

/**
 * @author luishpm
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Prueba {

	public static void main(String[] args) {
		Evaluador evaluador = new Evaluador();
		StringBuffer formula = new StringBuffer("PD002+PD003");
		String pd002="7500";
		String pd003="PD002*(.5)";
		String idTemp;
		String temp;
		TreeMap map = new TreeMap();
		map.put("PD002",pd002);
		map.put("PD003",pd003);
		
		int pos=0;
		pos = formula.indexOf("PD");
		while(pos != -1){
			idTemp=formula.substring(pos,pos+5);
			temp=(String)map.get(idTemp);
			formula.replace(pos,pos+5,temp);
			pos=formula.indexOf("PD");
			
		}
		System.out.println(""+formula);
		System.out.println(""+evaluador.evaluaExpresion(formula.toString()));
		System.out.println(""+evaluador.evaluaExpresion("(2+3)*6"));
		
		 
	}
}
