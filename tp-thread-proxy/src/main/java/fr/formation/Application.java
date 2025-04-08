package fr.formation;

import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        Service service = new ServiceProxy(new Service(), Executors.newFixedThreadPool(3));

        service.noThread();
        service.withThread();
        service.withOtherThread();
        
        service.execTenTimes();
    }
}