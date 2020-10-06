package jiho.marvel01.domain.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "InspectResult")
public class InspectResultEntity extends TimeEntity {

    public InspectResultEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @Column(name = "iid")
    private Long iid;

    @Column(name = "STdate")
    private Timestamp STdate;

    @Column(name = "EndDate")
    private Timestamp EndDate;

    @Column(name = "status")
    private String status;

    @Builder
    public InspectResultEntity(Long rid, Long iid, Timestamp STdate, Timestamp EndDate, String status) {
        this.rid     = rid;
        this.iid     = iid;
        this.STdate  = STdate;
        this.EndDate = EndDate;
        this.status  = status;
    }

}