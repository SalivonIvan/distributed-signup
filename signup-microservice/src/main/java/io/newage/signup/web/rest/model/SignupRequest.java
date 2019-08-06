package io.newage.signup.web.rest.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest implements Serializable {

    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = -5351304218739402635L;

    @ApiModelProperty(hidden = true)
    private String uuid;

    @NotBlank
    @ApiModelProperty(example = "test@test.com", required = true, value = "The value is email.")
    private String email;

    @NotBlank
    @ApiModelProperty(example = "123456", required = true, value = "The value is password.")
    private String password;

}
