package com.v1.microservicios.service;

import com.v1.microservicios.entities.User;
import com.v1.microservicios.feignclients.CarFeignClient;
import com.v1.microservicios.models.Car;
import com.v1.microservicios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private final CarFeignClient carFeignClient;

    public UserService(CarFeignClient carFeignClient) {
        this.carFeignClient = carFeignClient;
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8090/car/user/" + userId
                , List.class);
        return cars;

    }
    public Car saveCar (int userId, Car car){
         car.setUserId(userId);
         Car newCar = carFeignClient.save(car);
         return newCar;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User not exists"));
    }

    public User saveUser(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }

}
