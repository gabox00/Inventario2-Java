package org.educa.util;

public enum Usuarios {
    USU_APP ("myApp");

    // The id database in the Table ROLE
    private final String name;

    Usuarios(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

}
