package jiho.marvel01.dto;

import jiho.marvel01.domain.Entity.ManageEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor

public class ManageDto{

    private Long mid;
    private Long iid;
    private Long rid;
    private Long oid;
    private String type;
    private Timestamp date;
    private String comment;
    private String status;
    private Long aid;

    public ManageEntity toEntity() {
        ManageEntity manageEntity = ManageEntity.builder()
                .mid(mid)
                .iid(iid)
                .rid(rid)
                .oid(oid)
                .type(type)
                .date(date)
                .comment(comment)
                .status(status)
                .aid(aid)
                .build();
        return manageEntity;
    }

    @Builder
    public ManageDto(Long mid, Long iid, Long rid, Long oid, String type, Timestamp date, String comment, String status, Long aid) {
        this.mid = mid;
        this.iid = iid;
        this.rid = rid;
        this.oid = oid;
        this.type = type;
        this.date = date;
        this.comment = comment;
        this.status  = status;
        this.aid  = aid;
    }

}
