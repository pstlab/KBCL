package it.istc.pst.gecko.kbcl.app.cli;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class KbclCLIMain 
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Thread thread = new Thread(new KbclCLIHandler());
			thread.start();
			thread.join();
		}catch (InterruptedException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
