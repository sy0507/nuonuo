package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.pojo.dto.AudioDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.AudioService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/audio")
@Slf4j
@Validated
public class AudioController {
    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    /**
     * 获取音频类型列表
     */
    @GetMapping("/type_list")
    public WebAsyncTask<Object> getTypeList(){
        return new WebAsyncTask<>(()-> JsonResult.ok(audioService.getTypeList()));
    }

    @PostMapping("/upload")
      public WebAsyncTask<Object> create(@RequestBody @Validated AudioDTO audioDTO,
                                         @RequestParam(value = "uid") Integer uid) {
    return new WebAsyncTask<>(() -> JsonResult.ok(audioService.insert(audioDTO, uid)));
  }

    @GetMapping("/{id}")
    public WebAsyncTask<Object> query(@PathVariable("id") Integer audioId ,@RequestParam(required = false,value = "uid") Integer uid) {
        return new WebAsyncTask<>(() -> JsonResult.ok(audioService.getDetail(audioId,uid)));
    }

//    @GetMapping("/carousel")
//    public WebAsyncTask<Object> getCarouselList(){
//        return new WebAsyncTask<>(()-> JsonResult.ok(audioService.getCarouselList()));
//    }

    @GetMapping("/hot_music")
    public WebAsyncTask<Object> getHotMusicList(){
        return new WebAsyncTask<>(()-> JsonResult.ok(audioService.getHotMusicList()));
    }

    @GetMapping("/cards")
    public WebAsyncTask<Object> getCardList(){
        return new WebAsyncTask<>(()-> JsonResult.ok(audioService.getCardList()));
    }









}
