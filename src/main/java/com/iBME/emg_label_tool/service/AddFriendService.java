package com.iBME.emg_label_tool.service;

import com.iBME.emg_label_tool.dto.UserDTO;

import java.util.List;

public interface AddFriendService {

    List<UserDTO> getSendingList();

    List<UserDTO> getRequestList();

    List<UserDTO> getFriendList();

    boolean addFriend(long userId);

    void acceptFriend(long userId);

    void cancelFriend(long userId);
}
