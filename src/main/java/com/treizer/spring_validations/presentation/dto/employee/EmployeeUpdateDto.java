package com.treizer.spring_validations.presentation.dto.employee;

import java.time.LocalDate;

import com.treizer.spring_validations.presentation.dto.department.DepartmentUpdateDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmployeeUpdateDto {

    @Min(value = 1, message = "NO existen IDs menores a uno")
    Long id;

    @Size(min = 2, message = "El nombre NO puede ser menor a dos caracteres")
    String name;

    @Size(min = 2, message = "El apellido NO puede ser menor a dos caracteres")
    String lastName;

    @Email(message = "El email NO tiene un formato valído")
    String email;

    @Pattern(regexp = "^(?!.*(\\d)\\1{4})\\d{10}$", message = "El número de teléfono debe tener exactamente diez dígitos y NO puede repetir el mismo número diez veces")
    String phone;

    @Min(value = 16, message = "El empleado NO puede ser menor de 16 años")
    @Max(value = 70, message = "El empleado NO puede ser mayor de 70 años")
    Byte age;

    @Min(value = 1, message = "La altura NO puede ser menor de un metro")
    @Max(value = 3, message = "La altura NO puede ser mayor de tres metros")
    @Digits(integer = 1, fraction = 3, message = "La altura NO debe tener más de un número entero y tres decimales")
    Double height;

    Boolean married;

    LocalDate dateBirth;

    @Valid
    DepartmentUpdateDto department;

}
