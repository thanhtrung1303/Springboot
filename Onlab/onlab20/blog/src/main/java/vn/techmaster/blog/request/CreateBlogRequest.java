package vn.techmaster.blog.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBlogRequest {
    private String title, description, content, thumbnail;
    private int status;
    private List<Integer> categories;
}
