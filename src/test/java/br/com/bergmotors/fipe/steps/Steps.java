package br.com.bergmotors.fipe.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.Allure;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Steps {

    private Response response;
    private String codigoFipe;

    @Before
    public static void setup() {
        RestAssured.baseURI = "https://brasilapi.com.br/api/fipe/preco/v1";
    }

    @Given("Que consulto a moto com código {string}")
    public void queConsultoAMotoComCodigo(String codigo) {
        this.codigoFipe = codigo;
    }

    @When("Realizo a requisição GET")
    public void realizoARequisicaoGet() {
        System.out.println(codigoFipe);
        System.out.println(baseURI+codigoFipe);
        response = given()
                .filter(new AllureRestAssured())
                .when()
                .get(codigoFipe);

        //response.prettyPrint();
    }

    @Then("Valido o statuscode {int}")
    public void oStatusCodeDeveSer(Integer statusCodeEsperado) {
        assertEquals((int) statusCodeEsperado, response.getStatusCode());
    }

    @And("Preencho o excel com os dados")
    public void preenchoOExcelComOsDados() throws IOException {
        response.then().statusCode(200);

        List<Map<String, Object>> listaVeiculos = response.jsonPath().getList("$");

        for (Map<String, Object> veiculo : listaVeiculos) {

            String modelo = (String) veiculo.get("modelo");
            Integer ano = (Integer) veiculo.get("anoModelo");
            String valor = (String) veiculo.get("valor");

            ExcelUtils.atualizarPrecoNoExcel(modelo, ano, valor);
            Allure.addAttachment("Log do Excel", "Gravei o modelo "+modelo+ " do ano "+ano);
        }
    }
}
