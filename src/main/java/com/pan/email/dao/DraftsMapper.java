package com.pan.email.dao;

import com.pan.email.entity.DiscussPost;
import com.pan.email.entity.Drafts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 邮件mapper
 * auth:
 */
@Mapper
public interface DraftsMapper {

    /**
     * 分页查询邮件信息
     *
     * @param userId 查询userId的草稿箱
     * @param offset 每页的起始索引
     * @param limit  每页显示多少条数据
     * @return
     */
    List<Drafts> selectList(@Param("userId")int userId,@Param("offset") int offset,@Param("limit") int limit);

    int selectCount(@Param("userId")int userId);

    /**
     * 插入/添加草稿箱
     * @param drafts
     * @return
     */
    int insertDrafts(@Param("drafts")Drafts drafts);

    /**
     * 根据 id 查询邮件
     * @param id
     * @return
     */
    Drafts selectById(@Param("id")int id);

    void deleteById(@Param("id")int id);

    void update(@Param("drafts")Drafts drafts);
}
