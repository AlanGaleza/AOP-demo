import com.aop.config.Config;
import com.aop.dao.AccountDAO;
import com.aop.dao.MembershipDAO;
import com.aop.model.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
        MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        Account myAccount = new Account();
        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();

        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();

        context.close();
    }
}
