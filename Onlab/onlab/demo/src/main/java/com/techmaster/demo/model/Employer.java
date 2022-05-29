package com.techmaster.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employer {
    private String id;
    private String name;
    private String logo_path;
    private String website;
    private String email;
}
