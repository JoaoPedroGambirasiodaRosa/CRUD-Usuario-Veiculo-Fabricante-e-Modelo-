package br.ulbra.model;
/**
 * @author Jeferson
 */
public class Modelo {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    private int id;
    private String modelo;
    private String marca;
    private int ano;
    
    public Modelo() {
        
    }
    

    
    @Override
    public String toString() {
        return "Modelo{" + "id=" + id + ", Modelo=" + modelo + ", Marca=" + marca + ", Ano=" + ano +'}';
    }
    
}
