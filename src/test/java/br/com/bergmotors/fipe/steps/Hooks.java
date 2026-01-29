package br.com.bergmotors.fipe.steps; // Ajuste para o seu pacote

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Hooks {

    @After // Roda SEMPRE ao final de cada cenário
    public void anexaPlanilhaNoRelatorio(Scenario scenario) {


        File arquivoExcel = new File("fipe.xlsx");

        if (arquivoExcel.exists()) {
            try (InputStream is = new FileInputStream(arquivoExcel)) {

                Allure.addAttachment(
                        "Planilha Fipe - " + scenario.getName(), // Nome que aparece no link
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // Tipo do arquivo (MIME type)
                        is,
                        ".xlsx" // Extensão
                );

            } catch (IOException e) {
                System.err.println("Erro ao anexar planilha: " + e.getMessage());
            }
        }
    }
}