package com.pan.email.util;

/**
 * 邮箱系统常量
 * auth:
 */
public interface CommunityConstant {

    // 激活成功
    int ACTIVATION_SUCCESS = 0;

    // 重复激活
    int ACTIVATION_REPEAT = 1;

    // 激活失败
    int ACTIVATION_FAILURE = 2;

    // 默认的登录凭证超时时间 (12小时)
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    // 记住我状态下的凭证超时时间 (100天)
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;

    // 权限：普通用户
    String AUTHORITY_USER = "user";

    // 权限：管理员
    String AUTHORITY_ADMIN = "admin";

    // 权限：版主
    String AUTHORITY_MODERATOR = "moderator";

}
