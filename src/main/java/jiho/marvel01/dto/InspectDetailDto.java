package jiho.marvel01.dto;
import jiho.marvel01.domain.Entity.InspectDetailEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InspectDetailDto{

    private Long idid;
    private Integer rid;
    private Integer oid;
    private Integer cid;
    private Timestamp date;
    private String result;
    private String status;

    public InspectDetailEntity toEntity() {
        InspectDetailEntity inspectDetailEntity = InspectDetailEntity.builder()
                .idid(idid)
                .rid(rid)
                .oid(oid)
                .cid(cid)
                .date(date)
                .result(result)
                .status(status)
                .build();
        return inspectDetailEntity;
    }

    @Builder
    public InspectDetailDto(Long idid, Integer rid, Integer oid, Integer cid, Timestamp date, String result, String status) {
        this.idid   = idid;
        this.rid    = rid;
        this.oid    = oid;
        this.cid    = cid;
        this.date   = date;
        this.result = result;
        this.status = status;
    }

}
