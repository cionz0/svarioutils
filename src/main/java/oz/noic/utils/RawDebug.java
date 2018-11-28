package oz.noic.utils;

public class RawDebug {
	
	
	
	
	/**
	 * Returns a String telling the class name and the line number
	 * at which the method has been invoked
	 * 
	 * @return
	 */
	public static String where() {
		int idx = 2;
		StringBuffer where = new StringBuffer();
		where.append(" at line ");
		where.append(Thread.currentThread().getStackTrace()[idx].getLineNumber());
		where.append(" of ");
		where.append(Thread.currentThread().getStackTrace()[idx].getClassName());
		return where.toString();
		
	}

}
