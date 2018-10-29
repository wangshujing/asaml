package com.saml.inter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saml.model.InvokableNode;
import com.saml.util.PageInfo;

public interface InvokableNodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InvokableNode record);

    int insertSelective(InvokableNode record);

    InvokableNode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InvokableNode record);

    int updateByPrimaryKey(InvokableNode record);
    
    int logicalDeleteListByProjectId(@Param("projectId") Integer projectId);

   	List<InvokableNode> selectInvokableNodeListByProjectIdAndType(@Param("projectId") Integer projectId,@Param("type") Integer type);

	List<InvokableNode> selectInvokableNodeListPageByProjectIdAndType(@Param("page") PageInfo page, @Param("projectId") Integer projectId,@Param("type") Integer type,@Param("keyword")  String keyword);

	InvokableNode selectByNodeId(@Param("nodeId")  String nodeId,@Param("projectId") Integer projectId);
}