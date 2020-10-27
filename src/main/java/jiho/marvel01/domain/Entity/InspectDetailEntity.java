package jiho.marvel01.domain.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "InspectDetail")
public class InspectDetailEntity extends TimeEntity {

    public InspectDetailEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idid;

    @Column(name = "rid")
    private Integer rid;

    @Column(name = "oid")
    private Integer oid;

    @Column(name = "cid")
    private Integer cid;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "result")
    private String result;

    @Column(name = "status")
    private String status;

    @Builder
    public InspectDetailEntity(Long idid, Integer rid, Integer oid, Integer cid, Timestamp date, String result, String status) {
        this.idid   = idid;
        this.rid    = rid;
        this.oid    = oid;
        this.cid    = cid;
        this.date   = date;
        this.result = result;
        this.status = status;
    }

}
