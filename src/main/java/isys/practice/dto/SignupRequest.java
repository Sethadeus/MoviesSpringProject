package isys.practice.dto;

import isys.practice.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private Set<String> role = new HashSet<>();
}
