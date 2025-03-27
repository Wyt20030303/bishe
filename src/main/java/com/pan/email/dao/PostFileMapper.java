package com.pan.email.dao;

import com.pan.email.entity.PostFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 附件表mapper
 * auth:
 */
@Mapper
public interface PostFileMapper {
    /**
     * 根据邮件ID去查询所有附件表信息
     * @param postId
     * @return
     */
    List<PostFile> selectByPostId(@Param("postId") Integer postId);

    int insert(@Param("postFile") PostFile postFile);

    void deleteByPostId(@Param("postId")int postId);
}
