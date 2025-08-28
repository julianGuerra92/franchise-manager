package co.com.accenture.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Branch {
    private Long id;
    private String name;
    private Long franchiseId;
}
