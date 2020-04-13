package lcqjoyce.bbs.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {
    /**
    * 自增长主键
    */
    private Long id;

    /**
    * 账户ID
    */
    private String accountId;

    /**
    * 姓名
    */
    private String name;

    /**
    * cookie
    */
    private String token;

    /**
    * 格林威治时间创建
    */
    private Long gmtCreate;

    /**
    * 格林威治时间修改
    */
    private Long gmtModified;

    /**
    * 简历
    */
    private String bio;

    private String avatarUrl;

    private static final long serialVersionUID = 1L;
}