package com.iBME.emg_label_tool.service.impl;

import com.iBME.emg_label_tool.constant.FriendShipStatus;
import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.entity.FriendShip;
import com.iBME.emg_label_tool.entity.User;
import com.iBME.emg_label_tool.exception.BusinessLogicException;
import com.iBME.emg_label_tool.mapper.impl.UserMapper;
import com.iBME.emg_label_tool.repository.FriendShipRepository;
import com.iBME.emg_label_tool.repository.UserRepository;
import com.iBME.emg_label_tool.service.AddFriendService;
import com.iBME.emg_label_tool.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddFriendServiceImpl implements AddFriendService {
    private final FriendShipRepository friendShipRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getSendingList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<FriendShip> friendShipList = friendShipRepository.findSendingList(email)
                .orElseThrow(BusinessLogicException::new);
        if (friendShipList.isEmpty()) throw new BusinessLogicException();

        List<User> users = friendShipList.stream()
                .map(friendShip -> friendShip.getAcceptFriend())
                .collect(Collectors.toList());
        return userMapper.toDTOList(users);
    }

    @Override
    public List<UserDTO> getRequestList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<FriendShip> friendShipList = friendShipRepository.findRequestList(email)
                .orElseThrow(() -> new BusinessLogicException());
        if (friendShipList.isEmpty()) throw new BusinessLogicException();

        List<User> users = friendShipList.stream()
                .map(friendShip -> friendShip.getSendFriend())

                .collect(Collectors.toList());
        return userMapper.toDTOList(users);
    }

    @Override
    public List<UserDTO> getFriendList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<FriendShip> friendShipList = friendShipRepository.findAllFriend(email)
                .orElseThrow(() -> new BusinessLogicException());
        if (friendShipList.isEmpty()) throw new BusinessLogicException();

        List<User> users = friendShipList.stream()
                .map(friendShip -> {
                    if (friendShip.getAcceptFriend().getEmail().equals(email)) {
                        return friendShip.getSendFriend();
                    } else return friendShip.getAcceptFriend();
                })

                .collect(Collectors.toList());

        return userMapper.toDTOList(users);
    }

    @Override
    public boolean addFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        User sendUser = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());

        // Lấy ra danh sách đã gửi lời mời cho mình
        List<FriendShip> friendShipList = friendShipRepository.findSendingListByUserId(userId)
                .orElseThrow(() -> new BusinessLogicException());
        List<User> sendingUsers = friendShipList.stream()
                .map(friendShip -> friendShip.getAcceptFriend())
                .collect(Collectors.toList());

        if ((sendUser.getId() != userId) && (!sendingUsers.contains(sendUser))) {
            User acceptUser = userRepository.findById(userId).orElseThrow(() -> new BusinessLogicException());

            FriendShip friendShip = FriendShip.builder()
                    .sendFriend(sendUser)
                    .acceptFriend(acceptUser)
                    .status(FriendShipStatus.WAITING_ACCEPT)
                    .build();

            friendShipRepository.save(friendShip);

            return true;
        }

        if (sendingUsers.contains(sendUser)) {
            FriendShip friendShip = friendShipRepository.findByAcceptedEmailAndSendingUserIdAndStatus(email, userId, FriendShipStatus.WAITING_ACCEPT).orElseThrow(() -> new BusinessLogicException());

            if (ObjectUtils.isEmpty(friendShip)) {
                throw new BusinessLogicException();
            }

            friendShip.setStatus(FriendShipStatus.FRIEND);
            friendShipRepository.save(friendShip);

            return true;
        }

        return false;
    }

    @Override
    public void acceptFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        FriendShip friendShip = friendShipRepository.findByAcceptedEmailAndSendingUserIdAndStatus(email, userId, FriendShipStatus.WAITING_ACCEPT)
                .orElseThrow(() -> new BusinessLogicException());
        if (ObjectUtils.isEmpty(friendShip)) {
            throw new BusinessLogicException();
        }

        friendShip.setStatus(FriendShipStatus.FRIEND);
        friendShipRepository.save(friendShip);
    }

    @Override
    public void cancelFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        FriendShip friendShip = friendShipRepository.findFriendShipByEmailAndId(email, userId).orElseThrow(() -> new BusinessLogicException());
        friendShipRepository.delete(friendShip);
    }

}
