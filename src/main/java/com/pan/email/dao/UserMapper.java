package com.pan.email.dao;

import com.pan.email.entity.DiscussPost;
import com.pan.email.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表mapper
 * auth:
 */
@Mapper
public interface UserMapper {

    /**
     * 分页查询邮件信息
     *
     * @param offset 每页的起始索引
     * @param limit  每页显示多少条数据
     * @return
     */
    List<User> selectUserList(int offset, int limit, String userName, String userDept);

    /**
     * 根据 id 查询用户
     * @param id
     * @return
     */
    User selectById (int id);

    /**
     * 根据 username 查询用户
     * @param username
     * @return
     */
    User selectByName(String username);

    /**
     * 根据 email 查询用户
     * @param email
     * @return
     */
    User selectByEmail(String email);

    /**
     * 插入用户（注册）
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改用户状态
     * @param id
     * @param status 0：未激活，1：已激活
     * @return
     */
    int updateStatus(int id, int status);

    /**
     * 修改头像
     * @param id
     * @param headerUrl
     * @return
     */
    int updateHeader(int id, String headerUrl);

    /**
     * 修改密码
     * @param id
     * @param password 新密码
     * @return
     */
    int updatePassword(int id, String password);

}
