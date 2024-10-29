package com.treizer.spring_validations.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.treizer.spring_validations.persistence.entity.EmployeeEntity;
import com.treizer.spring_validations.persistence.repository.IEmployeeRepository;
import com.treizer.spring_validations.presentation.dto.employee.EmployeeDto;
import com.treizer.spring_validations.presentation.dto.employee.EmployeeInsertDto;
import com.treizer.spring_validations.presentation.dto.employee.EmployeeUpdateDto;
import com.treizer.spring_validations.service.interfaces.ICommonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService implements ICommonService<EmployeeDto, EmployeeInsertDto, EmployeeUpdateDto> {

    @Autowired
    private IEmployeeRepository employeeRepository;

    private static final ModelMapper MAPPER = new ModelMapper();

    // public EmployeeService(IEmployeeRepository employeeRepository) {
    // this.employeeRepository = employeeRepository;
    // }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        var employees = this.employeeRepository.findAll();

        return StreamSupport
                .stream(employees.spliterator(), false)
                .map(employee -> MAPPER.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto findById(Long id) {
        var employeeEntity = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al empleado con ID: " + id));

        return MAPPER.map(employeeEntity, EmployeeDto.class);
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeInsertDto insertDto) {
        try {
            var employeeEntity = MAPPER.map(insertDto, EmployeeEntity.class);
            employeeEntity = this.employeeRepository.save(employeeEntity);
            return MAPPER.map(employeeEntity, EmployeeDto.class);

        } catch (Exception e) {
            throw new UnsupportedOperationException(
                    "No fue posible guardar al empleado: " + insertDto + " -> e: " + e.toString());
        }
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeUpdateDto updateDto, Long id) {
        var employeeEntity = id != null
                ? this.employeeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontró al empleado con ID: " + id))
                : this.employeeRepository.findById(updateDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontró al empleado con ID: " + updateDto.getId()));

        // if (!updateDto.getName().isBlank()) {
        // employeeEntity.setName(updateDto.getName());
        // }
        // if (!updateDto.getLastName().isBlank()) {
        // employeeEntity.setLastName(updateDto.getLastName());
        // }
        // if (!updateDto.getEmail().isBlank()) {
        // employeeEntity.setEmail(updateDto.getEmail());
        // }
        // if (!updateDto.getPhone().isBlank()) {
        // employeeEntity.setPhone(updateDto.getPhone());
        // }
        // if (updateDto.getAge() != null) {
        // employeeEntity.setAge(updateDto.getAge());
        // }
        // if (updateDto.getHeight() != null) {
        // employeeEntity.setHeight(updateDto.getHeight());
        // }
        // if (updateDto.getMarried() != null) {
        // employeeEntity.setMarried(updateDto.getMarried());
        // }
        // if (updateDto.getDateBirth() != null) {
        // employeeEntity.setDateBirth(updateDto.getDateBirth());
        // }
        // if (updateDto.getDepartment().getId() != null) {
        // employeeEntity.getDepartment().setId(updateDto.getDepartment().getId());
        // }
        // if (!updateDto.getDepartment().getName().isBlank()) {
        // employeeEntity.getDepartment().setName(updateDto.getDepartment().getName());
        // }
        // if (!updateDto.getDepartment().getCity().isBlank()) {
        // employeeEntity.getDepartment().setCity(updateDto.getDepartment().getCity());
        // }

        Optional.ofNullable(updateDto.getId()).ifPresent(employeeEntity::setId);
        Optional.ofNullable(updateDto.getName()).ifPresent(employeeEntity::setName);
        Optional.ofNullable(updateDto.getLastName()).ifPresent(employeeEntity::setLastName);
        Optional.ofNullable(updateDto.getEmail()).ifPresent(employeeEntity::setEmail);
        Optional.ofNullable(updateDto.getPhone()).ifPresent(employeeEntity::setPhone);
        Optional.ofNullable(updateDto.getAge()).ifPresent(employeeEntity::setAge);
        Optional.ofNullable(updateDto.getHeight()).ifPresent(employeeEntity::setHeight);
        Optional.ofNullable(updateDto.getMarried()).ifPresent(employeeEntity::setMarried);
        Optional.ofNullable(updateDto.getDateBirth()).ifPresent(employeeEntity::setDateBirth);

        if (updateDto.getDepartment() != null) {
            var department = employeeEntity.getDepartment();
            Optional.ofNullable(updateDto.getDepartment().getId())
                    .ifPresent(department::setId);
            Optional.ofNullable(updateDto.getDepartment().getName())
                    .ifPresent(department::setName);
            Optional.ofNullable(updateDto.getDepartment().getCity())
                    .ifPresent(department::setCity);
        }

        this.employeeRepository.save(employeeEntity);

        return MAPPER.map(employeeEntity, EmployeeDto.class);
    }

    @Override
    @Transactional
    public EmployeeDto deleteById(Long id) {
        var employeeEntity = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró al empleado con ID: " + id));

        this.employeeRepository.deleteById(id);

        return MAPPER.map(employeeEntity, EmployeeDto.class);
    }

}
