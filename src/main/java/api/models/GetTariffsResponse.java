package api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetTariffsResponse {
    private Integer id;

    private String type;

    private String interestRate;
}
