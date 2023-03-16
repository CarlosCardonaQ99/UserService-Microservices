package com.v1.microservicios.controllers;

import com.v1.microservicios.entities.User;
import com.v1.microservicios.models.Car;
import com.v1.microservicios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private ResponseEntity getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity("El usuario no existe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity saveUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return new ResponseEntity("user created", HttpStatus.CREATED);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> listarCarros(@PathVariable("userId") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(id);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/car/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId,
                                       @RequestBody Car car) {
        Car newCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(newCar);
    }
}

