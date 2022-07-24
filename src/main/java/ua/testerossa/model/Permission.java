package ua.testerossa.model;

public enum Permission {
  CREATE("create"),
  READ("read"),
  UPDATE("update"),
  DELETE("delete");

  private final String permission;

  Permission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
