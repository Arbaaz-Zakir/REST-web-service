package com.arbaaz.rest.restfulwebservices.user;

import java.util.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import com.arbaaz.rest.restfulwebservices.user_bean.User;

class userTest {
	
	// User user = new User();
	
	//@BeforeAll
//	public void beforeSetUp() {
//		//Date birthdate = new Date(1999, 6, 6);
//		//User user = new User();
//		//user.setId(99);
//		user.setName("Balerion");
//		//user.setBirthdate(birthdate);
//	}

	@Test
	void testGetId() {
		//given
		User user = new User();
		//when
		user.setId(99);
		int expected = 99;
		int result = user.getId();
		//then
		assertThat(result).isEqualTo(expected);
		//fail("Not yet implemented");
	}

}

//public Integer getId() {
//	return id;
//}
//public void setId(Integer id) {
//	this.id = id;
//}
//public String getName() {
//	return name;
//}
//public void setName(String name) {
//	this.name = name;
//}
//public Date getBirthdate() {
//	return birthdate;
//}
//public void setBirthdate(Date birthdate) {
//	this.birthdate = birthdate;
//}
//@Override
//public String toString() {
//	return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
//}