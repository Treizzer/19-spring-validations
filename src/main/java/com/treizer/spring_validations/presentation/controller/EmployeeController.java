package com.treizer.spring_validations.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.treizer.spring_validations.presentation.dto.employee.EmployeeDto;
import com.treizer.spring_validations.presentation.dto.employee.EmployeeInsertDto;
import com.treizer.spring_validations.presentation.dto.employee.EmployeeUpdateDto;
import com.treizer.spring_validations.service.interfaces.ICommonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private ICommonService<EmployeeDto, EmployeeInsertDto, EmployeeUpdateDto> employeeService;

    // public EmployeeController(ICommonService<EmployeeDto, EmployeeInsertDto,
    // EmployeeUpdateDto> employeeService) {
    // this.employeeService = employeeService;
    // }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(this.employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var employeeDto = this.employeeService.findById(id);

        return employeeDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(employeeDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> save(@Valid @RequestBody EmployeeInsertDto employeeInsertDto) {
        var employeeDto = this.employeeService.save(employeeInsertDto);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(employeeDto.getId())
                        .toUri())
                .body(employeeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto,
            @PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var employeeDto = this.employeeService.update(employeeUpdateDto, id);

        return employeeDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var employeeDto = this.employeeService.deleteById(id);

        return employeeDto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(employeeDto);
    }

}
