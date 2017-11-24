package Exceptions;

public class quantityParametersException extends Exception{
    
    public quantityParametersException(int numAttrib, int qtt){
        super("The length of the inputted vector must be " + numAttrib  + ", currently it is " + qtt);
    }
    
}