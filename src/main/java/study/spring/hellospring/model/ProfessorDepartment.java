package study.spring.hellospring.model;

public class ProfessorDepartment extends Professor {
	private String dname;
	private int limitStart;
	private int listCount;
	
	public int getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	@Override
	public String toString() {
		return "Department [dname=" + dname + ", getProfno()=" + getProfno() + ", getName()=" + getName()
				+ ", getUserid()=" + getUserid() + ", getPosition()=" + getPosition() + ", getSal()=" + getSal()
				+ ", getHiredate()=" + getHiredate() + ", getComm()=" + getComm() + ", getDeptno()=" + getDeptno()
				+ "]";
	}
}
