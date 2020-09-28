package jiho.marvel01.domain.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "Manage")
public class ManageEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    public ManageEntity() {}

    @Column(name = "iid")
    private Long iid;

    @Column(name = "rid")
    private Long rid;

    @Column(name = "oid")
    private Long oid;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @Column(name = "aid")
    private Long aid;

    @Builder
    public ManageEntity(Long mid, Long iid, Long rid, Long oid, String type, Timestamp date, String comment, String status, Long aid) {
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
