package com.example.ex25_branch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.ex25_branch.domain.Student;

@Mapper
public interface StudentMapper {

	List<Student> findAll();

	Student findById(Long id);

	void insert(Student student);

	void update(Student student);

	void delete(Long id);

}
