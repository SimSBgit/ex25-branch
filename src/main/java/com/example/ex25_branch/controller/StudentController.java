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
	
//	전체 학생 리스트 페이지
	@GetMapping
	public String allList(Model model) {
		model.addAttribute("students", studentService.getAllStudent());
		return "/list";
	}
	
//	새로운 학생 등록 리스트 페이지
	@GetMapping("/new")
	public String insertForm(Model model) {
		model.addAttribute("student", new Student());
		return "/form";
	}

//	새로운 학생 등록 처리
	@PostMapping
	public String insertStudent(@ModelAttribute Student student, Model model) {
		studentService.createStudent(student);
		return "redirect:/students";
	}
	
//	수정 페이지
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudent(id));
		return "/form";
	}
	
//	학생 정보 수정 처리
	@PostMapping("/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, Model model) {
		studentService.updateStudent(student);
		return "redirect:/students";
	}
	
//	학생 정보 삭제 처리
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
	
	
}
