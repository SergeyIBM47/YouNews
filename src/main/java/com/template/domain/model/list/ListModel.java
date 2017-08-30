package com.template.domain.model.list;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public abstract class ListModel implements Serializable {
    private List rows = new ArrayList<>();
    private Pagination pagination = new Pagination();

    public class Pagination {
        private Integer count = 20;
        private Integer page = 1;
        private Integer pages;
        private Long size;

        public Integer getCount() {
            //if (size < count && size > 0) {
            //this.count = Integer.valueOf(String.valueOf(size));
            //}

            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getPage() {
            if (page > size / count + 1) page = 1;

            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPages() {
            return (int) ((getSize() - 1) / getCount() + 1);    //get total pages
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void update(Page page, UiPageable pageable) {
        pagination.setSize(page.getTotalElements());
        pagination.setPage(pageable.getUiPageNumber());
        pagination.setCount(pageable.getPageSize());

        if (!page.getContent().isEmpty()) {
            rows = page.getContent();
        }
    }

    public abstract List<String> getHeaders();

}

