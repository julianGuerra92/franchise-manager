package co.com.accenture.presentation.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
