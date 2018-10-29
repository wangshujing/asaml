package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.Evaluation;

public interface EvaluationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);
    
    int logicalDeleteListByProjectId(@Param("projectId") Integer projectId);
    
    List<Evaluation> selectEvaluationListByProjectId(@Param("projectId") Integer projectId);
    
    Evaluation selectByLabel(@Param("label") String label);
    
    Evaluation selectByLabelAndClassType(@Param("label") String label,@Param("classType") String classType);
}