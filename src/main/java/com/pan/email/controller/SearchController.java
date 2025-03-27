package com.pan.email.controller;

import com.pan.email.entity.Page;
import com.pan.email.entity.User;
import com.pan.email.service.DiscussPostService;
import com.pan.email.util.CommunityConstant;
import com.pan.email.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * 搜索
 * auth:
 */
@Controller
public class SearchController implements CommunityConstant {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private DiscussPostService discussPostService;

    /**
     * 搜索
     * search?keword=xxx
     * @param keyword 关键词
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String search(String keyword, Page page, Model model) {
        User user1 = hostHolder.getUser();
        if(user1 == null){
            return "site/login";
        }
        List<Map> list = discussPostService.selectByKeyWordAndUser(keyword, user1, page.getOffset(), page.getLimit());
        model.addAttribute("discussPosts", list);
        return "/site/search";
    }


}
