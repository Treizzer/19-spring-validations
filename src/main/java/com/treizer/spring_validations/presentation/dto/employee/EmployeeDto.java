package com.treizer.spring_validations.presentation.dto.employee;

import java.time.LocalDate;

import com.treizer.spring_validations.presentation.dto.department.DepartmentDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {

    Long id;
    String name;
    String lastName;
    String email;
    String phone;
    Byte age;
    Double height;
    Boolean married;
    LocalDate dateBirth;
    DepartmentDto department;

}
