package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.blog.dto.BlogDto;
import vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.util.Utils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryTest {
    @Test
    void generate_category_string_test() {
        List<BlogDto.CategoryDto> categories =new ArrayList<>();
        categories.add(new BlogDto.CategoryDto(1, "lap trinh"));
        categories.add(new BlogDto.CategoryDto(2, "database"));
        categories.add(new BlogDto.CategoryDto(3, "frontend"));

        String rs = Utils.generateCategoryString(categories);
        System.out.println(rs);
    }
}
