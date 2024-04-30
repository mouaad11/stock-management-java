/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionstock;

/**
 *
 * @author ATLAS PRO ELECTRO
 */
import java.util.Date;


public class ProduitFrais extends Produit {
    private Date datePeremption;
    private int temperature;

    
    //Constructeur ProduitFrais
    public ProduitFrais(String nom, int qte, Date datePeremption, int temperature) {
        super(nom, qte);
        this.datePeremption = datePeremption;
        this.temperature = temperature;
    }

    //Getters
    public Date getDatePeremption() {
        return datePeremption;
    }
    
        public int getTemperature() {
        return temperature;
    }

    //Setters
    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    //ToString
    @Override
    public String toString() {
        return "ProduitFrais{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", qte=" + getQte() +
                ", datePeremption=" + datePeremption +
                ", temperature en C°=" + temperature +
                '}';
    }
}

