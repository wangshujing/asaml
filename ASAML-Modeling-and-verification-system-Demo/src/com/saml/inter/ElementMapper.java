package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.Element;

public interface ElementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Element record);

    int insertSelective(Element record);

    Element selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Element record);

    int updateByPrimaryKey(Element record);
    
    Element selectByTypeId(@Param("typeId") Integer typeId);
    
    int logicalDeleteByTypeId(@Param("typeId") Integer typeId);

	List<Element> selectByElement(@Param("element") String element);
}