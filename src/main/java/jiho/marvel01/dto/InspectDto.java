package jiho.marvel01.dto;

import jiho.marvel01.domain.Entity.InspectEntity;
import lombok.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InspectDto {
    private Long iid;
    private Integer igid;
    private String iName;
    private String type;
    private Date idate;
    private String content;
    private Date mdate;
    private String status;

    public InspectEntity toEntity() {
        InspectEntity inspectEntity = InspectEntity.builder()
                .iid(iid)
                .igid(igid)
                .iName(iName)
                .type(type)
                .idate(idate)
                .content(content)
                .mdate(mdate)
                .status(status)
                .build();
        return inspectEntity;
    }

    @Builder
    public InspectDto(Long iid, Integer igid, String iName, String type, Date idate, String content, Date mdate, String status) {
        this.iid        = iid;
        this.igid       = igid;
        this.iName      = iName;
        this.type       = type;
        this.idate      = idate;
        this.content    = content;
        this.mdate      = mdate;
        this.status     = status;
    }
}
