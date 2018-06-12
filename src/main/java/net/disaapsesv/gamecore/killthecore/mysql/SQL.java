package net.disaapsesv.gamecore.killthecore.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class SQL
{
  public static boolean tableExists(String table)
  {
    if (table == null) {
      return false;
    }
    try
    {
      Connection connection = MySQL.getConnection();
      if (connection == null) {
        return false;
      }
      DatabaseMetaData metadata = connection.getMetaData();
      if (metadata == null) {
        return false;
      }
      ResultSet rs = metadata.getTables(null, null, table, null);
      if (rs.next()) {
        return true;
      }
    }
    catch (Exception localException) {}
    return false;
  }

  public static void insertData(String columns, String values, String table)
  {
    MySQL.update("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")");
  }

  public static void deleteData(String column, String logic_gate, String data, String table)
  {
    if (data != null) {
      data = "'" + data + "'";
    }
    MySQL.update("DELETE FROM " + table + " WHERE " + column + logic_gate + data + ";");
  }

  public static boolean exists(String column, String data, String table)
  {
    if (data != null) {
      data = "'" + data + "'";
    }
    try
    {
      ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + "=" + data);
      while (rs.next()) {
        if (rs.getString(column) != null) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }

  public static void deleteTable(String table)
  {
    MySQL.update("DROP TABLE " + table + ";");
  }

  public static void truncateTable(String table)
  {
    MySQL.update("TRUNCATE TABLE " + table + ";");
  }

  public static void createTable(String table, String columns)
  {
    if (!tableExists(table)) {
      MySQL.update("CREATE TABLE " + table + " (" + columns + ")");
    }
  }

  public static void set(String selected, Object object, String column, String logic_gate, String data, String table)
  {
    if (object != null) {
      object = "'" + object + "'";
    }
    if (data != null) {
      data = "'" + data + "'";
    }
    MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + column + logic_gate + data + ";");
  }

  public static Object get(String selected, String column, String logic_gate, String data, String table)
  {
    if (data != null) {
      data = "'" + data + "'";
    }
    try
    {
      ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data);
      if (rs.next()) {
        return rs.getObject(selected);
      }
    }
    catch (Exception localException) {}
    return null;
  }

  public int countRows(String table)
  {
    int i = 0;
    if (table == null) {
      return i;
    }
    ResultSet rs = MySQL.query("SELECT * FROM " + table);
    try
    {
      while (rs.next()) {
        i++;
      }
    }
    catch (Exception localException) {}
    return i;
  }
}

