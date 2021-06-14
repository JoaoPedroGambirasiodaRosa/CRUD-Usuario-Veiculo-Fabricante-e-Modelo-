/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeferson
 */
public class ModeloDao {
 Connection con;
    
    public ModeloDao() throws SQLException{
        con = ConnectionFactory.getConnection();
    }
    
    // SALVA O USUARIO NO BANCO DE DADOS   ---- C
    public void create(Modelo u) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        BigInteger cripto;
        try {
            stmt = con.prepareStatement("INSERT INTO tbmodelo (modelo,"
                    + "marca,ano) VALUE (?,?,?)");
            stmt.setString(1, u.getModelo());
            stmt.setString(2, u.getMarca());
            stmt.setInt(3, u.getAno());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modelo " + u.getModelo()
                    + " Salvo com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }   
    
     //listagem de modelo na tabela do formulario   ---   R
    
    public ArrayList<Modelo> read(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Modelo> Modelo = new ArrayList<Modelo>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbmodelo ORDER BY marca ASC");
            rs = stmt.executeQuery();
            while(rs.next()){
                Modelo v = new Modelo();
                v.setId(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setMarca(rs.getString("marca"));
                v.setAno(rs.getInt("ano"));
                Modelo.add(v);
 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Modelo>) Modelo;
    }
    
     public ArrayList<Modelo> readPesq(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Modelo> Modelo = new ArrayList<Modelo>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbmodelo WHERE marca LIKE ?");
            rs = stmt.executeQuery();
            while(rs.next()){
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setMarca(rs.getString("marca"));
                v.setAno(rs.getInt("ano"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Modelo>) Modelo;
    }
    
    
   // SALVA O USUARIO NO BANCO DE DADOS   ---- C
    public void create(Veiculo v){
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbmodelo (modelo,"
                    + "marca,ano) VALUE (?,?,?)");
            stmt.setString(1, v.getModelo());
            stmt.setString(2, v.getMarca());
            stmt.setInt(3, v.getAno());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O "+v.getModelo()
                    +" Salvo com Sucesso!!");
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //ALTERAR O USUARIO NO BANCO DE DADOS   -- U 
    public void update(Modelo v){
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbmodelo SET Modelo = ?, Marca = ?, Ano = ?");
            stmt.setString(1, v.getModelo());
            stmt.setString(2, v.getMarca());
            stmt.setInt(3, v.getAno());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modelo "+v.getModelo()
                    +" Modificado com Sucesso!!");
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    //excluir do banco de dados   --- D
    public void delete(Modelo v){
            PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbmodelo WHERE id = ?");
           
            stmt.setInt   (1, v.getId());
            
            if (JOptionPane.showConfirmDialog(null, "Tem certeza que"
                    + " deseja excluir este Modelo","Exclusão",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, "Modelo excluído com Sucesso!!");
                stmt.executeUpdate();
            }else{
                JOptionPane.showMessageDialog(null, "A exclusão do Modelo foi Cancelada com Sucesso!!");
            }
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
   
}
