/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionstock;

/**
 *
 * @author ATLAS PRO ELECTRO
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestProduit {
    private static Scanner scanner = new Scanner(System.in);
    private static GestionStock gestionStock = new GestionStock();

    public static void main(String[] args) {
        int choix;
        do {
            System.out.println("MENU");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Ajouter un produit frais");
            System.out.println("3. Trouver un produit par ID");
            System.out.println("4. Trouver des produits par nom");
            System.out.println("5. Afficher tous les produits");
            System.out.println("6. Vendre un produit");
            System.out.println("7. Faire une livraison");
            System.out.println("8. Augementer la quantité");
            System.out.println("9. Afficher le nombre de produits en stock");
            System.out.println("10. Supprimer un produit");
            System.out.println("11. Supprimer les produits périmés ");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // pour consommer le retour à la ligne
            switch (choix) {
                case 1:
                    ajouterProduit();
                    break;
                case 2:
                    ajouterProduitFrais();
                    break;
                case 3:
                    trouverProduitParId();
                    break;
                case 4:
                    trouverProduitParNom();
                    break;
                case 5:
                    afficherTousLesProduits();
                    break;
                    
                case 6:
                    vendreunproduit();
                    break;
                    
                case 7:
                    faireLivraison();
                    break;
                case 8:
                    augementerqte();
                    break;
                case 9:
                    afficherNombreProduitsEnStock();
                    break;
                case 10:
                    supprimerProduit();
                    break;
                case 11:
                     gestionStock.supprimerProduitPerime();
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
            System.out.println();
        } while (choix != 0);
    }

    private static void ajouterProduit() {

        System.out.print("Entrez le nom du produit : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez la quantité du produit : ");
        int qte = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        gestionStock.ajouterProduit(nom, qte);
        System.out.println("Le produit a été ajouté avec succès !");
    }

    private static void ajouterProduitFrais() {

        System.out.print("Entrez le nom du produit : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez la quantité du produit : ");
        int qte = scanner.nextInt();
        System.out.print("Entrez la température du produit : ");
        int temperature = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez la date d'expiration (dd/MM/yyyy) : ");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date datePeremption = null;
        try {
            String input = scanner.nextLine();
            datePeremption = dateFormat.parse(input);
        } catch (ParseException ex) {
            System.out.print("Le format de la date est invalide! ");
            Logger.getLogger(TestProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        gestionStock.ajouterProduitFrais(nom, qte, datePeremption, temperature);
        System.out.println("Le produit a été ajouté avec succès !");
    }    
    
    private static void trouverProduitParId() {
        System.out.print("Entrez l'ID du produit à trouver : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        Produit produit = gestionStock.trouverProduitParId(id);
        if (produit != null) {
            System.out.println(produit.toString());
        } else {
            System.out.println("Aucun produit trouvé avec cet ID !");
        }
    }

    private static void trouverProduitParNom() {
        System.out.print("Entrez le nom du produit à trouver : ");
        String nom = scanner.nextLine();
        ArrayList<Produit> produitsTrouves = gestionStock.trouverProduitParNom(nom);
        if (!produitsTrouves.isEmpty()) {
            for (Produit produit : produitsTrouves) {
                System.out.println(produit.toString());
            }
        } else {
            System.out.println("Aucun produit trouvé avec ce nom !");
        }
    }
    
    private static void afficherTousLesProduits() {
        ArrayList<Produit> produits = gestionStock.getListProduits();
        if (!produits.isEmpty()) {
            for (Produit produit : produits) {
                System.out.println(produit.toString());
            }
        } else {
            System.out.println("Aucun produit trouvé !");
        }
    }

    private static void faireLivraison() {
        System.out.print("Entrez l'ID du produit à livrer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        System.out.print("Entrez la quantité à diminuer du stock : ");
        int qte = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        gestionStock.livraison(id, qte);
        
        Produit produit = gestionStock.trouverProduitParId(id);
        if (produit == null) {
                   System.out.println("Problème: La livraison n'a pas été effectuée!");
        }
        else{
        System.out.println("La livraison a été effectuée avec succès !");
        System.out.println("Détails du produit livré :");
        System.out.println(produit);
        }
       
    }
    
        private static void augementerqte() {
        System.out.print("Entrez l'ID du produit à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        System.out.print("Entrez la quantité à augementer du stock : ");
        int qte = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        gestionStock.augementerquantite(id, qte);
        
        Produit produit = gestionStock.trouverProduitParId(id);
        if (produit == null) {
                   System.out.println("Problème: La livraison n'a pas été effectuée!");
        }
        else{
        System.out.println("La modification a été effectuée avec succès !");
        System.out.println("Détails du produit livré :");
        System.out.println(produit);
        }
       
    }

    private static void afficherNombreProduitsEnStock() {
        if(gestionStock.nombreEnStock() == -1){
         System.out.println("Problème: Le Produit n'existe pas en stock!");
        }
        else{
        System.out.println("Il y a " + gestionStock.nombreEnStock() + " produit(s) en stock.");
        }
    }
    
        private static void vendreunproduit() {
        System.out.print("Entrez l'ID du produit désiré : ");
        int id = scanner.nextInt();
        if(gestionStock.vendreun(id) == -1){
         System.out.println("Problème: Le Produit n'existe pas en stock!");
        }
        else{
        System.out.println("succés de l'opération !");
        }
    }
    

    private static void supprimerProduit() {
        System.out.print("Entrez l'ID du produit à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // pour consommer le retour à la ligne
        Produit produit = gestionStock.trouverProduitParId(id);
        if (produit == null) {
            System.out.println("La suppression a échoué : aucun produit trouvé avec cet ID !");
        }
        else{
            gestionStock.supprimerProduit(id);
            System.out.println("Le produit a été supprimé avec succès !");
        
        }
    }
}