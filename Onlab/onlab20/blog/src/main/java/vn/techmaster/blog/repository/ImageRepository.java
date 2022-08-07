package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.dto.UserImageInfo;
import vn.techmaster.blog.entity.Image;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> getImagesByUserId(Integer id);

    @Query(name = "getImageUploadByUserInfo", nativeQuery = true)
    List<UserImageInfo> getImageUploadByUserInfo();
}