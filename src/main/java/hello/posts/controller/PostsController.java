package hello.posts.controller;

import hello.posts.domain.Posts;
import hello.posts.domain.PostsRequestDTO;
import hello.posts.service.PostsService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class PostsController {

    private final PostsService postsService;


   // --------- 전체 게시물 다 조회 ----------//

    @GetMapping("/posts")
    public String postsForm(Model model)
    {
        List<Posts> findAllList = postsService.findAll();
        model.addAttribute("postsList",findAllList);

        return "/view/postsForm";

    }

    // ------- 게시물 추가 ---------- //

    @GetMapping("/posts/addForm")
    public String addForm()
    {
        return "view/addForm";
    }

    @PostMapping("/posts/addForm")
    public String add(@ModelAttribute PostsRequestDTO postsRequestDTO)
    {
        Posts posts = Posts.builder().title(postsRequestDTO.getTitle())
                .content(postsRequestDTO.getContent())
                .author(postsRequestDTO.getAuthor())
                .build();

        postsService.save(posts);
        return "redirect:/view/posts";
    }

    //-------- 게시글 상세 ------------//

    @GetMapping("/posts/{postsid}")
    public String eachPosts(@PathVariable Long postsid, Model model)
    {
        Posts posts = postsService.findById(postsid);
        model.addAttribute("posts",posts);
        return "/view/eachPosts";

    }


    // --------- 게시글 수정 ------------ //

    @GetMapping("/posts/{postsid}/edit")
    public String update(@PathVariable Long postsid, Model model)
    {
        Posts posts = postsService.findById(postsid);
        model.addAttribute("posts",posts);
        return "view/updateForm";
    }

    @PostMapping("/posts/{postsid}/edit")
    public String update(@PathVariable Long postsid ,@ModelAttribute PostsRequestDTO postsRequestDTO)
    {
        postsService.update(postsid,postsRequestDTO);
        return "redirect:/view/posts/{postsid}";
    }

    // --------- 게시글 삭제 ---------- //
    @GetMapping("/posts/{postsid}/delete")
    public String delete(@PathVariable Long postsid)
    {
        postsService.delete(postsid);
        return "redirect:/view/posts";
    }


    @PostConstruct
    public void save()
    {
        postsService.save(Posts.builder().title("백엔드공부법").content("백엔드 공부법에 대해 알아보자").author("제민").build());
    }


}
