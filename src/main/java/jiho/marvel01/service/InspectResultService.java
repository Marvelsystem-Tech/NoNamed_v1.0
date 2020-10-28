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

}
