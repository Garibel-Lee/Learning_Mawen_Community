package lcqjoyce.bbs.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Question implements Serializable {
    private Long id;

    private String title;

    private String description;

    private Long gmtCreate;

    private Long gmtModified;

    private Long creator;

    private Integer commentCount;

    private Integer viewCount;

    private Integer likeCount;

    private String tag;

    private static final long serialVersionUID = 1L;
}