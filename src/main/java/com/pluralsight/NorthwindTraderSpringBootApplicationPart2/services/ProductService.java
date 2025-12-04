package com.pluralsight.NorthwindTraderSpringBootApplicationPart2.services;

import com.pluralsight.NorthwindTraderSpringBootApplicationPart2.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTraderSpringBootApplicationPart2.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProductService provides a layer of abstraction between the controller and the data access object (DAO).
 * It contains the business logic to interact with transactions and uses the IProductDAO for database operations.
 */
@Component
// Indicates that this class is a Spring component. It will be automatically detected and instantiated by Spring.
public class ProductService {
    private IProductDAO ProductDAO; // Interface to data access object for transactions.

    /**
     * Autowired constructor for dependency injection.
     * Spring will inject an implementation of IProductDAO when ProductService is created.
     *
     * @param ProductDAO the DAO implementation to be used by this service.
     */
    @Autowired // Injects the ProductDAO implementation.
    public ProductService(IProductDAO ProductDAO) {
        this.ProductDAO = ProductDAO;
    }

    /**
     * Adds a new transaction.
     *
     * @param product the transaction to add.
     * @return the new transaction
     */
    public Product addProduct(Product product) {
        return ProductDAO.add(product);
    }

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions.
     */
    public List<Product> getAllProducts() {
        return ProductDAO.getAllProducts();
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId the ID of the transaction.
     * @return the transaction with the specified ID, or null if not found.
     */
    public Product getProductById(int transactionId) {
        return ProductDAO.getProductById(transactionId);
    }

    /**
     * Updates an existing transaction.
     *
     * @param product the transaction with updated details.
     */
    public void updateProduct(int transactionId, Product product) {
        ProductDAO.update(transactionId, product);
    }

    /**
     * Deletes a transaction.
     *
     * @param transactionId The ID of the transaction to delete.
     */
    public void deleteProduct(int transactionId) {
        ProductDAO.delete(transactionId);
    }
}