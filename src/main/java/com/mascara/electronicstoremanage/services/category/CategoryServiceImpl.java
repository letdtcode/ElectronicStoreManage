package com.mascara.electronicstoremanage.services.category;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 09/04/2024
 * Time      : 7:45 CH
 * Filename  : CategoryServiceImpl
 */
public class CategoryServiceImpl implements CategoryService {
//    private Categ categoryRepository;

    @Override
    public String getHello() {
//        Category category = categoryRepository.findById(1L).get();
//        return category.getCategoryName();
        return "Hello";
    }
}
