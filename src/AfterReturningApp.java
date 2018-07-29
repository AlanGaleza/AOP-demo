import com.aop.config.Config;
import com.aop.dao.AccountDAO;
import com.aop.dao.MembershipDAO;
import com.aop.model.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

        List<Account> theAccounts = theAccountDAO.findAccounts();

        System.out.println("\n\nMain Program: AfterReturningApp");
        System.out.println("--------");
        System.out.println(theAccounts);

        context.close();
    }
}
