package TestAutomation;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcel {

    public WriteExcel(){

    }

    public void writeCellValue(String filepath, String sheetname, int rowNumber, int cellNumber, String resultText) throws IOException {
        File file = new File(filepath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook newWork = new XSSFWorkbook(inputStream);

        XSSFSheet newSheet = newWork.getSheet(sheetname);

        XSSFRow row = newSheet.getRow(rowNumber);
        //XSSFRow row = newSheet.createRow(rowNumber);

        XSSFCell nextCell = row.getCell(cellNumber);
        if(nextCell==null)
            nextCell = row.createCell(cellNumber);

        nextCell.setCellValue(resultText);

        inputStream.close();

        FileOutputStream outputStream = new FileOutputStream(file);

        newWork.write(outputStream);

        outputStream.close();



    }

}
