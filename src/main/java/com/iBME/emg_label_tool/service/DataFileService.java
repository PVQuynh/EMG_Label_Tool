package com.iBME.emg_label_tool.service;


import com.iBME.emg_label_tool.dto.PageDTO;
import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.dto.UserDetailDTO;
import com.iBME.emg_label_tool.dto.request.*;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.entity.User;

import java.text.ParseException;
import java.util.Optional;

public interface DataFileService {

    DataFile save(DataFileReq dataFileReq);


}