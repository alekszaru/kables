package procedures;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import static procedures.AditionalProcedure.actualityDate;
import static procedures.AditionalProcedure.addKableToDB;

public class LoadEAfileToDB {

    private static String eaFileName = "d://stock/EA.xls";
    private static String stock = "Энергоальянс (Запорожье)";
    private static int columnwithName = 1;
    private static int columnwithQuantity = 7;

    public static String getStock() {
        return stock;
    }

    //загрузка файла Энергоальянс в базу данных
    public static void loadEAfile() throws IOException, SQLException { //поиск в файле энергоальянс

        AditionalProcedure.truncateBySTOCK(stock);

        String date = actualityDate(eaFileName);

        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(eaFileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String name = "";
        for (int i = 11; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
            row = myExcelSheet.getRow(i);
            try {
                String fullname =row.getCell(columnwithName).getStringCellValue();

                if((fullname.contains("Провід "))||(fullname.contains("Кабель "))){
                    fullname = fullname.replaceAll("Провід ", "");
                    fullname = fullname.replaceAll("Кабель ", "");
                    String nameToDB = fullname;
                    fullname = fullname.toUpperCase().replaceAll(" ","");
                    Double cuantaty = row.getCell(columnwithQuantity).getNumericCellValue();
                    addKableToDB(fullname,cuantaty,0.00,stock,date,nameToDB);
                    System.out.println(fullname);
                }
            }catch (NullPointerException e5) {}
            catch (IllegalStateException e){}
        }
        myExcelBook.close();
    } //загрузка из файла остатков энергоальянс
}
