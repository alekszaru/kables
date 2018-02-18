package procedures;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import static procedures.AditionalProcedure.actualityDate;
import static procedures.AditionalProcedure.addKableToDB;
import static procedures.AditionalProcedure.getTextCellValue;


public class LoadDTSfileToDB {
    private static String dtsFileName = "d://stock/DTS.xls";
    private static String stock = "��� (����)";
    private static int columnwithName = 0;
    private static int columnwithQuantity = 1;
    private static int columnwithPrice = 2;

    //�������� ����� ��� � ���� ������
    public static void loadDTSfile() throws IOException, SQLException { //����� � ����� ���

        AditionalProcedure.truncateBySTOCK(stock);

        String date = actualityDate(dtsFileName);
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(dtsFileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String name = "";
        for (int i = 1; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
            row = myExcelSheet.getRow(i);
            String fullname = getTextCellValue(row.getCell(columnwithName));

            try {
                if ((fullname.contains("����� ")) || (fullname.contains("������ "))) {
                    fullname = fullname.replaceAll("����� ", "");
                    fullname = fullname.replaceAll("������ ", "");
                    String nameToBD = fullname;
                    fullname = fullname.toUpperCase().replaceAll(" ","");
                    Double cuantaty = (row.getCell(columnwithQuantity).getNumericCellValue() * 1000);
                    Double price = row.getCell(columnwithPrice).getNumericCellValue() / 1000;
                    addKableToDB(fullname,cuantaty,price,stock,date,nameToBD);
                    System.out.println(fullname);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (NullPointerException e5) {
                e5.printStackTrace();
            }
            myExcelBook.close();
        }
    } //�������� �� ����� ������� � ��� ���
}
