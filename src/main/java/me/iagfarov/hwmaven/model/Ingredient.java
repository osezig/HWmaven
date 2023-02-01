package me.iagfarov.hwmaven.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Ingredient {
    @NotBlank
    private String name;

    @Positive
    private Integer count;
    @NotBlank
    private String measure;
}
