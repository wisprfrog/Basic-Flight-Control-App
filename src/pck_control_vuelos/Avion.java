
package pck_control_vuelos;

import java.io.Serializable;

/**
 *
 * @author ZEPPMACED INC.
 */
public class Avion implements Serializable {

    protected int idAvion;
    protected String modelo;
    protected String marca;
    protected int capacidadTanque;
    protected int noMotores;
    protected float velocidad;

    public Avion(int idAvion, String modelo, String marca, int capacidadTanque, int noMotores, float velocidad) {
        this.idAvion = idAvion;
        this.modelo = modelo;
        this.marca = marca;
        this.capacidadTanque = capacidadTanque;
        this.noMotores = noMotores;
        this.velocidad = velocidad;
    }

    public Avion() {
        this(0, null, null, 0, 0, 0.0f);
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCapacidadTanque(int capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }

    public void setNoMotores(int noMotores) {
        this.noMotores = noMotores;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getCapacidadTanque() {
        return capacidadTanque;
    }

    public int getNoMotores() {
        return noMotores;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public String getDatos() {
        return "Id Avion: " + getIdAvion()
                + "\nModelo: " + getModelo()
                + "\nMarca: " + getMarca()
                + "\nCapacidad de tanque: " + getCapacidadTanque() + " lts"
                + "\nNo. de motores: " + getNoMotores()
                + "\nVelocidad: " + getVelocidad() + " Km/h";

    }

}