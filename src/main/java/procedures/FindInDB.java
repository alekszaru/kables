package procedures;

import Entity.KableEntity;
import hibernate.SessionFactoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FindInDB {

    private List<KableEntity> rezultListOfEntities = null;
    private String[] requests = null;

    public List<KableEntity> getRezultListOfEntities() {
        return rezultListOfEntities;
    }

    public ArrayList<String> findByRequest(String request){

        String name1="";
        String name2="";
        String name3="";

        ArrayList<String> rezults = new ArrayList<String>();
        requests = request.toUpperCase().split(" ");
        if(requests.length==0){
            rezults.add("Вы ничего не ввели!");
        }
        else if(requests.length==1){
            name1 = requests[0];
        }
        else if(requests.length==2){
            name1 = requests[0];
            name2 = requests[1];
        }
        else {
            name1 = requests[0];
            name2 = requests[1];
            name3 = requests[2];
        }

        Session session = SessionFactoryImpl.getSession();;
        Transaction transaction =  session.beginTransaction();
        Query query = session.createQuery("from KableEntity where kableType like :name1 and kableType like :name2 and kableType like :name3");
        query.setParameter("name1",name1+"%");
        query.setParameter("name2","%"+name2+"%");
        query.setParameter("name3","%"+name3+"%");
        rezultListOfEntities = query.list();
        transaction.commit();
        session.close();
        for(KableEntity kable : rezultListOfEntities){
            StringBuilder builder = new StringBuilder(kable.getKableType());
                if(kable.getKableVolume()>0){builder.append(" есть ").append(kable.getKableVolume()).append("м по   ");}
                else {builder.append(" только цена ");
                    if(kable.getKablePrice()!=0){builder.append(kable.getKablePrice()).append(" грн/м   ");}
                    else continue;}
                builder.append(kable.getKableStockCompany()).append(" ");
                builder.append(kable.getKableStockActuality());
                rezults.add(builder.toString());
        }


        return rezults;
    }

    public List<KableEntity> findByLIST(String request){

        String name1="";
        String name2="";
        String name3="";

        ArrayList<String> rezults = new ArrayList<String>();
        requests = request.toUpperCase().split(" ");
        if(requests.length==0){
            rezults.add("Вы ничего не ввели!");
        }
        else if(requests.length==1){
            name1 = requests[0];
        }
        else if(requests.length==2){
            name1 = requests[0];
            name2 = requests[1];
        }
        else {
            name1 = requests[0];
            name2 = requests[1];
            name3 = requests[2];
        }

        Session session = SessionFactoryImpl.getSession();;
        Transaction transaction =  session.beginTransaction();
        Query query = session.createQuery("from KableEntity where kableType like :name1 and kableType like :name2 and kableType like :name3 and kableVolume>0");
        query.setParameter("name1",name1+"%");
        query.setParameter("name2","%"+name2+"%");
        query.setParameter("name3","%"+name3+"%");
        rezultListOfEntities = query.list();
        transaction.commit();
        session.close();

        return rezultListOfEntities;
    }
}
