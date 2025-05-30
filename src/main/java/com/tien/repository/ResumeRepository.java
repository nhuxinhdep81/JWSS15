package com.tien.repository;

import com.tien.dto.ResumeDTO;

import java.util.List;

public interface ResumeRepository {
    ResumeDTO checkEmailExist(String email);

    boolean save(ResumeDTO resumeDTO);
    List<ResumeDTO> getAll();

    ResumeDTO getById(int id);
    boolean update(ResumeDTO resumeDTO);
    boolean delete(int id);
}

