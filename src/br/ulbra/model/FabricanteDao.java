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
 * @author Jeferson
 */
public class FabricanteDao {

    Connection con;

    public FabricanteDao() throws SQLException {
        con = ConnectionFactory.getConnection();
    }

    // SALVA O USUARIO NO BANCO DE DADOS   ---- C
    public void create(Fabricante u) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        BigInteger cripto;
        try {
            stmt = con.prepareStatement("INSERT INTO tbfabricante (marca,"
                    + "email,telefone,site) VALUE (?,?,?,?)");
            stmt.setString(1, u.getMarca());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getSite());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fabricante " + u.getMarca()
                    + " Salvo com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    //ALTERAR O USUARIO NO BANCO DE DADOS   -- U 
    public void update(Fabricante u) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        BigInteger cripto;
        try {
            stmt = con.prepareStatement("UPDATE tbfabricante SET marca = ?,"
                    + "email = ?, telefone = ? ,site = ? WHERE id = ?");
            stmt.setString(1, u.getMarca());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getSite());
            stmt.setInt(5, u.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fabricante " + u.getMarca()
                    + " Modificado com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //listagem de fabricante na tabela do formulario   ---   R

    public ArrayList<Fabricante> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fabricante> usuarios = new ArrayList<Fabricante>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfabricante ORDER BY marca ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Fabricante usuario = new Fabricante();
                usuario.setId(rs.getInt("id"));
                usuario.setMarca(rs.getString("marca"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSite(rs.getString("site"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Fabricante>) usuarios;
    }

    public ArrayList<Fabricante> readPesq(String marca) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fabricante> usuarios = new ArrayList<Fabricante>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfabricante WHERE marca LIKE ?");
            stmt.setString(1, "%" + marca + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Fabricante usuario = new Fabricante();
                usuario.setId(rs.getInt("id"));
                usuario.setMarca(rs.getString("marca"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSite(rs.getString("site"));
                usuario.setTelefone(rs.getString("telefone"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Fabricante>) usuarios;
    }

        
    //excluir do banco de dados   --- D
    public void delete(Fabricante u) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbfabricante WHERE id = ?");

            stmt.setInt(1, u.getId());

            if (JOptionPane.showConfirmDialog(null, "Tem certeza que"
                    + " deseja excluir este Fabricante", "Exclusão",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Fabricante excluído com Sucesso!!");
                stmt.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "A exclusão do Fabricante Cancelado com Sucesso!!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
