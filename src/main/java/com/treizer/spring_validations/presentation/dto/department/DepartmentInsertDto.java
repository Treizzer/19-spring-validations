package com.treizer.spring_validations.presentation.dto.department;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Validated
public class DepartmentInsertDto {

    @NotBlank(message = "El nombre NO puede ser nulo o vacío")
    @Size(min = 3, max = 30, message = "El nombre NO puede ser menora tres caracteres NI mayor a treinta")
    String name;

    @NotBlank(message = "La ciudad NO puede ser nula o vacía")
    @Size(min = 3, max = 15, message = "La ciudad NO puede tener menos de tres caracteres NI más de quince")
    String city;

}
