package io.newage.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile")
@ToString(of = "uuid")
public class Profile implements Serializable {

    private static final long serialVersionUID = -4827087355941026678L;

    @Id
    @NotBlank
    private String uuid;

    @NotBlank
    @Field
    private String email;

    @NotBlank
    @Field
    private String password;
}
