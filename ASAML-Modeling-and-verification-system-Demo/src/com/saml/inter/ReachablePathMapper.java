package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.ReachablePath;
import com.saml.util.PageInfo;

public interface ReachablePathMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReachablePath record);

    int insertSelective(ReachablePath record);

    ReachablePath selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReachablePath record);

    int updateByPrimaryKey(ReachablePath record);
    
    int logicalDeleteListByProjectId(@Param("projectId") Integer projectId);

    List<ReachablePath> selectReachablePathListByProjectId(@Param("keyword") String keyword,@Param("projectId") Integer projectId);

    List<ReachablePath> selectReachablePathListPage(@Param("page") PageInfo page, @Param("keyword") String keyword,@Param("projectId") Integer projectId);
}