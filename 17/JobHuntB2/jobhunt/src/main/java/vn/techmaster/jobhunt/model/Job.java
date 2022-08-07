package vn.techmaster.jobhunt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job")
@Entity

public class Job {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id")
  private Employer employer;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  private String city;

  @LastModifiedDate
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime updated_at;

  @CreatedDate
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime created_at;

  @PrePersist
  public void prePersist() {
    this.created_at = LocalDateTime.now();
    this.updated_at = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.updated_at = LocalDateTime.now();
  }
}
