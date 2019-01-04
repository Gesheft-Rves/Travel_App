package com.javastudents.travelagency.repository.impl;

import com.javastudents.travelagency.AbstractTest;
import com.javastudents.travelagency.entity.Tour;
import com.javastudents.travelagency.repository.CrudTest;
import com.javastudents.travelagency.repository.TourRepository;
import org.intellij.lang.annotations.Language;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TourRepositoryImplTest extends AbstractTest implements CrudTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private TourRepository tourRepository;

    @Test
    @Override
    public void createTest() {
        String tourName = "Test tour";
        Tour tour = Tour.builder()
                .name(tourName)
                .description("dded")
                .price(new BigDecimal(2323.2))
                .tourCategoryId(1)
                .build();
        tourRepository.create(tour);

        @Language("MySQL")
        String sql = "SELECT name from tour where tour_id = (select max(tour_id) from tour)";
        String nameFromDb = jdbcTemplate.queryForObject(sql, String.class);

        Assert.assertEquals(tourName, nameFromDb);
    }

    @Test
    @Override
    public void readTest() {
        Tour byId = tourRepository.read(1);
        Assert.assertNotNull(byId);
        Assert.assertEquals("test", byId.getName());
    }

    @Test
    @Override
    public void updateTest() {
        Tour tour = tourRepository.read(1);

        tour.setName("tor New");

        tourRepository.update(tour);

        Tour tourNew = tourRepository.read(1);

        Assert.assertNotNull(tourNew.getId());
        Assert.assertEquals(tour.getId(), tourNew.getId());
    }

    @Test
    @Override
    public void deleteTest() {

        String tourName = "Test delete";
        Tour tourNew = Tour.builder()
                .name(tourName)
                .description("dded")
                .price(new BigDecimal(22323.2))
                .tourCategoryId(1)
                .build();
        tourRepository.create(tourNew);

        @Language("MySQL")
        String sql = "select max(tour_id) from tour";
        int id = jdbcTemplate.queryForObject(sql, int.class);

        Tour tour = tourRepository.read(id);

        Assert.assertNotNull(tour);

        tourRepository.delete(tour.getId());

        Assert.assertNull(tourRepository.read(id));
    }
}