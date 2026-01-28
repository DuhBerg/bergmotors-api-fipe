package br.com.bergmotors.fipe.steps;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    private static final String CAMINHO_ARQUIVO = "fipe.xlsx"; // Ajuste o caminho

    public static void atualizarPrecoNoExcel(String modelo, int ano, String valor) throws IOException {
        FileInputStream fis = new FileInputStream(CAMINHO_ARQUIVO);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int colunaModelo = -1;
        int linhaAno = -1;

        // 1. Encontrar a Coluna do Modelo
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().toLowerCase().contains(modelo.toLowerCase())) {
                colunaModelo = cell.getColumnIndex();
                break;
            }
        }

        // 2. Encontrar a Linha (Lógica nova para Zero KM)
        for (Row row : sheet) {
            Cell cellAno = row.getCell(0); // Coluna A

            if (cellAno != null) {
                // REGRA DO ZERO KM (Ano 32000)
                if (ano == 32000) {
                    // Verifica se é texto e se contém "Zero KM"
                    if (cellAno.getCellType() == CellType.STRING &&
                            cellAno.getStringCellValue().equalsIgnoreCase("Zero KM")) {
                        linhaAno = row.getRowNum();
                        break;
                    }
                }
                // REGRA PARA ANOS NORMAIS
                else {
                    if (cellAno.getCellType() == CellType.NUMERIC &&
                            (int) cellAno.getNumericCellValue() == ano) {
                        linhaAno = row.getRowNum();
                        break;
                    }
                }
            }
        }

        // 3. Escrever o valor
        if (colunaModelo != -1 && linhaAno != -1) {
            Row row = sheet.getRow(linhaAno);
            Cell cell = row.getCell(colunaModelo);
            if (cell == null) {
                cell = row.createCell(colunaModelo);
            }

            // Se for Zero KM, escreve bonitinho, senão escreve normal
            String prefixo = (ano == 32000) ? "Zero KM" : String.valueOf(ano);
            cell.setCellValue(prefixo + " - " + valor);

            //System.out.println("Salvo: " + modelo + " | " + prefixo);
        } else {
            // Log para ajudar a debugar se não achar a linha "Zero KM"
            String anoLog = (ano == 32000) ? "Zero KM" : String.valueOf(ano);
            System.err.println("Não encontrado no Excel: Modelo ("+modelo+") ou Ano ("+anoLog+")");
        }

        fis.close();

        // 4. Salvar
        FileOutputStream fos = new FileOutputStream(CAMINHO_ARQUIVO);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
}