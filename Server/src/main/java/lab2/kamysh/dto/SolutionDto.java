package lab2.kamysh.dto;

import lab2.kamysh.entity.Solution;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolutionDto {
    private Integer id;
    private Integer user;
    private String solution;

    public SolutionDto(Solution solution) {
        this.id = solution.getId();
        this.solution = solution.getSolution();

        if (solution.getUser() != null) {
            this.user = solution.getUser().getId();
        }
    }
}
