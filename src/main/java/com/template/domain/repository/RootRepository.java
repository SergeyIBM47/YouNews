package com.template.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 5:41 PM
 * Project: Spring MVC
 */
@NoRepositoryBean
public interface RootRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
