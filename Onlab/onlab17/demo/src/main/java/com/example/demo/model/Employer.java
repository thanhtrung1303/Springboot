package com.example.demo.model;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table
@Entity

public class Employer {
    private Long id;
    private String name, email,website;
}
