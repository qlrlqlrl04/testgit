package study.spring.hellospring;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import study.spring.hellospring.model.Department;
import study.spring.hellospring.service.DepartmentService;
import study.spring.helper.WebHelper;

@Controller
public class DepartmentApi {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentApi.class);
	
	/** 사용하고자 하는 Helper + Service 객체 주입 설정*/
	@Autowired
	WebHelper web;
	
	@Autowired
	DepartmentService departmentService;
	
	/** 학과 목록 API */
	@ResponseBody
	@RequestMapping(value = "/department_api/DepartmentSelectListApi", method = RequestMethod.GET)
	public void DepartmentSelectListApi(Locale locale, Model model, HttpServletResponse response) {
		
		/** 1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		/** 2) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		List<Department> item = null;
		try {
			item = departmentService.getDepartmentList(null);
		}catch(Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		/** 3) 처리 결과를 JSON으로 출력하기 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rt", "OK");
		data.put("item", item);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 학과 상세 정보 조회 API */
	@ResponseBody
	@RequestMapping(value = "/department_api/DepartmentSelectItemApi", method = RequestMethod.GET)
	public void DepartmentSelectItemApi(Locale locale, Model model, HttpServletResponse response) {
		
		/** 1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		/** 2) 파라미터 받기 및 유효성 검사 */
		int deptno = web.getInt("deptno");
		logger.debug("deptno=" + deptno);
		
		if(deptno == 0) {
			web.printJsonRt("학과번호가 없습니다.");
			return;
		}
		
		// 전달받은 파라미터를 Beans로 구성
		Department department = new Department();
		department.setDeptno(deptno);
		
		/** 3) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		Department item = null;
		try {
			item = departmentService.getDepartmentItem(department);
		}catch(Exception e) {
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		/** 4) 처리 결과를 JSON으로 출력하기 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("rt", "OK");
		data.put("item", item);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getWriter(), data);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 학과 정보 등록 API */
	@ResponseBody
	@RequestMapping(value = "/department_api/DepartmentInsertApi", method = RequestMethod.POST)
	public void DepartmentInsertApi(Locale locale, Model model, HttpServletResponse response) {
		/** 1) WebHelper 초기화 + 컨텐츠 형식 지정 */
		web.init();
		response.setContentType("application/json");
		
		/** 2) 파라미터 받기 및 유효성 검사 */
		String dname = web.getString("dname");
		String loc = web.getString("loc");
		
		// 파라미터 로그 확인
		logger.debug("dname=" + dname);
		logger.debug("loc=" + loc);
		
		// 필수 항목 유효성 검사		
		if(dname == null) {web.printJsonRt("학과이름을 입력하세요."); return;}
		if(loc == null) {web.printJsonRt("강의동을 입력하세요."); return;}
		
		// 파라미터 Beans 구성
		// --> study.spring.hellospring.model.Department;
		Department department = new Department();
		department.setDname(dname);
		department.setLoc(loc);
		
		/** 3) Service를 통한 SQL 수행 */
		try {
			departmentService.addDepartment(department);
		}catch(Exception e) {
			// 에러메시지를 JSON으로 표시한다.
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		/** 4) 처리 결과를 JSON으로 출력하기 */
		web.printJsonRt("OK");
	}
	
	/** 학과 삭제 API */
	@ResponseBody
	@RequestMapping(value = "department_api/DepartmentDeleteApi", method = RequestMethod.POST)
	public void DepartmentDeleteApi(Locale locale, Model model, HttpServletResponse response) {
		
		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		response.setContentType("application/json");
		
		/** 2) 파라미터 받기 및 유효성 검사 */
		int deptno = web.getInt("deptno");
		
		logger.debug("deptno=" + deptno);
		
		// 필수 항목에 대한 입력 여부 검사하기
		if(deptno == 0) {
			web.printJsonRt("학과번호가 없습니다.");
			return;
		}
		
		// 저장을 위한 JavaBeans 구성
		// --> study.spring.hellospring.model.Department;
		Department department = new Department();
		department.setDeptno(deptno);
		
		/** 3) Service를 통한 SQL 수행 */
		try {
			departmentService.deleteDepartment(department);
		}catch(Exception e) {
			// 에러메시지를 JSON으로 표시한다.
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		/** 4) 처리 결과를 JSON으로 출력하기 */
		web.printJsonRt("OK");
	}
	
	/** 학과 수정 API */
	@ResponseBody
	@RequestMapping(value = "department_api/DepartmentUpdateApi", method = RequestMethod.POST)
	public void DepartmentUpdateApi(Locale locale, Model model, HttpServletResponse response) {

		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		response.setContentType("application/json");
		
		/** 2) 파라미터 받기 및 유효성 검사 */
		int deptno = web.getInt("deptno");
		String dname = web.getString("dname");
		String loc = web.getString("loc");
		
		// 파라미터 로그 확인
		logger.debug("deptno=" + deptno);
		logger.debug("dname=" + dname);
		logger.debug("loc=" + loc);
		
		// 필수 항목에 대한 입력 여부 검사하기
		if(deptno == 0) {web.printJsonRt("학과번호가 없습니다."); return;}
		if(dname == null) {web.printJsonRt("학과이름을 입력하세요."); return;}
		if(loc == null) {web.printJsonRt("강의동을 입력하세요."); return;}
		
		// 저장을 위한 JavaBeans 구성하기
		Department department = new Department();
		department.setDeptno(deptno);
		department.setDname(dname);
		department.setLoc(loc);
		
		/** 3) Service를 통한 SQL 수행 */
		try {
			departmentService.editDepartment(department);
		}catch(Exception e) {
			// 에러메시지를 JSON으로 표시한다.
			web.printJsonRt(e.getLocalizedMessage());
			return;
		}
		
		/** 4) 처리 결과를 JSON으로 출력하기 */
		web.printJsonRt("OK");
	}
}
