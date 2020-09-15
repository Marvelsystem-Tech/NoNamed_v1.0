package jiho.marvel01.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "organazation")
public class OrgEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    public OrgEntity() {
    }

    @Column(name = "codes")
    private String codes;

    @Column(name = "name")
    private String name;

    @Column(name = "eng_name")
    private String eng_name;

    @Column(name = "upper_codes")
    private String upper_codes;

    @Column(name = "upper_name")
    private String upper_name;

    @Column(name = "status")
    private String status;

    @Column(name = "order")
    private String order;

    @Column(name = "top_check")
    private Integer top_check;

    @Builder
    public OrgEntity(Long oid, String codes, String name, String eng_name, String upper_codes, String upper_name, String status, String order, Integer top_check) {
        this.oid = oid;
        this.codes = codes;
        this.name = name;
        this.eng_name = eng_name;
        this.upper_codes = upper_codes;
        this.upper_name = upper_name;
        this.status = status;
        this.order = order;
        this.top_check = top_check;
    }
}