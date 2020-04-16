package lcqjoyce.bbs.dto;

import lcqjoyce.bbs.entity.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageinfoDTO {
    private List<QuestionDTO> questionS;
    private Integer page;
    private Boolean showPrevious;
    private Boolean showNext;
    private Boolean showFirstPage;
    private Boolean showEndPage;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();


    public void setPageinfo(Integer totalPage, Integer page) {

        this.page = page;
        this.totalPage=totalPage;

        pages.add(page);
        for (int i = 1; i <= 2; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }


        //是否展示上一页
        if (page > 1) {
            showPrevious = true;
        }else {
            showPrevious = false;
        }
        //是否展示下一页
        if (page.equals(totalPage)) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}
