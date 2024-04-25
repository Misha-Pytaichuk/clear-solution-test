package clearsolution.pytaichuk.test;

import clearsolution.pytaichuk.test.util.builder.UserBuilder;
import clearsolution.pytaichuk.test.util.exception.BindingExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public BindingExceptionHandler bindingExceptionHandler(){
		return new BindingExceptionHandler();
	}

	@Bean
	public UserBuilder userBuilder(){
		return new UserBuilder();
	}
}
