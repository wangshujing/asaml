package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.SamlType;
import com.saml.util.PageInfo;

public interface SamlTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SamlType record);

    int insertSelective(SamlType record);

    SamlType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SamlType record);

    int updateByPrimaryKey(SamlType record);
    
    List<SamlType> selectSamlTypeListPageByLevel(@Param("page") PageInfo page, @Param("keyword") String keyword,@Param("level") Integer level);

   	int logicalDeleteByPrimaryKey(@Param("id") Integer id);
   	
   	int logicalDeleteSamlTypeListByProjectId(@Param("projectId") Integer projectId);

   	List<SamlType> selectAllSamlTypeByLevel(@Param("projectId") Integer projectId,@Param("level") Integer level);
   	
   	int selectSamlTypeCountByLevel(@Param("projectId") Integer projectId,@Param("level") Integer level);

   	
}