package com.template.domain.model.list;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;


public class UiPageRequest extends PageRequest implements UiPageable, Serializable {

    public UiPageRequest(int uiPageNo, int size) {
        super(uiPageNo <= 0 ? 0 : uiPageNo - 1, size);
    }

    public UiPageRequest(int uiPageNo, int size, Sort.Direction direction, String... properties) {
        super(uiPageNo <= 0 ? 0 : uiPageNo - 1, size, direction, properties);
    }

    public UiPageRequest(int uiPageNo, int size, Sort sort) {
        super(uiPageNo <= 0 ? 0 : uiPageNo - 1, size, sort);
    }

    @Override
    public int getUiPageNumber() {
        return getPageNumber() + 1;
    }

}
