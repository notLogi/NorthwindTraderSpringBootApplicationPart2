package com.pluralsight.NorthwindTraderSpringBootApplicationPart2.dao.impl;


import com.pluralsight.NorthwindTraderSpringBootApplicationPart2.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTraderSpringBootApplicationPart2.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDAO implements IProductDAO {
    private List<Product> products;

    public SimpleProductDAO() {
        this.products = new ArrayList<>();
        // Add some initial transactions
        products.add(new Product(1, 150.75, "Amazon"));
        products.add(new Product(2, 89.99, "Walmart"));
        products.add(new Product(3, 200.00, "Best Buy"));
    }

    @Override
    public Product add(Product product) {
        int maxId = 0;
        for (Product t : products) {
            if (t.getProductId() > maxId) {
                maxId = t.getProductId();
            }
        }
        product.setProductId(maxId + 1); // Increment max ID by 1
        products.add(product);
        return product;
    }


    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int transactionId) {
        for (Product product : products) {
            if (product.getProductId() == transactionId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(int transactionId, Product product) {
        int index = getProductIndex(transactionId);
        if (index != -1) {
            products.set(index, product);
        }
    }

    @Override
    public void delete(int transactionId) {
        int index = getProductIndex(transactionId);
        if (index != -1) {
            products.remove(index);
        }
    }

    private int getProductIndex(int transactionId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == transactionId) {
                return i;
            }
        }
        return -1;
    }
}
