package jiho.marvel01.service;

import jiho.marvel01.domain.Entity.UserEntity;
import jiho.marvel01.domain.Repository.UserRepository;
import jiho.marvel01.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    @Transactional
    public Long saveUser(UserDto userDto) {
        return userRepository.save(userDto.toEntity()).getUid();
    }

    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertUser(UserDto userDto, Map<String, String> datas, Integer i_group) {
        UserEntity userEntity = userDto.toEntity();
        userEntity.setEmail(datas.get("email"));
        userEntity.setName(datas.get("name"));
        userEntity.setCorp(datas.get("corp"));
        userEntity.setDepartment(datas.get("dept"));
        userEntity.setRanks(datas.get("ranks"));
        userEntity.setCodes(datas.get("codes"));
        userEntity.setStatus(datas.get("status"));
        userEntity.setI_group(i_group);
        userRepository.save(userEntity).getUid();
    }

    @Transactional
    public void updateUser(Long uid, Map<String, String> infos, Integer i_group) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(uid);
        UserEntity userEntity = userEntityWrapper.get();
        userEntity.setEmail(infos.get("email"));
        userEntity.setName(infos.get("name"));
        userEntity.setCorp(infos.get("corp"));
        userEntity.setDepartment(infos.get("dept"));
        userEntity.setRanks(infos.get("ranks"));
        userEntity.setCodes(infos.get("codes"));
        userEntity.setStatus(infos.get("status"));
        userEntity.setI_group(i_group);
        userRepository.save(userEntity);
    }

    @Transactional
    public List<UserDto> getUserlist() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntities) {
            UserDto userDTO = UserDto.builder()
                    .uid(userEntity.getUid())
                    .name(userEntity.getName())
                    .email(userEntity.getEmail())
                    .corp(userEntity.getCorp())
                    .department(userEntity.getDepartment())
                    .ranks(userEntity.getRanks())
                    .codes(userEntity.getCodes())
                    .status(userEntity.getStatus())
                    .i_group(userEntity.getI_group())
                    .build();

            userDtoList.add(userDTO);
        }
        return userDtoList;
    }

    @Transactional
    public List<UserDto> getUserlistbyId(List<Long> uids) {
        List<UserEntity> userEntities = userRepository.findAllById(uids);
        List<UserDto> userDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntities) {
            UserDto userDTO = UserDto.builder()
                    .uid(userEntity.getUid())
                    .name(userEntity.getName())
                    .email(userEntity.getEmail())
                    .corp(userEntity.getCorp())
                    .department(userEntity.getDepartment())
                    .ranks(userEntity.getRanks())
                    .codes(userEntity.getCodes())
                    .status(userEntity.getStatus())
                    .i_group(userEntity.getI_group())
                    .build();

            userDtoList.add(userDTO);
        }
        return userDtoList;
    }

    @Transactional
    public UserDto getUser(Long uid) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(uid);
        UserEntity userEntity = userEntityWrapper.get();

        UserDto userDTO = UserDto.builder()
                .uid(userEntity.getUid())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .corp(userEntity.getCorp())
                .department(userEntity.getDepartment())
                .ranks(userEntity.getRanks())
                .codes(userEntity.getCodes())
                .status(userEntity.getStatus())
                .i_group(userEntity.getI_group())
                .build();

        return userDTO;
    }

    @Transactional
    public void deleteUser(Long uid) {
        userRepository.deleteById(uid);
    }

    @Transactional //idx 기준으로 조회하여 리스팅 후 유저 삭제
    public void deleteUsers(List<Long> uid) {
        List<UserEntity> userEntities = userRepository.findAllById(uid);
        List<UserDto> userDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntities) {
            userRepository.deleteById(userEntity.getUid());
        }
    }

}
