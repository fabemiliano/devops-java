package com.java.devops3.devops3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.micrometer.core.annotation.Timed;
import io.opentelemetry.api.trace.Tracer;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private final Tracer tracer;

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, Tracer tracer) {
        this.tracer = tracer;
		this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Timed(value = "post", description = "Tempo para fazer um post")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    @Timed(value = "get", description = "Tempo para fazer um get")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
        
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,
                                                   @RequestBody Employee employee){
        return employeeService.getEmployeeById(employeeId)
                .map(savedEmployee -> {

                    savedEmployee.setFirstName(employee.getFirstName());
                    savedEmployee.setFirstName(employee.getFirstName());
                    savedEmployee.setEmail(employee.getEmail());

                    Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
                    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){

        employeeService.deleteEmployee(employeeId);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);

    }
}