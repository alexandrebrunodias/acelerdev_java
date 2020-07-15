package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    /**
     * Calculate the sum of all OrderItems
     */
    @Override
    public Double calculateOrderValue(List<OrderItem> items) {
        return items.stream().map(i -> {
            Product product = productRepository.findById(i.getProductId()).get();
            Double value = product.getIsSale() ? product.getValue() * .8 : product.getValue();
            return i.getQuantity() * value;
        }).reduce(0.0, Double::sum);
    }

    /**
     * Map from idProduct List to Product Set
     */
    @Override
    public Set<Product> findProductsById(List<Long> ids) {
        Set<Product> products = new HashSet<>();
        ids.forEach(id -> {
            Optional<Product> product = productRepository.findById(id);
            product.ifPresent(products::add);
        });
        return products;
    }

    /**
     * Calculate the sum of all Orders(List<OrderIten>)
     */
    @Override
    public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
        return orders.stream().map(this::calculateOrderValue).reduce(0.0, Double::sum);
    }

    /**
     * Group products using isSale attribute as the map key
     */
    @Override
    public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
        Map<Boolean, List<Product>> groups = new HashMap<>();
        List<Product> productsInSale = new ArrayList<>();
        List<Product> productsNotInSale = new ArrayList<>();

        productIds.forEach(productId -> {
            Product product = productRepository.findById(productId).get();
            if (product.getIsSale()) productsInSale.add(product);
            else productsNotInSale.add(product);
        });

        groups.put(true, productsInSale);
        groups.put(false, productsNotInSale);

        return groups;
    }

}