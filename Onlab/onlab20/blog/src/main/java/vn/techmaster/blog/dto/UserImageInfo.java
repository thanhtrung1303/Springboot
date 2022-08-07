package vn.techmaster.blog.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
@ToString
public class UserImageInfo {
    private Integer id;
    private String name;
    private List<ImageInfo> images;

    public UserImageInfo(Integer id, String name, String images) {
        this.id = id;
        this.name = name;
        if (images != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.images = mapper.readValue(images, new TypeReference<List<ImageInfo>>(){});
            } catch (IOException e) {
                this.images = null;
            }
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static
    class ImageInfo {
        private Integer id;
        private String url;
        private String created_at;
    }
}

