package procedures;

import Entity.KableEntity;
import hibernate.SessionFactoryImpl;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static procedures.AditionalProcedure.getTextCellValue;

public class LoadEApriceToDB {
    private static String eaPriceFileName = "d://stock/ЕАprisenew.xls";
    private static String stock = LoadEAfileToDB.getStock();
    private static int columnwithName1 = 0;
    private static int columnwithName2 = 1;
    private static int columnwithPrice = 2;

    //загрузка прайса Энергоальянс
    public static void loadPriceEA() throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(eaPriceFileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String fullname = "";
        List<KableEntity> list = null;
        Session session1 = SessionFactoryImpl.getSession();
        Transaction transaction1 = session1.beginTransaction();
        try {
            Query q1 = session1.createQuery("from KableEntity where kableStockCompany = :stock");
            q1.setParameter("stock",stock);
            list = q1.list();
            transaction1.commit();
        }catch (Exception e){}
        finally {
            session1.close();
        }

        Session session =null;
        Transaction transaction = null;
        for (KableEntity kable : list) {
            String name = kable.getKableType();
            Double price = 0.0;
            session = SessionFactoryImpl.getSession();;
            transaction =  session.beginTransaction();
            for (int i = 0; i <= myExcelSheet.getPhysicalNumberOfRows(); i++) {
                try {
                    row = myExcelSheet.getRow(i);
                    fullname = getTextCellValue(row.getCell(columnwithName1)) + getTextCellValue(row.getCell(columnwithName2));
                    fullname = fullname.toUpperCase().replaceAll(" ", "");

                    if (fullname.equals(name)) {
                        System.out.println(fullname);
                        price = row.getCell(columnwithPrice).getNumericCellValue();
                        Query query = session.createQuery("update KableEntity set kablePrice = :price where kableType = :name and kableStockCompany = :stock");
                        query.setParameter("price",price);
                        query.setParameter("name", name);
                        query.setParameter("stock",stock);
                        int rezult = query.executeUpdate();

                    }
                } catch (NullPointerException e) {
                    System.out.println("NULL");
                }
                catch (IllegalStateException e){
                    System.out.println("ILLEGAL");
                }
            }
            transaction.commit();
            session.close();
        }







    }
}
