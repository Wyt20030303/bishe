package com.pan.email.controller;

import com.pan.email.entity.*;
import com.pan.email.service.UserService;
import com.pan.email.util.AsymmetricEncryption;
import com.pan.email.util.CommunityConstant;
import com.pan.email.util.CommunityUtil;
import com.pan.email.util.HostHolder;
import com.pan.email.service.DiscussPostService;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * auth:
 */
@Controller
@RequestMapping("/user")
public class UserController implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private DiscussPostService discussPostService;

    /**
     * 进入邮件发布页
     * @return
     */
    @GetMapping("/userlist")
    public String getUserList(Model model, Page page, String userName, String userDept) {
        User user1 = hostHolder.getUser();
        if(user1 == null){
            return "site/login";
        }

        // 分页查询
        List<User> list = userService.selectUserList(page.getOffset(), page.getLimit(), userName, userDept);
        // 封装邮件和该邮件对应的用户信息
        List<Map<String, Object>> userList = new ArrayList<>();
        if (list != null) {
            for (User uu : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("user", uu);
                userList.add(map);
            }
        }
        model.addAttribute("userList", userList);
        model.addAttribute("userName", userName);
        model.addAttribute("userDept", userDept);
        return "/site/adderbook";
    }


    /**
     * 跳转至账号设置界面
     * @return
     */
    @GetMapping("/setting")
    public String getSettingPage(Model model) {
        // 生成上传文件的名称
        String fileName = CommunityUtil.generateUUID();
        model.addAttribute("fileName", fileName);

        // 设置响应信息(qiniu 的规定写法)
        StringMap policy = new StringMap();
        policy.put("returnBody", CommunityUtil.getJSONString(0));
        // 生成上传到 qiniu 的凭证(qiniu 的规定写法)

        return "/site/setting";
    }

    /**
     * 更新图像路径（将本地的图像路径更新为云服务器上的图像路径）
     * @param fileName
     * @return
     */
    @PostMapping("/header/url")
    @ResponseBody
    public String updateHeaderUrl(String fileName) {

        userService.updateHeader(hostHolder.getUser().getId(), fileName);

        return CommunityUtil.getJSONString(0);

    }

    /**
     * 修改用户密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param model
     * @return
     */
    @PostMapping("/password")
    public String updatePassword(String oldPassword, String newPassword, Model model) {
        // 验证原密码是否正确
        User user = hostHolder.getUser();
        String md5OldPassword = CommunityUtil.md5(oldPassword + user.getSalt());
        if (!user.getPassword().equals(md5OldPassword)) {
            model.addAttribute("oldPasswordError", "原密码错误");
            return "/site/setting";
        }

        // 判断新密码是否合法
        String md5NewPassword = CommunityUtil.md5(newPassword + user.getSalt());
        if (user.getPassword().equals(md5NewPassword)) {
            model.addAttribute("newPasswordError", "新密码和原密码相同");
            return "/site/setting";
        }

        // 修改用户密码
        userService.updatePassword(user.getId(), newPassword);

        return "redirect:/index";
    }

    /**
     * 进入自己主页
     * @param userId 用户ID
     * @param model
     * @return
     */
    @GetMapping("/profile/{userId}")
    public String getProfilePage(@PathVariable("userId") int userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在");
        }
        // 用户
        model.addAttribute("user", user);
        // 已发送邮件数量
        long sendCount = discussPostService.selectSendCount(user.getId());
        model.addAttribute("sendCount", sendCount);
        // 接收邮件数量
        long reactorCount = discussPostService.selectReactorCount(user.getId());
        model.addAttribute("reactorCount", reactorCount);
        model.addAttribute("tab", "profile"); // 该字段用于指示标签栏高亮
        return "/site/profile";
    }


    /**
     * 进入首页
     * @param model
     * @param page
     * @return
     */
    @GetMapping("/index")
    public String getIndexPage(Model model, Page page) {
        User user1 = hostHolder.getUser();
        if(user1 == null){
            return "site/login";
        }
        // 分页查询
        List<Drafts> list = discussPostService.findDrafts(user1.getId(), page.getOffset(), page.getLimit());
        // 封装邮件和该邮件对应的用户信息
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (Drafts post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        //获取当前登陆用户未读邮件数
        int count = discussPostService.selectNotReadCount(user1);
        model.addAttribute("notReadCount", count);
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("orderModeFlag", true);
        return "index";
    }




}
