package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.Student;

public interface StudentService {
	/**
	 * 학생 등록하기
	 * @param student 저장할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	// --> import study.spring.hellospring.model.Student;
	public void addStudent(Student student) throws Exception;
	
	/**
	 * 학생 수정하기
	 * @param student 수정할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	public void editStudent(Student student) throws Exception;
	
	/**
	 * 학생 삭제하기
	 * @param student 삭제할 학생의 일련번호를 담고 있느 Beans
	 * @throws Exception
	 */
	public void deleteStudent(Student student) throws Exception;
	
	/**
	 * 학생 상세 조회
	 * @param student 조회할 학생의 일련번호를 담고 있는 Beans
	 * @return 조회된 데이터가 저장된 Beans
	 * @throws Exception
	 */
	public Student getStudentItem(Student student) throws Exception;
	
	/**
	 * 학생 목록 조회
	 * @return 조회 결과에 대한 컬렉션
	 * @throws Exception
	 */
	// -> import java.util.List;
	public List<Student> getStudentList(Student student) throws Exception;
}
