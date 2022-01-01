package xyz.zerotower.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import xyz.zerotower.blog.dao.*;
import xyz.zerotower.blog.entity.UserAuth;
import xyz.zerotower.blog.entity.UserInfo;
import xyz.zerotower.blog.exception.ServeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static xyz.zerotower.blog.constant.RedisPrefixConst.ARTICLE_USER_LIKE;
import static xyz.zerotower.blog.constant.RedisPrefixConst.COMMENT_LIKE_COUNT;
import static xyz.zerotower.blog.utils.UserUtil.convertLoginUser;


/**
 * @author 11921
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserAuthDao userAuthDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new ServeException("用户名不能为空！");
        }
        // 查询账号是否存在
        UserAuth user = userAuthDao.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getId, UserAuth::getUserInfoId, UserAuth::getUsername, UserAuth::getPassword)
                .eq(UserAuth::getUsername, username));
        if (Objects.isNull(user)) {
            throw new ServeException("用户名不存在!");
        }
        // 查询账号信息
        UserInfo userInfo = userInfoDao.selectOne(new LambdaQueryWrapper<UserInfo>()
                .select(UserInfo::getId, UserInfo::getNickname, UserInfo::getAvatar, UserInfo::getIntro, UserInfo::getWebSite, UserInfo::getIsDisable)
                .eq(UserInfo::getId, user.getUserInfoId()));
        // 查询账号角色
        List<String> roleList = roleDao.listRolesByUserInfoId(userInfo.getId());
        // 查询账号点赞信息
        Set<Integer> articleLikeSet = (Set<Integer>) redisTemplate.boundHashOps(ARTICLE_USER_LIKE).get(userInfo.getId().toString());
        Set<Integer> commentLikeSet = (Set<Integer>) redisTemplate.boundHashOps(COMMENT_LIKE_COUNT).get(userInfo.getId().toString());
        // 封装登录信息
        return convertLoginUser(user, userInfo, roleList, articleLikeSet, commentLikeSet, request);
    }

}
