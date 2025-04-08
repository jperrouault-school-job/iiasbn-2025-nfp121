package fr.formation;

import java.util.concurrent.ExecutorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceProxy extends Service {
    private final Service service;
    private final ExecutorService threadPool;

    @Override
    public void withThread() {
        this.threadPool.submit(this.service::withThread);
    }
    
    @Override
    public void withOtherThread() {
        this.threadPool.submit(this.service::withOtherThread);
    }

    @Override
    public void execTenTimes() {
        this.threadPool.submit(this.service::execTenTimes);
    }
}
