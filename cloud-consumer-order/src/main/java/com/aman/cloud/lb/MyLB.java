package com.aman.cloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        ServiceInstance serviceInstance = serviceInstances.get(index);
        log.info("     调用端口服务【{}】", serviceInstance.getPort());
        return serviceInstance;
    }

    private final Integer getAndIncrement() {
        int current = atomicInteger.get();
        int next;
        next = (current >= 2147483647) ? 0 : current + 1;
        while (!this.atomicInteger.compareAndSet(current, next)) {

        }
        log.info("*****第【{}】次进入", next);
        return next;

    }

}
