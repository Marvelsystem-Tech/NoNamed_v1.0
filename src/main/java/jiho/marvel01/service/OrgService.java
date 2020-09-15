package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.OrgEntity;
import jiho.marvel01.domain.Repository.OrgRepository;
import jiho.marvel01.dto.OrgDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrgService {
    private OrgRepository orgRepository;

    @Transactional
    public Long save(OrgDto orgDto) {
        return orgRepository.save(orgDto.toEntity()).getOid();
    }

    @Transactional
    public void update(Long uid, Map<String, String> infos, Integer i_group) {
        Optional<OrgEntity> orgEntityWrapper = orgRepository.findById(uid);
        OrgEntity orgEntity = orgEntityWrapper.get();
        orgEntity.setName(infos.get("name"));
        orgEntity.setCodes(infos.get("codes"));
        orgEntity.setStatus(infos.get("status"));
        orgRepository.save(orgEntity);
    }

    @Transactional
    public List<OrgDto> getList() {
        List<OrgEntity> orgEntities = orgRepository.findAll();
        List<OrgDto> orgDtoList = new ArrayList<>();

        for(OrgEntity orgEntity : orgEntities) {
            OrgDto orgDTO = OrgDto.builder()
                    .name(orgEntity.getName())
                    .codes(orgEntity.getCodes())
                    .status(orgEntity.getStatus())
                    .build();

            orgDtoList.add(orgDTO);
        }
        return orgDtoList;
    }

    @Transactional
    public List<OrgDto> getListbyId(List<Long> uids) {
        List<OrgEntity> orgEntities = orgRepository.findAllById(uids);
        List<OrgDto> orgDtoList = new ArrayList<>();

        for(OrgEntity orgEntity : orgEntities) {
            OrgDto orgDTO = OrgDto.builder()
                    .name(orgEntity.getName())
                    .codes(orgEntity.getCodes())
                    .status(orgEntity.getStatus())
                    .build();

            orgDtoList.add(orgDTO);
        }
        return orgDtoList;
    }

    @Transactional
    public OrgDto getOne(Long uid) {
        Optional<OrgEntity> orgEntityWrapper = orgRepository.findById(uid);
        OrgEntity orgEntity = orgEntityWrapper.get();

        OrgDto orgDTO = OrgDto.builder()
                .name(orgEntity.getName())
                .codes(orgEntity.getCodes())
                .status(orgEntity.getStatus())
                .build();

        return orgDTO;
    }

//    @Transactional
//    public void delete(Long uid) {
//        orgRepository.deleteById(uid);
//    }

    @Transactional //idx 기준으로 조회하여 리스팅 후 유저 삭제
    public void delete(List<Long> uid) {
        List<OrgEntity> orgEntities = orgRepository.findAllById(uid);
        List<OrgDto> orgDtoList = new ArrayList<>();

        for(OrgEntity orgEntity : orgEntities) {
            orgRepository.deleteById(orgEntity.getOid());
        }
    }

}