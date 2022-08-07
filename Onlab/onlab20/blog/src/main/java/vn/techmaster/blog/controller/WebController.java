package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.repository.CommentRepository;
import vn.techmaster.blog.service.BlogService;

@Controller
public class WebController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BlogRepository blogRepository;


    @GetMapping("/")
    public String getHomepage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("blogs", blogService.getAllBlogInfo());
        model.addAttribute("blogsPopular", blogService.getBlogPopular(3));
        model.addAttribute("categories", categoryRepository.getCategoriesPopular(5));
        return "web/index";
    }

    @GetMapping("/blogs/{id}/{slug}")
    public String getDetail(@PathVariable String id, Model model) {
        model.addAttribute("blog", blogService.getBlogInfoById(id));
        model.addAttribute("comments", commentRepository.getCommentByBlogId(id));
        return "web/detail";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "web/about";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "web/contact";
    }

}
