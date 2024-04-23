//package com.iBME.emg_label_tool.service;
//
//import com.iBME.emg_label_tool.dto.response.VocabularyRes;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//public interface UploadVocabularyService {
//
//    VocabularyRes uploadFile(String name, byte[] content);
//
//    List<VocabularyRes> uploadFileList(List<MultipartFile> files) throws IOException;
//
//    byte[] getFile(String key);
//
//    List<String> getAllFile();
//
//    List<VocabularyRes> getAllFileLocation();
//
//    boolean deleteFile(String fileName);
//}
