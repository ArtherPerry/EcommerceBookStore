package com.example.bookstorecopy.service;


import com.example.bookstorecopy.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private CategoryRepo categoryRepository;

    @Autowired
    private AuthorRepo authorRepository;

    @Autowired
    private BookRepo bookRepository;
    @Autowired
    private CustomerBookOrderRepo customerBookOrderRepository;



    public Map<String, Object> calculateTrend(String type) {
        Map<String, Object> trend = new HashMap<>();

        LocalDate now = LocalDate.now();
        LocalDate lastMonth = now.minusMonths(1);

        int currentCount = 0;
        int previousCount = 0;

        switch(type) {
            case "category":
                currentCount = categoryRepository.countByCreatedDateAfter(lastMonth. atStartOfDay());
                previousCount = categoryRepository. countByCreatedDateBetween(
                        lastMonth.minusMonths(1).atStartOfDay(),
                        lastMonth. atStartOfDay()
                );
                break;
            case "author":
                currentCount = authorRepository.countByCreatedDateAfter(lastMonth.atStartOfDay());
                previousCount = authorRepository.countByCreatedDateBetween(
                        lastMonth.minusMonths(1).atStartOfDay(),
                        lastMonth.atStartOfDay()
                );
                break;
            case "book":
                currentCount = bookRepository.countByCreatedDateAfter(lastMonth.atStartOfDay());
                previousCount = bookRepository.countByCreatedDateBetween(
                        lastMonth.minusMonths(1).atStartOfDay(),
                        lastMonth.atStartOfDay()
                );
                break;
            case "order":
                currentCount = customerBookOrderRepository.countByCreatedDateAfter(lastMonth.atStartOfDay());
                previousCount = customerBookOrderRepository.countByCreatedDateBetween(
                        lastMonth.minusMonths(1).atStartOfDay(),
                        lastMonth.atStartOfDay()
                );
                break;
        }

        // Calculate percentage change
        double percentage = 0;
        if (previousCount > 0) {
            percentage = ((double)(currentCount - previousCount) / previousCount) * 100;
        } else if (currentCount > 0) {
            percentage = 100; // If no previous data, show 100% increase
        }

        trend.put("percentage", Math.abs(percentage));
        trend.put("isPositive", percentage >= 0);
        trend.put("hasData", currentCount > 0 || previousCount > 0);

        return trend;
    }
}
