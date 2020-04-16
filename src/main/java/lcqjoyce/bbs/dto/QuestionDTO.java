package lcqjoyce.bbs.dto;

import lcqjoyce.bbs.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionDTO implements Serializable {
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

    private User user;

    private static final long serialVersionUID = 1L;
}