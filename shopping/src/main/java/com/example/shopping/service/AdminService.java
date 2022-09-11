package com.example.shopping.service;

import com.example.shopping.dto.AdminDto;
import com.example.shopping.entity.Admin;

public interface AdminService {
    Admin findByUsername(String usernam);

    Admin save(AdminDto adminDto);
}
