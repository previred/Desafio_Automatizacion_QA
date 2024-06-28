$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/Demo_Cucumber1.feature");
formatter.feature({
  "line": 2,
  "name": "Demo_Cucumber1",
  "description": "",
  "id": "demo-cucumber1",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@DemoC1"
    }
  ]
});
formatter.scenario({
  "line": 4,
  "name": "Busqueda de sitio Falabella",
  "description": "",
  "id": "demo-cucumber1;busqueda-de-sitio-falabella",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "Abrir pagina Google",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Digito Falabella",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Puedo hacer click en la pagina principal",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});