package br.imd.ufrn.sistema.db;

import br.imd.ufrn.sistema.models.Bem;
import br.imd.ufrn.sistema.models.Categoria;
import br.imd.ufrn.sistema.models.Localizacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BemData extends SQLiteDB {
  public BemData() {
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Bem (" +
        "ID_Bem INTEGER PRIMARY KEY AUTOINCREMENT," +
        "Nome TEXT NOT NULL, " +
        "Descricao TEXT, " +
        "ID_Categoria INTEGER NOT NULL, " +
        "ID_Localizacao INTEGER NOT NULL, " +
        "FOREIGN KEY(ID_Categoria) REFERENCES Categoria(ID_Categoria), " +
        "FOREIGN KEY(ID_Localizacao) REFERENCES Localizacao(ID_Localizacao) );");

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public void create(Bem b) {
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("INSERT INTO Bem(Nome,Descricao,ID_Categoria,ID_Localizacao) VALUES(?,?,?,?);");

      stm.setString(1, b.getNome());
      stm.setString(2, b.getDescricao());
      stm.setInt(3, b.getCategoria().getCodigo());
      stm.setInt(4, b.getLocalizacao().getCodigo());

      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public List<Bem> all() {
    ArrayList<Bem> result = new ArrayList<>();

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM Bem ORDER BY ID_Bem ASC;");
      ResultSet rs = stm.executeQuery();

      while (rs.next()) {
        Bem b = new Bem(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          Localizacao.find(rs.getInt(5)),
          Categoria.find(rs.getInt(4))
        );

        result.add(b);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return result;
  }


  public void update(Bem b) {

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("UPDATE Bem SET " +
        "Nome = ?," +
        "Descricao = ?," +
        "ID_Categoria = ?," +
        "ID_Localizacao = ? WHERE ID_Bem = ?;");

      stm.setString(1, b.getNome());
      stm.setString(2, b.getDescricao());
      stm.setString(3, String.valueOf(b.getCategoria().getCodigo()));
      stm.setString(4, String.valueOf(b.getLocalizacao().getCodigo()));
      stm.setInt(5, b.getCodigo());

      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public void delete(Bem b) {

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("DELETE FROM Bem WHERE ID_Bem = ?;");
      stm.setInt(1, b.getCodigo());
      stm.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }
  }

  public Bem find(int pk) {
    Bem result = null;
    open();

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM Bem WHERE ID_Bem = ?;");

      stm.setInt(1, pk);
      ResultSet rs = stm.executeQuery();

      CategoriaData categoriaData = new CategoriaData();
      LocalizacaoData localizacaoData = new LocalizacaoData();

      while (rs.next()) {
        result = new Bem(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          localizacaoData.find(rs.getInt(5)),
          categoriaData.find(rs.getInt(4))
        );
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return result;
  }

  public List<Bem> find(String column, String str) {
    ArrayList<Bem> result = new ArrayList<>();

    open();

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM Bem WHERE " + column + " LIKE '%" + str + "%';");
      ResultSet rs = stm.executeQuery();

      while (rs.next()) {
        Bem b = new Bem(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3),
          Localizacao.find(rs.getInt(5)),
          Categoria.find(rs.getInt(4))
        );

        result.add(b);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close();
    }

    return result;
  }


}
