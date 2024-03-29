package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.InspectResultEntity;
import jiho.marvel01.domain.Repository.InspectResultRepository;
import jiho.marvel01.dto.InspectResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
@PersistenceContext
public class InspectResultService {
    private EntityManager em;
    private InspectResultRepository inspectResultRepository;

    @Transactional
    public Integer saveInspectResult(InspectResultDto inspectResultDto) {
        return inspectResultRepository.save(inspectResultDto.toEntity()).getRid();
    }

    @Transactional
    public Integer insertInspectResult(InspectResultDto inspectResultDto,
                                    Map<String, String> infos,
                                    Integer iid,
                                    Integer oid,
                                    Integer cid) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        InspectResultEntity inspectResultEntity = inspectResultDto.toEntity();
        inspectResultEntity.setIid(iid);
        inspectResultEntity.setOid(oid);
        inspectResultEntity.setCid(cid);
        inspectResultEntity.setDate(timestamp);
        inspectResultEntity.setResult(infos.get("result"));
        inspectResultEntity.setStatus(infos.get("status"));
        return inspectResultRepository.save(inspectResultEntity).getRid();
    }

    @Transactional
    public void closeResult(Integer rid) {
        Optional<InspectResultEntity> inspectResultEntityWrapper = inspectResultRepository.findById(rid);
        InspectResultEntity inspectResultEntity = inspectResultEntityWrapper.get();
        inspectResultEntity.setRid(rid);
        inspectResultEntity.setStatus("Y");
        inspectResultRepository.save(inspectResultEntity);
    }

    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertTestInspectResult(InspectResultDto inspectResultDto) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        for(int i=0; i<21; i++) {
            InspectResultEntity inspectResultEntity = inspectResultDto.toEntity();
            inspectResultEntity.setRid(i);
            inspectResultEntity.setIid(1);
            inspectResultEntity.setOid(i);
            inspectResultEntity.setCid(i);
            inspectResultEntity.setDate(timestamp);
            inspectResultEntity.setResult(i+"번째 점검결과는 이렇습니다");
            inspectResultEntity.setStatus("상태"+i);
            inspectResultRepository.save(inspectResultEntity).getOid();
        }
    }

    @Transactional
    public List<InspectResultDto> getInspectResultlist() {
        List<InspectResultEntity> inspectresultEntities = inspectResultRepository.findAll();
        List<InspectResultDto> inspectresultDtoList = new ArrayList<>();

        for(InspectResultEntity inspectresultEntity : inspectresultEntities) {
            InspectResultDto inspectresultDTO = InspectResultDto.builder()
                    .rid(inspectresultEntity.getRid())
                    .iid(inspectresultEntity.getIid())
                    .oid(inspectresultEntity.getOid())
                    .cid(inspectresultEntity.getCid())
                    .date(inspectresultEntity.getDate())
                    .result(inspectresultEntity.getResult())
                    .status(inspectresultEntity.getStatus())
                    .build();

            inspectresultDtoList.add(inspectresultDTO);
        }
        return inspectresultDtoList;
    }

    @Transactional
    public List<Object[]> getTotalResults(Integer iid) {

//        String jpql = "SELECT IE FROM InspectResultEntity IE WHERE IE.iid=:iid";
//        List<InspectResultEntity> query = em.createQuery(jpql, InspectResultEntity.class)
//                .setParameter("iid",iid)
//                .getResultList();
//        Long test = new Long(1);
//        String jpql2 = "SELECT OB FROM ObjectEntity OB WHERE OB.oid=:gege";
//        List<ObjectEntity> query2 = em.createQuery(jpql2, ObjectEntity.class)
//                .setParameter("gege",test)
//                .getResultList();

        String jpql = "SELECT DISTINCT IE, CF, OBJ FROM InspectResultEntity IE, ConfluenceEntity CF, ObjectEntity OBJ WHERE "
                +"CF.cid = IE.cid AND OBJ.oid = IE.oid AND IE.iid = :iid ANd IE.status = :status";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("iid",iid)
                .setParameter("status","N")
                .getResultList();

//        Map<String, Object> mp = new HashMap<String, Object>();
//        mp.put("TotalData", query);

        return totalResult;
    }

    @Transactional
    public List<Object[]> getInspectProgress(Integer iid) {

        String jpql = "SELECT COUNT(IE) FROM InspectResultEntity IE WHERE IE.iid = :iid";
        List<Object[]> donelist = em.createQuery(jpql, Object[].class).setParameter("iid", iid).getResultList();

        return donelist;
    }

    @Transactional
    public List<Object[]> getTotalResultsByRid(Integer rid) {
        String jpql = "SELECT DISTINCT IE, CF, OBJ, ME FROM InspectResultEntity IE, ConfluenceEntity CF, ObjectEntity OBJ, ManageEntity ME WHERE "
                +"CF.cid = IE.cid AND OBJ.oid = IE.oid AND ME.rid = IE.rid AND IE.rid = :rid";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("rid",rid)
                .getResultList();
        return totalResult;
    }

    @Transactional
    public List<Object[]> getTotalDoneResults(Integer iid) {
        String jpql = "SELECT DISTINCT IE, CF, OBJ FROM InspectResultEntity IE, ConfluenceEntity CF, ObjectEntity OBJ WHERE "
                +"CF.cid = IE.cid AND OBJ.oid = IE.oid AND IE.iid = :iid AND IE.status <> :status";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("iid",iid)
                .setParameter("status","N")
                .getResultList();

        return totalResult;
    }

    @Transactional
    public List<Object[]> getResultsbyCat(String type) {
        String jpql = "SELECT DISTINCT COUNT(IE) FROM InspectResultEntity IE, ObjectEntity OBJ WHERE "
                +"OBJ.oid = IE.oid AND OBJ.type = :type";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("type",type)
                .getResultList();

        return totalResult;
    }

    @Transactional
    public List<Object[]> getDoneResultsbyCat(String type) {
        String jpql = "SELECT DISTINCT IE FROM InspectResultEntity IE, ObjectEntity OBJ WHERE "
                +"OBJ.oid = IE.oid AND IE.status = :status AND OBJ.type = :type";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("status","Y")
                .setParameter("type",type)
                .getResultList();

        return totalResult;
    }

    @Transactional
    public List<Object[]> getNoneResultsbyCat(String type) {
        String jpql = "SELECT DISTINCT IE FROM InspectResultEntity IE, ObjectEntity OBJ WHERE "
                +"OBJ.oid = IE.oid AND IE.status = :status AND OBJ.type = :type";
        List<Object[]> totalResult = em.createQuery(jpql, Object[].class)
                .setParameter("status","N")
                .setParameter("type",type)
                .getResultList();

        return totalResult;
    }

    @Transactional
    public List<InspectResultDto> getByIid(Integer iid) {
        List<InspectResultEntity> ListByIid = inspectResultRepository.findByIid(iid);
        List<InspectResultDto> inspectResultDtoList = new ArrayList<>();


        for(InspectResultEntity inspectresultEntity : ListByIid) {
            InspectResultDto inspectresultDTO = InspectResultDto.builder()
                    .rid(inspectresultEntity.getRid())
                    .iid(inspectresultEntity.getIid())
                    .oid(inspectresultEntity.getOid())
                    .cid(inspectresultEntity.getCid())
                    .date(inspectresultEntity.getDate())
                    .result(inspectresultEntity.getResult())
                    .status(inspectresultEntity.getStatus())
                    .build();

            inspectResultDtoList.add(inspectresultDTO);

        }
        return inspectResultDtoList;
    }

    @Transactional
    public InspectResultDto getInspectResult(Integer rid) {
        Optional<InspectResultEntity> inspectResultEntityWrapper = inspectResultRepository.findById(rid);
        InspectResultEntity inspectResultEntity = inspectResultEntityWrapper.get();

        InspectResultDto inspectDTO = InspectResultDto.builder()
                .rid(inspectResultEntity.getRid())
                .iid(inspectResultEntity.getIid())
                .oid(inspectResultEntity.getOid())
                .cid(inspectResultEntity.getCid())
                .date(inspectResultEntity.getDate())
                .result(inspectResultEntity.getResult())
                .status(inspectResultEntity.getStatus())
                .build();

        return inspectDTO;
    }

    @Transactional
    public List<InspectResultDto> getByIidCidOid(Integer iid, Integer cid, Integer oid) {
        List<InspectResultEntity> ListByIid = inspectResultRepository.findByIidAndCidAndOid(iid, cid, oid);
        List<InspectResultDto> inspectResultDtoList = new ArrayList<>();

        for(InspectResultEntity inspectresultEntity : ListByIid) {
            InspectResultDto inspectresultDTO = InspectResultDto.builder()
                    .rid(inspectresultEntity.getRid())
                    .iid(inspectresultEntity.getIid())
                    .oid(inspectresultEntity.getOid())
                    .cid(inspectresultEntity.getCid())
                    .date(inspectresultEntity.getDate())
                    .result(inspectresultEntity.getResult())
                    .status(inspectresultEntity.getStatus())
                    .build();

            inspectResultDtoList.add(inspectresultDTO);

        }
        return inspectResultDtoList;
    }

}
