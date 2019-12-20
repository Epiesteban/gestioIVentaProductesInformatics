package ExceptionsBotiga;

public class EstocNegatiu extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EstocNegatiu(String missatgeError) {
		super(missatgeError);
	}
}
