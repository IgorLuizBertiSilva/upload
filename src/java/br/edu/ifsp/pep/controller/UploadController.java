/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.util.Util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author aluno
 */
@Named
@RequestScoped
public class UploadController {

    private final String DIRETORIO = "/home/aluno/uploads/";
    
    private UploadedFile file;
    

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            System.out.println("Chegou arquivo: " + file.getFileName() + " e seu tama"
                    + "nho é de: " + file.getSize() + "bitys");
            
            String nomeArquivo = UUID.randomUUID()+ " -- " + file.getFileName();
            
            Path arquivo = Paths.get(DIRETORIO + nomeArquivo);
            
            try {
                Files.copy(file.getInputStream(), arquivo);
            } catch (IOException ex) {
                ex.printStackTrace();
                Util.addMessageError(ex.getMessage());
            }
            
            
            
            
        }
    }

    public UploadController() {
    }

}
