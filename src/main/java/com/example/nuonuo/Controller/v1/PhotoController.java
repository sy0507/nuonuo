//package com.example.nuonuo.Controller.v1;
//
//
//import com.example.nuonuo.pojo.dto.PhotoDTO;
//import com.example.nuonuo.pojo.entity.User;
//import com.example.nuonuo.service.PhotoService;
//import com.example.nuonuo.token.Token;
//import com.example.nuonuo.util.JsonResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.async.WebAsyncTask;
//
//@RestController
//@RequestMapping("/v1/photo")
//public class PhotoController {
//  private final PhotoService photoService;
//
//  public PhotoController(PhotoService photoService) {
//    this.photoService = photoService;
//  }
//
//  /**
//   * 上传拍照
//   * @param photoDTO
//   * @param user
//   * @return
//   */
//  @PostMapping("/upload")
//  public Object upload(@RequestBody PhotoDTO photoDTO, @Token User user){
//    return JsonResult.ok(photoService.uploadPhoto(photoDTO,user));
//  }
//
//  /**
//   * 修改上传内容
//   * @param id
//   * @param photoDTO
//   * @param user
//   * @return
//   */
//
//  @PutMapping("/{id}")
//  public WebAsyncTask<Object> update(@PathVariable Integer id,
//                                     @RequestBody @Validated PhotoDTO photoDTO,
//                                   @Token User user) {
//
//    photoService.Update(id, user, photoDTO);
//    return new WebAsyncTask<>(() -> JsonResult.ok());
//  }
//
//  /**
//   * 根据拍照获取详情
//   */
//  @GetMapping("/{photoId}")
//  public WebAsyncTask<Object> query(@PathVariable("photoId") String photoId,
//                                    @Token(required = false) User user) {
//    return new WebAsyncTask<>(() -> JsonResult.ok(photoService.getDetail(photoId, user)));
//  }
//
//  @DeleteMapping("/remove")
//  public WebAsyncTask<Object> remove(@Token User user) {
//    return new WebAsyncTask<>(() -> JsonResult.ok(photoService.remove(user)));
//  }
//
//
//
//
//
//}
