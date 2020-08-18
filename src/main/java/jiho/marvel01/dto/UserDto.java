package jiho.marvel01.dto;

import jiho.marvel01.domain.Entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long uid;
    private String email;
    private String name;

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .uid(uid)
                .email(email)
                .name(name)
                .build();
        return userEntity;
    }

    @Builder
    public UserDto(Long uid, String email, String name) {
        this.uid = uid;
        this.email = email;
        this.name = name;
    }
}
