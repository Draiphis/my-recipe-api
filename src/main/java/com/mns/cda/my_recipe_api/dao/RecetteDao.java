package com.mns.cda.my_recipe_api.dao;


import com.mns.cda.my_recipe_api.model.Recette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetteDao extends JpaRepository<Recette, Integer> {
}
