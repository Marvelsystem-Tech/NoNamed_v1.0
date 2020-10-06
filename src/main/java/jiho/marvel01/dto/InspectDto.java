package jiho.marvel01.dto;

import jiho.marvel01.domain.Entity.InspectEntity;
import lombok.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InspectDto {
    private Long iid;
    private Integer igid;
    private String type;
    private Timestamp idate;
    private String content;
    private Timestamp mdate;
    private String status;

    public InspectEntity toEntity() {
        InspectEntity inspectEntity = InspectEntity.builder()
                .iid(iid)
                .igid(igid)
                .type(type)
                .idate(idate)
                .content(content)
                .mdate(mdate)
                .status(status)
                .build();
        return inspectEntity;
    }

    @Builder
    public InspectDto(Long iid, Integer igid , String type , Timestamp idate , String content , Timestamp mdate , String status) {
        this.iid        = iid;
        this.igid       = igid;
        this.type       = type;
        this.idate      = idate;
        this.content    = content;
        this.mdate      = mdate;
        this.status     = status;
    }
}