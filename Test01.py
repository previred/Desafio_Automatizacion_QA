#Autor: Jorge Morandin
#Empresa: Para Previred
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException, StaleElementReferenceException
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import Select 
from selenium.webdriver.chrome.options import Options
import time
import os
import json
import random
import string

# Crear la carpeta "Evidencia" si no existe
screenshot_dir = "Evidencia"
direccion_completa = ''
if not os.path.exists(screenshot_dir):
    os.makedirs(screenshot_dir)

# Configurar las opciones de Chrome
chrome_options = Options()
chrome_options.add_argument("--allow-insecure-localhost")
chrome_options.add_argument("--ignore-certificate-errors")
chrome_options.add_argument("--disable-gpu")
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")
chrome_options.add_argument("--window-size=1920,1080")

# Configurar el controlador de Selenium
service = ChromeService(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=chrome_options)

# Maximizar la ventana del navegador
driver.maximize_window()

# Función para tomar una captura de pantalla
def tomar_screenshot(step_name):
    driver.save_screenshot(os.path.join(screenshot_dir, f'evidencia_{step_name}.png'))

# Función Paso 1: Localizar el campo de búsqueda, escribir el texto de búsqueda y tomar captura
def Buscar_Elemento(Texto_Busqueda):
    try:
        search_field = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.NAME, "search"))
        )
        search_field.send_keys(Texto_Busqueda)
        tomar_screenshot(f'search_filled_{Texto_Busqueda}')

        search_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, "//button[@class='btn btn-default btn-lg']"))
        )
        search_button.click()
        tomar_screenshot(f'click_busqueda_{Texto_Busqueda}')

        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, "//div[@id='content']"))
        )
        tomar_screenshot(f'search_results_{Texto_Busqueda}')
    except Exception as e:
        print(f"Error al buscar el elemento: {e}")
        tomar_screenshot('error_Buscar_Elemento')

# Función Paso 2: Buscar la imagen del producto y hacer clic en ella
def Click_Boton_Texto(Texto_Busqueda):
    try:
        product_image = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, f"//a[contains(text(),'{Texto_Busqueda}')]/ancestor::div[@class='product-thumb']//img"))
        )
        product_image.click()
        tomar_screenshot(f'product_page_{Texto_Busqueda}')
    except Exception as e:
        print(f"Error al hacer clic en la imagen de {Texto_Busqueda}: {e}")
        tomar_screenshot(f'error_click_{Texto_Busqueda}')

# Función Paso 3: Buscar el botón "Add to Cart" y hacer clic en él
def Agregar_Carro_Compra():
    try:
        add_to_cart_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.ID, "button-cart"))
        )
        add_to_cart_button.click()
        tomar_screenshot('add_to_cart')
    except Exception as e:
        print(f"Error al hacer clic en el botón Agregar al Carro: {e}")
        tomar_screenshot('error_add_to_cart')

# Función Paso 4: Hacer clic en el enlace del carrito de compras
def Click_Shopping_Cart():
    try:
        shopping_cart_link = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.XPATH, "//a[@href='http://opencart.abstracta.us:80/index.php?route=checkout/cart']"))
        )
        shopping_cart_link.click()
        tomar_screenshot('shopping_cart')
    except Exception as e:
        print(f"Error al hacer clic en el enlace del Carrito de Compras: {e}")
        tomar_screenshot('error_shopping_cart')

def Verificar_Productos_en_Tabla(driver):
    try:
        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, "//table[@class='table table-bordered']"))
        )
        
        filas = driver.find_elements(By.XPATH, "//table[@class='table table-bordered']/tbody/tr")
        productos_a_verificar = ['iPod Classic', 'iMac']
        productos_encontrados = []

        for fila in filas:
            try:
                nombre_producto = fila.find_element(By.XPATH, "./td[2]").text
                productos_encontrados.append(nombre_producto)
            except NoSuchElementException:
                print("No se pudo encontrar el elemento en la fila:", fila.get_attribute('outerHTML'))
        
        for producto in productos_a_verificar:
            if producto in productos_encontrados:
                print(f"El producto '{producto}' está en el carro.")
            else:
                print(f"El producto '{producto}' no está en el carro.")
                
    except Exception as e:
        print(f"Error al verificar productos en la tabla: {e}")
        tomar_screenshot('error_verificar_productos')

