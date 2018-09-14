package app.mrquan.text;

import app.mrquan.factory.ServiceFactory;
import app.mrquan.pojo.SportVenue;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        List<SportVenue> pojos = ServiceFactory.getClientServiceInstance().listSportsByRent();
        List<SportVenue> pojos =  ServiceFactory.getClientServiceInstance().findSportsByReserve(true);
        for (int i = 0; i < pojos.size(); i++) {
            System.out.println(pojos.get(i));
        }
    }
}
