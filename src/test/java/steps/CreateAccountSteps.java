package steps;


import java.util.HashMap;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MainPage;
import utils.FileReaderUtil;

public class CreateAccountSteps {
    

    @And("realizo login con credenciales locales")
    public void realizoLoginConCredencialesLocales() {
        
        // Leer credenciales desde archivo txt en el proyecto
            HashMap<String, String> creds;
            try {
                creds = FileReaderUtil.leerCredenciales();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                
                creds = null;
            }
            String email = creds.get("email");
            String password = creds.get("password");

    }
    @And("creo una cuenta")
    public void creoUnaCuenta() {

    }

}
