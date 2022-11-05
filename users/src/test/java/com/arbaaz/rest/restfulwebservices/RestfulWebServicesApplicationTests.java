package com.arbaaz.rest.restfulwebservices;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class RestfulWebServicesApplicationTests {
	
	Calc test = new Calc();

	@Test
	void addNum() {
		//given
		int numberOne = 20;
		int numberTwo = 30;
		
		// when
		int result = test.add(numberOne, numberTwo);
		int expected = 50;
		//then
		assertThat(result).isEqualTo(expected);
	}
	
	class Calc {
		int add(int a, int b) {
			return a+b;
		}
	}

}
