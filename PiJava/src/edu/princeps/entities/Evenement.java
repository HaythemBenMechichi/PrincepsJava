/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.princeps.entities;

import com.itextpdf.text.Document;
import edu.princeps.services.ServiceEvenement;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author zribi
 */
public class Evenement {

    int id;
    Date DateDebut, DateFin;
    String image, nom;

    public Evenement(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Evenement(String nom, Date DateDebut, Date DateFin, String image) {
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.image = image;
        this.nom = nom;
    }

    public Evenement(int id, String nom, Date DateDebut, Date DateFin, String image) {
        this.id = id;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.image = image;
        this.nom = nom;
    }

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
    
    
    /*
    public void pdf()
    {
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateAuj = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
      
        ServiceEvenement se=new ServiceEvenement();
        List<Evenement> lee=Evenement.getAll();
        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Nour Hammami\\Documents\\NetBeansProjects\\PIJavaFX\\src\\Apis\\"+String.valueOf(DateAuj + "produit.pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Rapport pour les produits : " + DateRapport);
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(6);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("id");
            table.addCell("nom produit");
            table.addCell("description");
            table.addCell("prix");
            table.addCell("image");
            table.addCell("id categorie");
            
            for (int i = 0; i < lee.size(); i++) {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(lee.get(i).getId()));
                table.addCell(lee.get(i).getNomProduit());
                table.addCell(lee.get(i).getDescriptionProduit());
                table.addCell(String.valueOf(lee.get(i).getPrixProduit()));
                table.addCell(lee.get(i).getPhoto());
                table.addCell(String.valueOf(lee.get(i).getQuantiteProduit()));
          
            
            }
           
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
    }*/



}
