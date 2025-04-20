package com.excelfileimport.service;

import com.excelfileimport.model.Employee;
import com.excelfileimport.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveExcelFileData(InputStream file) throws IOException {
        List<Employee> employeeList =new LinkedList<>();
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet =workbook.getSheetAt(0);

        sheet.forEach( row->{
            if(row.getRowNum()!=0) {
                Employee employee = new Employee();
                employee.setEmpFirstName(row.getCell(0).getStringCellValue());
                employee.setEmpLastName(row.getCell(1).getStringCellValue());
                employee.setEmpDateofBirth(row.getCell(2).getDateCellValue());
                employee.setEmpSalary(row.getCell(3).getNumericCellValue());
                employee.setEmpAddress(row.getCell(4).getStringCellValue());
                employeeList.add(employee);
            }
        });

        employeeRepository.saveAll(employeeList);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public void deleteById(int empId){
        employeeRepository.deleteById(empId);
    }
}
