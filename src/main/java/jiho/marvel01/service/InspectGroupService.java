package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.InspectGroupEntity;
import jiho.marvel01.domain.Repository.InspectGroupRepository;
import jiho.marvel01.dto.InspectGroupDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InspectGroupService {
    private InspectGroupRepository inspectGroupRepository;

    @Transactional
    public Long saveInspectGroup(InspectGroupDto inspectGroupDto) {
        return inspectGroupRepository.save(inspectGroupDto.toEntity()).getIid();
    }
    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertTestInspectGroup(InspectGroupDto inspectGroupDto) {
        for(long i=0; i<5; i++) {
            InspectGroupEntity inspectgroupEntity = inspectGroupDto.toEntity();
            inspectgroupEntity.setIid(i);
            inspectgroupEntity.setIgName("테스트그룹" + i);
            inspectgroupEntity.setObjects("1, 2, 3, 4, 5");
            inspectgroupEntity.setManager("킴지호");
            inspectGroupRepository.save(inspectgroupEntity).getIgid();
        }
    }
    @Transactional
    public void insertInspectGroup(InspectGroupDto inspectGroupDto, Map<String, String> infos, Long iid) {
        InspectGroupEntity inspectgroupEntity = inspectGroupDto.toEntity();
        inspectgroupEntity.setIid(iid);
        inspectgroupEntity.setIgName(infos.get("igName"));
        inspectgroupEntity.setObjects(infos.get("objects"));
        inspectgroupEntity.setManager(infos.get("manager"));
        inspectGroupRepository.save(inspectgroupEntity).getIgid();
    }

    @Transactional
    public List<InspectGroupDto> getInspectGrouplist() {
        List<InspectGroupEntity> inspectgroupEntities = inspectGroupRepository.findAll();
        List<InspectGroupDto> inspectgroupDtoList = new ArrayList<>();

        for(InspectGroupEntity inspectEntity : inspectgroupEntities) {
            InspectGroupDto inspectDTO = InspectGroupDto.builder()
                    .igid(inspectEntity.getIgid())
                    .iid(inspectEntity.getIid())
                    .igName(inspectEntity.getIgName())
                    .manager(inspectEntity.getManager())
                    .build();

            inspectgroupDtoList.add(inspectDTO);
        }
        return inspectgroupDtoList;
    }

    @Transactional
    public InspectGroupDto getInspectGroup(Long igid) {
        Optional<InspectGroupEntity> inspectGroupEntityWrapper = inspectGroupRepository.findById(igid);
        InspectGroupEntity inspectGroupEntity = inspectGroupEntityWrapper.get();

        InspectGroupDto inspectGroupDto = InspectGroupDto.builder()
                .igid(inspectGroupEntity.getIgid())
                .iid(inspectGroupEntity.getIid())
                .igName(inspectGroupEntity.getIgName())
                .manager(inspectGroupEntity.getManager())
                .build();

        return inspectGroupDto;
    }

}
