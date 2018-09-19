package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Personnel;
import app.mrquan.service.ILoginService;

import java.sql.SQLException;

public class ILoginServiceImpl implements ILoginService {

    @Override
    public Personnel login(String id) throws SQLException {
        Personnel pojo = null;
        pojo = DAOFactory.getIPersonnelDAOInstance().findPersonnelById(id);
        return pojo;
    }
}
