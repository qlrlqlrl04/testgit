package study.spring.hellospring;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import study.spring.hellospring.model.Department;
import study.spring.hellospring.service.DepartmentService;
import study.spring.helper.PageHelper;
import study.spring.helper.WebHelper;

/** 컨트롤러 선언 */
@Controller
public class DepartmentController {
	
	/** log4j 객체 생성 및 사용할 객체 주입받기 */
	private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);
	
	// --> import study.spring.helper.WebHelper;
	@Autowired
	WebHelper web;
		
	// --> import study.spring.helper.PageHelper;
	@Autowired
	PageHelper page;
	
	// 등록, 삭제, 수정에서 사용할 서비스 객체
	// --> import study.spring.hellospring.service.ProfessorService;
	@Autowired
	DepartmentService departmentService;
	
	/** 학과 목록 페이지 */
	@RequestMapping(value = "/department/dept_list.do", method = RequestMethod.GET)
	public ModelAndView DeptList(Locale locale, Model model) {
		
		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		
		// 파라미터를 저장할 Beans
		Department department = new Department();
		
		// 검색어 파라미터 받기 + Beans 설정
		String keyword = web.getString("keyword", "");
		department.setDname(keyword);
		
		// 현재 페이지 번호에 대한 파라미터 받기
		int nowPage = web.getInt("page", 1);
		
		/** 2) 페이지 번호 구현하기 */
		// 전체 데이터 수 조회하기
		int totalCount = 0;
		try {
			totalCount = departmentService.getDepartmentCount(department);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		// 페이지 번호에 대한 연산 수행 후 조회조건값 지정을 위한 Beans에 추가하기
		page.pageProcess(nowPage, totalCount, 10, 5);
		department.setLimitStart(page.getLimitStart());
		department.setListCount(page.getListCount());
		
		/** 3) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		List<Department> list = null;
		try {
			list = departmentService.getDepartmentList(department);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		/** 4) View 처리하기 */
		// 조회 결과를 View에게 전달한다.
		model.addAttribute("list", list);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		
		return new ModelAndView("department/dept_list");
	}
	
	/** 학과 정보 상세보기 페이지 */
	@RequestMapping(value = "/department/dept_view.do", method = RequestMethod.GET)
	public ModelAndView DeptView(Locale locale, Model model) {

		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		
		int deptno = web.getInt("deptno");
		logger.debug("deptno=" + deptno);
		
		if(deptno == 0) {
			return web.redirect(null, "학과번호가 없습니다.");
		}
		
		// 파라미터를 저장할 Beans
		Department department = new Department();
		department.setDeptno(deptno);
		
		/** 2) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		Department item = null;
		try {
			item = departmentService.getDepartmentItem(department);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) View 처리하기 */
		// 조회 결과를 View에게 전달한다.
		model.addAttribute("item", item);
		
		return new ModelAndView("department/dept_view");
	}
	
	/** 학과 등록 페이지 */
	@RequestMapping(value = "/department/dept_add.do", method = RequestMethod.GET)
	public ModelAndView DeptAdd(Locale locale, Model model) {

		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		
		/** 2) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		List<Department> deptList= null;
		try {
			deptList = departmentService.getDepartmentJoinList(null);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) View 처리하기 */
		// 조회 결과를 View에게 전달한다.
		model.addAttribute("deptList", deptList);
		
		return new ModelAndView("department/dept_add");
	}
	
	/** 학과 등록 처리 페이지 (action 페이지로 사용된다.) */
	@RequestMapping(value = "/department/dept_add_ok.do", method = RequestMethod.POST)
	public ModelAndView DeptAddOk(Locale locale, Model model) {

		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		
		// input 태그의 name속성에 명시된 값을 사용한다.
		String dname = web.getString("dname");
		String loc = web.getString("loc");
		
		// 전달 받은 파라미터는 로그로 값을 확인하는 것이 좋다.
		logger.debug("dname=" + dname);
		logger.debug("loc=" + loc);
		
		/** 2) 필수항목에 대한 입력 여부 검사하기 */
		// RegexHelper를 사용하여 입력값의 형식을 검사할 수 도 있다. (여기서는 생략)
		if(dname == null) {return web.redirect(null, "학과이름을 입력하세요.");}
		if(loc == null) {return web.redirect(null, "강의동을 입력하세요.");}
		
		/** 3) 저장을 위한 JavaBeans 구성하기 */
		// --> study.jsp.myschool.model.Professor
		Department department = new Department();
		department.setDname(dname);
		department.setLoc(loc);
		
		/** 4) Service를 통한 SQL 수행 */
		try {
			departmentService.addDepartment(department);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		/** 5) 결과를 확인하기 위한 페이지로 이동 */
		// 조회 결과를 View에게 전달한다.
		String url = web.getRootPath() + "/department/dept_view.do?deptno=" + department.getDeptno();
		
		return web.redirect(url, "저장되었습니다.");
	}
	
	/** 학과 정보 삭제 페이지 */
	@RequestMapping(value = "/department/dept_delete.do", method = RequestMethod.GET)
	public ModelAndView DeptDelete(Locale locale, Model model) {

		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		
		int deptno = web.getInt("deptno");
		logger.debug("deptno=" + deptno);
		
		if(deptno == 0) {
			return web.redirect(null, "학과번호가 없습니다.");
		}
		
		// 파라미터를 Beans에 저장한다.
		Department department = new Department();
		department.setDeptno(deptno);
		
		/** 2) Service를 통한 SQL 수행 */
		try {
			departmentService.deleteDepartment(department);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) 목록페이지로 이동 */
		return web.redirect(web.getRootPath() + "/department/dept_list.do", "삭제되었습니다.");
	}
	
	/** 학과 정보 수정 페이지 */
	@RequestMapping(value = "/department/dept_edit.do", method = RequestMethod.GET)
	public ModelAndView DeptEdit(Locale locale, Model model) {

		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		
		int deptno = web.getInt("deptno");
		logger.debug("deptno=" + deptno);
		
		if(deptno == 0) {
			return web.redirect(null, "학과번호가 없습니다.");
		}
		
		// 파라미터를 Beans에 저장한다.
		Department department = new Department();
		department.setDeptno(deptno);
		
		/** 2) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		Department item = null;
		List<Department> deptList = null;
		try {
			item = departmentService.getDepartmentItem(department);
			deptList = departmentService.getDepartmentJoinList(null);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) View 처리하기 */
		// 조회 결과를 View에게 전달한다.
		model.addAttribute("item", item);
		model.addAttribute("deptList", deptList);
		
		return new ModelAndView("department/dept_edit");
	}
	
	/** 학과 정보 삭제 처리 페이지 (action 페이지로 사용된다.) */
	@RequestMapping(value = "/department/dept_edit_ok.do", method = RequestMethod.POST)
	public ModelAndView DeptEditOk(Locale locale, Model model) {

		/** 1) WebHelper 초기화 및 파라미터 처리 */
		web.init();
		
		// input 태그의 name속성에 명시된 값을 사용한다.
		int deptno = web.getInt("deptno");
		String dname = web.getString("dname");
		String loc = web.getString("loc");
		
		// 전달 받은 파라미터는 로그로 값을 확인하는 것이 좋다.
		logger.debug("deptno=" + deptno);
		logger.debug("dname=" + dname);
		logger.debug("loc=" + loc);
		
		/** 2) 필수항목에 대한 입력 여부 검사하기 */
		// RegexHelper를 사용하여 입력값의 형식을 검사할 수 도 있다. (여기서는 생략)
		if(deptno == 0) {return web.redirect(null, "학과번호가 없습니다.");}
		if(dname == null) {return web.redirect(null, "학과이름을 입력하세요.");}
		if(loc == null) {return web.redirect(null, "강의동을 입력하세요.");}
		
		/** 3) 저장을 위한 JavaBeans 구성하기 */
		// --> study.jsp.myschool.model.Professor
		Department department = new Department();
		department.setDeptno(deptno);
		department.setDname(dname);
		department.setLoc(loc);
		
		/** 4) Service를 통한 SQL 수행 */
		try {
			departmentService.editDepartment(department);
		}catch(Exception e) {
			return web.redirect(null, e.getLocalizedMessage());
		}
		
		/** 5) 결과를 확인하기 위한 페이지로 이동 */
		// 조회 결과를 View에게 전달한다.
		String url = web.getRootPath() + "/department/dept_view.do?deptno=" + department.getDeptno();
		
		return web.redirect(url, "수정되었습니다.");
	}
}
