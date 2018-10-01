package app.mrquan.service.impl;

import app.mrquan.factory.DAOFactory;
import app.mrquan.pojo.Order;
import app.mrquan.pojo.SportVenue;
import app.mrquan.service.IAdministratorService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdministratorServiceImpl implements IAdministratorService {
    @Override
    public List<Order> findOrdersByReservationStadiumSerialNumber(String stadium) throws SQLException {
        return DAOFactory.getIOrderDAOInstance().findAllOrdersByStadium(stadium);
    }

    @Override
    public List<SportVenue> listSportVenueByReserve(String stadium) throws SQLException {
        return null;
    }

    @Override
    public int turnover(String stadium) throws SQLException {
        return 0;
    }

    @Override
    public Map<String, Integer> ageDistribution() throws SQLException {
        return null;
    }

    @Override
    public List<SportVenue> listSportVenueByTurnover() throws SQLException {
        return null;
    }

    @Override
    public String favoriteSport(boolean manOrWoman) throws SQLException {
        return null;
    }
}
