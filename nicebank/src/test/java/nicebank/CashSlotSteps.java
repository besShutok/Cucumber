package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class CashSlotSteps {
    KnowsTheDomain helper;
    public CashSlotSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }
    @Then("^(\\$\\d+\\.\\d+) should be dispensed$")
    public void $ShouldBeDispensed(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        Assert.assertEquals(helper.getCashSlot().getContents(),amount,"Incorrect account balance - ");
    }
}