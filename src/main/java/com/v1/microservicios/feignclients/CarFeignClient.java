package com.v1.microservicios.feignclients;

import com.v1.microservicios.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "carro-service", url = "http://localhost:8090")
@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping
     Car save(@RequestBody Car car);

}
