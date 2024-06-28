package helpers;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;

public class Helper {

    private static String PATH_EVIDENCE = "ExtentReports\\Evidence";
    private static String DIR_EVIDENCE = ".\\Evidence\\";

    /**
     * <p>
     * Agrega la evidencia de prueba al reporte a generar.
     *
     * @param TAKE_SS    boolean que indica si se debe adjuntar la evidencia.
     * @param driver     WebDriver del navegador utilizado.
     * @param test       referencia al reporte de ExtentReports.
     * @param imageTitle titulo/descripci�n de la imagen.
     * @param subDir     Subdirectorio en el cual se guardara la imagen.
     * @param imageName  nombre de la imagen sin extension.
     */

    public static void addEvidence(Boolean TAKE_SS, WebDriver driver, ExtentTest test, String imageTitle, String subDir,
                                   String imageName) {
        if (TAKE_SS) {
            Helper.takeScreenShot(driver, subDir, imageName);
            Helper.takeEvidence(test, imageTitle, subDir, imageName);
        }
    }

    /**
     * Toma una impresion de pantalla y la guarda en un directorio donde quedan
     * todas las evidencias de prueba
     *
     * @param driver    driver navegador.
     * @param subDir    directorio de las imagenes.
     * @param imageName nombre de la imagen sin extension.
     */
    public static void takeScreenShot(WebDriver driver, String subDir, String imageName) {
        // Directorio donde quedaran las imagenes guardadas
        File directory = new File(PATH_EVIDENCE);

        try {
            if (directory.isDirectory()) {
                // Toma la captura de imagen
                File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                // Mueve el archivo a la carga especificada con el respectivo nombre
                FileUtils.copyFile(imagen,
                        new File(directory.getAbsolutePath() + "\\" + subDir + "\\" + imageName + ".png"));
            } else {
                // Se lanza la excepcion cuando no encuentre el directorio
                throw new IOException("ERROR : La ruta especificada no es un directorio!");
            }
        } catch (IOException e) {
            // Impresion de Excepciones
            e.printStackTrace();
        }
    }

    /**
     * Adjunta una imagen como evidencia de prueba, la imagen ya ha sido capturada
     * por:
     *
     * @param test       referencia a la instancia de ExtentReports.
     * @param imageTitle titulo de la imagen.
     * @param subDir     subdirectorio de la imagen.
     * @param imageName  nombre de la imagen sin extension.
     */
    private static void takeEvidence(ExtentTest test, String imageTitle, String subDir, String imageName) {
        File directory = new File(PATH_EVIDENCE);

        try {
            if (directory.isDirectory()) {
                test.log(Status.INFO,
                        imageTitle + test.addScreenCaptureFromPath(DIR_EVIDENCE + subDir + "\\" + imageName + ".png"));

            } else {
                // Se lanza la excepcion cuando no encuentre el directorio
                throw new IOException("ERROR : La ruta especificada no es un directorio!");
            }
        } catch (IOException e) {
            // Impresion de Excepciones
            e.printStackTrace();
        }
    }

    /**
     * Detiene la ejecucion la cantidad de segundos indicados por parametro.
     * Utilitario para usarse en conjunto con los implicitWait.
     */
    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utilizamos un robot para mover el mouse a la posicion x,y de la pantalla.
     *
     * @param x posicion x de la pantalla.
     * @param y posicion y de la pantalla.
     */
    @SuppressWarnings("unused")
    public static void robotMoveMouse(int x, int y) throws AWTException {
        // Implementamos un robot para mover el mouse a la posicion x,y.
        // Obtenemos un objeto Robot.
        try {
            Robot robot = new Robot();
            robot.mouseMove(x,y);
        }catch (AWTException e){
            System.err.println("Error al mover el ratón a la posición (" + x + ", " + y + ")");
            e.printStackTrace();
        }



                /*
        Robot robotObj;
        try {
            robotObj = new Robot();
            robotObj.mouseMove(x, y);
        } catch (AWTException e) {
            // Error al seleccionar el archivo.
            e.printStackTrace();
        }*/
    }

}
