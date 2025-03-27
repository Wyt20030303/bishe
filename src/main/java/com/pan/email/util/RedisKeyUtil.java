package com.pan.email.util;

/**
 * 生成 Redis 的 key
 * auth:
 */
public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_KAPTCHA = "kaptcha"; // 验证码
    private static final String PREFIX_TICKET = "ticket"; // 登录凭证
    private static final String PREFIX_USER = "user"; // 登录凭证


    /**
     * 登录验证码（指定这个验证码是针对哪个用户的）
     * @param owner 用户进入登录页面的时候，由于此时用户还未登录，无法通过 id 标识用户
     *              随机生成一个字符串，短暂的存入 cookie，使用这个字符串来标识这个用户
     * @return
     */
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    /**
     * 登陆凭证
     * @param ticket
     * @return
     */
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    /**
     * 用户信息
     * @param userId
     * @return
     */
    public static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }

}
