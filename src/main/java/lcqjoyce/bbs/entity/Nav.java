package lcqjoyce.bbs.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Nav implements Serializable {
    private Integer id;

    private String title;

    private String url;

    private Integer priority;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer status;

    private static final long serialVersionUID = 1L;
}