package dev.com.movieflix.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
                            @Schema(type = "Long", description = "Numero de identifição principal")
                            Long id,

                            @Schema(type = "String", description = "Nome do filme")
                            String title,

                            @Schema(type = "String", description = "Descrição do filme")
                            String description,

                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            @Schema(type = "LocalDate", description = "Data de lançamento do filme")
                            LocalDate releaseDate,

                            @Schema(type = "double", description = "Score do filme")
                            double rating,

                            @Schema(type = "array", description = "Lista de codigo de categoria")
                            List<CategoryResponse> categories,

                            @Schema(type = "array", description = "Lista de codigo de serviços de streaming")
                            List<StreamingResponse> streamings) {
}
