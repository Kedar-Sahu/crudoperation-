package CrudOperation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CrudOperation.entity.Student;
import CrudOperation.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	
	@PostMapping("/post")
	public ResponseEntity<Student> add(@RequestBody Student student){
		Student save =studentRepository.save(student);
		return new ResponseEntity<>(save,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Student>> get(){
		List<Student> std=studentRepository.findAll();
		return ResponseEntity.ok(std);
	}
	
	@PutMapping("/put/{rollno}")
	public ResponseEntity<Student> put(@PathVariable Long rollno,@RequestBody Student student){
		Student student1=studentRepository.findById(rollno).orElseThrow(()-> new RuntimeException("student not found"));
		student1.setName(student.getName());
		student1.setMarks(student.getMarks());
		student1.setCity(student.getCity());
		studentRepository.save(student1);
		return ResponseEntity.ok(student1);
		
	}
	
	@DeleteMapping("/delete/{rollno}")
	public String delete(@PathVariable Long rollno){
		studentRepository.deleteById(rollno);
		return "student is deleted";
	}
}
