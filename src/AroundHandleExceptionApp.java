import com.aop.config.Config;
import com.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionApp {

    private static Logger myLogger = Logger.getLogger(AroundHandleExceptionApp.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        myLogger.info("\nMain Program: AroundDemoApp");

        myLogger.info("\nCalling getFortune");

        try {
            boolean tripWire = true;
            String data = theFortuneService.getFortune(tripWire);
            myLogger.info("\nMy fortune is: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        myLogger.info("Finished");

        context.close();
    }
}
