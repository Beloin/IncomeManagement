package com.income.management.service.category;

import com.income.management.exception.GenericTransactionException;
import com.income.management.model.category.Category;
import com.income.management.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    final private CategoryRepository catRepo;

    public CategoryService(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    public void createCategory(String name) {
        try {
            this.catRepo.createCategory(name);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
    }

    public List<Category> findAllCategories() {
        try {
            return this.catRepo.findCategories();
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteCategory(long id) {
        try {
            this.catRepo.deleteCategory(id);
        } catch (GenericTransactionException e) {
            e.printStackTrace();
        }
    }
}
