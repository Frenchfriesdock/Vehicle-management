package com.hosiky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosiky.domain.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-04-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
