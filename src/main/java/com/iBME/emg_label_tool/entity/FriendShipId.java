package com.iBME.emg_label_tool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendShipId implements Serializable {

    private User sendFriend;

    private User acceptFriend;

}