# Función Paso 5: Hacer clic en el enlace "Checkout"
def Click_Checkout():
    try:
        checkout_link = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, "//a[@href='https://opencart.abstracta.us:443/index.php?route=checkout/checkout' and contains(@class, 'btn-primary')]"))
        )
        checkout_link.click()
        tomar_screenshot('checkout')
    except Exception as e:
        print(f"Error al hacer clic en el enlace Checkout: {e}")
        tomar_screenshot('error_checkout')

# Función principal para ejecutar los pasos para un producto específico
def Procesar_Producto(Texto_Busqueda):
    driver.get("http://opencart.abstracta.us/index.php?route=common/home")
    tomar_screenshot(f'home_page_{Texto_Busqueda}')
    Buscar_Elemento(Texto_Busqueda)
    Click_Boton_Texto(Texto_Busqueda)
    Agregar_Carro_Compra()

# Función Paso 6: Agregar el valor del correo electrónico al campo de entrada
def Agregar_Email(username):
    try:
        email_field = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "input-email"))
        )
        email_field.send_keys(username)        
    except Exception as e:
        print(f"Error al ingresar el correo electrónico: {e}")
        tomar_screenshot('error_ingresar_email')

# Función Paso 7: Agregar el valor de la contraseña al campo de entrada
def Agregar_Password(password):
    try:
        password_field = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "input-password"))
        )
        password_field.send_keys(password)
        tomar_screenshot('password_completado')
    except Exception as e:
        print(f"Error al ingresar la contraseña: {e}")
        tomar_screenshot('error_ingresar_password')

# Función Paso 8: Hacer clic en el botón "Login"
def Click_Login():
    try:
        login_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.ID, "button-login"))
        )
        login_button.click()
        tomar_screenshot('login_clickedo')
    except Exception as e:
        print(f"Error al hacer clic en el botón Login: {e}")
        tomar_screenshot('error_click_login')

# Función Paso 9: Hacer clic en el botón "Continue"
def Click_Continue():
    try:
        continue_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.ID, "button-account"))
        )
        continue_button.click()
        tomar_screenshot('continue_clicked')
    except Exception as e:
        print(f"Error al hacer clic en el botón Continuar: {e}")
        tomar_screenshot('error_click_continue')

def Generar_Email_Aleatorio(email_base):
    combinacion = ''.join(random.choices(string.ascii_letters + string.digits, k=3))
    nombre_usuario, dominio = email_base.split('@')
    email_aleatorio = f"{nombre_usuario}{combinacion}@{dominio}"
    return email_aleatorio

# Función para autentificar las credenciales
def Autentificar_Credenciales():
    try:
        with open('credenciales.json') as cred_file:
            credentials = json.load(cred_file)
        email = credentials['email']
        password = credentials['password']
        Agregar_Email(email)
        Agregar_Password(password)
        Click_Login()
    except Exception as e:
        print(f"Error al autentificar las credenciales: {e}")
        tomar_screenshot('error_autentificar_credenciales')

# Función para crear un usuario
def Crear_Usuario():
    try:
        with open('datos_usuarios.json', 'r') as file:
            datos_usuario = json.load(file)

        campos = {
            "input-payment-firstname": datos_usuario["firstname"],
            "input-payment-lastname": datos_usuario["lastname"],
            "input-payment-email": Generar_Email_Aleatorio(datos_usuario["email"]),
            "input-payment-telephone": datos_usuario["telephone"],
            "input-payment-password": datos_usuario["password"],
            "input-payment-confirm": datos_usuario["confirm"],
            "input-payment-address-1": datos_usuario["address_1"],
            "input-payment-city": datos_usuario["city"],
            "input-payment-postcode": datos_usuario["postcode"]
        }
        
        #Concatena la dirección completa para validación N° 17
        #direccion_completa = f"{datos_usuario['firstname']}, {datos_usuario['lastname']}, {datos_usuario['address_1']}, {datos_usuario['city']}, {datos_usuario['region']}, {datos_usuario['country']}"

        for campo_id, value in campos.items():
            campo = WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.ID, campo_id))
            )
            campo.send_keys(value)
            tomar_screenshot(f'completitud_{campo_id}')

        country_select = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "input-payment-country"))
        )
        select_country = Select(country_select)
        select_country.select_by_value("43")
        tomar_screenshot('country_selected')

        time.sleep(2)

        zone_select = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "input-payment-zone"))
        )
        select_zone = Select(zone_select)
        select_zone.select_by_value("681")
        tomar_screenshot('zone_selected')

        agree_checkbox = WebDriverWait(driver, 20).until(
            EC.presence_of_element_located((By.XPATH, "//input[@type='checkbox' and @name='agree' and @value='1']"))
        )
        if not agree_checkbox.is_selected():
            agree_checkbox.click()
        tomar_screenshot('checkbox_agree_checked')

        continue_button = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.ID, "button-register"))
        )
        continue_button.click()
        tomar_screenshot('register_continue_clicked')
    except Exception as e:
        print(f"Error al crear el usuario: {e}")
        tomar_screenshot('error_crear_usuario')

