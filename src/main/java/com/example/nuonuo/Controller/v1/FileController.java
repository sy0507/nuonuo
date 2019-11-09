package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.exception.FileEmptyException;
import com.example.nuonuo.service.FileService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
@Slf4j
public class FileController {
     private final FileService fileService;

  public FileController(FileService fileService) {
    this.fileService = fileService;
  }


  @PostMapping("/files")
  public WebAsyncTask<Object> uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                         @Token Integer userId) {
    if (multipartFile.isEmpty()) {
      throw new FileEmptyException("上传的文件必须非空");
    }
    return new WebAsyncTask<>(() -> JsonResult.ok(fileService.saveFile(multipartFile, userId)));
  }
}
