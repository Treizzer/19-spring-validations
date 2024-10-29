package com.treizer.spring_validations.presentation.dto.employee;

import java.time.LocalDate;

import com.treizer.spring_validations.presentation.dto.department.DepartmentInsertDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmployeeInsertDto {

    @NotBlank(message = "El nombre NO puede ser nulo o vacío")
    @Size(min = 3, max = 15, message = "El nombre NO puede ser menor a tres caracteres NI mayor a quince")
    // @ValidName // My own validator
    String name;

    @NotBlank(message = "El apellido NO puede ser nulo o vacío")
    @Size(min = 3, max = 15, message = "El apellido NO puede ser menor a tres caracteres NI mayor a quince")
    String lastName;

    @NotBlank(message = "El email NO puede ser nulo o vacío")
    @Email(message = "El email NO tiene un formato valído")
    String email;

    @NotBlank(message = "El número de teléfono NO puede ser nulo o vacío")
    @Pattern(regexp = "^(?!.*(\\d)\\1{4})\\d{10}$", message = "El número de teléfono debe tener exactamente diez dígitos y no puede repetir el mismo número diez veces")
    String phone;

    @NotNull(message = "La edad NO puede ser nulo")
    @Min(value = 16, message = "El empleado NO puede ser menor de 16 años")
    @Max(value = 70, message = "El empleado NO puede ser mayor de 70 años")
    Byte age;

    @NotNull(message = "La altura no puede ser nulo")
    @Min(value = 1, message = "La altura NO puede ser menor a un metro")
    @Max(value = 3, message = "La altura NO puede ser mayor a tres metros")
    @Digits(integer = 1, fraction = 3, message = "La altura NO debe tener más de un número entero y tres decimales")
    Double height;

    @NotNull(message = "El estado marital NO puede ser nulo")
    Boolean married;

    @NotNull(message = "La fecha de nacimiento NO puede ser nulo")
    LocalDate dateBirth;

    @Valid
    DepartmentInsertDto department;

}

/*
 * @NotNull: El campo no debe ser null.
 * 
 * @NotEmpty: El campo no debe ser null y su tamaño debe ser mayor que cero.
 * 
 * @NotBlank: El campo no debe ser null y debe contener al menos un carácter que
 * no sea un espacio en blanco.
 * 
 * @Size: Controla el tamaño de cadenas, listas, arreglos, etc.
 * 
 * @Min y @Max: Define los valores mínimo y máximo para números.
 * 
 * @Email: Verifica si el campo tiene un formato de correo electrónico válido.
 * 
 * @Pattern: Define una expresión regular que el campo debe cumplir.
 * 
 * @Digits: Define el limite de dígitos que puedes ingresar en números enteros y
 * decimales.
 * 
 * @CreditCardNumber: Valida números de tarjetas de crédito
 * 
 * @Past: Solo acepta fechas pasadas del día actual (fechas).
 * 
 * @Future Solo acepta fechas futuras del día actual (fechas).
 * 
 * @AssertTrue: Solo acepta que el valor sea un true (Booleans).
 * 
 * @AssertFalse: Solo acepta que el valor sea un false (Booleans).
 */
