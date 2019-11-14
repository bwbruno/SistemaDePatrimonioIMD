package br.imd.ufrn.sistema.db;

import br.imd.ufrn.sistema.models.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaData extends SQLiteDB {

  public CategoriaData() {
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Categoria (" +
        "ID_Categoria INTEGER PRIMARY KEY AUTOINCREMENT," +
        "Nome TEXT NOT NULL," +
        "Descricao TEXT);");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public void create(Categoria c) {
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("INSERT INTO Categoria(Nome,Descricao) VALUES(?,?);");

      stm.setString(1, c.getNome());
      stm.setString(2, c.getDescricao());

      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public List<Categoria> all() {
    ArrayList<Categoria> result = new ArrayList<>();

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM Categoria ORDER BY ID_Categoria ASC;");
      ResultSet rs = stm.executeQuery();

      while (rs.next()) {
        Categoria c = new Categoria(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3)
        );

        result.add(c);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return result;
  }


  public void update(Categoria c) {

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("UPDATE Categoria SET " +
        "Nome = ?," +
        "Descricao = ? WHERE ID_Categoria = ?;");

      stm.setString(1, c.getNome());
      stm.setString(2, c.getDescricao());
      stm.setInt(3, c.getCodigo());

      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public void delete(Categoria c) {

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("DELETE FROM Categoria WHERE ID_Categoria = ?;");
      stm.setInt(1, c.getCodigo());
      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public Categoria find(int pk) {
    Categoria result = null;
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM Categoria WHERE ID_Categoria = ?;");

      stm.setInt(1, pk);
      ResultSet rs = stm.executeQuery();

      if (rs.next()) {
        result = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return result;
  }
}
