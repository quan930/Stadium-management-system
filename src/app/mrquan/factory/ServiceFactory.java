package app.mrquan.factory;

import app.mrquan.service.IAdministratorService;
import app.mrquan.service.IClientService;
import app.mrquan.service.ILoginService;
import app.mrquan.service.impl.AdministratorServiceImpl;
import app.mrquan.service.impl.ClientServiceImpl;
import app.mrquan.service.impl.ILoginServiceImpl;

public class ServiceFactory {
    public static IClientService getIClientServiceInstance(){
        return new ClientServiceImpl();
    }
    public static IAdministratorService getIAdministratorServiceInstance(){
        return new AdministratorServiceImpl();
    }
    public static ILoginService getILoginServiceInstance(){
        return new ILoginServiceImpl();
    }
}
