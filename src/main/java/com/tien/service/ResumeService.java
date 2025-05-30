package com.tien.service;

import com.tien.dto.ResumeDTO;

import java.util.List;

public interface ResumeService {
    ResumeDTO checkEmailExist(String email);

    boolean save(ResumeDTO resumeDTO);
    List<ResumeDTO> getAll();

    ResumeDTO getById(int id);
    boolean update(ResumeDTO resumeDTO);
    boolean delete(int id);
}
