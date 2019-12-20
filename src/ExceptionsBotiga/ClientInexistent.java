package ExceptionsBotiga;

public class ClientInexistent extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ClientInexistent(String missatgeError) {
		super(missatgeError);
	}
}
