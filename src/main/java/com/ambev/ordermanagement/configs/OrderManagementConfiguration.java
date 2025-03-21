package com.ambev.ordermanagement.configs;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class OrderManagementConfiguration {

    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServiceInstanceListSuppler("order-management");
    }

}

class DemoServiceInstanceListSuppler implements ServiceInstanceListSupplier {

    private final String serviceId;

    DemoServiceInstanceListSuppler(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 9501, false),
                        new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 9502, false),
                        new DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 9503, false)));
    }
}
