package com.hosiky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosiky.common.Result;
import com.hosiky.domain.po.User;
import com.hosiky.security.entity.OAuthRequest;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2025-04-01
 */
public interface IUserService extends IService<User> {

    Result loginByUsername(User user);

    Result loginByGitee(OAuthRequest oauth);
}
