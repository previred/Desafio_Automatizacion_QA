from behave import given, when, then
from selenium.webdriver.common.by import By
import time
import os
from selenium import webdriver
import pandas as pd
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


@given('El navegador está abierto y en la página principal de OpenCart')
def abrir_pagina(context):
    driver = webdriver.Chrome()
    driver.maximize_window()
    driver.get("http://opencart.abstracta.us/")
    context.driver = driver
    time.sleep(2)


@when('Añadir el producto "{producto}" al carrito')
def añadir_producto(context, producto):
    driver = context.driver
    search_box = driver.find_element(By.CSS_SELECTOR, '#search > input')
    search_box.clear()
    search_box.send_keys(producto)
    driver.find_element(By.CSS_SELECTOR, '#search > span > button').click()
    time.sleep(2)
    try:
        product_link = driver.find_element(By.CSS_SELECTOR, '#content > div:nth-child(8) > div > div > div:nth-child(2) > div.button-group > button:nth-child(1)')
        product_link.click()
        time.sleep(2)
    except:
        print(f"No se encontró la lista del producto '{producto}'.")
        return
    try:
        driver.find_element(By.CSS_SELECTOR, '#cart-total').click()
        time.sleep(2)
        driver.save_screenshot(f"evidencias/{producto}_agregado.png")
    except:
        print(f"No se pudo agregar {producto} al carrito.")

@then('Evidenciar la adición del producto "{producto}"')
def evidenciar_producto(context, producto):
    pass


@then("Validar que los artículos en el carrito sean \"Ipod Classic\" y \"iMac\"")
def step_validar_articulos_carrito(context):
    driver = context.driver
    driver.find_element(By.XPATH, '/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[1]').click()
    time.sleep(3)
    nombres_elementos = driver.find_elements(By.XPATH, '//table//tr/td[2]/a')
    nombres = [n.text for n in nombres_elementos]
    print("Nombres en carrito:", nombres)
    assert any("Ipod Classic".lower() == n.lower() for n in nombres), "'Ipod Classic' no está en el carrito"
    assert any("iMac".lower() == n.lower() for n in nombres), "'iMac' no está en el carrito"
    driver.save_screenshot("evidencias/carrito_validado.png")
    from behave import then, when
from selenium.webdriver.common.by import By
import time

@then("realiza la compra")
def step_realiza_la_compra(context):
    driver = context.driver
    driver.find_element(By.CSS_SELECTOR, "#content > div.buttons.clearfix > div.pull-right > a").click()
    time.sleep(3)
   
    

@when("Cargar credenciales desde archivo y realizar login")
def step_cargar_credenciales(context):

    archivo_path = r"C:\Users\Canisama\Desktop\previred\data\datos.xlsx"
    df = pd.read_excel(archivo_path, sheet_name='data')
    username = df.iloc[0]['username']
    password = df.iloc[0]['password']
    driver = context.driver
    time.sleep(2)
    driver.find_element(By.CSS_SELECTOR, "#input-email").send_keys(username)
    driver.find_element(By.CSS_SELECTOR, "#input-password").send_keys(password)
    driver.find_element(By.CSS_SELECTOR, "#button-login").click()
    time.sleep(3)
    assert driver.find_element(By.CSS_SELECTOR, "#button-account")

