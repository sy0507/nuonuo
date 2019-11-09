package com.example.nuonuo.service.Impl;

import com.example.nuonuo.exception.CommonException;
import com.example.nuonuo.exception.FileNameIllegalException;
import com.example.nuonuo.exception.FileStoreFailedException;
import com.example.nuonuo.mapper.FileStoreMapper;
import com.example.nuonuo.pojo.entity.FileStore;
import com.example.nuonuo.pojo.vo.SaveFileResultVO;
import com.example.nuonuo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;


@Service
@Slf4j
public class FileServiceImpl implements FileService {
  private final FileStoreMapper fileStoreMapper;
  private final int maxOriginalFilenameLength;
  private final String storeRootPath;
  private final int maxDiskNameLength;
  private final String webRootUrl;


  public FileServiceImpl(@Value("#{150}") int maxOriginalFilenameLength,
                         @Value("${store.file.path}") String storeRootPath,
                         @Value("${store.file.max-path-length}") int maxDiskNameLength,
                         @Value("${web.url.file}") String webRootUrl,
                         FileStoreMapper fileStoreMapper) {
    this.fileStoreMapper = fileStoreMapper;
    this.maxOriginalFilenameLength = maxOriginalFilenameLength;
    this.storeRootPath = storeRootPath + "/";
    this.maxDiskNameLength = maxDiskNameLength;
    this.webRootUrl = webRootUrl;
  }

  @Override
  public String getWebUrl(String path) {
    if (hasText(path)) {
      return webRootUrl + "/" + path;
    }
    return "";
  }

  @Override
  public Object saveFile(MultipartFile multipartFile, Integer userId) {
    String contentType = multipartFile.getContentType();
    if (contentType == null || !contentType.startsWith("image")) {
      throw new CommonException("上传的文件不是图片");
    }
    String originalFilename = multipartFile.getOriginalFilename();
    if (originalFilename == null) {
      log.info("上传的文件名为空，该文件信息{}", multipartFile.toString());
      throw new FileNameIllegalException("文件名为空");
    }
    if (maxOriginalFilenameLength - originalFilename.length() < 0) {
      throw new FileNameIllegalException("文件名长度不能超过" + maxOriginalFilenameLength + "个字");
    }
    File saveFile = new File(storeRootPath + originalFilename);
    String diskName = originalFilename;
    while (saveFile.exists()) {
      diskName = generateRandomFilename(originalFilename);
      saveFile = new File(storeRootPath + diskName);
    }

    FileStore file = new FileStore();
    file.setDiskName(diskName);
    file.setRealName(originalFilename);
    fileStoreMapper.insertSelective(file);

    SaveFileResultVO resultVO = new SaveFileResultVO();
    resultVO.setId(file.getId());
    resultVO.setUrl(getWebUrl(file.getDiskName()));
    resultVO.setName(originalFilename);
    resultVO.setSize(multipartFile.getSize());

    File parentFile = saveFile.getParentFile();
    if (!parentFile.exists() && !parentFile.mkdir()) {
      log.error("创建文件目录失败，目录路径: {}", parentFile.getAbsolutePath());
    }
    try {
      multipartFile.transferTo(saveFile);
      setReadable(saveFile);
    } catch (IOException e) {
      throw new FileStoreFailedException("文件保存失败", e);
    }

    return resultVO;
  }

  @Override
  public String getUrlById(Integer fileId) {
    FileStore file = fileStoreMapper.selectByPrimaryKey(fileId);
    if (file == null) {
      return "";
    }
    return getWebUrl(file.getDiskName());
  }

  @Override
  public void setReadable(File saveFile) throws IOException {
    Runtime.getRuntime().exec("chmod 744 -R " + saveFile.getAbsolutePath());
  }


  private String generateRandomFilename(String originalFilename) {
    String randString = UUID.randomUUID() + "/" + originalFilename;
    if (randString.length() > maxDiskNameLength) {
      randString = randString.substring(randString.length() - maxDiskNameLength);
    }
    return randString;
  }
}
