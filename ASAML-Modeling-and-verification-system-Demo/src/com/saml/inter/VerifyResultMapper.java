package com.saml.inter;

import org.apache.ibatis.annotations.Param;

import com.saml.model.VerifyResult;

public interface VerifyResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VerifyResult record);

    int insertSelective(VerifyResult record);

    VerifyResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VerifyResult record);

    int updateByPrimaryKey(VerifyResult record);
    
    VerifyResult selectByProjectId(@Param("projectId") Integer projectId);

    int logicalDeleteByProjectId(@Param("projectId") Integer projectId);
}