package com.example.ex25_branch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ex25_branch.domain.Student;
import com.example.ex25_branch.mapper.StudentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

	private final StudentMapper studentMapper;

	public List<Student> getAllStudent() {
		return studentMapper.findAll();
	}

	public Student getStudent(Long id) {
		return studentMapper.findById(id);
	}

	public void createStudent(Student student) {
		studentMapper.insert(student);
	}

	public void updateStudent(Student student) {
		studentMapper.update(student);
	}

	public void deleteStudent(Long id) {
		studentMapper.delete(id);
	}

}
