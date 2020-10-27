package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.InspectResultEntity;
import jiho.marvel01.domain.Repository.InspectResultRepository;
import jiho.marvel01.dto.InspectResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class InspectResultService {
    private InspectResultRepository inspectResultRepository;

    @Transactional
    public Long saveInspectResult(InspectResultDto inspectResultDto) {
        return inspectResultRepository.save(inspectResultDto.toEntity()).getIid();
    }

    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertTestInspectResult(InspectResultDto inspectResultDto) {
        for(long i=0; i<5; i++) {
            InspectResultEntity inspectresultEntity = inspectResultDto.toEntity();
            inspectresultEntity.setRid(i);
            inspectresultEntity.setIid(i);
            inspectresultEntity.setStatus("wating");
            inspectResultRepository.save(inspectresultEntity).getRid();
        }
    }

}
