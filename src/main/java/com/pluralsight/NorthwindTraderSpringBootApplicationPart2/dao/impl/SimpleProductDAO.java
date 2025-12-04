package com.pluralsight.NorthwindTraderSpringBootApplicationPart2.dao.impl;


import com.pluralsight.NorthwindTraderSpringBootApplicationPart2.dao.interfaces.ITransactionDAO;
import com.pluralsight.NorthwindTraderSpringBootApplicationPart2.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleTransactionDAO implements ITransactionDAO {
    private List<Product> products;

    public SimpleTransactionDAO() {
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
            if (t.getTransactionId() > maxId) {
                maxId = t.getTransactionId();
            }
        }
        product.setTransactionId(maxId + 1); // Increment max ID by 1
        products.add(product);
        return product;
    }


    @Override
    public List<Product> getAllTransactions() {
        return products;
    }

    @Override
    public Product getTransactionById(int transactionId) {
        for (Product product : products) {
            if (product.getTransactionId() == transactionId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(int transactionId, Product product) {
        int index = getTransactionIndex(transactionId);
        if (index != -1) {
            products.set(index, product);
        }
    }

    @Override
    public void delete(int transactionId) {
        int index = getTransactionIndex(transactionId);
        if (index != -1) {
            products.remove(index);
        }
    }

    private int getTransactionIndex(int transactionId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getTransactionId() == transactionId) {
                return i;
            }
        }
        return -1;
    }
}
