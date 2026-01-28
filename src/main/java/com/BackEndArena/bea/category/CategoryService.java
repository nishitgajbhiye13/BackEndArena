package com.BackEndArena.bea.category;

import java.util.List;

public interface CategoryService {
    Category createCategory(String name, String description);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);
}
