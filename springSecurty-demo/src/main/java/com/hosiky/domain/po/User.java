package com.hosiky.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 身份 1 学生 2 教师 3 管理员
     */
    private Integer identity;

    /**
     * 头像
     */
    private String iconImg;

    /**
     * 背景图片
     */
    private String bgImg;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个人介绍
     */
    private String introduction;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 插入时间
     */
    private LocalDateTime insertTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private String openId;


}
