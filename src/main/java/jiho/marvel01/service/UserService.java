package jiho.marvel01.service;

import jiho.marvel01.Role;
import jiho.marvel01.domain.Entity.UserEntity;
import jiho.marvel01.domain.Repository.UserRepository;
import jiho.marvel01.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Transactional
    public Long saveUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd = userDto.toEntity().getPassword();
        userDto.toEntity().setPassword(passwordEncoder.encode(pwd));
        return userRepository.save(userDto.toEntity()).getUid();
    }

    @Transactional
    public void registerUser(Map<String, String> datas) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDto userDto = new UserDto();
        UserEntity userEntity = userDto.toEntity();
        String status = datas.get("dept");
        userEntity.setEmail(datas.get("email"));
        userEntity.setName(datas.get("name"));
        userEntity.setCorp(datas.get("corp"));
        userEntity.setDepartment(datas.get("dept"));
        userEntity.setRanks(datas.get("ranks"));
        userEntity.setCodes(datas.get("codes"));
        userEntity.setPassword(passwordEncoder.encode(datas.get("pwd")));
        if(status == null) {
            userEntity.setStatus("재직");
        } else {
            userEntity.setStatus(status);
        }
        userEntity.setI_group(1);
        userRepository.save(userEntity).getUid();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    @Transactional
    public void insertMultiUsers(List<Map<String, String>> datas) {
        UserDto userDto = new UserDto();
        for(int idx = 0; idx < datas.size(); idx++) {
            UserEntity userEntity = userDto.toEntity();
            userEntity.setEmail(datas.get(idx).get("email"));
            userEntity.setName(datas.get(idx).get("name"));
            userEntity.setCorp(datas.get(idx).get("corp"));
            userEntity.setDepartment(datas.get(idx).get("dept"));
            userEntity.setRanks(datas.get(idx).get("ranks"));
            userEntity.setCodes(datas.get(idx).get("codes"));
            userEntity.setStatus(datas.get(idx).get("status"));
            userEntity.setI_group(Integer.parseInt(datas.get(idx).get("i_group")));
            userRepository.save(userEntity).getUid();
        }
    }

    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void inserttestUser() {
        UserDto userDto = new UserDto();
        UserEntity userEntity = userDto.toEntity();
        userEntity.setEmail("email");
        userEntity.setName("name");
        userEntity.setCorp("corp");
        userEntity.setDepartment("dept");
        userEntity.setRanks("ranks");
        userEntity.setCodes("codes");
        userEntity.setStatus("status");
        userEntity.setI_group(3);
        userRepository.save(userEntity).getUid();
    }

    // 빠른 디버깅을 위한 더미 insert
    @Transactional
    public void insertUser(Map<String, String> datas) {
        UserDto userDto = new UserDto();
        UserEntity userEntity = userDto.toEntity();
        userEntity.setEmail(datas.get("email"));
        userEntity.setName(datas.get("name"));
        userEntity.setCorp(datas.get("corp"));
        userEntity.setDepartment(datas.get("dept"));
        userEntity.setRanks(datas.get("ranks"));
        userEntity.setCodes(datas.get("codes"));
        userEntity.setStatus(datas.get("status"));
        userEntity.setI_group(Integer.parseInt(datas.get("i_group")));
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
