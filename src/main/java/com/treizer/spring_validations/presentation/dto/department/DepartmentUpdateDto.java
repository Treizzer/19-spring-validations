package com.treizer.spring_validations.presentation.dto.department;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Validated
public class DepartmentUpdateDto {

    @Min(value = 1, message = "NO existen IDs menores a uno")
    Long id;

    @Size(min = 3, max = 30, message = "El nombre NO puede ser menor a tres caracteres NI mayor a treinta")
    String name;

    @Size(min = 3, max = 15, message = "La ciudad NO puede tener menos de tres caracteres NI más de quince")
    String city;

}
