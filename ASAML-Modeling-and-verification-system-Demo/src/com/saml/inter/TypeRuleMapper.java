package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.TypeRule;

public interface TypeRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TypeRule record);

    int insertSelective(TypeRule record);

    TypeRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TypeRule record);

    int updateByPrimaryKey(TypeRule record);
    
    int logicalDeleteByPrimaryKey(@Param("id") Integer id);
    
    int logicalDeleteTypeRuleListByProjectId(@Param("projectId") Integer projectId);
    
    int logicalDeleteTypeRuleListByContentId(@Param("contentId")Integer contentId);

    TypeRule selectByContentIdAndRuleType(@Param("contentId")Integer contentId,  @Param("ruleType")Integer ruleType);
    
    int logicalDeleteByContentIdAndRuleTypeAndKeyId(@Param("contentId")Integer contentId,  @Param("ruleType")Integer ruleType, @Param("keyId")String keyId);
    
    List<TypeRule>selectListByContentIdAndRuleType(@Param("contentId")Integer contentId,  @Param("ruleType")Integer ruleType);

    int selectRuleCountByContentId(@Param("contentId")Integer contentId);

	int selectTypeRuleCountByRuleType(@Param("projectId") Integer projectId,@Param("type") Integer type);

	List<TypeRule> selectByConclusion(@Param("conclusion")String conclusion);
}