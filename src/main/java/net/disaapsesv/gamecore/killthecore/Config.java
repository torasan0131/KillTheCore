package net.disaapsesv.gamecore.killthecore;

public class Config
{
  private static final String host = "host";
  private static final String user = "user";
  private static final String password = "password";
  private static final String database = "database";
  private static final String port = "port";

  public static void clear()
  {
    set("host", "", false);
    set("user", "", false);
    set("password", "", false);
    set("database", "", false);
    set("port", "3306", false);
  }

  public static void create()
  {
    set("host", "", true);
    set("user", "", true);
    set("password", "", true);
    set("database", "", true);
    set("port", "3306", true);
  }

  public static void reload()
  {
    GameCore.plugin.reloadConfig();
    create();
  }

  public static void setHost(String s)
  {
    if (!getHost().equalsIgnoreCase(s)) {
      set("host", s, false);
    }
  }

  public static void setUser(String s)
  {
    if (!getUser().equalsIgnoreCase(s)) {
      set("user", s, false);
    }
  }

  public static void setPassword(String s)
  {
    if (!getPassword().equalsIgnoreCase(s)) {
      set("password", s, false);
    }
  }

  public static void setDatabase(String s)
  {
    if (!getDatabase().equalsIgnoreCase(s)) {
      set("database", s, false);
    }
  }

  public static void setPort(String s)
  {
    if (!getPort().equalsIgnoreCase(s)) {
      set("port", s, false);
    }
  }

  public static String getHost()
  {
    return get("host");
  }

  public static String getUser()
  {
    return get("user");
  }

  public static String getPassword()
  {
    return get("password");
  }

  public static String getDatabase()
  {
    return get("database");
  }

  public static String getPort()
  {
    return get("port");
  }

  private static void set(String name, Object value, boolean checkIfExists)
  {
    if ((name == null) || (value == null) || ((checkIfExists) && (GameCore.plugin.getConfig().contains(name)))) {
      return;
    }
    GameCore.plugin.getConfig().set(name, value);
    GameCore.plugin.saveConfig();
  }

  private static String get(String name)
  {
    return (name == null) || (!GameCore.plugin.getConfig().contains(name)) ? "" : GameCore.plugin.getConfig().getString(name);
  }
}
