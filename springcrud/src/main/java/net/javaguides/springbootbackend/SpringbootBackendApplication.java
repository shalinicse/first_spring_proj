package net.javaguides.springbootbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		
			Employee employee= new Employee();
			employee.setFirstName("Ramesh");
			employee.setLastName("shukla");
			employee.setEmailId("123@gmail.com");
			employeeRepository.save(employee);

			Employee employee1= new Employee();
			employee1.setFirstName("john");
			employee1.setLastName("seena");
			employee1.setEmailId("1234@gmail.com");
			employeeRepository.save(employee1);		
	}
	
	
	

}
