package steps;


import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Steps {

    private String endpoint;
    private Response response;

    @Dado("que o endpoint é {string}")
    public void queOEndpointE(String url) {
        this.endpoint = url;
    }

    @Quando("eu fizer uma requisição GET")
    public void euFizerUmaRequisicaoGET() {
        response = given()
                .when()
                .get(endpoint);
    }

    @Então("o status code deve ser {int}")
    public void oStatusCodeDeveSer(Integer statusCodeEsperado) {
        assertEquals((int) statusCodeEsperado, response.getStatusCode());
    }

    @E("o corpo da resposta deve conter o nome {string}")
    public void oCorpoDaRespostaDeveConterONome(String nomeEsperado) {
        String nomeRetornado = response.jsonPath().getString("name");
        assertEquals(nomeEsperado, nomeRetornado);
    }
}