package com.example.ex25_branch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ex25_branch.domain.Student;
import com.example.ex25_branch.service.StudentService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentRestController {

	private final StudentService studentService;
	
//	전체 조회
	@GetMapping
	public List<Student> list() {
		return studentService.getAllStudent();
	}
	
//	개별 정보 가져오기
	@GetMapping("/{id}")
	public ResponseEntity<Student> detail(@PathVariable Long id) {
		Student student = studentService.getStudent(id);
		return ResponseEntity.ok(student);
	}
	
//	등록
	@PostMapping
	public ResponseEntity<Student> create(@RequestBody Student student) {
		studentService.createStudent(student);
		return ResponseEntity.ok(student);
	}

//	개별 정보 수정 처리
	@PostMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
		student.setId(id);
		studentService.updateStudent(student);
		return ResponseEntity.ok(student);
	}
	
//	삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> delete(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.ok().build();
	}
}
