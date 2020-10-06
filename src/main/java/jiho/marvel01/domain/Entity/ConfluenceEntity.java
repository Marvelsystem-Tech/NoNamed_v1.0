package jiho.marvel01.domain.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "confluence")
public class ConfluenceEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    public ConfluenceEntity() {}

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private String category;

    @Column(name = "risk")
    private String risk;

    @Column(name = "demand")
    private String demand;

    @Column(name = "comply")
    private String comply;

    @Column(name = "manual")
    private String manual;

    @Column(name = "mid")
    private Long mid;

    @Builder
    public ConfluenceEntity(Long cid, String type, String name , String content , String category , String risk , String demand , String comply , String manual, Long mid) {
        this.cid       = cid;
        this.type      = type;
        this.name      = name;
        this.content   = content;
        this.category  = category;
        this.risk      = risk;
        this.demand    = demand;
        this.comply    = comply;
        this.manual    = manual;
        this.mid       = mid;
    }

}