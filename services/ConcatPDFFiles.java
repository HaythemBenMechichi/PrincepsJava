/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

/**
 *
 * @author Dhia werteteni
 */
public class ConcatPDFFiles {
    
    public void concat(){
        try {
            PDFMergerUtility ut = new PDFMergerUtility();
            File f1 = new File("C:\\test\\Order.pdf");
            ut.addSource(f1);
           
            Desktop.getDesktop().open(new File("C:\\test\\Order.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(ConcatPDFFiles.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