# Función Paso 10: Hacer clic en el botón "Continue" en la sección de dirección de envío
def Click_Continue_Delivery_Details():
    try:
        button = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.ID, 'button-shipping-address'))
        )
        button.click()
    except Exception as e:
        print(f"Error al hacer clic en el botón Continuar en los detalles de envío: {e}")
        tomar_screenshot('error_click_continue_delivery_details')

# Función Paso 11: Agregar comentario en el método de entrega
def Agregar_Comentario_Delivery_Method(comentario):
    try:
        comment_field = WebDriverWait(driver, 20).until(
            EC.presence_of_element_located((By.NAME, "comment"))
        )
        comment_field.send_keys(comentario)        
    except Exception as e:
        print(f"Error al agregar comentario: {e}")
        tomar_screenshot('error_agregar_comentario')

# Función Paso 12: Hacer clic en el botón "Continue" en la sección del método de entrega
def Click_Continue_Delivery_Method():
    try:
        continue_button = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.ID, "button-shipping-method"))
        )
        continue_button.click()        
    except Exception as e:
        print(f"Error al hacer clic en el botón Continuar en el método de entrega: {e}")
        tomar_screenshot('error_click_continue_delivery_method')

# Función Paso 13: Marcar la casilla de verificación "agree"
def Marcar_Checkbox_Agree_Payment_Method():
    try:
        agree_checkbox = WebDriverWait(driver, 20).until(
            EC.presence_of_element_located((By.XPATH, "//input[@type='checkbox' and @name='agree' and @value='1']"))
        )
        agree_checkbox.click()        
    except Exception as e:
        print(f"Error al marcar la casilla de verificación: {e}")
        tomar_screenshot('error_marcar_checkbox')

# Función Paso 14: Hacer clic en el botón "Continue" en la sección de método de pago
def Click_Continue_Payment_Method():
    try:
        continue_button = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.ID, "button-payment-method"))
        )
        continue_button.click()
        tomar_screenshot('continue_payment_method_clicked')
    except Exception as e:
        print(f"Error al hacer clic en el botón Continuar en el método de pago: {e}")
        tomar_screenshot('error_click_continue_payment_method')

# Función Paso 15: Hacer clic en el botón "Confirm Order"
def Click_Confirm_Order():
    try:
        confirm_button = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.ID, "button-confirm"))
        )
        confirm_button.click()
        tomar_screenshot('confirm_order_clicked')
    except Exception as e:
        print(f"Error al hacer clic en el botón Confirmar Orden: {e}")
        tomar_screenshot('error_click_confirm_order')

# Función Paso 16: Ir a My Account y luego a Order History
def Go_To_Order_History():
    try:
        my_account_menu = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.XPATH, "//a[@title='My Account']"))
        )
        my_account_menu.click()
        
        order_history_option = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.LINK_TEXT, "Order History"))
        )
        order_history_option.click()
        tomar_screenshot('order_history')
    except StaleElementReferenceException:
        my_account_menu = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.XPATH, "//a[@title='My Account']"))
        )
        my_account_menu.click()
        
        order_history_option = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.LINK_TEXT, "Order History"))
        )
        order_history_option.click()
        tomar_screenshot('order_history')
    except Exception as e:
        print(f"Error al navegar a Historial de Órdenes: {e}")
        tomar_screenshot('error_go_to_order_history')

