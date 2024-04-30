/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionstock;

import java.util.Objects;


/**
 *
 * @author ATLAS PRO ELECTRO
 */
public class Produit {
    protected int id;
    protected String nom;
    protected int qte;

    //Constructeur Produit
    public Produit(String nom, int qte) {
        this.nom = nom;
        this.qte = qte;
    }
    
        public Produit(int id,String nom, int qte) {
        this.id = id;
        this.nom = nom;
        this.qte = qte;
    }

    //méthode pour augementer la quantité
    public void augmenterQuantite(int id) {
        if (this.id == id) {
            this.qte++;
        }
    }

    //méthode pour diminer la quantité
    public void vendreUn() {
        if (this.qte > 0) {
            this.qte--;
        }
    }

    //ToString
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", qte=" + qte + '}';
    }


//equals
    @Override 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        return Objects.equals(this.nom, other.nom);
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getQte() {
        return qte;
    }

    
    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}

