public class Logger {
    private volatile static Logger uniqueInstance;
    private Logger() {}
    public static synchronized Logger getLogger() {
        if (uniqueInstance == null) {
            // If there does not exist a resource instance,  
            // then the program should run into a synchronized block. 
            synchronized(Logger.class) {
                // Once it goes into the synchronized block,
                // it should check if it is still null.
                if (uniqueInstance == null) {
                    // If the resource is null, we create such an instance.
                    uniqueInstance = new Logger();
                }
            }
        }
        return Logger;
    }
}