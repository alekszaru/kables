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
    private static String stock = "������������ (�������)";
    private static int columnwithName = 1;
    private static int columnwithQuantity = 5;


    //�������� ����� ������������ � ���� ������
    public static void loadMKfile() throws IOException, SQLException { //����� � ����� ������������

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
                            fullname.startsWith("�-")||
                            fullname.startsWith("��")||
                            fullname.startsWith("���")||
                            fullname.startsWith("���")||
                            fullname.startsWith("��")||
                            fullname.startsWith("��")||
                            fullname.startsWith("��")||
                            fullname.startsWith("��")||
                            fullname.startsWith("���")||
                            fullname.startsWith("��")||
                            fullname.startsWith("��")||
                            fullname.startsWith("���")||
                            fullname.startsWith("���")){
                        fullname = fullname.replaceAll(" ������ �������","");
                        fullname = fullname.replaceAll(" �����","");
                        fullname = fullname.replaceAll(" ��������","");
                        fullname = fullname.replaceAll(" ������������","");
                        fullname = fullname.replaceAll(" ��������������","");
                        fullname = fullname.replaceAll(" ����","");
                        fullname = fullname.replaceAll(" ���������","");
                        fullname = fullname.replaceAll(" ����","");
                        fullname = fullname.replaceAll(" ����������","");
                        fullname = fullname.replaceAll(" �������","");
                        fullname = fullname.replaceAll(" ������������","");
                        fullname = fullname.replaceAll(" �����������","");
                        fullname = fullname.replaceAll(" ���� (��)","");
                        fullname = fullname.replaceAll(" ������","");
                        fullname = fullname.replaceAll(" ������ ������","");
                        fullname = fullname.replaceAll(" ������ ���","");


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
    } //�������� �� ����� �������� ������������

}
