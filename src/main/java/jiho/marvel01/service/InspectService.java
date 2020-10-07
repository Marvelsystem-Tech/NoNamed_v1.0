package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.InspectEntity;
import jiho.marvel01.domain.Repository.InspectRepository;
import jiho.marvel01.dto.InspectDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;

@AllArgsConstructor
@Service
public class InspectService {
    private InspectRepository inspectRepository;

    @Transactional
    public Long saveInspect(InspectDto inspectDto) {
        return inspectRepository.save(inspectDto.toEntity()).getIid();
    }

    @Transactional
    public void insertInspect(InspectDto inspectDto, Map<String, String> infos, Integer igid, Date idate, Date idateEx, Date mdate, Date mdateEx) {
        InspectEntity inspectEntity = inspectDto.toEntity();
        inspectEntity.setIgid(igid);
        inspectEntity.setType(infos.get("type"));
        inspectEntity.setIdate(idate);
        inspectEntity.setIdateEx(idateEx);
        inspectEntity.setContent(infos.get("content"));
        inspectEntity.setMdate(mdate);
        inspectEntity.setMdateEx(mdateEx);
        inspectEntity.setStatus(infos.get("status"));
        inspectRepository.save(inspectEntity).getIid();
    }

    @Transactional
    public void updateInspect(Long iid, Map<String, String> infos, Integer igid, Date idate, Date idateEx, Date mdate, Date mdateEx) {
        Optional<InspectEntity> inspectEntityWrapper = inspectRepository.findById(iid);
        InspectEntity inspectEntity = inspectEntityWrapper.get();
        inspectEntity.setIid(iid);
        inspectEntity.setIgid(igid);
        inspectEntity.setType(infos.get("type"));
        inspectEntity.setIdate(idate);
        inspectEntity.setIdateEx(idateEx);
        inspectEntity.setContent(infos.get("content"));
        inspectEntity.setMdate(mdate);
        inspectEntity.setMdateEx(mdateEx);
        inspectEntity.setStatus(infos.get("status"));
        inspectRepository.save(inspectEntity);
    }

    @Transactional
    public List<InspectDto> getInspectlist() {
        List<InspectEntity> inspectEntities = inspectRepository.findAll();
        List<InspectDto> inspectDtoList = new ArrayList<>();

        for(InspectEntity inspectEntity : inspectEntities) {
            InspectDto inspectDTO = InspectDto.builder()
                    .iid(inspectEntity.getIid())
                    .igid(inspectEntity.getIgid())
                    .type(inspectEntity.getType())
                    .idate(inspectEntity.getIdate())
                    .content(inspectEntity.getContent())
                    .mdate(inspectEntity.getMdate())
                    .status(inspectEntity.getStatus())
                    .build();

            inspectDtoList.add(inspectDTO);
        }
        return inspectDtoList;
    }

    @Transactional
    public List<InspectDto> getInspectlistbyId(List<Long> iids) {
        List<InspectEntity> inspectEntities = inspectRepository.findAllById(iids);
        List<InspectDto> inspectDtoList = new ArrayList<>();

        for(InspectEntity inspectEntity : inspectEntities) {
            InspectDto inspectDTO = InspectDto.builder()
                    .iid(inspectEntity.getIid())
                    .igid(inspectEntity.getIgid())
                    .type(inspectEntity.getType())
                    .idate(inspectEntity.getIdate())
                    .content(inspectEntity.getContent())
                    .mdate(inspectEntity.getMdate())
                    .status(inspectEntity.getStatus())
                    .build();

            inspectDtoList.add(inspectDTO);
        }
        return inspectDtoList;
    }

    @Transactional
    public InspectDto getInspect(Long iid) {
        Optional<InspectEntity> inspectEntityWrapper = inspectRepository.findById(iid);
        InspectEntity inspectEntity = inspectEntityWrapper.get();

        InspectDto inspectDTO = InspectDto.builder()
                .iid(inspectEntity.getIid())
                .igid(inspectEntity.getIgid())
                .type(inspectEntity.getType())
                .idate(inspectEntity.getIdate())
                .content(inspectEntity.getContent())
                .mdate(inspectEntity.getMdate())
                .status(inspectEntity.getStatus())
                .build();

        return inspectDTO;
    }

    @Transactional
    public void deleteInspect(Long iid) {
        inspectRepository.deleteById(iid);
    }

    @Transactional //idx 기준으로 조회하여 리스팅 후 유저 삭제
    public void deleteInspects(List<Long> iid) {
        List<InspectEntity> inspectEntities = inspectRepository.findAllById(iid);
        List<InspectDto> inspectDtoList = new ArrayList<>();

        for(InspectEntity inspectEntity : inspectEntities) {
            inspectRepository.deleteById(inspectEntity.getIid());
        }
    }

}
