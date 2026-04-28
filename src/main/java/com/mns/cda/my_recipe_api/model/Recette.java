package com.mns.cda.my_recipe_api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mns.cda.my_recipe_api.view.RecetteView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Le nom ne peut pas être vide")
    @JsonView(RecetteView.class)
    protected String nom;
}
