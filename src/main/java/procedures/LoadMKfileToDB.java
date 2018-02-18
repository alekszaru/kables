package procedures;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import static procedures.AditionalProcedure.actualityDate;
import static procedures.AditionalProcedure.addKableToDB;

public class LoadMKfileToDB {
    private static String mkFileName = "d://stock/MK.xls";
    private static String stock = "МастерКабель (Прилуки)";
    private static int columnwithName = 1;
    private static int columnwithQuantity = 5;


    //загрузка файла МастерКабель в базу данных
    public static void loadMKfile() throws IOException, SQLException { //поиск в файле энергоальянс

        AditionalProcedure.truncateBySTOCK(stock);

        String date = actualityDate(mkFileName);
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(mkFileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String name = "";
        for (int i = 11; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
            row = myExcelSheet.getRow(i);
            try {
                if(row.getCell(columnwithName).getCellType()==1){
                    String fullname =row.getCell(columnwithName).getStringCellValue();
                    String nameToDB = fullname;
                    if(fullname.startsWith("(N) HXH")||
                            fullname.startsWith("AsXSn")||
                            fullname.startsWith("А-")||
                            fullname.startsWith("АА")||
                            fullname.startsWith("АВБ")||
                            fullname.startsWith("АВВ")||
                            fullname.startsWith("АП")||
                            fullname.startsWith("АС")||
                            fullname.startsWith("ВБ")||
                            fullname.startsWith("ВВ")||
                            fullname.startsWith("КВВ")||
                            fullname.startsWith("КГ")||
                            fullname.startsWith("ПВ")||
                            fullname.startsWith("ШВВ")||
                            fullname.startsWith("СИП")){
                        fullname = fullname.replaceAll(" Заводы Украины","");
                        fullname = fullname.replaceAll(" Катех","");
                        fullname = fullname.replaceAll(" Южкабель","");
                        fullname = fullname.replaceAll(" Интерэлектро","");
                        fullname = fullname.replaceAll(" Электротехника","");
                        fullname = fullname.replaceAll(" Кама","");
                        fullname = fullname.replaceAll(" Укркабель","");
                        fullname = fullname.replaceAll(" ПАКТ","");
                        fullname = fullname.replaceAll(" Кольчугино","");
                        fullname = fullname.replaceAll(" Рыбинск","");
                        fullname = fullname.replaceAll(" ИнтерЭлектро","");
                        fullname = fullname.replaceAll(" Интеркабель","");
                        fullname = fullname.replaceAll(" ГОСТ (ТЗ)","");
                        fullname = fullname.replaceAll(" Одесса","");
                        fullname = fullname.replaceAll(" Мастер Кабель","");
                        fullname = fullname.replaceAll(" Энерго ХКЗ","");


                        fullname = fullname.toUpperCase().replaceAll(" ","");

                        Double cuantaty = row.getCell(columnwithQuantity).getNumericCellValue()*1000;
                        addKableToDB(fullname,cuantaty,0.00,stock,date,nameToDB);
                        System.out.println(fullname);
                    }
                }
            }catch (NullPointerException e5) {}
            catch (IllegalStateException e){}
        }
        myExcelBook.close();
    } //загрузка из файла остатков МастерКабель

}
