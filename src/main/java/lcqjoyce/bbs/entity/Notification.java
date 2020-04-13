package lcqjoyce.bbs.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Notification implements Serializable {
    private Long id;

    private Long notifier;

    private Long receiver;

    private Long outerid;

    private Integer type;

    private Long gmtCreate;

    private Integer status;

    private String notifierName;

    private String outerTitle;

    private static final long serialVersionUID = 1L;
}