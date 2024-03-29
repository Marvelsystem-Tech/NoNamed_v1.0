package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.ObjectEntity;
import jiho.marvel01.domain.Repository.ObjectRepository;
import jiho.marvel01.dto.ObjectDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
public class ObjectService {
    private ObjectRepository objectRepository;

    @Transactional
    public Long saveObject(ObjectDto objectDto) {
        return objectRepository.save(objectDto.toEntity()).getOid();
    }

    @Transactional
    public void insertObject(ObjectDto objectDto, Map<String, String> infos) {
            ObjectEntity objectEntity = objectDto.toEntity();
            objectEntity.setType(infos.get("type"));
            objectEntity.setOName(infos.get("oName"));
            objectEntity.setPurpose(infos.get("purpose"));
            objectEntity.setOGroup(infos.get("oGroup"));
            objectEntity.setPart1(infos.get("part1"));
            objectEntity.setPart2(infos.get("part2"));
            objectEntity.setIP(infos.get("IP"));
            objectEntity.setLocation(infos.get("location"));
            objectRepository.save(objectEntity).getOid();
    }

    @Transactional
    public void insertMultiObjects(List<Map<String, String>> datas) {
        ObjectDto objectDto = new ObjectDto();
        for(int idx = 0; idx < datas.size(); idx++) {
            ObjectEntity objectEntity = objectDto.toEntity();
            objectEntity.setType(datas.get(idx).get("type"));
            objectEntity.setOName(datas.get(idx).get("oName"));
            objectEntity.setPurpose(datas.get(idx).get("purpose"));
            objectEntity.setOGroup(datas.get(idx).get("oGroup"));
            objectEntity.setPart1(datas.get(idx).get("part1"));
            objectEntity.setPart2(datas.get(idx).get("part2"));
            objectEntity.setIP(datas.get(idx).get("IP"));
            objectEntity.setLocation(datas.get(idx).get("location"));
            objectRepository.save(objectEntity).getOid();
        }
    }

    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertTestObject(ObjectDto objectDto) {
        for(int i=0; i<21; i++) {
            ObjectEntity objectEntity = objectDto.toEntity();
            objectEntity.setType("테스트"+i);
            objectEntity.setOName("테스트"+i);
            objectEntity.setPurpose("테스트"+i);
            objectEntity.setOGroup("테스트"+i);
            objectEntity.setPart1("테스트"+i);
            objectEntity.setPart2("테스트"+i);
            objectEntity.setIP("테스트"+i);
            objectEntity.setLocation("테스트"+i);
            objectRepository.save(objectEntity).getOid();
        }
    }

    @Transactional
    public void updateObject(Long oid, Map<String, String> infos) {
        Optional<ObjectEntity> objectEntityWrapper = objectRepository.findById(oid);
        ObjectEntity objectEntity = objectEntityWrapper.get();
        objectEntity.setOid(oid);
        objectEntity.setType(infos.get("type"));
        objectEntity.setOName(infos.get("oName"));
        objectEntity.setPurpose(infos.get("purpose"));
        objectEntity.setOGroup(infos.get("oGroup"));
        objectEntity.setPart1(infos.get("part1"));
        objectEntity.setPart2(infos.get("part2"));
        objectEntity.setIP(infos.get("IP"));
        objectEntity.setLocation(infos.get("location"));
        objectRepository.save(objectEntity);
    }

    @Transactional
    public List<ObjectDto> getObjectlist() {
        List<ObjectEntity> objectEntities = objectRepository.findAll();
        List<ObjectDto> objectDtoList = new ArrayList<>();

        for(ObjectEntity objectEntity : objectEntities) {
            ObjectDto objectDTO = ObjectDto.builder()
                    .oid(objectEntity.getOid())
                    .type(objectEntity.getType())
                    .oName(objectEntity.getOName())
                    .purpose(objectEntity.getPurpose())
                    .oGroup(objectEntity.getOGroup())
                    .part1(objectEntity.getPart1())
                    .part2(objectEntity.getPart2())
                    .IP(objectEntity.getIP())
                    .location(objectEntity.getLocation())
                    .build();

            objectDtoList.add(objectDTO);
        }
        return objectDtoList;
    }

    @Transactional
    public List<ObjectDto> getObjectlistbyId(List<Long> oids) {
        List<ObjectEntity> objectEntities = objectRepository.findAllById(oids);
        List<ObjectDto> objectDtoList = new ArrayList<>();

        for(ObjectEntity objectEntity : objectEntities) {
            ObjectDto objectDTO = ObjectDto.builder()
                    .oid(objectEntity.getOid())
                    .type(objectEntity.getType())
                    .oName(objectEntity.getOName())
                    .purpose(objectEntity.getPurpose())
                    .oGroup(objectEntity.getOGroup())
                    .part1(objectEntity.getPart1())
                    .part2(objectEntity.getPart2())
                    .IP(objectEntity.getIP())
                    .location(objectEntity.getLocation())
                    .build();

            objectDtoList.add(objectDTO);
        }
        return objectDtoList;
    }

    @Transactional
    public ObjectDto getObject(Long oid) {
        Optional<ObjectEntity> objectEntityWrapper = objectRepository.findById(oid);
        ObjectEntity objectEntity = objectEntityWrapper.get();

        ObjectDto objectDTO = ObjectDto.builder()
                .oid(objectEntity.getOid())
                .type(objectEntity.getType())
                .oName(objectEntity.getOName())
                .purpose(objectEntity.getPurpose())
                .oGroup(objectEntity.getOGroup())
                .part1(objectEntity.getPart1())
                .part2(objectEntity.getPart2())
                .IP(objectEntity.getIP())
                .location(objectEntity.getLocation())
                .build();

        return objectDTO;
    }

    @Transactional
    public void deleteObject(Long oid) {
        objectRepository.deleteById(oid);
    }

    @Transactional //idx 기준으로 조회하여 리스팅 후 유저 삭제
    public void deleteObjects(List<Long> oid) {
        List<ObjectEntity> objectEntities = objectRepository.findAllById(oid);
        List<ObjectDto> objectDtoList = new ArrayList<>();

        for(ObjectEntity objectEntity : objectEntities) {
            objectRepository.deleteById(objectEntity.getOid());
        }
    }

}
