package com.pan.email.dao;

import com.pan.email.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 邮件mapper
 * auth:
 */
@Mapper
public interface DiscussPostMapper {

    /**
     * 分页查询邮件信息
     *
     * @param userId 当传入的 userId = 0 时查找所有用户的邮件
     *               当传入的 userId != 0 时，查找该指定用户的邮件
     * @param offset 每页的起始索引
     * @param limit  每页显示多少条数据
     * @param orderMode  排行模式(若传入 1, 则按照热度来排序)
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit, int orderMode);

    /**
     * 插入/添加邮件
     * @param discussPost
     * @return
     */
    int insertDiscussPost(DiscussPost discussPost);

    /**
     * 根据 id 查询邮件
     * @param id
     * @return
     */
    DiscussPost selectDiscussPostById(int id);


    /**
     * 修改邮件类型：0-普通; 1-置顶;
     * @param id
     * @param type
     * @return
     */
    int updateType(int id, int type);

    /**
     * 修改邮件状态：0-正常; 1-精华; 2-拉黑;
     * @param id
     * @param status
     * @return
     */
    int updateStatus(int id, int status);

    int selectCountByToUserIdAndStatus(int toUserId, int status);

    List<Map> selectByKeyWordAndUser(@Param("keyword")String keyword, @Param("userId") int id, int offset, int limit);

    void deleteById(@Param("id")int id);

    int selectSendCount(@Param("userId")int userId);

    int selectReactorCount(@Param("userId")int userId);
}
