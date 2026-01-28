package com.BackEndArena.bea.category;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/categories")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCategoryController {

    private CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ADMIN: create category
    @PostMapping

    public ResponseEntity<Category> createCategory(
            @RequestParam String name,
            @RequestParam(required = false) String description
    ) {
        return ResponseEntity.ok(
                categoryService.createCategory(name, description)
        );
    }



}
