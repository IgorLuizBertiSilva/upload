/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

/**
 *
 * @author aluno
 */
@Named
@RequestScoped

public class UploadController {

    private final String DIRETORIO = "/home/aluno/uploads/";

    private UploadedFile file;

    private UploadedFiles files;

    private List<DefaultStreamedContent> fotos;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public void criarArquivoEmDisco(UploadedFile file) {
        String nomeArquivo = UUID.randomUUID() + " -- " + file.getFileName();

        Path arquivo = Paths.get(DIRETORIO + nomeArquivo);

        try {
            Files.copy(file.getInputStream(), arquivo);
            Util.addMessageInformation("Arquivo enviado: " + nomeArquivo);
        } catch (IOException ex) {
            ex.printStackTrace();
            Util.addMessageError(ex.getMessage());
        }
    }

    public void upload() {
        if (this.file != null) {
            System.out.println("Chegou arquivo: " + this.file.getFileName() + " e seu tama"
                    + "nho é de: " + this.file.getSize() + "bitys");
            criarArquivoEmDisco(this.file);

        }
    }

    public void uploadMultiple() {
        if (this.files != null) {

            for (UploadedFile file1 : files.getFiles()) {
                System.out.println("Chegou arquivo: " + file1.getFileName() + " e seu tama"
                        + "nho é de: " + file1.getSize() + "bitys");
                criarArquivoEmDisco(file1);
            }

        }
    }

    public StreamedContent getImage() {
        try {
            
            return DefaultStreamedContent.builder()
                    .contentType("image/png")
                    .stream(() -> {
                        try {
                            File file = new File(DIRETORIO + "Com Titulo.png");
                            return new FileInputStream(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    })
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
    }

    public List<DefaultStreamedContent> getFotos() {

        File pasta = new File(DIRETORIO + "originais/");

        File[] arquivos = pasta.listFiles();

        for (File arquivo : arquivos) {
            
            
            try {
                fotos.add(DefaultStreamedContent.builder()
                        .contentType("image/png")
                        .stream(() -> {
                            try {
                                
                                return new FileInputStream(arquivo);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .build());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
        
        for (StreamedContent foto : fotos) {
            System.out.println(foto.getName());
        }

        return fotos;
    }
    
 

    public UploadController() {
        fotos = new ArrayList<>();
    }

}
