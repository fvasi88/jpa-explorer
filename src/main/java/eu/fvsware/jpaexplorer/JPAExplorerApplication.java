package eu.fvsware.jpaexplorer;

import eu.fvsware.jpaexplorer.service.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@SpringBootApplication
@Controller
@ComponentScan("eu.fvsware")
@EnableAutoConfiguration
public class JPAExplorerApplication {

	@Autowired
    EmployeeRepository employeeRepository;
	
	@RequestMapping(path = "/test")
	@ResponseBody
	public String home() {
		long count = employeeRepository.count();
		return "Hello Duke " + count;
	}

//	@RequestMapping(path = "/index")
//	public String index() {
//		return "index";
//	}

	public static void main(String[] args) throws SQLException {
		DatabaseAccess.startDB();
		SpringApplication.run(JPAExplorerApplication.class, args);
	}
}
