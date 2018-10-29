package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.Project;
import com.saml.util.PageInfo;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    List<Project> selectProjectListPage(@Param("page") PageInfo page, @Param("keyword") String keyword);

    int logicalDeleteByPrimaryKey(@Param("id") Integer id);

	List<Project> selectAllProject(@Param("keyword") String keyword);
}