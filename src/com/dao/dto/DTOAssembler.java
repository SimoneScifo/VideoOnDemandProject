package com.dao.dto;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.videoondemand.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DTOAssembler {

    public Product getFilm(FilmDTO filmDTO){
        Product p = new Product(filmDTO.title, filmDTO.id_genre, filmDTO.year);
        p.setId(filmDTO.id);
        p.setCover(filmDTO.cover);
        return p;
    }

    public List<FilmDTO> getFilmsDTO(String order) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        List<FilmDTO> filmDTOList = new ArrayList<>();
        for(Product x : filmDAO.findAll(order)){
            filmDTOList.add(getFilmDTO(x));
        }
        return filmDTOList;
    }

    public FilmDTO getFilmDTO(Product film){
        return new FilmDTO(film);
    }
}
