package baylinux01.studentInfoSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
public class StudentInfoSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentInfoSystemApplication.class, args);
	}

}
