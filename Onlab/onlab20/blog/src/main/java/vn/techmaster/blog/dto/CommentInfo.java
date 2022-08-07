package vn.techmaster.blog.dto;

import java.time.LocalDateTime;

public interface CommentInfo {
    Integer getId();

    String getContent();

    LocalDateTime getCreated_at();

    UserInfo getUser();

    interface UserInfo {
        Integer getId();

        String getAvatar();

        String getName();
    }
}
