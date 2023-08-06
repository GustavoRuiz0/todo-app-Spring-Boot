package com.cyber.inter.internalizarworkflow.dtos;

import jakarta.validation.constraints.NotBlank;

public record TaskRecordDto(@NotBlank String title, @NotBlank String content) {

}
