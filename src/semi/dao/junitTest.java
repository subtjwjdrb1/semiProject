package semi.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class junitTest {

	@Test
	void delete() {
		ReviewDao dao = ReviewDao.getInstance();
		int n=dao.delete(33, "qqqqqq");
		boolean a= false;
		if(n>0) a= true;
		assertTrue(a);
		
	}

}
