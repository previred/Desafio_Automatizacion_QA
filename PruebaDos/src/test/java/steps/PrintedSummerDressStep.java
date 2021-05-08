package steps;

import cucumber.api.java.en.And;

public class PrintedSummerDressStep extends BaseTest {

    @And("^Selecciono vestido de verano largo$")
    public void seleccionoVestidoDeVeranoLargo() {
        printedSummerDressPage.hacerClicImgDress();
        screenShot.takeScreenShot();
    }

}
