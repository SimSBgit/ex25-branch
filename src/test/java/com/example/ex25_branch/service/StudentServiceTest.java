package com.example.ex25_branch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.ex25_branch.domain.StudentTest;
import com.example.ex25_branch.mapper.StudentMapperTest;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceTest {

	@Autowired
	private StudentMapperTest studentMapperTest;
	
	@Test
	@DisplayName("학생 등록 후 조회 확인(TDD)")
	void testRegisterAndFind() {
//		given
		StudentTest s1 = StudentTest.builder().name("홍길동1").email("hong1@test.com").age(21).build();
		StudentTest s2 = StudentTest.builder().name("홍길동2").email("hong2@test.com").age(22).build();
		
//		when
		int result = studentMapperTest.insert(s1);
//		int result2 = studentMapperTest.insert(s2);
		
//		then
		assertEquals(1, result, "등록은 1건 성공해야 한다.");
		assertNotNull(s1.getId(), "등록 후 id가 자동생성되어야 한다.");
		
		StudentTest findStudent = studentMapperTest.findById(s1.getId());
		assertEquals("홍길동1", findStudent.getName());
		assertEquals("hong1@test.com", findStudent.getEmail());
		assertEquals(21, findStudent.getAge());
		
	}
	
	@Test
	@DisplayName("학생 전체 조회(TDD)")
	void testFindAll() {
//		given
		StudentTest s3 = StudentTest.builder().name("홍길동3").email("hong3@test.com").age(23).build();
		StudentTest s4 = StudentTest.builder().name("홍길동4").email("hong4@test.com").age(24).build();
		studentMapperTest.insert(s3);
		studentMapperTest.insert(s4);
		
//		when
		List<StudentTest> students = studentMapperTest.findAll();
		
//		then
		assertTrue(students.size() >= 2);
		assertEquals("박민수", students.get(6).getName());
		
		
	}
}	
