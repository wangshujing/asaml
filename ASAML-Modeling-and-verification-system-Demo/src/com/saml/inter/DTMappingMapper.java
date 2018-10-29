package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.DTMapping;

public interface DTMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DTMapping record);

    int insertSelective(DTMapping record);

    DTMapping selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(DTMapping record);

    int updateByPrimaryKey(DTMapping record);
    
    List<DTMapping> selectByTypeId(@Param("typeId") Integer typeId);

    int logicalDeleteByTypeId(@Param("typeId") Integer typeId);

	List<DTMapping> selectByDemandId(@Param("demandId") Integer demandId);

	int logicalDeleteListByProjectId(@Param("projectId") Integer projectId);
}