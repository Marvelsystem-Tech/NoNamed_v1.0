package jiho.marvel01.dto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
public class InspectDetailDto{

    private Long idid;
    private Long rid;
    private Long oid;
    private Timestamp date;
    private String result;
    private String status;

    @Builder
    public InspectDetailDto(Long idid, Long rid, Long oid, Timestamp date, String result, String status) {
        this.idid   = idid;
        this.rid    = rid;
        this.oid    = oid;
        this.date   = date;
        this.result = result;
        this.status = status;
    }

}
