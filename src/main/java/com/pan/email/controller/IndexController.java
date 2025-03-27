package com.pan.email.controller;

import com.pan.email.entity.DiscussPost;
import com.pan.email.entity.Drafts;
import com.pan.email.entity.Page;
import com.pan.email.entity.User;
import com.pan.email.service.DiscussPostService;
import com.pan.email.service.UserService;
import com.pan.email.util.CommunityConstant;
import com.pan.email.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页controller
 * auth:
 */
@Controller
public class IndexController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;


    @Autowired
    private HostHolder hostHolder;

    @GetMapping("/")
    public String root() {
        return "forward:/index";
    }

    /**
     * 进入首页
     * @param model
     * @param page
     * @param orderMode 默认是 0（最新）
     * @return
     */
    @GetMapping("/index")
    public String getIndexPage(Model model, Page page, @RequestParam(name = "orderMode", defaultValue = "0") int orderMode) {
        User user1 = hostHolder.getUser();
        if(user1 == null){
            return "site/login";
        }
        if(orderMode == 2){
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
            model.addAttribute("orderMode", orderMode);
            model.addAttribute("orderModeFlag", true);
            return "index";
        }else {
            // 分页查询
            List<DiscussPost> list = discussPostService.findDiscussPosts(user1.getId(), page.getOffset(), page.getLimit(), orderMode);
            // 封装邮件和该邮件对应的用户信息
            List<Map<String, Object>> discussPosts = new ArrayList<>();
            if (list != null) {
                for (DiscussPost post : list) {
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
            model.addAttribute("orderMode", orderMode);
            model.addAttribute("orderModeFlag", true);
            return "index";
        }
    }



    /**
     * 进入 500 错误界面
     * @return
     */
    @GetMapping("/error")
    public String getErrorPage() {
        return "/error/500";
    }

    /**
     * 没有权限访问时的错误界面（也是 404）
     * @return
     */
    @GetMapping("/denied")
    public String getDeniedPage() {
        return "/error/404";
    }

}
