package steps;

import cucumber.api.java.en.When;

public class BlousesStep extends BaseTest{


    @When("^Selecciono Blouses$")
    public void selecionoBlouses() {
        screenShot.takeScreenShot();

        blousesPage.hacerClicImgBlouse();
    }

}
