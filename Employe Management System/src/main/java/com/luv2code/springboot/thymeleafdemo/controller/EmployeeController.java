package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


	private EmployeeService employeeService;
	 @Autowired
	 public EmployeeController(EmployeeService employeeService){
		 this.employeeService = employeeService;
	 }

	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		// get employees from database
		List<Employee> employees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		// create model attribute to bind from the data
		Employee employee = new Employee();
		model.addAttribute("employee",employee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id,Model model){
		// get employee from the service
		Employee employee = employeeService.findById(id);
		//set employee in the model to prepopulate the form
		model.addAttribute("employee",employee);
		//send over to our form
		return "employees/employee-form";
	}


	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute ("employee") Employee employee){
		employeeService.save(employee);

		return "redirect:/employees/list";

	}
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int id,Model model){
		employeeService.deleteById(id);
		return "redirect:/employees/list";

	}
}









