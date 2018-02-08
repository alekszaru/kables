package main;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class FindMatches {
       private static String dtsFileName = "d://stock/DTS.xls";
        private static String eaFileName = "d://stock/EA.xls";
        private static String eaPriceFileName = "d://stock/EAprise.xls";
        private static String mkPriceFileName = "";
        private static String mkFileName = "d://stock/MK.xls";
        private static String bkzPriceFileName = "";
        private static String kpkzPriceFileName = "d://stock/KPKZ.xls";


    private double eaAluminiumDiscount = 1.03;
    private double eaCuprumDiscount = 1.03;




    public static void main(String[] args) throws IOException, SQLException {


        Connection connection = createConnection();

        //загрузка файла дтс
        try {
            loadDTSfile(connection);
        }catch (NullPointerException e){

        } catch (SQLException e) {

        }

        //загрузка файла Энергоальянс
        try {
            loadEAfile(connection);
        }catch (NullPointerException e){

        } catch (SQLException e) {

        }

        //загрузка файла МастерКабель
        try {
            loadMKfile(connection);
        }catch (NullPointerException e){

        } catch (SQLException e) {

        }

        connection.close();

    }


       public ArrayList<String> findMatchesInAvailableFiles(String request) throws IOException {

           ArrayList<String> rezult = new ArrayList<String>();

            try {
                findInDB(request, rezult);
            } catch (SQLException e) {

            }


//            try{
//            findMatchesDTS(request, rezult);}catch (FileNotFoundException e){
//                System.out.println("File DTS notFound");
//                rezult.add("File DTS notFound");
//            }
//
//            try{
//            findMatchesEA(request, rezult);}catch (FileNotFoundException e){
//                System.out.println("File EA notFound");
//                rezult.add("File EA notFound");
//            }
//
//            try{
//            findMatchesMK(request, rezult);}catch (FileNotFoundException e){
//                System.out.println("File MK notFound");
//                rezult.add("File MK prise notFound");
//            }

//            try{
//            findMatchesInPriceEA(request, rezult);}catch (FileNotFoundException e){
//                System.out.println("File EAprise notFound");
//                rezult.add("File EAprise notFound");
//            }
////            try{
//            findMatchesKPKZ(request, rezult);}catch (FileNotFoundException e){
//                System.out.println("File KPKZ notFound");
//                rezult.add("File KPKZ notFound");
//            }
           return rezult;
        }


//
//    private void findMatchesDTS(String request, ArrayList<String> rezult) throws IOException {// поиск в файле DTS
//        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(dtsFileName));
//        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
//        HSSFRow row;
//        for (int i = 1; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
//            try {
//                row = myExcelSheet.getRow(i);
//                if(row.getCell(1).getCellType()==1) {
//                    String name = row.getCell(1).getStringCellValue().toUpperCase();
//                    Double cuantaty = null;
//                    Double price = null;
//                    String answer = "";
//
//                    if ((name.contains("ПРОВІД ") || name.contains("КАБЕЛЬ ")) && (row.getCell(3).getNumericCellValue()) > 0) {
//                        name = name.replaceAll("КАБЕЛЬ ", "");
//                        name = name.replaceAll("ПРОВІД ", "");
//
//                        if (request.contains(" ")) {
//                            String request1 = request.substring(0, request.lastIndexOf(" ")).toUpperCase();
//                            String request2 = request.substring(request.lastIndexOf(" ") + 1).toUpperCase();
//
//                            if (name.startsWith(request1) && name.contains(request2)) {
//                                cuantaty = row.getCell(3).getNumericCellValue() * 1000;
//                                price = row.getCell(4).getNumericCellValue() / 1000;
//                                answer = name + " - " + String.format("%.0f", cuantaty) + " м   по  " + String.format("%.2f", price) + " грн";
//                                rezult.add(answer + actualityDate(dtsFileName));
//                            }
//
//                        } else {
//                            if (name.contains(request)) {
//                                cuantaty = row.getCell(3).getNumericCellValue() * 1000;
//                                price = row.getCell(4).getNumericCellValue() / 1000;
//                                answer = name + " - " + String.format("%.0f", cuantaty) + " м   по  " + String.format("%.2f", price) + " грн";
//                                rezult.add(answer + actualityDate(dtsFileName));
//                            }
//                        }
//                    }
//                }
//            }catch (IllegalStateException e) {
//                e.printStackTrace();
//            }catch (NullPointerException e2) {
//                e2.printStackTrace();
//            }
//        } myExcelBook.close();
//    } // поиск в файле DTS
//
//    private void findMatchesEA(String request, ArrayList<String> rezult) throws IOException {// поиск в файле Энергоальянс
//        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(eaFileName));
//        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
//        HSSFRow row;
//        for (int i = 1; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
//            try {
//                row = myExcelSheet.getRow(i);
//                String name = row.getCell(1).getStringCellValue().toUpperCase();
//                Double cuantaty = null;
//                String answer = "";
//
//                if ((name.contains("ПРОВІД ") || name.contains("КАБЕЛЬ "))&& (row.getCell(7).getNumericCellValue()) > 0) {
//                    name = name.replaceAll("КАБЕЛЬ ", "");
//                    name = name.replaceAll("ПРОВІД ", "");
//
//                    if (request.contains(" ")) {
//                        String request1 = request.substring(0, request.lastIndexOf(" ")).toUpperCase();
//                        String request2 = request.substring(request.lastIndexOf(" ") + 1).toUpperCase();
//
//                            if (name.startsWith(request1)&& name.contains(request2)) {
//                                cuantaty = row.getCell(7).getNumericCellValue();
//                                answer = name + " - " + String.format("%.0f", cuantaty) + " м ";
//                                rezult.add(answer + actualityDate(eaFileName));
//                            }
//
//                    } else {
//                            if (name.contains(request)) {
//                                cuantaty = row.getCell(7).getNumericCellValue();
//                                answer = name + " - " + String.format("%.0f", cuantaty) + " м ";
//                                rezult.add(answer + actualityDate(eaFileName));
//                            }
//                    }
//                }
//            }catch (IllegalStateException e) {
//                e.printStackTrace();
//            }catch (NullPointerException e2) {
//                e2.printStackTrace();
//            }
//        } myExcelBook.close();
//    }// поиск в файле Энергоальянс
//
//    private void findMatchesKPKZ(String request, ArrayList<String> rezult) throws IOException {//поиск в прайсе Кабельный завод
//        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(kpkzPriceFileName));
//        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
//        HSSFRow row;
//
//        for (int i = 0; i < myExcelSheet.getPhysicalNumberOfRows() ; i++) {
//            row = myExcelSheet.getRow(i);
//            String fullName = "";
//
//            try {
//                for(int j=0; j<10;j++){
//                fullName+=getTextCellValue(row.getCell(j))+" ";
//                fullName=fullName.replace(".0 ","");
//                fullName=fullName.replace("х ","х");
//                fullName=fullName.replace("ХЛ ","");
//                    fullName=fullName.replace("1000","1кВ  ");
//                    fullName=fullName.replace("660","0,66кВ  ");
//                    fullName=fullName.replace("380/660","");
//                    fullName=fullName.replace("кму","");
//                }
//
//            } catch (IllegalStateException e){
//                System.out.println("incorrect celltype");
//            }
//            catch (NullPointerException e5){
//                System.out.println("null refference in cell");
//            }
//            //System.out.println(fullName);
//
//            if (request.contains(" ")) {
//                String request1 = request.substring(0, request.lastIndexOf(" "));
//                String request2 = request.substring(request.lastIndexOf(" ") + 1);
//                if (fullName.toUpperCase().contains(request1.toUpperCase()) && fullName.toUpperCase().contains(request2.toUpperCase())) {
//                    Double price = row.getCell(15).getNumericCellValue() / 1000;
//                    fullName += " - " + String.format("%.2f",price) + " грн/м"+ "  ПРАЙС КАМЕНЕЦ "+actualityDate(kpkzPriceFileName);
//                   // System.out.println(fullName);
//                    rezult.add(fullName);
//                }
//            }
//
//            else{
//                if(fullName.toUpperCase().contains(request.toUpperCase())){
//                    Double price = row.getCell(15).getNumericCellValue() / 1000;
//                    fullName += " - " + String.format("%.2f",price) + " грн/м"+ "  ПРАЙС КАМЕНЕЦ "+actualityDate(kpkzPriceFileName);
//                    //System.out.println(fullName);
//                    rezult.add(fullName);
//                }
//            }
//
//        }
//    } //поиск в прайсе Кабельный Завод
//
//    private void findMatchesMK(String request, ArrayList<String> rezult) throws IOException {// поиск в файле МастерКабель
//        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(mkFileName));
//        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
//        HSSFRow row;
//        for (int i = 1; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
//            try {
//                row = myExcelSheet.getRow(i);
//                if(row.getCell(1).getCellType()==1) {
//                    String name = row.getCell(1).getStringCellValue().toUpperCase();
//                    Double cuantaty = null;
//                    String answer = "";
//
//                    if ((row.getCell(5).getCellType()==0)&&(row.getCell(5).getNumericCellValue()) > 0) {
//
//                        if (request.contains(" ")) {
//                            String request1 = request.substring(0, request.lastIndexOf(" ")).toUpperCase();
//                            String request2 = request.substring(request.lastIndexOf(" ") + 1).toUpperCase();
//
//                            if (name.startsWith(request1) && name.contains(request2)) {
//                                cuantaty = row.getCell(5).getNumericCellValue();
//                                answer = name + " - " + String.format("%.0f", cuantaty) + " м ";
//                                rezult.add(answer + actualityDate(mkFileName));
//                            }
//
//                        } else {
//                            if (name.contains(request)) {
//                                cuantaty = row.getCell(5).getNumericCellValue();
//                                answer = name + " - " + String.format("%.0f", cuantaty) + " м ";
//                                rezult.add(answer + actualityDate(mkFileName));
//                            }
//                        }
//                    }
//                }
//            }catch (IllegalStateException e) {
//                e.printStackTrace();
//            }catch (NullPointerException e2) {
//                e2.printStackTrace();
//            }
//        } myExcelBook.close();
//    } // поиск в файле МастерКабель
//
//    private void findMatchesInPriceEA(String request, ArrayList<String> rezult) throws IOException {//поиск в прайсе Энергоальянс
//        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(eaPriceFileName));
//        int numsOfSheets = myExcelBook.getNumberOfSheets();
//        if(request.indexOf(" ")==0){request=request.substring(1);}
////        System.out.println("----------------------------------------------------------");
////        System.out.println("ПРАЙС ЭНЕРГОАЛЬЯНС" );
//
//        for(int s=0; s<numsOfSheets; s++){
//
//            HSSFSheet myExcelSheet = myExcelBook.getSheetAt(s);
//            HSSFRow row;
//            if (request.contains(" ")) {
//                String request1 = request.substring(0, request.lastIndexOf(" "));
//                String request2 = request.substring(request.lastIndexOf(" ") + 1);
//
//                for (int j = 0; j < 5; j++) {
//                    for (int i = 1; i < myExcelSheet.getPhysicalNumberOfRows() ; i++) {
//                        row = myExcelSheet.getRow(i);
//                        try {
//                            String name = row.getCell(1 + 4 * j).getStringCellValue();
//                            String name2 = row.getCell(2 + 4 * j).getStringCellValue();
//                            if (name.toUpperCase().startsWith(request1.toUpperCase()) && name2.toUpperCase().contains(request2.toUpperCase())) {
//                                Double price = row.getCell(3 + 4 * j).getNumericCellValue();
//                                String answer ="";
//                                if(name.toUpperCase().startsWith("А")||name.toUpperCase().startsWith("СИП")){
//                                    answer = name + " " + name2+ "  -  " + String.format("%.2f", price*eaAluminiumDiscount ) + " грн/м";}
//                                else {answer = name + " " + name2+ "  -  " + String.format("%.2f", price*eaCuprumDiscount ) + " грн/м";}
//                                answer = answer.replaceAll("Кабель ", "");
//                                answer = answer.replaceAll("Провід ","");
//                                //System.out.println(answer);
//                                rezult.add(answer+ " ПРАЙС ЭНЕРГОАЛЬЯНС "+actualityDate(eaPriceFileName));
//                            }
//                        } catch (IllegalStateException e) { e.printStackTrace(); }
//                        catch (NullPointerException e1){ e1.printStackTrace(); }
//                    }
//                }
//            }
//
//            else{
//
//                for (int j = 0; j < 2; j++) {
//                    for (int i = 1; i < myExcelSheet.getPhysicalNumberOfRows() ; i++) {
//                        row = myExcelSheet.getRow(i);
//                        try {
//                            String name =row.getCell(1 + 4 * j).getStringCellValue();
//                            String name2 =row.getCell(2 + 4 * j).getStringCellValue();
//                            if (name.toUpperCase().startsWith(request.toUpperCase()) ) {
//                                Double price = row.getCell(3 + 4 * j).getNumericCellValue();
//
//                                String answer ="";
//                                if(name.toUpperCase().startsWith("А")||name.toUpperCase().startsWith("СИП")){
//                                    answer = name + " - "+ " " + name2+ "  " + String.format("%.2f", price*eaAluminiumDiscount ) + " грн/м";}
//                                else {answer = name + " - "+ " " + name2+ "  " + String.format("%.2f", price*eaCuprumDiscount ) + " грн/м";}
//                                //System.out.println(answer);
//                                answer = answer.replaceAll("Кабель ", "");
//                                answer = answer.replaceAll("Провід ","");
//                                rezult.add(answer+ " ПРАЙС ЭНЕРГОАЛЬЯНС "+actualityDate(eaPriceFileName));
//                            }
//                        } catch (IllegalStateException e) {e.printStackTrace();
//                        }
//                        catch (NullPointerException e1){e1.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    } //поиск в прайсе Энергоальянс
//

//    public void findinDB(String request) throws SQLException {
//        String[] listOfWords = request.split(" ");
//
//        for(String str: listOfWords){
//            System.out.println(str);}
//            Connection connection = createConnection();
//    PreparedStatement statement = connection.prepareStatement("SELECT KABLE_TYPE,KABLE_VOLUME,KABLE_PRICE,KABLE_STOCK_COMPANY,KABLE_STOCK_ACTUALITY FROM kable WHERE KABLE_TYPE LIKE (?,?,?,?,?)");
//    switch (listOfWords.length){
//        case (0):{
//            statement.setString(1,"");
//            statement.setString(2,"");
//            statement.setString(3,"");
//            statement.setString(4,"");
//            statement.setString(5,"");
//        }
//        case (1):{
//            statement.setString(1,"%"+listOfWords[0]+"%");
//            statement.setString(2,"");
//            statement.setString(3,"");
//            statement.setString(4,"");
//            statement.setString(5,"");
//
//        }
//        case (2):{
//            statement.setString(1,"%"+listOfWords[0]+"%");
//            statement.setString(2,"%"+listOfWords[1]+"%");
//            statement.setString(3,"");
//            statement.setString(4,"");
//            statement.setString(5,"");
//        }
//        case (3):{
//            statement.setString(1,"%"+listOfWords[0]+"%");
//            statement.setString(2,"%"+listOfWords[1]+"%");
//            statement.setString(3,"%"+listOfWords[2]+"%");
//            statement.setString(4,"");
//            statement.setString(5,"");
//        }
//        case (4):{
//            statement.setString(1,"%"+listOfWords[0]+"%");
//            statement.setString(2,"%"+listOfWords[1]+"%");
//            statement.setString(3,"%"+listOfWords[2]+"%");
//            statement.setString(4,"%"+listOfWords[3]+"%");
//            statement.setString(5,"");
//        }
//        case (5):{
//            statement.setString(1,"%"+listOfWords[0]+"%");
//            statement.setString(2,"%"+listOfWords[1]+"%");
//            statement.setString(3,"%"+listOfWords[2]+"%");
//            statement.setString(4,"%"+listOfWords[3]+"%");
//            statement.setString(5,"%"+listOfWords[4]+"%");
//        }
//    }
//    ResultSet resultSet = statement.executeQuery();
//    while (resultSet.next()){
//        String name = resultSet.getString("KABLE_TYPE");
//        System.out.println(name);
//        Double volume = resultSet.getDouble("KABLE_VOLUME");
//        Double price = resultSet.getDouble("KABLE_PRICE");
//        String stock = resultSet.getString("KABLE_STOCK_COMPANY");
//        String date = resultSet.getString("KABLE_STOCK_ACTUALITY");
//        String finalstr = name+" "+volume.toString()+" "+price.toString()+" "+stock+" "+date;
//        System.out.println(finalstr);
//        rezult.add(finalstr);
//    }
//}

//загрузка файла Энергоальянс в базу данных
    public static void loadEAfile(Connection connection) throws IOException, SQLException { //поиск в файле энергоальянс
        PreparedStatement statementDelete = connection.prepareStatement("DELETE FROM kables.kable WHERE KABLE_STOCK_COMPANY = 'Энергоальянс (Запорожье)'");
        statementDelete.execute();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO kable (KABLE_TYPE,KABLE_VOLUME,KABLE_STOCK_COMPANY,KABLE_STOCK_ACTUALITY) VALUES (?,?,?,?)");
        String date = actualityDate(eaFileName);
        String stock = "Энергоальянс (Запорожье)";
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(eaFileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String name = "";
        for (int i = 11; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
            row = myExcelSheet.getRow(i);
            try {
                String fullname =row.getCell(1).getStringCellValue();
                if((fullname.contains("Провід "))||(fullname.contains("Кабель "))){
                    fullname = fullname.replaceAll("Провід ", "");
                    fullname = fullname.replaceAll("Кабель ", "");
                    System.out.println(fullname);
                    Double cuantaty =row.getCell(7).getNumericCellValue();
                    statement.setString(1,fullname);
                    statement.setDouble(2,cuantaty);
                    statement.setString(3,stock);
                    statement.setString(4,date);
                    statement.executeUpdate();
                }
            }catch (NullPointerException e5) {}
            catch (IllegalStateException e){}
        }
        myExcelBook.close();
    } //загрузка из файла остатков энергоальянс

    //загрузка файла ДТС в базу данных
    public static void loadDTSfile(Connection connection) throws IOException, SQLException { //поиск в файле дтс
        PreparedStatement statementDelete = connection.prepareStatement("DELETE FROM kables.kable WHERE KABLE_STOCK_COMPANY = 'ДТС (Киев)'");
        statementDelete.execute();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO kable (KABLE_TYPE,KABLE_VOLUME,KABLE_PRICE,KABLE_STOCK_COMPANY,KABLE_STOCK_ACTUALITY) VALUES (?,?,?,?,?)");
        String date = actualityDate(dtsFileName);
        String stock = "ДТС (Киев)";
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(dtsFileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String name = "";
            for (int i = 1; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
                row = myExcelSheet.getRow(i);
                String fullname = getTextCellValue(row.getCell(2));

                try {
                    if ((fullname.contains("Провід ")) || (fullname.contains("Кабель "))) {
                        fullname = fullname.replaceAll("Провід ", "");
                        fullname = fullname.replaceAll("Кабель ", "");
                        System.out.println(fullname);
                        Double cuantaty = (row.getCell(3).getNumericCellValue() * 1000);

                        Double price = row.getCell(4).getNumericCellValue() / 1000;
                        statement.setString(1, fullname);
                        statement.setDouble(2, cuantaty);
                        statement.setDouble(3, price);
                        statement.setString(4, stock);
                        statement.setString(5, date);
                        statement.executeUpdate();
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (NullPointerException e5) {
                    e5.printStackTrace();
                }

                myExcelBook.close();
            }
    } //загрузка из файла статков и цен дтс

    //загрузка файла МастерКабель в базу данных
    public static void loadMKfile(Connection connection) throws IOException, SQLException { //поиск в файле энергоальянс
        PreparedStatement statementDelete = connection.prepareStatement("DELETE FROM kables.kable WHERE KABLE_STOCK_COMPANY = 'МастерКабель (Прилуки)'");
        statementDelete.execute();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO kable (KABLE_TYPE,KABLE_VOLUME,KABLE_STOCK_COMPANY,KABLE_STOCK_ACTUALITY) VALUES (?,?,?,?)");
        String date = actualityDate(mkFileName);
        String stock = "МастерКабель (Прилуки)";
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(mkFileName));
        HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        HSSFRow row;
        String name = "";
        for (int i = 11; i <= myExcelSheet.getPhysicalNumberOfRows() - 1; i++) {
            row = myExcelSheet.getRow(i);
            try {
                if(row.getCell(1).getCellType()==1){
                    String fullname =row.getCell(1).getStringCellValue();
                    System.out.println(fullname);
                    Double cuantaty =row.getCell(5).getNumericCellValue();
                    statement.setString(1,fullname);
                    statement.setDouble(2,cuantaty);
                    statement.setString(3,stock);
                    statement.setString(4,date);
                    statement.executeUpdate();
                }
            }catch (NullPointerException e5) {}
            catch (IllegalStateException e){}
        }
        myExcelBook.close();
    } //загрузка из файла остатков МастерКабель


//Поиск в базе данных
    public static void findInDB(String request, ArrayList<String> rezult) throws SQLException {
           PreparedStatement statement = null;
           String url1parametr = "SELECT * FROM kable WHERE KABLE_TYPE LIKE ?";
           String url2parametr = "SELECT * FROM kable WHERE KABLE_TYPE LIKE ? AND KABLE_TYPE LIKE ?";
           String url3parametr = "SELECT * FROM kable WHERE KABLE_TYPE LIKE ? AND KABLE_TYPE LIKE ? AND KABLE_TYPE LIKE ?";
           String string1 = "";
            String string2 = "";
            String string3 = "";
            String answer = "";
        ResultSet resultSet =null;
           if(!request.contains(" ")){
               string1="%"+request+"%";
               statement=createConnection().prepareStatement(url1parametr);
               statement.setString(1,string1);
               resultSet = statement.executeQuery();
               while (resultSet.next()) {
                   System.out.println(answer = resultSet.getString("KABLE_TYPE") + "\t" + resultSet.getInt("KABLE_VOLUME")+"м" +"\t"+resultSet.getDouble("KABLE_PRICE")+" грн/м"+"\t" +resultSet.getString("KABLE_STOCK_COMPANY")+ "\t" +resultSet.getString("KABLE_STOCK_ACTUALITY"));
                   rezult.add(answer);
               }
           }
           else{
               String[] strings = request.split(" ");
               if(strings.length==2){
                   string1 = "%"+strings[0]+"%";
                   string2 = "%"+strings[1]+"%";
                   statement=createConnection().prepareStatement(url2parametr);
                   statement.setString(1,string1);
                   statement.setString(2,string2);
                   resultSet = statement.executeQuery();
                   while (resultSet.next()) {
                       System.out.println(answer =resultSet.getString("KABLE_TYPE") + "\t" + resultSet.getInt("KABLE_VOLUME")+"м" +"\t"+resultSet.getDouble("KABLE_PRICE")+" грн/м"+"\t" +resultSet.getString("KABLE_STOCK_COMPANY")+ "\t" +resultSet.getString("KABLE_STOCK_ACTUALITY"));
                       rezult.add(answer);
                   }
               }
               else {
                   string1 = "%"+strings[0]+"%";
                   string2 = "%"+strings[1]+"%";
                   string3 = "%"+strings[2]+"%";
                   statement=createConnection().prepareStatement(url3parametr);
                   statement.setString(1,string1);
                   statement.setString(2,string2);
                   statement.setString(3,string3);
                   resultSet = statement.executeQuery();
                   while (resultSet.next()) {
                       System.out.println(answer =resultSet.getString("KABLE_TYPE") + "\t" + resultSet.getInt("KABLE_VOLUME")+"м" +"\t"+resultSet.getDouble("KABLE_PRICE")+" грн/м"+"\t" +resultSet.getString("KABLE_STOCK_COMPANY")+ "\t" +resultSet.getString("KABLE_STOCK_ACTUALITY"));
                       rezult.add(answer);
                   }
               }
           }

    }


//создание конекшена с базой данных
    public static Connection createConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("DatabaseDriver is OK!");
        } catch (Exception ex) {
            System.out.println("DataBaseDriver connection FAILED!");
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kables?user=root&password=322022&useSSL=false");
            System.out.println("Connection is OK!");
        } catch (Exception ex) {
            System.out.println("DATABASE Connection FAILED!");
        }
        return connection;
    }

//извлечение текстового значения из любой ячейки
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

    //извлечение последней даты обновления файла
    public static String actualityDate(String fileName) {
        File file = new File(fileName);
        Long dtsFileLastModifiated = file.lastModified();
        Date date = new Date(dtsFileLastModifiated);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return (" актуально на " + sdf.format(date));
    }
}
