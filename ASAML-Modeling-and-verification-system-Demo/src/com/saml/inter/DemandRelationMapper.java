package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.Demand;
import com.saml.model.DemandRelation;
import com.saml.util.PageInfo;

public interface DemandRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DemandRelation record);

    int insertSelective(DemandRelation record);

    DemandRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DemandRelation record);

    int updateByPrimaryKey(DemandRelation record);
    
    int logicalDeleteByPrimaryKey(@Param("id") Integer id);
    
    int logicalDeleteListByProjectId(@Param("projectId") Integer projectId);
    
    int logicalDeleteListByCombinationIdOrElementId(@Param("id")Integer id);
    
    int selectDemandRelationCount(@Param("projectId") Integer projectId);
    
    int selectDemandRelationCountByCombinationId(@Param("combinationId") Integer combinationId);
    
    List<DemandRelation> selectDemandRelationListByCombinationId(@Param("combinationId") Integer combinationId);
	
    List<DemandRelation> selectDemandRelationList(@Param("projectId") Integer projectId);
}