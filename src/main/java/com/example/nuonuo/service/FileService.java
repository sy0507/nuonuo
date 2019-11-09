package com.example.nuonuo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
  String getWebUrl(String path);

  Object saveFile(MultipartFile multipartFile, Integer userId);

  /**
   * 根据 fileId 返回web范问路径
   *
   * @param fileId 文件id
   * @return 该文件web访问路径，不存在则返回""
   */
  String getUrlById(Integer fileId);

  void setReadable(File saveFile) throws IOException;
}
