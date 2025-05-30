package com.tien.service;

import com.tien.dto.ResumeDTO;
import com.tien.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;


    @Override
    public ResumeDTO checkEmailExist(String email) {
        return resumeRepository.checkEmailExist(email);
    }

    @Override
    public boolean save(ResumeDTO resumeDTO) {
        return resumeRepository.save(resumeDTO);
    }

    @Override
    public List<ResumeDTO> getAll() {
        return resumeRepository.getAll();
    }

    @Override
    public ResumeDTO getById(int id) {
        return resumeRepository.getById(id);
    }

    @Override
    public boolean update(ResumeDTO resumeDTO) {
        return resumeRepository.update(resumeDTO);
    }

    @Override
    public boolean delete(int id) {
        return resumeRepository.delete(id);
    }
}
