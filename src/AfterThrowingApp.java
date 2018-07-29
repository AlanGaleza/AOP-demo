import com.aop.config.Config;
import com.aop.dao.AccountDAO;
import com.aop.model.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        List<Account> theAccounts = null;

        try {
            boolean tripWire = true;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Progream .. caught exception: " + e);
        }


        System.out.println("\n\nMain Program: AfterThrowingApp");
        System.out.println("--------");
        System.out.println(theAccounts);

        context.close();
    }
}
