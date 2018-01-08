package com.dao;

import com.videoondemand.model.Product;

import java.util.List;

/**
 * Created by Simone on 28/11/2017.
 */
public interface FilmDAO {
    void insert(Product f);
    List<Product> findAll(String order);
    Product findById(int id);
    void update(Product f);
    void delete(Product f);
}
