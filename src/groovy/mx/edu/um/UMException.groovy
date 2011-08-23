/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.edu.um

/**
 *
 * @author eder
 */
class UMException extends Exception{
    
    public UMException(){
        super()
    }
    public UMException(String message) {
	super(message);
    }
    public UMException(String message, Throwable cause) {
        super(message, cause);
    }
    public UMException(Throwable cause) {
        super(cause);
    }
	
}

