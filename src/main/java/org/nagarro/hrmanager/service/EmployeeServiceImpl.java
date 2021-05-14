package org.nagarro.hrmanager.service;

import javax.servlet.http.HttpServletRequest;

import org.nagarro.hrmanager.model.Employee;
import org.nagarro.hrmanager.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	final String url = "http://localhost:8081/employee/";
	
	@Autowired
	RestTemplate restTemplate;

	/* (non-Javadoc)
	 * @see org.nagarro.hrmanager.service.EmployeeService#getAllEmployee(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Employee[] getAllEmployee(HttpServletRequest request) {
		
		HttpHeaders headers = createHttpHeaders(request);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Employee[]> responseEntity = restTemplate.exchange(url
				,HttpMethod.GET,entity,Employee[].class);
		Employee[] employee = responseEntity.getBody();
		return employee;		
	}
	
	/* (non-Javadoc)
	 * @see org.nagarro.hrmanager.service.EmployeeService#getEmployee(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	public Employee getEmployee(HttpServletRequest request , int employeeCode) {
		HttpHeaders headers = createHttpHeaders(request);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Employee> responseEntity = restTemplate.exchange(url+employeeCode,HttpMethod.GET,entity,Employee.class);
		Employee employee = responseEntity.getBody();
		return employee;
	}
	
	private HttpHeaders createHttpHeaders(HttpServletRequest request){
	    String jwtToken = (String) request.getSession().getAttribute("jwtToken");
	    System.out.println(jwtToken);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization" , "Bearer "+jwtToken);
	    return headers;
	}
	
	/* (non-Javadoc)
	 * @see org.nagarro.hrmanager.service.EmployeeService#saveOrUpdate(javax.servlet.http.HttpServletRequest, org.nagarro.hrmanager.model.Employee)
	 */
	@Override
	public void saveOrUpdate(HttpServletRequest request , Employee employee){
		HttpHeaders headers = createHttpHeaders(request);
        HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
        String response = restTemplate.postForObject(url, entity, String.class);
	}
	
	/* (non-Javadoc)
	 * @see org.nagarro.hrmanager.service.EmployeeService#deleteEmployee(javax.servlet.http.HttpServletRequest, org.nagarro.hrmanager.model.Employee)
	 */
	@Override
	public void deleteEmployee(HttpServletRequest request , int employeeCode) {
		HttpHeaders headers = createHttpHeaders(request);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url+employeeCode,HttpMethod.DELETE,entity,String.class);
	}

}
