import com.aop.config.Config;
import com.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundWithLoggerApp {

    private static Logger myLogger = Logger.getLogger(AroundWithLoggerApp.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        myLogger.info("\nMain Program: AroundDemoApp");

        myLogger.info("\nCalling getFortune");

        String data = theFortuneService.getFortune();

        myLogger.info("\nMy fortune is: " + data);

        myLogger.info("Finished");

        context.close();
    }
}
