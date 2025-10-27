package com.example.ex25_branch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex25_branch.domain.Student;
import com.example.ex25_branch.service.StudentService;

import lombok.RequiredArgsConstructor;






@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
//	전체 조회 페이지
	@GetMapping
	public String list(Model model) {
		model.addAttribute("students", studentService.getAllStudent());
		return "list";
	}
	
//	등록 페이지
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("student", new Student());
		return "form";
	}
	
//	등록 처리
	@PostMapping
	public String create(@ModelAttribute Student student) {
		studentService.createStudent(student);
		return "redirect:/students";
	}
	
//	수정 페이지
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudent(id));
//		Student student = studentService.getStudent(id);
//		model.addAttribute("student", student);
		return "form";
	}
	
//	수정 처리
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, @ModelAttribute Student student) {
		student.setId(id);
		studentService.updateStudent(student);
		return "redirect:/students";
	}
	
//	삭제 처리
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
	
}
