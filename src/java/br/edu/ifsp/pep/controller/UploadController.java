/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

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
    
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadController() {
    }
    
    
    
}
