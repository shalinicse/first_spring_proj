package net.javaguides.springbootbackend.controller;

import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootbackend.repository.EmployeeRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@PostMapping
	//build create employee Rest Api
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);		
	}
	
	@GetMapping("{id}")
	//build get employee by id rest api
	public ResponseEntity<Employee> getEmployeeId(@PathVariable long id)
	{
		Employee employee = employeeRepository.findById(id).orElseThrow
				(() -> new ResourceNotFoundException("Employee does not exist"+id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("{id}")
	//build update employee rest api
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails)
	{
		Employee updateEmployee= employeeRepository.findById(id).orElseThrow
				(() -> new ResourceNotFoundException("Employee not found"+id));
		updateEmployee.setFirstName(employeeDetails.getFirstName());
		updateEmployee.setLastName(employeeDetails.getLastName());
		updateEmployee.setEmailId(employeeDetails.getEmailId());
		
		employeeRepository.save(updateEmployee);
		
		return ResponseEntity.ok(updateEmployee);				
	}
	
	
	@DeleteMapping("{id}")
	//build delete employee rest api
	public ResponseEntity<Employee> deleteEmployee(@PathVariable long id)
	{
		Employee employee=employeeRepository.findById(id).orElseThrow
				(() -> new ResourceNotFoundException("Employee not found"+id));
		
		employeeRepository.delete(employee);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	


}
