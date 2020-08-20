package jiho.marvel01.domain.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "user")
public class UserEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "corp")
    private String corp;

    @Column(name = "department")
    private String department;

    @Column(name = "i_group")
    private Integer i_group;

    @Builder
    public UserEntity(Long uid, String email, String name, String corp, String department, Integer i_group) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.corp = corp;
        this.department = department;
        this.i_group = i_group;
    }
}
