package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.ManageEntity;
import jiho.marvel01.domain.Repository.ManageRepository;
import jiho.marvel01.dto.ManageDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@AllArgsConstructor
@Service
public class ManageService {
    private ManageRepository manageRepository;

    @Transactional
    public Long saveManage(ManageDto measureDto) {
        return manageRepository.save(measureDto.toEntity()).getIid();
    }

    @Transactional
    public void insertManage(ManageDto measureDto, Map<String, String> infos, Long iid, Long rid, Long igid, Timestamp date, Long aid) {
        ManageEntity manageEntity = measureDto.toEntity();
        manageEntity.setIid(iid);
        manageEntity.setRid(rid);
        manageEntity.setIgid(igid);
        manageEntity.setType(infos.get("type"));
        manageEntity.setDate(date);
        manageEntity.setComment(infos.get("comment"));
        manageEntity.setStatus(infos.get("status"));
        manageEntity.setAid(aid);
        manageRepository.save(manageEntity).getMid();
    }

    @Transactional
    public void updateManage(Map<String, String> infos, Long mid, Long iid, Long rid, Long igid, Timestamp date, Long aid) {
        Optional<ManageEntity> manageEntityWrapper = manageRepository.findById(iid);
        ManageEntity manageEntity = manageEntityWrapper.get();
        manageEntity.setMid(mid);
        manageEntity.setIid(iid);
        manageEntity.setRid(rid);
        manageEntity.setIgid(igid);
        manageEntity.setType(infos.get("type"));
        manageEntity.setDate(date);
        manageEntity.setComment(infos.get("comment"));
        manageEntity.setStatus(infos.get("status"));
        manageEntity.setAid(aid);
        manageRepository.save(manageEntity);
    }

    @Transactional
    public List<ManageDto> getManagelist() {
        List<ManageEntity> manageEntities = manageRepository.findAll();
        List<ManageDto> measureDtoList = new ArrayList<>();

        for(ManageEntity manageEntity : manageEntities) {
            ManageDto manageDTO = ManageDto.builder()
                    .mid(manageEntity.getMid())
                    .iid(manageEntity.getIid())
                    .rid(manageEntity.getRid())
                    .igid(manageEntity.getIgid())
                    .type(manageEntity.getType())
                    .date(manageEntity.getDate())
                    .comment(manageEntity.getComment())
                    .status(manageEntity.getStatus())
                    .aid(manageEntity.getAid())
                    .build();

            measureDtoList.add(manageDTO);
        }
        return measureDtoList;
    }

    @Transactional
    public List<ManageDto> getManagelistbyId(List<Long> mid) {
        List<ManageEntity> manageEntities = manageRepository.findAllById(mid);
        List<ManageDto> measureDtoList = new ArrayList<>();

        for(ManageEntity manageEntity : manageEntities) {
            ManageDto manageDTO = ManageDto.builder()
                    .mid(manageEntity.getMid())
                    .iid(manageEntity.getIid())
                    .rid(manageEntity.getRid())
                    .igid(manageEntity.getIgid())
                    .type(manageEntity.getType())
                    .date(manageEntity.getDate())
                    .comment(manageEntity.getComment())
                    .status(manageEntity.getStatus())
                    .aid(manageEntity.getAid())
                    .build();

            measureDtoList.add(manageDTO);
        }
        return measureDtoList;
    }

    @Transactional
    public ManageDto getManage(Long mid) {
        Optional<ManageEntity> manageEntityWrapper = manageRepository.findById(mid);
        ManageEntity manageEntity = manageEntityWrapper.get();

        ManageDto measureDto = ManageDto.builder()
                .mid(manageEntity.getMid())
                    .iid(manageEntity.getIid())
                    .rid(manageEntity.getRid())
                    .igid(manageEntity.getIgid())
                    .type(manageEntity.getType())
                    .date(manageEntity.getDate())
                    .comment(manageEntity.getComment())
                    .status(manageEntity.getStatus())
                    .aid(manageEntity.getAid())
                .build();

        return measureDto;
    }

    @Transactional
    public void deleteManage(Long mid) {
        manageRepository.deleteById(mid);
    }

    @Transactional //idx 기준으로 조회하여 리스팅 후 유저 삭제
    public void deleteManages(List<Long> mid) {
        List<ManageEntity> manageEntities = manageRepository.findAllById(mid);
        List<ManageDto> measureDtoList = new ArrayList<>();

        for(ManageEntity manageEntity : manageEntities) {
            manageRepository.deleteById(manageEntity.getMid());
        }
    }
}
