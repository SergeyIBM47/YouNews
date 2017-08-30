package com.template.domain.model.list;

import org.springframework.data.domain.Pageable;


public interface UiPageable extends Pageable {

    int getUiPageNumber();

}
