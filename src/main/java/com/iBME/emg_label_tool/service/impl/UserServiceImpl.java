package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.dto.PageDTO;
import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.dto.UserDetailDTO;
import com.iBME.emg_label_tool.dto.request.*;
import com.iBME.emg_label_tool.dto.response.MessageResponse;
import com.iBME.emg_label_tool.entity.FriendShip;
import com.iBME.emg_label_tool.entity.Role;
import com.iBME.emg_label_tool.entity.User;
import com.iBME.emg_label_tool.enum_constant.Gender;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.impl.UserMapper;
import com.iBME.emg_label_tool.repository.FriendShipRepository;
import com.iBME.emg_label_tool.repository.RoleRepository;
import com.iBME.emg_label_tool.repository.UserRepository;
import com.iBME.emg_label_tool.service.KeycloakService;
import com.iBME.emg_label_tool.service.UserService;
import com.iBME.emg_label_tool.utils.EmailUtils;
import com.iBME.emg_label_tool.utils.RandomString;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;

    private final KeycloakService keycloakService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final FriendShipRepository friendShipRepository;


    @Override
    public User create(RegisterReq registerReq) {
        User user = new User();
        user.setName(registerReq.getName());
        user.setEmail(registerReq.getEmail());
        user.setPassword(registerReq.getPassword());
        Role role = roleRepository.findByCode(registerReq.getRole()).orElse(null);
        user.setRole(role);

        return userRepository.save(user);
    }


    @Override
    public void deleteUser(long id) {
        String email = EmailUtils.getCurrentUser();
        if (org.apache.commons.lang3.ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public User findById(long Id) {
        var userOptional = userRepository.findById(Id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        return null;
    }

    @Override
    public UserDTO getCurrentUser() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());

        return userMapper.toDTO(user);
    }

    @Override
    public void updateUser(UpdateUserReq updateUserReq) throws ParseException {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        User user = userRepository.findByEmail(email).orElseThrow(BusinessLogicException::new);
        if (updateUserReq.getName() != null) {
            user.setName(updateUserReq.getName());
        }
        if (updateUserReq.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserReq.getPhoneNumber());
        }
        if (updateUserReq.getAddress() != null) {
            user.setAddress(updateUserReq.getAddress());
        }
        if (updateUserReq.getBirthDay() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDay = dateFormat.parse(updateUserReq.getBirthDay());
            user.setBirthDay(birthDay);
        }
        if (updateUserReq.getGender() != null) {
            user.setGender(Gender.valueOf(updateUserReq.getGender()));
        }

        userRepository.save(user);
    }

    @Override
    public boolean changePassword(ChangePasswordReq changePasswordReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());

        if (user.getPassword().equals(changePasswordReq.getOldPassword())) {
            user.setPassword(changePasswordReq.getNewPassword());
            userRepository.save(user);
            keycloakService.changePassword(changePasswordReq);

            return true;
        }

        return false;
    }

    @Override
    public User randomlyGeneratePassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(BusinessLogicException::new);

        String password = RandomString.generateRandomString(12);
        user.setPassword(password);
        keycloakService.randomlyGeneratePassword(email, password);

        return userRepository.save(user);
    }


    @Override
    public PageDTO<UserDTO> search(UserSearchReq userSearchReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(userSearchReq.text)) {
            String searchText = "%" + userSearchReq.text + "%";
            Predicate nameLike = criteriaBuilder.like(root.get("name"), searchText);
            Predicate emailLike = criteriaBuilder.like(root.get("email"), searchText);
            Predicate validEmail = criteriaBuilder.notEqual(root.get("email"), email);
            predicates.add(criteriaBuilder.or(nameLike, emailLike));
            predicates.add(validEmail);
        } else return null;

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(userSearchReq.ascending) && !ObjectUtils.isEmpty(
                userSearchReq.orderBy)) {
            if (userSearchReq.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(userSearchReq.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(userSearchReq.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();

        List<User> results = query
                .setFirstResult((userSearchReq.page - 1) * userSearchReq.size) // Offset
                .setMaxResults(userSearchReq.size) // Limit
                .getResultList();

        PageDTO<UserDTO> userResPageDTO = new PageDTO<>(userMapper.toDTOList(results),
                userSearchReq.page, totalRows);

        return userResPageDTO;
    }

    @Override
    public PageDTO<UserDTO> search_v2(int page, int size, String text, boolean ascending, String orderBy) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(text)) {
            String searchText = "%" + text + "%";
            Predicate nameLike = criteriaBuilder.like(root.get("name"), searchText);
            Predicate emailLike = criteriaBuilder.like(root.get("email"), searchText);
            Predicate validEmail = criteriaBuilder.notEqual(root.get("email"), email);
            predicates.add(criteriaBuilder.or(nameLike, emailLike));
            predicates.add(validEmail);
        } else return null;

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(ascending) && !ObjectUtils.isEmpty(orderBy)) {
            if (ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();

        List<User> results = query
                .setFirstResult((page - 1) * size) // Offset
                .setMaxResults(size) // Limit
                .getResultList();

        PageDTO<UserDTO> userResPageDTO = new PageDTO<>(userMapper.toDTOList(results), page, totalRows);

        return userResPageDTO;
    }

    @Override
    public UserDetailDTO getUserById(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessLogicException());

        UserDTO userDTO = userMapper.toDTO(user);
        UserDetailDTO userDetailDTO = new UserDetailDTO(userDTO);

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException());

        if (currentUser.getRole().getCode().equals("USER")) {
            FriendShip friendShip = friendShipRepository.checkFriendStatus(currentUser.getId(),
                            userDetailDTO.getUserId())
                    .orElseThrow(BusinessLogicException::new);

            userDetailDTO.setFriendStatus(friendShip.getStatus());
        }
        return userDetailDTO;

    }

    @Override
    public void uploadAvatar(UploadAvatarReq uploadAvatarReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());
        user.setAvatarLocation(uploadAvatarReq.getAvatarLocation());
        userRepository.save(user);
    }

}
