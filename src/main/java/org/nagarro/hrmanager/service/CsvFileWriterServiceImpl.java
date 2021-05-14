package org.nagarro.hrmanager.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.nagarro.hrmanager.model.Employee;
import org.nagarro.hrmanager.service.interfaces.CsvFileService;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Service("csvFileService")
public class CsvFileWriterServiceImpl implements CsvFileService {

	/* (non-Javadoc)
	 * @see org.nagarro.hrmanager.service.CsvFileService#writeCSVFile(java.lang.String, java.util.List)
	 */
	@Override
	public void writeCSVFile(String csvFileName, List<Employee> employee) {
	    ICsvBeanWriter beanWriter = null;
	    CellProcessor[] processors = new CellProcessor[] {
	            new ParseInt(), // code
	            new NotNull(), // name
	            new NotNull(), // location
	            new NotNull(), // email
	            new NotNull(), //  date of birth
	    };
	 
	    try {
	        beanWriter = new CsvBeanWriter(new FileWriter(csvFileName),
	                CsvPreference
	                .STANDARD_PREFERENCE);
	        String[] header = {"employeeCode", "employeeName", "location", "email", "dateOfBirth"};
	        beanWriter.writeHeader(header);
	 
	        for (Employee emp : employee) {
	            beanWriter.write(emp, header, processors);
	        }
	 
	    } catch (IOException ex) {
	        System.err.println("Error writing the CSV file: " + ex);
	    } finally {
	        if (beanWriter != null) {
	            try {
	                beanWriter.close();
	            } catch (IOException ex) {
	                System.err.println("Error closing the writer: " + ex);
	            }
	        }
	    }
	}
}
