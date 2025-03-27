package com.pan.email.service;

import com.pan.email.dao.DiscussPostMapper;
import com.pan.email.dao.DraftsMapper;
import com.pan.email.dao.PostFileMapper;
import com.pan.email.entity.DiscussPost;
import com.pan.email.entity.Drafts;
import com.pan.email.entity.PostFile;
import com.pan.email.entity.User;
import com.pan.email.util.SensitiveFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 邮件相关
 * auth:
 */
@Service
public class DiscussPostService {

    private static final Logger logger = LoggerFactory.getLogger(DiscussPostService.class);

    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private DraftsMapper draftsMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private PostFileMapper postFileMapper;

    /**
     * 分页查询邮件信息
     *
     * @param userId    当传入的 userId = 0 时查找所有用户的邮件
     *                  当传入的 userId != 0 时，查找该指定用户的邮件
     * @param offset    每页的起始索引
     * @param limit     每页显示多少条数据
     * @param orderMode 排行模式(若传入 1, 则按照热度来排序)
     * @return
     */
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit, int orderMode) {
        logger.debug("load post list from DB");
        return discussPostMapper.selectDiscussPosts(userId, offset, limit, orderMode);
    }

    /**
     * 添加邮件
     *
     * @param discussPost
     * @return
     */
    public int addDiscussPost(DiscussPost discussPost) {
        if (discussPost == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        // 转义 HTML 标记，防止在 HTML 标签中注入攻击语句
        discussPost.setTitle(HtmlUtils.htmlEscape(discussPost.getTitle()));
        discussPost.setContent(HtmlUtils.htmlEscape(discussPost.getContent()));

        // 过滤敏感词
        discussPost.setTitle(sensitiveFilter.filter(discussPost.getTitle()));
        discussPost.setContent(sensitiveFilter.filter(discussPost.getContent()));

        return discussPostMapper.insertDiscussPost(discussPost);
    }

    /**
     * 根据 id 查询邮件
     *
     * @param id
     * @return
     */
    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }

    /**
     * 修改邮件类型：0-普通; 1-置顶;
     *
     * @param id
     * @param type
     * @return
     */
    public int updateType(int id, int type) {
        return discussPostMapper.updateType(id, type);
    }

    /**
     * 修改邮件状态：0-正常; 1-精华; 2-拉黑;
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(int id, int status) {
        return discussPostMapper.updateStatus(id, status);
    }


    public void addPostFile(DiscussPost discussPost, List<String> files, List<String> fileNames) {
        for (int i = 0; i < files.size(); i++) {
            PostFile postFile = new PostFile();
            postFile.setPostId(discussPost.getId());
            postFile.setFileName(fileNames.get(i));
            postFile.setFileUrl(files.get(i));
            postFile.setCreateTime(new Date());
            postFileMapper.insert(postFile);
        }
    }

    public int selectNotReadCount(User user1) {
        return discussPostMapper.selectCountByToUserIdAndStatus(user1.getId(), 0);
    }

    public List<Map> selectByKeyWordAndUser(String keyword, User user1, int offset, int limit) {
        keyword = "%" + keyword + "%";
        return discussPostMapper.selectByKeyWordAndUser(keyword, user1.getId(), offset, limit);
    }

    public List<PostFile> selectPostFile(int discussPostId) {
        return postFileMapper.selectByPostId(discussPostId);
    }

    public void delete(int id) {
        //先删邮件
        discussPostMapper.deleteById(id);
        //再删附件
        postFileMapper.deleteByPostId(id);
    }

    /**
     * 查询已发送邮件的数量
     * @param userId
     * @return
     */
    public int selectSendCount(int userId) {
        return discussPostMapper.selectSendCount(userId);
    }

    /**
     * 查询收到邮件的总数
     * @param userId
     * @return
     */
    public int selectReactorCount(int userId) {
        return discussPostMapper.selectReactorCount(userId);
    }

    public void addDrafts(Drafts drafts) {
        draftsMapper.insertDrafts(drafts);
    }

    public void addDraftsFile(Drafts drafts, List<String> files, List<String> fileNames) {
        for (int i = 0; i < files.size(); i++) {
            PostFile postFile = new PostFile();
            postFile.setPostId(drafts.getId());
            postFile.setFileName(fileNames.get(i));
            postFile.setFileUrl(files.get(i));
            postFile.setCreateTime(new Date());
            postFileMapper.insert(postFile);
        }
    }

    public List<Drafts> findDrafts(int id, int offset, int limit) {
        return draftsMapper.selectList(id, offset, limit);
    }

    public void delDrafts(Integer id) {
        draftsMapper.deleteById(id);
    }

    public Drafts selectDrafts(Integer id) {
        return draftsMapper.selectById(id);
    }

    public void removeDraftsFile(Drafts drafts) {
        postFileMapper.deleteByPostId(drafts.getId());
    }

    public void updateDrafts(Drafts drafts) {
        draftsMapper.update(drafts);
    }
}
