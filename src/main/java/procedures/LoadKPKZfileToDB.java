package procedures;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import static procedures.AditionalProcedure.*;


public class LoadKPKZfileToDB {
    private static String fileName = "d://stock/KPKZ.xls";
    private static String stock = "Кабельный Завод (Харьков)";
    private static int columnwithName = 0;
    private static int columnwithPrice = 15;


    public static void main(String[] args) {
        try {
            loadKPKZpriceToDB();
        } catch (NullPointerException e) {
        } catch (SQLException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //загрузка файла ДТС в базу данных
    public static void loadKPKZpriceToDB() throws IOException, SQLException { //поиск в файле дтс

        AditionalProcedure.truncateBySTOCK(stock);

        String date = actualityDate(fileName);
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(fileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String name = "";
        for (int i = 10; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
            row = myExcelSheet.getRow(i);
            String fullname ="";
            if(myExcelSheet.getRow(i).getCell(0)==null)continue;
            for(int j = 0; j<10; j++) {
                fullname += getTextCellValue(row.getCell(j))+" ";
            }
            try {

                    fullname = fullname.replaceAll(".0","");
                    fullname =fullname.replaceAll("ХЛ","");
                fullname =fullname.replaceAll("380/660","");
                fullname =fullname.replaceAll("450","");
                fullname =fullname.replaceAll("380","");
                fullname =fullname.replaceAll("660","");
                fullname =fullname.replaceAll("3КЛ","");
                fullname =fullname.replaceAll("1000","");

                String nameToBD = fullname;
                fullname = fullname.toUpperCase().replaceAll(" ", "");

                Double price = row.getCell(columnwithPrice).getNumericCellValue() / 1000;
                    if(fullname.equals("")){continue;}
                    if(price==0.0){continue;}
                    addKableToDB(fullname, 0.0, price, stock, date, nameToBD);
                    System.out.println(fullname+" "+stock);

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (NullPointerException e5) {
                e5.printStackTrace();
            }
            myExcelBook.close();


        }
    } //загрузка из файла статков и цен дтс
}
