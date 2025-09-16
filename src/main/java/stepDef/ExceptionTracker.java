package stepDef;

public class ExceptionTracker {
	 public static ThreadLocal<Throwable> lastError = new ThreadLocal<>();

	    public static void capture(Throwable t) {
	        lastError.set(t);
	    }

	    public static Throwable get() {
	        return lastError.get();
	    }

	    public static void clear() {
	        lastError.remove();
	    }
	}