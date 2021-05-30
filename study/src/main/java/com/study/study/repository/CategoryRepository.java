package com.study.study.repository;

import com.study.study.model.entity.Category;
import com.study.study.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByType(String type);
}
