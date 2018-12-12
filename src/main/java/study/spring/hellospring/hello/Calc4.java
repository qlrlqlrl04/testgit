package study.spring.hellospring.hello;

import java.util.Random;

public class Calc4 {
	private int x;
	private int y;
	
	public void init() {
		// 렌덤객체 생성
		Random r = new Random(System.currentTimeMillis());
		// 렌덤한 int값을 생성한다.
		x = r.nextInt();
		y = r.nextInt();
	}

	public int sum() {
		return this.x + this.y;
	}
}
