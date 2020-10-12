package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.ConfluenceEntity;
import jiho.marvel01.domain.Repository.ConfluenceRepository;
import jiho.marvel01.dto.ConfluenceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
public class ConfluenceService {
    private ConfluenceRepository confluenceRepository;

    @Transactional
    public Long saveConf(ConfluenceDto confluenceDto) {
        return confluenceRepository.save(confluenceDto.toEntity()).getCid();
    }

    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertTestConf(ConfluenceDto confluenceDto) {
        for(int i=0; i<21; i++) {
            ConfluenceEntity confluenceEntity = confluenceDto.toEntity();
            confluenceEntity.setType("보안관리"+i);
            confluenceEntity.setConfCategory("분류?"+i);
            confluenceEntity.setCName("항목명은 이렇게 여기에 적는거징"+i);
            confluenceEntity.setRisk("high"+i);
            confluenceEntity.setDemand("테스트"+i);
            confluenceEntity.setComply("준수기준준수기준준수기준준수기준준수기준준수기준준수기준"+i);
            confluenceEntity.setContent("내용내용내용내용내용내용내용내용내용내용내용내용"+i);
            confluenceEntity.setManual("메뉴얼메뉴얼메뉴얼메뉴얼메뉴얼메뉴얼메뉴얼메뉴얼"+i);
            confluenceRepository.save(confluenceEntity).getCid();
        }
    }

    @Transactional
    public void updateConf(Long cid, Map<String, String> infos) {
        Optional<ConfluenceEntity> confluenceEntityWrapper = confluenceRepository.findById(cid);
        ConfluenceEntity confluenceEntity = confluenceEntityWrapper.get();
        confluenceEntity.setCid(cid);
        confluenceRepository.save(confluenceEntity);
    }

    @Transactional
    public List<ConfluenceDto> getConfList() {
        List<ConfluenceEntity> confluenceEntities = confluenceRepository.findAll();
        List<ConfluenceDto> confluenceDtoList = new ArrayList<>();

        for(ConfluenceEntity confluenceEntity : confluenceEntities) {
            ConfluenceDto objectDTO = ConfluenceDto.builder()
                    .cid(confluenceEntity.getCid())
                    .type(confluenceEntity.getType())
                    .cName(confluenceEntity.getCName())
                    .content(confluenceEntity.getContent())
                    .confCategory(confluenceEntity.getConfCategory())
                    .risk(confluenceEntity.getRisk())
                    .demand(confluenceEntity.getDemand())
                    .comply(confluenceEntity.getComply())
                    .manual(confluenceEntity.getManual())
                    .mid(confluenceEntity.getMid())
                    .build();

            confluenceDtoList.add(objectDTO);
        }
        return confluenceDtoList;
    }

    @Transactional
    public List<ConfluenceDto> getConflistbyId(List<Long> oids) {
        List<ConfluenceEntity> confluenceEntities = confluenceRepository.findAllById(oids);
        List<ConfluenceDto> confluenceDtoList = new ArrayList<>();

        for(ConfluenceEntity confluenceEntity : confluenceEntities) {
            ConfluenceDto objectDTO = ConfluenceDto.builder()
                    .build();

            confluenceDtoList.add(objectDTO);
        }
        return confluenceDtoList;
    }

    @Transactional
    public ConfluenceDto getConf(Long oid) {
        Optional<ConfluenceEntity> confluenceEntityWrapper = confluenceRepository.findById(oid);
        ConfluenceEntity confluenceEntity = confluenceEntityWrapper.get();

        ConfluenceDto objectDTO = ConfluenceDto.builder()
                .build();

        return objectDTO;
    }

    @Transactional
    public void deleteConf(Long oid) {
        confluenceRepository.deleteById(oid);
    }

    @Transactional //idx 기준으로 조회하여 리스팅 후 유저 삭제
    public void deleteConfs(List<Long> oid) {
        List<ConfluenceEntity> confluenceEntities = confluenceRepository.findAllById(oid);
        List<ConfluenceDto> confluenceDtoList = new ArrayList<>();

        for(ConfluenceEntity confluenceEntity : confluenceEntities) {
            confluenceRepository.deleteById(confluenceEntity.getCid());
        }
    }

}