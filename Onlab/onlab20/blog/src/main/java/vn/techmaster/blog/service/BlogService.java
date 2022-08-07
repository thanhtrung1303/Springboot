package vn.techmaster.blog.service;

import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.entity.User;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.repository.UserRepository;
import vn.techmaster.blog.request.CreateBlogRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Slugify slugify;


    public List<BlogInfo> getAllBlogInfo() {
        return blogRepository.getAllBlogInfo();
    }

    public List<BlogInfo> getBlogPopular(int limit) {
        return blogRepository.getAllBlogInfo()
                .stream()
                .sorted((a, b) -> b.getCountComment() - a.getCountComment())
                .limit(3)
                .collect(Collectors.toList());
    }

    public BlogInfo getBlogInfoById(String id) {
        Optional<BlogInfo> blogInfoOptional = blogRepository.getAllBlogInfo().stream()
                .filter(blogInfo -> blogInfo.getId().equals(id))
                .findFirst();
        return blogInfoOptional.orElse(null);
    }

    //lay danh sach tat ca blog o dang dto
    public List<BlogDto> getAllBlogDto() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    //lay dang sach tat ca blog o dang dto cua user
    public List<BlogDto> getAllBlogDtoByUserId(Integer id){
        List<Blog> blogs = blogRepository.getBlogsByUser_Id(id);
        return blogs.stream()
                .map(blog -> modelMapper.map(blog, BlogDto.class))
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    //tao bai viet
    public Blog createBlog(Integer userId, CreateBlogRequest request) {
        //lay thong tin cua user
        User user = userRepository.getUserById(userId, User.class);

        //lay thong tin category [1,2,3]
        List<Category> categories = categoryRepository.getByIdIn(request.getCategories());

        //tao blog
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .content(request.getContent())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .status(request.getStatus())
                .categories(categories)
                .user(user)
                .build();

        blogRepository.save(blog);

        return blog;
    }

    //lay chi tiet bai viet -> Dto
    public BlogDto getBlogDtoById(String id) {
        Blog blog = blogRepository.getBlogById(id);
        return modelMapper.map(blog, BlogDto.class);
    }

    public void remove(String id) {
        Blog blog = blogRepository.getBlogById(id);
        blogRepository.delete(blog);

    }
}
