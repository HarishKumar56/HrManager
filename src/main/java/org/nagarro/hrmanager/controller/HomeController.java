package org.nagarro.hrmanager.controller;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.nagarro.hrmanager.model.Employee;
import org.nagarro.hrmanager.service.interfaces.CsvFileService;
import org.nagarro.hrmanager.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CsvFileService csvFileService ;

	@GetMapping("/")
	public String home(HttpServletRequest request, Map<String, Employee[]> model) {
		Employee[] employee = employeeService.getAllEmployee(request);
		model.put("allEmployee", employee);
		return "home";
	}

	@PostMapping("/update")
	public String updatePage(@RequestParam int employeeCode, @ModelAttribute("employee") Employee employee,
			HttpServletRequest request) {
		Employee emp = employeeService.getEmployee(request, employeeCode);
		employee.setEmployeeName(emp.getEmployeeName());
		employee.setDateOfBirth(emp.getDateOfBirth());
		employee.setEmail(emp.getEmail());
		employee.setEmployeeCode(emp.getEmployeeCode());
		employee.setLocation(emp.getLocation());

		return "alter";
	}

	@GetMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		return "alter";
	}

	@PostMapping("/delete")
	public String deleteEmployee(@RequestParam int employeeCode, HttpServletRequest request) {
		employeeService.deleteEmployee(request, employeeCode);
		return "redirect:/";
	}

	@PostMapping("/save")
	public String saveOrUpdate(@ModelAttribute("employee") Employee employee, HttpServletRequest request) {
		employeeService.saveOrUpdate(request, employee);
		return "redirect:/";
	}
	
	@GetMapping("/download")
	public String downloadFile(HttpServletRequest request) {
		String fileName = "E:\\Training\\Advance Java\\files\\download.csv";
		Employee[] employee = employeeService.getAllEmployee(request);
		csvFileService.writeCSVFile(fileName, Arrays.asList(employee));
		return "redirect:/";
	}

}
