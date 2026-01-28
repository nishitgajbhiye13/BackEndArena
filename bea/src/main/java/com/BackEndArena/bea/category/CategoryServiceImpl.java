package com.BackEndArena.bea.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category createCategory(String name, String description) {
        if (categoryRepository.existsByName(name)) {
            throw new IllegalStateException("Category already exists");

        }

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);


        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->
        new RuntimeException("Category Not Found"));
    }
}
