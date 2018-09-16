package app.mrquan.factory;

import app.mrquan.dao.IOrderDAO;
import app.mrquan.dao.IPersonnelDAO;
import app.mrquan.dao.ISportVenueDAO;
import app.mrquan.dao.impl.OrderDAOImpl;
import app.mrquan.dao.impl.PersonnelDAOImpl;
import app.mrquan.dao.impl.SportVenueDAOImpl;

public class DAOFactory {
    //IStudentDAO getIStudentDAOInstance
    public static IPersonnelDAO getIPersonnelDAOInstance(){
        return new PersonnelDAOImpl();
    }

    public static ISportVenueDAO getISportVenueDAOInstance(){
        return new SportVenueDAOImpl();
    }

    public static IOrderDAO getIOrderDAOInstance(){
        return new OrderDAOImpl();
    }
}
