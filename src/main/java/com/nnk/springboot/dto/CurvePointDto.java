package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto object to parse data from forms of CurvePoint pages.
 * curvePoint/add to save a new CurvePoint, managed by the validate method in the CurveController.
 * curvePoint/update to update an existing CurvePoint, managed by the updateCurvePoint method in the CurvePointController.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurvePointDto {
    
    private Integer id;
    @NotNull(message = "Term can't be null, enter 0.")
    private Double term;
    @NotNull(message = "Value can't be null, enter 0.")
    private Double value;
}
