package app.mrquan.factory;

import app.mrquan.service.impl.AdministratorServiceImpl;
import app.mrquan.service.impl.ClientServiceImpl;

public class ServiceFactory {
    public static ClientServiceImpl getClientServiceInstance(){
        return new ClientServiceImpl();
    }
    public static AdministratorServiceImpl getAdministratorServiceInstance(){
        return new AdministratorServiceImpl();
    }
}
