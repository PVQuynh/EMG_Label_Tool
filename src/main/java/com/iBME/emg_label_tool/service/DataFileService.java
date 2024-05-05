package com.iBME.emg_label_tool.service;


import com.iBME.emg_label_tool.dto.PageDTO;
import com.iBME.emg_label_tool.dto.UserDTO;
import com.iBME.emg_label_tool.dto.UserDetailDTO;
import com.iBME.emg_label_tool.dto.request.*;
import com.iBME.emg_label_tool.dto.response.CoordinatesRes;
import com.iBME.emg_label_tool.dto.response.DataFileRes;
import com.iBME.emg_label_tool.entity.DataFile;
import com.iBME.emg_label_tool.entity.User;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface DataFileService {

    DataFile save(DataFileReq dataFileReq);

    List<DataFileRes> getAllDataFile();

    DataFileRes getDataFileByPatientId(long patientId);

    List<CoordinatesRes> getXYCoordinates(long id);
}