@when("Crear una cuenta con datos específicos y evidenciar la creación")
def step_crear_cuenta(context):
    driver = context.driver
    driver.find_element(By.CSS_SELECTOR, "#button-account").click()
    time.sleep(2)
    driver.find_element(By.CSS_SELECTOR, "#input-payment-firstname").send_keys("Nombre")
    driver.find_element(By.CSS_SELECTOR, "#input-payment-lastname").send_keys("Apellido")
    driver.find_element(By.CSS_SELECTOR, "#input-payment-email").send_keys("previredprevired@gmail.com")
    driver.find_element(By.CSS_SELECTOR, "#input-payment-telephone").send_keys("123456789")
    driver.find_element(By.CSS_SELECTOR, "#input-payment-password").send_keys("Contraseña123")
    driver.find_element(By.CSS_SELECTOR, "#input-payment-confirm").send_keys("Contraseña123")
    driver.find_element(By.CSS_SELECTOR, "#input-payment-address-1").send_keys("Calle Falsa 123")
    driver.find_element(By.CSS_SELECTOR, "#input-payment-city").send_keys("Melipilla")
    
    country_select = driver.find_element(By.CSS_SELECTOR, "#input-payment-country")
    for option in country_select.find_elements(By.TAG_NAME, "option"):
        if option.text == "Chile":
            option.click()
            break
    time.sleep(5)
    
    region_select = driver.find_element(By.CSS_SELECTOR, "#input-payment-zone")
    for option in region_select.find_elements(By.TAG_NAME, "option"):
        if option.text == "Region Metropolitana": 
            option.click()
            break
    time.sleep(2)
    
    checkbox = driver.find_element(By.CSS_SELECTOR, "#collapse-payment-address > div > div.buttons.clearfix > div > input[type=checkbox]:nth-child(2)")
    if not checkbox.is_selected():
        checkbox.click()
    time.sleep(2)
    driver.save_screenshot('evidencias/creacion_de_cuenta.png')
    driver.find_element(By.CSS_SELECTOR, "#button-register").click()
    time.sleep(5)
    

def wait_and_click(driver, selector, timeout=10):
    element = WebDriverWait(driver, timeout).until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, selector))
    )
    element.click()

@when('Continuar con la compra')
def step_continuar_con_la_compra(context):
    driver = context.driver
    selector_boton = "#button-shipping-address"
    WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, selector_boton))
    ).click()
    time.sleep(5)

@then("Validar que el costo de envío sea \"$5.00\"")
def step_validar_costo_envio(context):
    driver = context.driver
    driver.save_screenshot("evidencias/$5.00.png")
    texto_total = driver.find_element(By.CSS_SELECTOR, '#collapse-shipping-method > div > div.radio > label').text
    driver.find_element(By.CSS_SELECTOR, "#button-shipping-method").click()
    time.sleep(5)
    assert "$5.00" in texto_total

@then("Confirmar la orden")
def step_confirmar_orden(context):
    driver = context.driver
    driver.find_element(By.CSS_SELECTOR, "#collapse-payment-method > div > div:nth-child(3) > label > input[type=radio]").click()
    time.sleep(3)
    driver.find_element(By.CSS_SELECTOR, "#collapse-payment-method > div > div.buttons > div > input[type=checkbox]:nth-child(2)").click()
    time.sleep(5)
    driver.save_screenshot("evidencias/confirmar orden.png")
    driver.find_element(By.CSS_SELECTOR, "#button-payment-method").click()
    time.sleep(5)
    driver.save_screenshot("evidencias/confirmar orden 2.png")
    driver.find_element(By.CSS_SELECTOR, "#button-confirm").click()
    time.sleep(5)
    

@when("Visitar el historial de órdenes")
def step_ver_historial(context):
    driver = context.driver
    driver.find_element(By.CSS_SELECTOR, "#top-links > ul > li.dropdown > a > span.hidden-xs.hidden-sm.hidden-md").click()
    time.sleep(5)
    driver.find_element(By.CSS_SELECTOR, "#top-links > ul > li.dropdown.open > ul > li:nth-child(2) > a").click()
    time.sleep(5)
    driver.find_element(By.CSS_SELECTOR, "#content > div.table-responsive > table > tbody > tr:nth-child(1) > td:nth-child(7) > a > i").click()
    time.sleep(5)
    driver.save_screenshot("evidencias/historial.png")


@when("Cerrar sesión")
def step_cerrar_sesion(context):
    driver = context.driver
    driver.find_element(By.CSS_SELECTOR, "#top-links > ul > li.dropdown > a > span.hidden-xs.hidden-sm.hidden-md").click()
    time.sleep(2)
    driver.find_element(By.CSS_SELECTOR, "#top-links > ul > li.dropdown.open > ul > li:nth-child(5) > a").click()
    time.sleep(5)
    driver.quit()







