package com.tien.repository;

import com.tien.config.ConnectionDB;
import com.tien.dto.ResumeDTO;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumeRepositoryImpl implements ResumeRepository {
    @Override
    public ResumeDTO checkEmailExist(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResumeDTO resumeDTO = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_email_exist(?)}");
            callSt.setString(1, email);
            rs = callSt.executeQuery();
            while (rs.next()) {
                resumeDTO = new ResumeDTO();
                resumeDTO.setId(rs.getInt("id"));
                resumeDTO.setFullName(rs.getString("full_name"));
                resumeDTO.setEmail(rs.getString("email"));
                resumeDTO.setPhoneNumber(rs.getString("phone_number"));
                resumeDTO.setEducation(rs.getString("education"));
                resumeDTO.setExperience(rs.getString("experience"));
                resumeDTO.setSkills(rs.getString("skills"));

            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return resumeDTO;
    }

    @Override
    public boolean save(ResumeDTO resumeDTO) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_resume(?, ?,?,?,?,?)}");
            callSt.setString(1, resumeDTO.getFullName());
            callSt.setString(2, resumeDTO.getEmail());
            callSt.setString(3, resumeDTO.getPhoneNumber());
            callSt.setString(4, resumeDTO.getEducation());
            callSt.setString(5, resumeDTO.getExperience());
            callSt.setString(6, resumeDTO.getSkills());
            callSt.executeUpdate();
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public List<ResumeDTO> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<ResumeDTO> resumeDTOList = null;
        try {
            resumeDTOList = new ArrayList<>();
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_resume()}");
            rs = callSt.executeQuery();
            while (rs.next()) {
                ResumeDTO resumeDTO = new ResumeDTO();
                resumeDTO = new ResumeDTO();
                resumeDTO.setId(rs.getInt("id"));
                resumeDTO.setFullName(rs.getString("full_name"));
                resumeDTO.setEmail(rs.getString("email"));
                resumeDTO.setPhoneNumber(rs.getString("phone_number"));
                resumeDTO.setEducation(rs.getString("education"));
                resumeDTO.setExperience(rs.getString("experience"));
                resumeDTO.setSkills(rs.getString("skills"));
                resumeDTOList.add(resumeDTO);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return resumeDTOList;
    }

    @Override
    public ResumeDTO getById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        ResumeDTO resumeDTO = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_resume_by_id(?)}");
            callSt.setInt(1, id);
            rs = callSt.executeQuery();
            while (rs.next()) {
                resumeDTO = new ResumeDTO();
                resumeDTO.setId(rs.getInt("id"));
                resumeDTO.setFullName(rs.getString("full_name"));
                resumeDTO.setEmail(rs.getString("email"));
                resumeDTO.setPhoneNumber(rs.getString("phone_number"));
                resumeDTO.setEducation(rs.getString("education"));
                resumeDTO.setExperience(rs.getString("experience"));
                resumeDTO.setSkills(rs.getString("skills"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return resumeDTO;
    }

    @Override
    public boolean update(ResumeDTO resumeDTO) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn= ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_resume_by_id(?, ?,?,?,?,?,?)}");
            callSt.setInt(1, resumeDTO.getId());
            callSt.setString(2, resumeDTO.getFullName());
            callSt.setString(3, resumeDTO.getEmail());
            callSt.setString(4, resumeDTO.getPhoneNumber());
            callSt.setString(5, resumeDTO.getEducation());
            callSt.setString(6, resumeDTO.getExperience());
            callSt.setString(7, resumeDTO.getSkills());
            return callSt.executeUpdate() > 0;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_resume_by_id(?)}");
            callSt.setInt(1, id);
            result = callSt.executeUpdate() > 0;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false;
    }
}
