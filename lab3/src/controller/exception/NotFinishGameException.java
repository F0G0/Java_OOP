package controller.exception;

public class NotFinishGameException extends Exception {


	public NotFinishGameException(){
    	   super("GAME IS NOT FINISHED YET");
     }
}
