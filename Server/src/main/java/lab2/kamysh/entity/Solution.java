package lab2.kamysh.entity;


import lombok.*;
import lab2.kamysh.dto.SolutionDto;
import lab2.kamysh.errors.ErrorMessage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "solution")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Solution {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "solution", nullable = false)
    @NotBlank(message = ErrorMessage.NOT_BLANK)
    private String solution;

    public SolutionDto mapToDTO() {
        return new SolutionDto(this);
    }
}
