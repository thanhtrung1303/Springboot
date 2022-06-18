package vn.techmaster.userbackend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
