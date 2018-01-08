package com.dao.memory;

import com.dao.FilmDAO;
import com.videoondemand.model.DataModel;
import com.videoondemand.model.Product;

import java.util.List;

/**
 * Created by Simone on 28/11/2017.
 */
public class FilmMemoryDAOImpl implements FilmDAO {
    private static FilmMemoryDAOImpl instance;

    private FilmMemoryDAOImpl(){
    }

    public static synchronized FilmMemoryDAOImpl getInstance(){
        if(instance==null){
            instance=new FilmMemoryDAOImpl();
        }
        return instance;
    }
private List<Product> database = DataModel.getProductList();
    @Override
    public void insert(Product f) {
        database.add(f);
    }

    @Override
    public List<Product> findAll(String order) {
        return this.database;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public void update(Product f) {

    }

    @Override
    public void delete(Product f) {

    }
}
