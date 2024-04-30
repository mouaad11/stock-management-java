/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.gestionstock;

/**
 *
 * @author ATLAS PRO ELECTRO
 */
import java.util.ArrayList;
import java.util.Date;


public interface GestionStockInterface {
    void ajouterProduit(String nom, int qte);
    void ajouterProduitFrais(String nom, int qte, Date datePeremption, int temperature);
    Produit trouverProduitParId(int id);
    ArrayList<Produit> trouverProduitParNom(String nom);
    ArrayList<Produit> getListProduits();
    void afficherDetails();
    void livraison(int id, int qte);
    int nombreEnStock();
    void supprimerProduit(int id);
    void supprimerProduitPerime();
}
