package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.DNMapping;

public interface DNMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DNMapping record);

    int insertSelective(DNMapping record);

    DNMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DNMapping record);

    int updateByPrimaryKey(DNMapping record);
    
    List<DNMapping> selectListByDemandId(@Param("demandId") Integer demandId);
    
    int logicalDeleteByPrimaryKey(@Param("id") Integer id);
    
    int logicalDeleteListByProjectId(@Param("projectId") Integer projectId);
    
    int logicalDeleteListByDemandId(@Param("demandId") Integer demandId);

	DNMapping selectByDemandIdAndNodeId(@Param("demandId") Integer demandId,@Param("nodeId") Integer nodeId);

	List<DNMapping> selectListByNodeId(@Param("nodeId") Integer nodeId);
}