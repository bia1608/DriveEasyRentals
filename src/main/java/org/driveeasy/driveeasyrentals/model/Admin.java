package org.driveeasy.driveeasyrentals.model;

class Admin {
    private String username;
    private String password; // plain-text for Phase 1; hash in Phase 2

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getUsername() {
        return username;
    }
}