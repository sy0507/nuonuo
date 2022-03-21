package com.example.nuonuo.Controller.v1;

import com.example.nuonuo.pojo.dto.AudioDTO;
import com.example.nuonuo.pojo.dto.CommentDTO;
import com.example.nuonuo.pojo.dto.UserLoginDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.CommentService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/comment")
@Slf4j
@Validated
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 发布评论
     * @param commentDTO
     * @param user
     * @return
     */
    @PostMapping("/upload")
    public WebAsyncTask<Object> create(@RequestBody @Validated CommentDTO commentDTO,
                                       @Token User user) {
        return new WebAsyncTask<>(() -> JsonResult.ok(commentService.insert(commentDTO, user)));}

    @GetMapping("/comment_list/{id}")
    public WebAsyncTask<Object> getCommentList(@PathVariable("id") Integer audioId){
        return new WebAsyncTask<>(()-> JsonResult.ok(commentService.getCommentList(audioId)));
    }

    @PostMapping("/on/{comment_id}")
    public WebAsyncTask<Object> on(@PathVariable("comment_id") Integer id){
        return new WebAsyncTask<>(() -> JsonResult.ok(commentService.on(id)));
    }


    @PostMapping("/out/{comment_id}")
    public WebAsyncTask<Object> out(@PathVariable("comment_id") Integer id){
        return new WebAsyncTask<>(() -> JsonResult.ok(commentService.out(id)));
    }



}
