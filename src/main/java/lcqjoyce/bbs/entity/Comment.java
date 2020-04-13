package lcqjoyce.bbs.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Comment implements Serializable {
    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private String content;

    private Integer commentCount;

    private static final long serialVersionUID = 1L;
}