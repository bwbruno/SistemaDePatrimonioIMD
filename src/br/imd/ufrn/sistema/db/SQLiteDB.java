package br.imd.ufrn.sistema.db;

import br.imd.ufrn.sistema.models.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDB {

  private static final String SQLCONN = "jdbc:sqlite:db.sqlite";

  protected Connection conn;

  public Connection open() {
    try {
      conn = DriverManager.getConnection(SQLCONN);
      return conn;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public void close() {
    try {
      if (conn != null)
        conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
