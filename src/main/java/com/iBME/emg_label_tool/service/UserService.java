package com.iBME.emg_label_tool.service;


import com.iBME.emg_label_tool.dto.PageDTO;
import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.dto.UserDetailDTO;
import com.iBME.emg_label_tool.dto.request.*;
import com.iBME.emg_label_tool.entity.User;

import java.text.ParseException;
import java.util.Optional;

public interface UserService {
    User create(RegisterReq registerReq);

    void deleteUser(long id);

    Optional<User> findByEmail(String email);

    User findById(long Id);

    UserDTO getCurrentUser();

    void updateUser(UpdateUserReq updateUserReq) throws ParseException;

    boolean changePassword(ChangePasswordReq changePasswordReq);

    User randomlyGeneratePassword(String email);

    void uploadAvatar(UploadAvatarReq uploadAvatarReq);

    PageDTO<UserDTO> search(UserSearchReq userSearchReq);

    PageDTO<UserDTO> search_v2(int page, int size, String text, boolean ascending, String orderBy);

    UserDetailDTO getUserById(long userId);


}