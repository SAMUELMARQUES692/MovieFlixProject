package dev.com.movieflix.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(@Schema(type = "String", description = "Nome do filme")
                           @NotEmpty(message = "Titulo do filme é obrigatorio")
                           String title,

                           @Schema(type = "String", description = "Descrição do filme")
                           String description,

                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           @Schema(type = "LocalDate", description = "Data de lançamento do filme")
                           LocalDate releaseDate,

                           @Schema(type = "double", description = "Score do filme")
                           double rating,

                           @Schema(type = "array", description = "Lista de codigo de categoria")
                           List<Long> categories,

                           @Schema(type = "array", description = "Lista de codigo de serviços de streaming")
                           List<Long> streamings
                           ) {
}
