package com.example.ex25_branch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex25_branch.domain.Student;
import com.example.ex25_branch.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students/valid")
public class StudentValidController {

//	post로 처리하는 등록/수정 코드만 다름
	
	private final StudentService studentService;
	
//	전체 화면
	@GetMapping
	public String list(Model model) {
		model.addAttribute("students", studentService.getAllStudent());
		return "listValid";
	}
	
//	새로운 학생 등록 폼 페이지
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("student", new Student());
		return "formValid";
	}
	
//	새로운 학생 등록 처리 
	@PostMapping
	public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "formValid";
		}
		studentService.createStudent(student);
		return "redirect:/students/valid";
	}
	
//	학생 수정 폼 페이지
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudent(id));
		return "formValid";
	}
	
//	학생 수정 처리
	@PostMapping("/{id}")
	public String updateStudent(@Valid @PathVariable Long id, @ModelAttribute Student student, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "formValid";
		}
		student.setId(id);
		studentService.updateStudent(student);
		return "redirect:/students/valid";
	}
	
//	삭제 처리
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students/valid";
	}
}
