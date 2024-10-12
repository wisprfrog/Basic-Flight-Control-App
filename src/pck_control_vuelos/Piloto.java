/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck_control_vuelos;

import pck_fecha.Fecha;

/**
 *
 * @author diego
 */
public class Piloto {
    private String idPiloto;
    private String nombre;
    private String categoria;
    private String licencia;
    private Fecha fechaNacimiento;
    private int aniosExperiencia;

    public Piloto(String idPiloto, String nombre, String categoria, String licencia, Fecha fechaNacimiento, int aniosExperiencia) {
        this.idPiloto = idPiloto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.licencia = licencia;
        this.fechaNacimiento = fechaNacimiento;
        this.aniosExperiencia = aniosExperiencia;
    }
    
    public Piloto(String idPiloto, String nombre, String categoria, String licencia, int dia, int mes, int anio, int aniosExperiencia) {
        this.idPiloto = idPiloto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.licencia = licencia;
        this.fechaNacimiento = new Fecha(dia, mes, anio);
        this.aniosExperiencia = aniosExperiencia;
    }
    
    public Piloto(){
        this(null, null, null, null, 0, 0, 0, 0);
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public boolean setFechaNacimiento(int dia, int mes, int anio) {
        fechaNacimiento.setFecha(dia, mes, anio);
        return fechaNacimiento.fechaCorrecta();
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getLicencia() {
        return licencia;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.getFecha();
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }
    
    public String getDatos(){
        return  "Id Piloto: " + idPiloto +
                "\nNombre: " + nombre +
                "\nCategoria: " + categoria +
                "\nLicencia: " + licencia +
                "\nFecha de nacimiento: " + fechaNacimiento.getFecha() +
                "\nAnios de experiencia: " + aniosExperiencia;
    }
}
