package eu.fvsware.jpaexplorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@ComponentScan("eu.fvsware")
public class JPAExplorerApplication {
	
	@RequestMapping(path = "/test")
	@ResponseBody
	public String home() {
		return "Hello Duke";
	}

	public static void main(String[] args) {
		SpringApplication.run(JPAExplorerApplication.class, args);
	}
}
