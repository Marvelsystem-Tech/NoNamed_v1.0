package jiho.marvel01.dto;

import jiho.marvel01.domain.Entity.OrgEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrgDto {
    private Long oid;
    private String codes;
    private String name;
    private String eng_name;
    private String upper_codes;
    private String upper_name;
    private String status;
    private String order;
    private Integer top_check;


    public OrgEntity toEntity() {
        OrgEntity orgEntity = OrgEntity.builder()
                .oid(oid)
                .codes(codes)
                .name(name)
                .eng_name(eng_name)
                .upper_codes(upper_codes)
                .upper_name(upper_name)
                .status(status)
                .order(order)
                .top_check(top_check)
                .build();
        return orgEntity;
    }

    @Builder
    public OrgDto(Long oid, String codes, String name, String eng_name, String upper_codes, String upper_name, String status, String order, Integer top_check) {
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

