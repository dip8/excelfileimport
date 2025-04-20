package com.excelfileimport.controller;

import com.excelfileimport.model.Employee;
import com.excelfileimport.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<String> saveExcelFile(@RequestParam("file") MultipartFile file) throws IOException{
        employeeService.saveExcelFileData(file.getInputStream());
        return ResponseEntity.ok("File Data Saved Successfully");
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deletedById(@PathVariable int empId){
        employeeService.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }
}
