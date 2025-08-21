package com.hosiky.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.domain.po.UserRole;
import com.hosiky.mapper.UserRoleMapper;
import com.hosiky.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author author
 * @since 2025-04-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
