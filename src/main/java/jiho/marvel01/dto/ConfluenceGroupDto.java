package jiho.marvel01.dto;

import jiho.marvel01.domain.Entity.ConfluenceGroupEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ConfluenceGroupDto {

    private Long cgid;
    private String objects;
    private String cgName;

    public ConfluenceGroupEntity toEntity() {
        ConfluenceGroupEntity confluencegroupEntity = ConfluenceGroupEntity.builder()
                .cgid(cgid)
                .objects(objects)
                .cgName(cgName)
                .build();
        return confluencegroupEntity;
    }

    @Builder
    public ConfluenceGroupDto(Long cgid, String objects, String cgName) {
        this.cgid    = cgid;
        this.objects = objects;
        this.cgName  = cgName;
    }

}
