package me.iagfarov.hwmaven.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {
    @NotBlank

    private String name;

    @Positive
    private Integer count;
    private String measure;
}
