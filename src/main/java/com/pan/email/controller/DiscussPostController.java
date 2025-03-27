package com.pan.email.controller;

import com.alibaba.fastjson.JSONObject;
import com.pan.email.entity.*;
import com.pan.email.entity.DiscussPost;
import com.pan.email.service.DiscussPostService;
import com.pan.email.service.UserService;
import com.pan.email.util.AsymmetricEncryption;
import com.pan.email.util.CommunityConstant;
import com.pan.email.util.CommunityUtil;
import com.pan.email.util.HostHolder;
import com.pan.email.entity.PostFile;
import com.pan.email.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.io.File;
import java.util.*;

/**
 * 邮件controller
 * auth:
 */
@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    // 网站域名
    @Value("${community.path.domain}")
    private String domain;

    // 项目名(访问路径)
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 进入邮件发布页
     * @return
     */
    @GetMapping("/publish")
    public String getPublishPage (Model model, Integer id) {
        User login = hostHolder.getUser();
        if (login == null) {
            return CommunityUtil.getJSONString(403, "您还未登录");
        }
        if(id != null){
            // 邮件
            Drafts drafts = discussPostService.selectDrafts(id);
            //公匙解密过程
            drafts.setContent(AsymmetricEncryption.decryptRSA(drafts.getContent()));
            model.addAttribute("drafts", drafts);
            //附件
            List<PostFile> postFiles = discussPostService.selectPostFile(id);
            model.addAttribute("postFiles", postFiles);
        }else {
            model.addAttribute("postFiles", new ArrayList<>());
        }
        return "/site/discuss-publish";
    }

    /**
     * 删除草稿箱功能
     * @param id
     * @return
     */
    @PostMapping("/delDrafts")
    @ResponseBody
    public Integer delDrafts (Integer id) {
        discussPostService.delDrafts(id);
        return 0;
    }

    /**
     * markdown 图片上传
     * @param file
     * @return
     */
    @PostMapping("/uploadMdPic")
    @ResponseBody
    public String uploadMdPic(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {

        String url = null; // 图片访问地址
        try {
            // 获取上传文件的名称
            String trueFileName = file.getOriginalFilename();
            String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
            String fileName = CommunityUtil.generateUUID() + suffix;
            String dir = System.getProperty("user.dir");
            dir += "/upload";
            // 图片存储路径
            File dest = new File(dir + "/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // 保存图片到存储路径
            file.transferTo(dest);

            // 图片访问地址
            url = domain + contextPath + "/editor-md-upload/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return CommunityUtil.getEditorMdJSONString(0, "上传失败", url);
        }
        return CommunityUtil.getEditorMdJSONString(1, "上传成功", url);
    }

    /**
     * markdown 附件上传
     * @param file
     * @return
     */
    @PostMapping("/fileUpload")
    @ResponseBody
    public Map fileUpload(@RequestParam(value = "file", required = true) MultipartFile file) {
        String url = null; // 附件访问地址
        String trueFileName = "";
        try {
            // 获取上传文件的名称
            trueFileName = file.getOriginalFilename();
            String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
            String fileName = CommunityUtil.generateUUID() + suffix;
            String dir = System.getProperty("user.dir");
            dir += "/upload";
            // 图片存储路径
            File dest = new File(dir + "/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // 保存图片到存储路径
            file.transferTo(dest);

            // 图片访问地址
            url = domain + contextPath + "/editor-md-upload/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("code", 0);
            map.put("message", "上传失败");
            return map;
        }
        Map map = new HashMap();
        map.put("code", 1);
        map.put("message", "上传成功");
        map.put("url", url);
        map.put("fileName", trueFileName);
        return map;
    }

    /**
     * markdown 头像上传
     * @param file
     * @return
     */
    @PostMapping("/uploadHand")
    @ResponseBody
    public Map uploadHand(@RequestParam(value = "file", required = true) MultipartFile file) {

        String url = null; // 图片访问地址
        try {
            // 获取上传文件的名称
            String trueFileName = file.getOriginalFilename();
            String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
            String fileName = CommunityUtil.generateUUID() + suffix;
            String dir = System.getProperty("user.dir");
            dir += "/upload";
            // 图片存储路径
            File dest = new File(dir + "/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            // 保存图片到存储路径
            file.transferTo(dest);

            // 图片访问地址
            url = domain + contextPath + "/editor-md-upload/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("code", 0);
            map.put("message", "上传失败");
            return map;
        }
        Map map = new HashMap();
        map.put("code", 1);
        map.put("message", "上传成功");
        map.put("url", url);
        return map;
    }

    /**
     * 发送邮件
     * @param title 邮件主题
     * @param content 正文
     * @param fileUrl 附件
     * @param toUserId 收件人
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String addDiscussPost(String title, String content, String mailType, String weichatPush, String fileUrl, String fileName, String toUserId) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "您还未登录");
        }
        if (user.getUsername().equals(toUserId)) {
            return CommunityUtil.getJSONString(2, "不能自己给自己发送邮件");
        }
        //附件列表
        List<String> files = JSONObject.parseArray(fileUrl, String.class);
        List<String> fileNames = JSONObject.parseArray(fileName, String.class);
        //判断收件人是否存在
        User toUser = userService.findUserByName(toUserId);
        if(toUser == null){
            return CommunityUtil.getJSONString(2, "系统中找不到该名字的收件人");
        }

        DiscussPost discussPost = new DiscussPost();
        discussPost.setToUserId(toUser.getId());
        discussPost.setUserId(user.getId());
        discussPost.setTitle(title);
        discussPost.setMailType(mailType);
        //系统私匙加密过程
        discussPost.setContent(AsymmetricEncryption.rsaEncrypt(content));
        discussPost.setCreateTime(new Date());
        discussPostService.addDiscussPost(discussPost);
        //添加附件信息
        discussPostService.addPostFile(discussPost, files, fileNames);

        String returnMsg = "邮件发送成功";
        if("1".equals(weichatPush)){
            returnMsg = "邮件发送成功，并推送至收件人企业微信。";
        }


        return CommunityUtil.getJSONString(0, returnMsg);
    }

    /**
     * 存为草稿箱
     * @param title 邮件主题
     * @param content 正文
     * @param fileUrl 附件
     * @param toUserId 收件人
     * @return
     */
    @PostMapping("/drafts")
    @ResponseBody
    public String addDrafts(String id, String title, String content, String mailType, String fileUrl, String fileName, String toUserId, Integer postId) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "您还未登录");
        }
        //附件列表
        List<String> files = JSONObject.parseArray(fileUrl, String.class);
        List<String> fileNames = JSONObject.parseArray(fileName, String.class);
        if(postId != null){
            Drafts drafts = discussPostService.selectDrafts(postId);
            //删除原来的附件表
            discussPostService.removeDraftsFile(drafts);
            //系统私匙加密过程
            drafts.setContent(AsymmetricEncryption.rsaEncrypt(content));
            drafts.setCreateTime(new Date());
            drafts.setUserId(user.getId());
            drafts.setToUser(toUserId);
            drafts.setTitle(title);
            drafts.setMailType(mailType);
            discussPostService.updateDrafts(drafts);
            //添加附件信息
            discussPostService.addDraftsFile(drafts, files, fileNames);
            return CommunityUtil.getJSONString(0, "草稿箱已保存");
        }else {
            Drafts drafts = new Drafts();
            drafts.setContent(AsymmetricEncryption.rsaEncrypt(content));
            drafts.setCreateTime(new Date());
            drafts.setUserId(user.getId());
            drafts.setToUser(toUserId);
            drafts.setTitle(title);
            drafts.setMailType(mailType);
            discussPostService.addDrafts(drafts);
            //添加附件信息
            discussPostService.addDraftsFile(drafts, files, fileNames);
            return CommunityUtil.getJSONString(0, "已存入草稿箱");
        }
    }

    /**
     * 进入邮件详情页
     * @param discussPostId
     * @param model
     * @return
     */
    @GetMapping("/detail/{discussPostId}")
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model) {
        User login = hostHolder.getUser();
        if (login == null) {
            return CommunityUtil.getJSONString(403, "您还未登录");
        }
        // 邮件
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        String content = HtmlUtils.htmlUnescape(AsymmetricEncryption.decryptRSA(discussPost.getContent())); // 内容反转义，不然 markDown 格式无法显示
        discussPost.setContent(content);
        model.addAttribute("post", discussPost);
        // 发件人
        User user = userService.findUserById(discussPost.getUserId());
        model.addAttribute("user", user);
        //判断是否本人的邮件  如果是本人发的,则添加撤回功能
        if((login.getId()+"").equals(discussPost.getUserId()+"")){
            model.addAttribute("isSelf", 1);
        }else {
            //标记为已读
            model.addAttribute("isSelf", 0);
            discussPostService.updateStatus(discussPostId, 1);
        }
        //附件
        List<PostFile> postFiles = discussPostService.selectPostFile(discussPostId);
        model.addAttribute("postFiles", postFiles);
        return "/site/discuss-detail";

    }

    /**
     * 撤回邮件
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String setDelete(int id) {
        discussPostService.delete(id);
        return CommunityUtil.getJSONString(0);
    }

    /**
     * 检查收件人信息
     * @param toUser
     * @return
     */
    @GetMapping("/checkUser")
    @ResponseBody
    public Map checkUser(String toUser) {
        Map map = new HashMap();
        User login = hostHolder.getUser();
        if (login == null) {
            map.put("code", 403);
            map.put("msg", "您还未登录");
            return map;
        }
        if(login.getUsername().equals(toUser)){
            map.put("code", 1);
            map.put("msg", "不能自己给自己发送邮件哦");
            return map;
        }
        User userByName = userService.findUserByName(toUser);
        if(userByName == null){
            map.put("code", 1);
            map.put("msg", "查询不到该收件用户哦");
            return map;
        }
        map.put("code", 0);
        map.put("msg", "收件人正常");
        return map;
    }


}
