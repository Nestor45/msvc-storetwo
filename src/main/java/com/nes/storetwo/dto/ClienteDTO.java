package com.nes.storetwo.dto;

import com.nes.storetwo.models.entity.Cliente;

public class ClienteDTO {
    private String nombre;
    private String razonSocial;
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}
