package br.ulbra.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeferson
 */
public class Fabricante {

    private int id;
    private String marca;
    private String telefone;
    private String site;
    private String email;
    
    public Fabricante() {
        
    }
    
    public BigInteger criptografarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md5;
        BigInteger senhacrip = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(senha.getBytes(), 0, senha.length());
            senhacrip = new BigInteger(1, md5.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Fabricante.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senhacrip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", marca=" + marca + ", email=" + email + ", telefone=" + telefone + ", site=" + site + '}';
    }
    
}
