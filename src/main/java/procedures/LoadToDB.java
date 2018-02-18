package procedures;

import hibernate.SessionFactoryImpl;

import java.io.IOException;
import java.sql.*;
import java.util.Date;


import static procedures.LoadDTSfileToDB.loadDTSfile;
import static procedures.LoadEAfileToDB.loadEAfile;
import static procedures.LoadEApriceToDB.loadPriceEA;
import static procedures.LoadMKfileToDB.loadMKfile;

public class LoadToDB {

    public static void main(String[] args) throws IOException, SQLException {
        Date startDate = new Date();
        //загрузка файла дтс
        try {
            loadDTSfile();
        }catch (NullPointerException e){
        } catch (SQLException e) {
        }

        //загрузка файла Ёнергоаль€нс
        try {
            loadEAfile();
        }catch (NullPointerException e){
        } catch (SQLException e) {
        }

        //загрузка файла ћастер абель
        try {
            loadMKfile();
        }catch (NullPointerException e){
        } catch (SQLException e) {
        }

        //загрузка файла Ёнергоаль€нс price. должен идти только после загрузки файла с остатками Ёнергоаль€нс
        try {
            loadPriceEA();
        }catch (NullPointerException e){e.printStackTrace();}
        catch (Exception e) { e.printStackTrace();}



        SessionFactoryImpl.closeSessionFactory();
        System.out.println("Connection closed");

        Date endDate = new Date();


        System.out.println((endDate.getTime()-startDate.getTime())/60000+" минут затрачено");

    }



}
