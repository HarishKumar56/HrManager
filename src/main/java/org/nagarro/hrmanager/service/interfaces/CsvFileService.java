package org.nagarro.hrmanager.service.interfaces;

import java.util.List;

import org.nagarro.hrmanager.model.Employee;

public interface CsvFileService {

	void writeCSVFile(String csvFileName, List<Employee> employee);

}