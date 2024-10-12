
package pck_control_vuelos;

/**
 *
 * @author ZEPPMACED INC.
 */
public class Carga extends Avion {

    private int noPallets;
    private float volumen;
    private float capacidad;

    public Carga() {
        this.noPallets = 0;
        this.volumen = 0.0f;
        this.capacidad = 0.0f;
    }

    public Carga(int idAvion, String modelo, String marca, int capacidadTanque, int noMotores,
            float velocidad, int noPallets, float volumen, float capacidad) {
        super(idAvion, modelo, marca, capacidadTanque, noMotores, velocidad);
        this.noPallets = noPallets;
        this.volumen = volumen;
        this.capacidad = capacidad;
    }

    public void setNoPallets(int noPallets) {
        this.noPallets = noPallets;
    }

    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    public void setCapacidad(float capacidad) {
        this.capacidad = capacidad;
    }

    public int getNoPallets() {
        return noPallets;
    }

    public float getVolumen() {
        return volumen;
    }

    public float getCapacidad() {
        return capacidad;
    }

    @Override
    public String getDatos() {
        return  super.getDatos()
                + "\nNo. de Pallets: " + getNoPallets()
                + "\nVolúmen: " + getVolumen()
                + "\nCapacidad: " + getCapacidad();

    }

}
