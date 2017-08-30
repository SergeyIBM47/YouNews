package com.template.service.impl;

import com.template.domain.model.Rate;
import com.template.domain.repository.RateRepository;
import com.template.domain.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by: Sergey Volokh
 * Date: 6/3/2016
 * Time: 8:39 PM
 * Project: springmvcs
 */
@Service
public class RateServiceImpl extends RootServiceImpl<Rate> {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public RootRepository<Rate, Long> getRepository() {
        return rateRepository;
    }

    @Override
    public Class<Rate> getEntityClass() {
        return Rate.class;
    }
}
