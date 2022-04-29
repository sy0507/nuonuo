package com.example.nuonuo.Controller.v1;

import com.example.nuonuo.pojo.dto.PageDTO;
import com.example.nuonuo.pojo.dto.SearchDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.SearchService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/search")
@Slf4j
@Validated
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/getAuthor/{condition}")
    public WebAsyncTask<Object> listAuthor(@PathVariable("condition") String condition,@RequestBody @Validated PageDTO pageDTO, @RequestParam(required = false,value = "uid") Integer uid) {
        return new WebAsyncTask<>(() -> JsonResult.ok(searchService.list(condition, pageDTO,uid)));
    }


    @GetMapping("/getAudio/{condition}")
    public WebAsyncTask<Object> listAudio(@PathVariable("condition") String condition,@RequestBody @Validated PageDTO pageDTO) {
        return new WebAsyncTask<>(() -> JsonResult.ok(searchService.listAudio(condition, pageDTO)));
    }

    @GetMapping("/adminAudio")
    public WebAsyncTask<Object> listAdminAudio(@RequestParam(required = false ,value = "audio_id") Integer audioId, @RequestParam(required = false,value = "audio_name") String audioName,
                                               @RequestParam(required = false,value = "uid") Integer uid,@RequestParam(required = false,value = "audio_type_id") Integer audioTypeId,@RequestBody @Validated PageDTO pageDTO) {
        return new WebAsyncTask<>(() -> JsonResult.ok(searchService.listAdminAudio(audioId,audioName,uid,audioTypeId, pageDTO)));
    }
}
