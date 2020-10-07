package jiho.marvel01.domain.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "Inspect")
public class InspectEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    public InspectEntity() {}

    @Column(name = "igid")
    private Integer igid;

    @Column(name = "iName")
    private String iName;

    @Column(name = "type")
    private String type;

    @Column(name = "idate")
    private Date idate;

    @Column(name = "content")
    private String content;

    @Column(name = "mdate")
    private Date mdate;

    @Column(name = "status")
    private String status;

    @Builder
    public InspectEntity(Long iid, Integer igid, String iName, String type, Date idate, String content, Date mdate, String status) {
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
