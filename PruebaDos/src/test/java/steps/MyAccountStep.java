package steps;

import cucumber.api.java.en.And;

public class MyAccountStep extends BaseTest {

    @And("^Visito historia de ordenes$")
    public void visitoHistoriaDeOrdenes() {
        screenShot.takeScreenShot();
        myAccountPage.procionoHistoria();
    }

}
