package TestData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public  class Log2 {
    private static final Logger log = LogManager.getLogger(Log2.class);

    public static void main(String[] args) {
        String message = "Hello there!";
        System.out.println(message);
        log.info("Message sent successfully.");
    }

}
