package com.masai.App;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@org.springframework.stereotype.Controller
@RestController
public class Controller {
	
	 @RequestMapping("/hello")
	 @ResponseBody
	 public String Hello() {
		return "Welcome to Spring Boot";
	}
	 
	 @RequestMapping("/hii")
     public String sayHello2() {		
		return "Welcome to Spring boot part 2";
			
	}
	 
	 @RequestMapping("/student")
		public Student getStudentHandler() {
			
			Student student= new Student(31, "Sakshi", "Prayagraj", 98);
			return student;
	}
	 
//	 @RequestMapping("/students")
//	 @RequestMapping(value="/students",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 @GetMapping("/students")
	 public List<Student> getStudenthandler(){
		 List<Student> students= new ArrayList<>();
		 
		 students.add(new Student(31,"Neha","prayag",80));
		 students.add(new Student(10,"Gauri","Lucknow",99));
		 students.add(new Student(22,"Seeku","Delhi",10));
		 students.add(new Student(12,"Sibbu","ballia",90));
		 students.add(new Student(13,"Divya","bhopal",85));
		 students.add(new Student(15,"Jyoti","indore",92));
		 
		 return students;
	 }
	 
	 @GetMapping("/stud/{roll}/{address}")
	 
	 public Student getStudentHandler(@PathVariable("roll") int roll, @PathVariable("address") String address) {
		 
		 Student student= new Student(roll, "Sakshi", address, 98);
		 return student;
	 }
	 
	 @PostMapping("/students")
		public String registerStudent(@RequestBody Student student) {
			
			
			//here we can connect with the DB and save the student object to the DB
			
			return "Student registered..."+student;
			
		}

}
