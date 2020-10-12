package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.ConfluenceGroupEntity;
import jiho.marvel01.domain.Repository.ConfluenceGroupRepository;
import jiho.marvel01.dto.ConfluenceGroupDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConfluenceGroupService {
    private ConfluenceGroupRepository confluenceGroupRepository;

    @Transactional
    public Long saveConfluenceGroup(ConfluenceGroupDto confluenceGroupDto) {
        return confluenceGroupRepository.save(confluenceGroupDto.toEntity()).getCgid();
    }
    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertTestConfluenceGroup(ConfluenceGroupDto confluenceGroupDto) {
        for(long i=0; i<5; i++) {
            ConfluenceGroupEntity confluencegroupEntity = confluenceGroupDto.toEntity();
            confluencegroupEntity.setCgName("테스트그룹" + i);
            confluencegroupEntity.setObjects("1, 2, 3, 4, 5");
            confluenceGroupRepository.save(confluencegroupEntity).getCgid();
        }
    }
    @Transactional
    public void insertConfluenceGroup(ConfluenceGroupDto confluenceGroupDto, Map<String, String> infos) {
        ConfluenceGroupEntity confluencegroupEntity = confluenceGroupDto.toEntity();
        confluencegroupEntity.setCgName(infos.get("igName"));
        confluencegroupEntity.setObjects(infos.get("objects"));
        confluenceGroupRepository.save(confluencegroupEntity).getCgid();
    }

    @Transactional
    public List<ConfluenceGroupDto> getConfluenceGrouplist() {
        List<ConfluenceGroupEntity> confluencegroupEntities = confluenceGroupRepository.findAll();
        List<ConfluenceGroupDto> confluencegroupDtoList = new ArrayList<>();

        for(ConfluenceGroupEntity confluenceEntity : confluencegroupEntities) {
            ConfluenceGroupDto confluenceDTO = ConfluenceGroupDto.builder()
                    .cgid(confluenceEntity.getCgid())
                    .cgName(confluenceEntity.getCgName())
                    .objects(confluenceEntity.getObjects())
                    .build();

            confluencegroupDtoList.add(confluenceDTO);
        }
        return confluencegroupDtoList;
    }

    @Transactional
    public ConfluenceGroupDto getConfluenceGroup(Long cgid) {
        Optional<ConfluenceGroupEntity> confluenceGroupEntityWrapper = confluenceGroupRepository.findById(cgid);
        ConfluenceGroupEntity confluenceGroupEntity = confluenceGroupEntityWrapper.get();

        ConfluenceGroupDto confluenceGroupDto = ConfluenceGroupDto.builder()
                .cgid(confluenceGroupEntity.getCgid())
                .cgName(confluenceGroupEntity.getCgName())
                .objects(confluenceGroupEntity.getObjects())
                .build();

        return confluenceGroupDto;
    }

}
