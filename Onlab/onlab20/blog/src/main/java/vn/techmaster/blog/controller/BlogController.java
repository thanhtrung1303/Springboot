package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.request.CreateBlogRequest;
import vn.techmaster.blog.service.BlogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryRepository categoryRepository;

//    Danh sach tat ca bai viet
    @GetMapping("/admin/blogs")
    public String getBlogPage(Model model) {
        //TODO: Bo xung them phan trang
        model.addAttribute("blogs", blogService.getAllBlogDto());

        return "admin/blog/blog-index";
    }

    //Danh sach bai viet cua toi
    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogPage(Model model) {
        // TODO: Ve sau userId se la id cua user dang dang nhap
        Integer userId = 1;
        model.addAttribute("blogs", blogService.getAllBlogDtoByUserId(userId));
        return "admin/blog/blog-yourself";
    }

    //Tao bai viet
    @GetMapping("/admin/blogs/create")
    public String getBlogCreatePage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/blog/blog-create";
    }

    //chi tiet bai viet
    @GetMapping("/admin/blogs/{id}/detail")
    public String getBlogDetailPage(@PathVariable String id, Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("blog", blogService.getBlogDtoById(id));
        return "admin/blog/blog-detail";
    }

    //tao bai viet
    @PostMapping("/api/admin/blogs")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest request){
        // TODO: Ve sau userId se la id cua user dang dang nhap
        Integer userId = 1;

        //tao blog
        Blog blog = blogService.createBlog(userId, request);

        //tra ve ket qua
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

//    xoa bai viet
    @DeleteMapping("/api/admin/blogs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable String id) {
        blogService.remove(id);
    }


}
