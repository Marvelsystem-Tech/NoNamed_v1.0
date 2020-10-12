package jiho.marvel01.domain.Entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ConfluenceGroup")
public class ConfluenceGroupEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cgid;

    public ConfluenceGroupEntity() {}

    @Column(name = "objects")
    private String objects;

    @Column(name = "cgName")
    private String cgName;

    @Builder
    public ConfluenceGroupEntity(Long cgid, String objects, String cgName) {
        this.cgid    = cgid;
        this.objects = objects;
        this.cgName  = cgName;
    }

}