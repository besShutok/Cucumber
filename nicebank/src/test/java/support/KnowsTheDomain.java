package support;

import nicebank.Account;
import nicebank.CashSlot;
import org.javalite.activejdbc.Base;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class KnowsTheDomain {
    private Account myAccount;
    private CashSlot cashSlot;
    private TellerWithState teller;
    private EventFiringWebDriver webDriver;

    public KnowsTheDomain() {
        if (!Base.hasConnection()){
            Base.open(
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost/bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "teller", "password");
            try {
                Base.connection().setAutoCommit(false);
            } catch (Exception se){
                // Ignore
            }
        }
    }

    public EventFiringWebDriver getWebDriver() {
        if (webDriver == null) {
            // For the driver installation see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
            System.setProperty("webdrive.chrome.driver","C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools");
            webDriver = new EventFiringWebDriver(new ChromeDriver());
        }
        return webDriver;
    }

    public TellerWithState getTeller() {
        if (teller == null) {teller = new AtmUserInterface(getWebDriver());}
        return teller;
    }

    public Account getMyAccount() {
        if (myAccount == null) {
            myAccount = new Account(1234);
            myAccount.saveIt();
        }
        return myAccount;
    }

    public CashSlot getCashSlot() {
        if (cashSlot == null) {cashSlot = new CashSlot();}
        return cashSlot;
    }
}