# Función Paso 17: Hacer clic en el botón "View" en la lista de órdenes
def Click_View_Order():
    try:
        view_button = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.XPATH, "//a[contains(@data-original-title, 'View')]"))
        )
        view_button.click()
        tomar_screenshot('Resumen de orden')
    except Exception as e:
        print(f"Error al hacer clic en el botón Ver: {e}")
        tomar_screenshot('error_click_view_order')

# Función Paso 18: Ir a "Mi cuenta" y luego seleccionar "Logout"
def Logout():
    try:
        my_account_menu = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.XPATH, "//a[@title='My Account']"))
        )
        my_account_menu.click()
        
        logout_option = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.LINK_TEXT, "Logout"))
        )
        logout_option.click()
        tomar_screenshot('logout')
    except Exception as e:
        print(f"Error al cerrar sesión: {e}")
        tomar_screenshot('error_logout')

# Función para validar el costo del envío
def Validar_Costo_Envio():
    try:
        # Esperar a que el elemento esté presente en el DOM
        label_element = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, "//label[input[@type='radio' and @name='shipping_method' and @value='flat.flat']]"))
        )

        # Obtener el texto del label
        label_text = label_element.text.strip()
        
        # Validar el texto
        if label_text == "Flat Shipping Rate - $5.00":
            print("El valor del costo de envío es de $5.00")
            tomar_screenshot('costo_el_valor_es_5')
        else:
            print(f"El valor del costo de envío es distinto de $5.00: {label_text}")
            tomar_screenshot('costo_el_valor_no_es_5')
    except NoSuchElementException:
        print("No se pudo encontrar el elemento del costo de envío.")
        tomar_screenshot('error_no_elemento_costo_envio')
    except TimeoutException:
        print("El elemento del costo de envío no se cargó a tiempo.")
        tomar_screenshot('error_tiempo_carga_costo_envio')
    except Exception as e:
        print(f"Error al validar el texto dentro del label: {e}")
        tomar_screenshot('error_validar_costo_envio')


# Validación N°17
# Función para comparar la dirección completa con el valor seleccionado del select
#def Comparar_Direccion_Completa():
#    try:
#        # Encontrar el elemento select
#        select_element = WebDriverWait(driver, 20).until(
#            EC.presence_of_element_located((By.NAME, "address_id"))
#        )
#
#        # Encontrar la opción seleccionada
#        selected_option = select_element.find_element(By.CSS_SELECTOR, "option[selected='selected']")
#        
#        # Obtener el texto de la opción seleccionada
#        selected_text = selected_option.text.strip()
#        
#        # Comparar con la dirección completa
#        if selected_text == direccion_completa:
#            print("La dirección completa coincide con el valor seleccionado en el select.")
#            tomar_screenshot('si_coincide_direccion_registro_y_pago')
#        else:
#            print(f"La dirección completa no coincide.\nDirección completa: {direccion_completa}\nValor seleccionado: {selected_text}")
#            tomar_screenshot('no_coincide_direccion_registro_y_pago')
#    except Exception as e:
#        print(f"Error al comparar la dirección completa: {e}")


# Función para ejecutar todos los pasos
def ejecutar_script():
    try:
        Procesar_Producto('iPod Classic')
        Procesar_Producto('iMac')
        Click_Shopping_Cart()
        Verificar_Productos_en_Tabla(driver)
        Click_Checkout()
        Autentificar_Credenciales()
        Click_Continue()
        Crear_Usuario()
        Click_Continue_Delivery_Details()
        Validar_Costo_Envio()
        Agregar_Comentario_Delivery_Method("Test 02 - Prueba Automatización")
        Click_Continue_Delivery_Method()
        time.sleep(3)
        Marcar_Checkbox_Agree_Payment_Method()
        Click_Continue_Payment_Method()        
        Click_Confirm_Order()
        Go_To_Order_History()
        Click_View_Order()
        driver.implicitly_wait(10)
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        tomar_screenshot('Evidencia_Order_History')
        driver.implicitly_wait(5)
        Logout()
        time.sleep(5)
    finally:
        driver.quit()

# Ejecutar el script
ejecutar_script()
