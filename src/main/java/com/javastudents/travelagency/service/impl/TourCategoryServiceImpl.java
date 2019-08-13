package com.javastudents.travelagency.service.impl;

import com.javastudents.travelagency.entity.TourCategory;
import com.javastudents.travelagency.repository.TourCategoryRepository;
import com.javastudents.travelagency.service.TourCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourCategoryServiceImpl implements TourCategoryService {

    private final TourCategoryRepository repository;

    @Autowired
    public TourCategoryServiceImpl(TourCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TourCategory> list() {
        return repository.findAll();
    }

    @Override
    public TourCategory getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public TourCategory save(TourCategory obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(getById(id));
    }
}
