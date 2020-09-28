package jiho.marvel01.dto;

import jiho.marvel01.domain.Entity.InspectResultEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
public class InspectResultDto  {

    private Long rid;
    private Long iid;
    private Timestamp STdate;
    private Timestamp EndDate;
    private String status;


    public InspectResultEntity toEntity() {
        InspectResultEntity inspectResultEntity = InspectResultEntity.builder()
                .rid(rid)
                .iid(iid)
                .STdate(STdate)
                .EndDate(EndDate)
                .status(status)
                .build();
        return inspectResultEntity;
    }

    @Builder
    public InspectResultDto(Long rid, Long iid, Timestamp STdate, Timestamp EndDate, String status) {
        this.rid     = rid;
        this.iid     = iid;
        this.STdate  = STdate;
        this.EndDate = EndDate;
        this.status  = status;
    }

}
