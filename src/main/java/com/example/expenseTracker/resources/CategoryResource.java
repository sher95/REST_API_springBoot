package com.example.expenseTracker.resources;


import com.example.expenseTracker.domain.Category;
import com.example.expenseTracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {


    @Autowired
    CategoryService categoryService;


    @GetMapping("")
    public String getAllCategories(HttpServletRequest request){
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated! UserId: " + userId;
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request, @RequestBody Map<String, Object> categoryMap){

        int userId = (Integer) request.getAttribute("userID");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);



    }
}
