package ru.gb.internetshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.internetshop.entities.Category;
import ru.gb.internetshop.repositories.CategoryRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle (String title){
        return categoryRepository.findByTitle(title);
    }
}
