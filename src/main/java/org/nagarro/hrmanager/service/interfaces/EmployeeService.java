package org.nagarro.hrmanager.service.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.nagarro.hrmanager.model.Employee;

public interface EmployeeService {

	Employee[] getAllEmployee(HttpServletRequest request);

	Employee getEmployee(HttpServletRequest request, int employeeCode);

	void saveOrUpdate(HttpServletRequest request, Employee employee);

	void deleteEmployee(HttpServletRequest request, int employeeCode);

}