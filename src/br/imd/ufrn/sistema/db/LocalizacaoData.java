package br.imd.ufrn.sistema.db;

import br.imd.ufrn.sistema.models.Localizacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalizacaoData extends SQLiteDB {

  public LocalizacaoData() {
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Localizacao (" +
        "ID_Localizacao INTEGER PRIMARY KEY AUTOINCREMENT," +
        "Nome TEXT NOT NULL," +
        "Descricao TEXT);");
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public void create(Localizacao l) {
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("INSERT INTO Localizacao(Nome,Descricao) VALUES(?,?);");

      stm.setString(1, l.getNome());
      stm.setString(2, l.getDescricao());

      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public List<Localizacao> all() {
    ArrayList<Localizacao> result = new ArrayList<>();

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM Localizacao ORDER BY ID_Localizacao ASC;");
      ResultSet rs = stm.executeQuery();

      while (rs.next()) {
        Localizacao l = new Localizacao(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3)
        );

        result.add(l);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return result;
  }


  public void update(Localizacao l) {

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("UPDATE Localizacao SET " +
        "Nome = ?," +
        "Descricao = ? WHERE ID_Localizacao = ?;");

      stm.setString(1, l.getNome());
      stm.setString(2, l.getDescricao());
      stm.setInt(3, l.getCodigo());

      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public void delete(Localizacao l) {

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("DELETE FROM Localizacao WHERE ID_Localizacao = ?;");
      stm.setInt(1, l.getCodigo());
      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public Localizacao find(int pk) {
    Localizacao result = null;
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM Localizacao WHERE ID_Localizacao = ?;");

      stm.setInt(1, pk);
      ResultSet rs = stm.executeQuery();

      if (rs.next()) {
        result = new Localizacao(rs.getInt(1), rs.getString(2), rs.getString(3));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return result;
  }
}
