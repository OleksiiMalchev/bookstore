package com.javacourse.bookstore;

import com.javacourse.bookstore.configuration.TestDatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
@Import(TestDatabaseConfig.class)
class BookstoreApplicationTests {

	@Test
	void contextLoads() {
	}

}
