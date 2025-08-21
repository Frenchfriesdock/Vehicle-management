package com.hosiky.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosiky.domain.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-04-01
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
