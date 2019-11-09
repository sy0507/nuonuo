//package com.example.nuonuo.Controller.v1;
//
//
//import com.example.nuonuo.pojo.dto.PostActivityDTO;
//import com.example.nuonuo.pojo.entity.User;
//import com.example.nuonuo.service.ActivityService;
//import com.example.nuonuo.token.Token;
//import com.example.nuonuo.util.JsonResult;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.async.WebAsyncTask;
//
//@RestController
//@RequestMapping("/v1/activities")
//public class ActivityController {
//  private final ActivityService activityService;
//
//  public ActivityController(ActivityService activityService) {
//    this.activityService = activityService;
//  }
//
//
//  @PostMapping
//  @ResponseStatus(HttpStatus.CREATED)
//  public WebAsyncTask<Object> create(@RequestBody @Validated PostActivityDTO postActivityDTO,
//                                     @Token User user) {
//    return new WebAsyncTask<>(() -> JsonResult.ok(activityService.insert(postActivityDTO, user)));
//  }
//
//  @GetMapping("/list")
//  public WebAsyncTask<Object> totalList() {
//    return new WebAsyncTask<>(() -> JsonResult.ok(activityService.getAllList()));
//  }
//
//  @GetMapping("/{id}")
//  public WebAsyncTask<Object> query(@PathVariable("id") Integer activityId,
//                                    @Token(required = false) User user) {
//    return new WebAsyncTask<>(() -> JsonResult.ok(activityService.getDetail(activityId, user)));
//  }
//
//  @PostMapping("/remove/{id}")
//  public WebAsyncTask<Object> remove(@PathVariable("id") Integer activityId,
//                                     @Token(required = false) User user) {
//    return new WebAsyncTask<>(() -> JsonResult.ok(activityService.removeActivity(activityId)));
//  }
//
//
//  @PutMapping("/{id}")
//  public WebAsyncTask<Object> update(@PathVariable Integer id,
//                                     @RequestBody @Validated PostActivityDTO dto,
//                                     @Token User user) {
//    return new WebAsyncTask<>(() -> {
//      activityService.update(id, dto, user);
//      return JsonResult.ok();
//    });
//  }
//}
