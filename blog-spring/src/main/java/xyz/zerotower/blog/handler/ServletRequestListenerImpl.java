package xyz.zerotower.blog.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.zerotower.blog.utils.IpUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static xyz.zerotower.blog.constant.RedisPrefixConst.BLOG_VIEWS_COUNT;
import static xyz.zerotower.blog.constant.RedisPrefixConst.IP_SET;


/**
 * request监听
 *
 * @author 11921
 */
@Component
public class ServletRequestListenerImpl implements ServletRequestListener {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession();
        String ip = (String) session.getAttribute("ip");
        // 判断当前ip是否访问，增加访问量
        String ipAddr = IpUtil.getIpAddr(request);
        if (!ipAddr.equals(ip)) {
            session.setAttribute("ip", ipAddr);
            redisTemplate.boundValueOps(BLOG_VIEWS_COUNT).increment(1);
        }
        // 将ip存入redis，统计每日用户量
        redisTemplate.boundSetOps(IP_SET).add(ipAddr);
    }

    @Scheduled(cron = " 0 1 0 * * ?")
    private void clear() {
        //清空redis中的ip
        redisTemplate.delete("ip_set");
    }


}
