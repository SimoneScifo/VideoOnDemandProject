package com.videoondemand.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 28/11/2017.
 */
public class DataModel {

    private static List<Product> productList;

    private DataModel() {}
    public static synchronized List<Product> getProductList() {
        if (productList == null) {
            productList = new ArrayList<>();
            productList.add(new Product("Fast and Furious",1,2016));
            productList.add(new Product("Saw Legacy",2,2017));
            productList.add(new Product("Titanic",5,2000));
        }
        return productList;
    }
}
