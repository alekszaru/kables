package procedures;

import Entity.KableEntity;
import hibernate.SessionFactoryImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AditionalProcedure {

    //извлечение текстового значени€ из любой €чейки
    public static String getTextCellValue(Cell cell){
        String result = "";
        switch (cell.getCellType()) {
            case 1:
                result = cell.getRichStringCellValue().getString();
                break;
            case 0:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = cell.getDateCellValue().toString();
                } else {
                    result = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case 4:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            case 2:
                result = String.valueOf(cell.getCellFormula());
                break;
            case 3:
                break;
            default:
                result = "";
        }
        return result;
    }

    //извлечение последней даты обновлени€ файла
    public static String actualityDate(String fileName) {
        File file = new File(fileName);
        Long fileLastModifiated = file.lastModified();
        Date date = new Date(fileLastModifiated);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return (sdf.format(date));
    }

    //загрузка экземпл€ра кабель в Ѕƒ
    public static void addKableToDB(String fullname, Double cuantaty, Double price, String stock, String date, String nameToDB) {
        Session session = SessionFactoryImpl.getSession();
        Transaction transaction = session.beginTransaction();
        KableEntity kable = new KableEntity();
        kable.setKableType(fullname);
        kable.setKableVolume(cuantaty);
        kable.setKablePrice(price);
        kable.setKableStockActuality(date);
        kable.setKableStockCompany(stock);
        kable.setKableFullName(nameToDB);
        session.save(kable);
        transaction.commit();
        session.close();
    }

    public static void truncateBySTOCK(String stock) {
        Session session = SessionFactoryImpl.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query q1 = session.createQuery("delete from KableEntity where kableStockCompany = :stock");
            q1.setParameter("stock",stock);
            q1.executeUpdate();
            transaction.commit();
        }catch (Exception e){}
        finally {
            session.close();
        }
    }

}
