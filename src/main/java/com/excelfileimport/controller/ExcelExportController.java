package com.excelfileimport.controller;

import com.excelfileimport.model.Employee;
import com.excelfileimport.service.ExcelExportService;
import com.excelfileimport.repository.EmployeeRepository; // Assuming you have this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class ExcelExportController {

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportExcel() throws Exception {
        List<Employee> employees = employeeRepository.findAll(); // fetch all employee data

        ByteArrayInputStream in = excelExportService.exportToExcel(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
}
