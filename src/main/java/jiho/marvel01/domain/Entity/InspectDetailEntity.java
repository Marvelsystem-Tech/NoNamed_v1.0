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
    private Long rid;

    @Column(name = "oid")
    private Long oid;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "result")
    private String result;

    @Column(name = "status")
    private String status;

    @Builder
    public InspectDetailEntity(Long idid, Long rid, Long oid, Timestamp date, String result, String status) {
        this.idid   = idid;
        this.rid    = rid;
        this.oid    = oid;
        this.date   = date;
        this.result = result;
        this.status = status;
    }

}
