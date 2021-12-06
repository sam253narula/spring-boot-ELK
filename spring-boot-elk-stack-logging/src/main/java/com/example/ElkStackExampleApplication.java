package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class ElkStackExampleApplication {

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) {
		List<User> users=getUsers();
		User user=users.stream().
				filter(u->u.getId()==id).findAny().orElse(null);
		if(user!=null){
			log.info("user found : {}",user);
			return user;
		}else{
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("User Not Found with ID : {}",id);
			}
			return new User();
		}
    }


    private List<User> getUsers() {
        return Stream.of(new User(1, "Samarth"),
				new User(2, "Harshit"),
				new User(3, "Ankit"),
				new User(4, "Kshitij"))
				.collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(ElkStackExampleApplication.class, args);
    }

}
