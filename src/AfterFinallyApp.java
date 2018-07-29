import com.aop.config.Config;
import com.aop.dao.AccountDAO;
import com.aop.model.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterFinallyApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        List<Account> theAccounts = null;

        try {
            boolean tripWire = false;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Progream .. caught exc: " + e);
        }


        System.out.println("\n\nMain Program: AfterFinallyApp");
        System.out.println("--------");
        System.out.println(theAccounts);

        context.close();
    }
}
