/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.gestionstock;

/**
 *
 * @author ATLAS PRO ELECTRO
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class GestionStock implements GestionStockInterface {
       private Connection conn;

    public GestionStock() {
        // create a connection to the database
        String url = "jdbc:mysql://localhost:3306/gestionstock?useSSL=false";
        String user = "root";
        String password = "root";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connecté à la base de données");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void ajouterProduit(String nom, int qte) {
        try {
        // create a prepared statement
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO produit (nom, qte) VALUES (?, ?)");
            stmt.setString(1, nom);
            stmt.setInt(2, qte);

            // execute the statement
            stmt.executeUpdate();
            System.out.println("Produit ajouté avec succés");
        } catch (SQLException e) {
            System.out.println("Erreur d'ajout produit: " + e.getMessage());
        }
    }

    public void ajouterProduitFrais(String nom, int qte, Date datePeremption, int temperature) {
        try {
            // create a prepared statement
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO produitfrais (nom, qte, date_peremption, temperature) VALUES (?, ?, ?, ?)");
            stmt.setString(1, nom);
            stmt.setInt(2, qte);
            stmt.setDate(3, new java.sql.Date(datePeremption.getTime()));
            stmt.setInt(4, temperature);

            // execute the statement
            stmt.executeUpdate();
            System.out.println("Produit frais ajouté avec succés !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout du produit frais !: " + e.getMessage());
        }
    }

    public Produit trouverProduitParId(int id) {
        try {
            // create a prepared statement
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produit WHERE id = ?");
            stmt.setInt(1, id);

            // execute the statement
            ResultSet rs = stmt.executeQuery();

            // check if a product was found
            if (rs.next()) {
                String nom = rs.getString("nom");
                int qte = rs.getInt("qte");

                return new Produit(id,nom, qte);
            }
        } catch (SQLException e) {
            System.out.println("Erreur rechrche produit: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Produit> trouverProduitParNom(String nom) {
        ArrayList<Produit> produitsTrouves = new ArrayList<>();

        try {
            // create a prepared statement
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM produit WHERE nom = ?");
            stmt.setString(1, nom);

            // execute the statement
            ResultSet rs = stmt.executeQuery();

            // add all matching products to the list
            while (rs.next()) {
                int id = rs.getInt("id");
                int qte = rs.getInt("qte");

                produitsTrouves.add(new Produit(id,nom, qte));
            }
        } catch (SQLException e) {
            System.out.println("Erreur recherche produits: " + e.getMessage());
        }

        return produitsTrouves;
    }
public ArrayList<Produit> getListProduits() {
    ArrayList<Produit> produits = new ArrayList<>();

    try {
        // create a statement
        Statement stmt = conn.createStatement();

        // execute the query
        String sql = "SELECT * FROM produit";
        ResultSet rs = stmt.executeQuery(sql);

        // loop through the result set
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int qte = rs.getInt("qte");
            Date datePeremption = rs.getDate("date_peremption");
            int temperature = rs.getInt("temperature");

            // create a new Produit object and add it to the ArrayList
            if (datePeremption != null) {
                ProduitFrais produitFrais = new ProduitFrais(nom, qte, datePeremption, temperature);
                produitFrais.setId(id);
                produits.add(produitFrais);
            } else {
                Produit produit = new Produit(nom, qte);
                produit.setId(id);
                produits.add(produit);
            }
        }

        // close the result set and statement
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return produits;
}
public void livraison(int id, int qte) {
    try {
        Statement stmt = conn.createStatement();
        // Execute the SQL query to update the quantity of the product
        String sql = "UPDATE produit SET qte = qte - " + qte + " WHERE id = " + id;
        stmt.executeUpdate(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void augementerquantite(int id, int qte) {
    try {
        Statement stmt = conn.createStatement();
        // Execute the SQL query to update the quantity of the product
        String sql = "UPDATE produit SET qte = qte + " + qte + " WHERE id = " + id;
        stmt.executeUpdate(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public int nombreEnStock() {
    try {
        Statement stmt = conn.createStatement();
        // Execute the SQL query to get the count of rows in the table
        String sql = "SELECT count(*) FROM produit";
        ResultSet rs = stmt.executeQuery(sql);

        // Check if the result set has any rows
        if (rs.next()) {
            // Return the count
            return rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    // If there was an error or no rows were returned, return -1
    return -1;
}

public void supprimerProduit(int id) {
    try {
        Statement stmt = conn.createStatement();
        // Execute the SQL query to delete the product
        String sql = "DELETE FROM produit WHERE id = " + id;
        stmt.executeUpdate(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public int vendreun(int id) {
    try {
        Statement stmt = conn.createStatement();
        // Execute the SQL query to update the quantity of the product
        String sql = "UPDATE produit SET qte = qte - 1 WHERE id = " + id;
        int updatedRows = stmt.executeUpdate(sql);

        // Check if the product was found and updated
        if (updatedRows > 0) {
            // Execute a SELECT statement to get the updated quantity of the product
            sql = "SELECT qte FROM produit WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("qte");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1;
}

public void afficherDetails() {
    try {
        Statement stmt = conn.createStatement();
        // Execute the SQL query
        String sql = "SELECT * FROM produit";
        ResultSet rs = stmt.executeQuery(sql);

        // Loop through the result set and print out the product details
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int qte = rs.getInt("qte");
            Date date = rs.getDate("date_peremption");
            int temperature = rs.getInt("temperature");

            System.out.println("ID: " + id);
            System.out.println("Nom: " + nom);
            System.out.println("Quantité: " + qte);
            System.out.println("Date Peremption: " + date.toString());
            System.out.println("Tempétature: " + temperature);
            System.out.println();
        }

        // Close the result set, statement, and connection
        rs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void supprimerProduitPerime() {
    try {
        // create a prepared statement
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM produit WHERE date_peremption < ?");
        stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));

        // execute the statement
        int rowsDeleted = stmt.executeUpdate();
        System.out.println(rowsDeleted + " produit(s) frais supprimé(s) avec succès !");
    } catch (SQLException e) {
        System.out.println("Erreur suppression des produits frais périmés: " + e.getMessage());
    }
}

}
