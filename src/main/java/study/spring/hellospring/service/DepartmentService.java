package study.spring.hellospring.service;

import java.util.List;

import study.spring.hellospring.model.Department;

/** 학과 관리 기능을 제공하기 위한 Service 계층. */
public interface DepartmentService {
	
	/**
	 * 교수 등록하기
	 * @param department 저장할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	// --> import study.jsp.myschool.model.Department;
	public void addDepartment(Department department) throws Exception;
	
	/**
	 * 교수 수정하기
	 * @param department 수정할 정보를 담고 있는 Beans
	 * @throws Exception
	 */
	public void editDepartment(Department department) throws Exception;
	
	/**
	 * 교수 삭제하기
	 * @param department 삭제할 교수의 일련번호를 담고 있느 Beans
	 * @throws Exception
	 */
	public void deleteDepartment(Department department) throws Exception;
	
	/**
	 * 교수 상세 조회
	 * @param department 조회할 교수의 일련번호를 담고 있는 Beans
	 * @return 조회된 데이터가 저장된 Beans
	 * @throws Exception
	 */
	public Department getDepartmentItem(Department department) throws Exception;
	
	/**
	 * 학과 목록 조회
	 * @return 조회 결과에 대한 컬렉션
	 * @throws Exception
	 */
	// -> import java.util.List;
	public List<Department> getDepartmentList(Department department) throws Exception;
	//public List<Department> getDepartmentList(Department professor) throws Exception;
	
	/**
	 * 학과 목록 조회 (드롭다운)
	 * @return 조회 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<Department> getDepartmentJoinList(Department department) throws Exception;
	
	/**
	 * 전체 목록 수 조회
	 * @return 조회 결과
	 * @throws Exception
	 */
	public int getDepartmentCount(Department department) throws Exception;
}