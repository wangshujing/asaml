package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.Demand;
import com.saml.model.DemandAndRelation;
import com.saml.util.PageInfo;

public interface DemandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demand record);

    int insertSelective(Demand record);

    Demand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demand record);

    int updateByPrimaryKey(Demand record);
    
    int logicalDeleteByPrimaryKey(@Param("id") Integer id);
    
    int logicalDeleteListByProjectId(@Param("projectId") Integer projectId);
    
    List<Demand> selectDemandListByLevel(@Param("projectId") Integer projectId,@Param("level") Integer level);
    
    List<Demand> selectDemandListByParentId(@Param("parentId") Integer parentId);

	List<Demand> selectDemandListPageByParentId(@Param("page") PageInfo page,@Param("parentId") Integer parentId, @Param("keyword") String keyword);

	List<Demand> selectDemandList(@Param("projectId") Integer projectId);
	
	List<Demand> selectDemandListPage(@Param("page") PageInfo page,@Param("projectId") Integer projectId, @Param("keyword") String keyword);
	
	int selectDemandCountByLevel(@Param("projectId") Integer projectId, @Param("level") Integer level);

	int logicalDeleteListByProjectIdAndLevel(@Param("projectId") Integer projectId, @Param("level") Integer level);
}