
package pck_control_vuelos;

/**
 *
 * @author ZEPPMACED INC.
 */
public class Pasajeros extends Avion {

    private int noPasajeros;
    private String clases;
    private int noTripulantes;

    public Pasajeros() {
        this.noPasajeros = 0;
        this.clases = null;
        this.noTripulantes = 0;
    }

    public Pasajeros(int idAvion, String modelo, String marca, int capacidadTanque, int noMotores,
            float velocidad, int noPasajeros, String clases, int noTripulantes) {
        super(idAvion, modelo, marca, capacidadTanque, noMotores, velocidad);
        this.noPasajeros = noPasajeros;
        this.clases = clases;
        this.noTripulantes = noTripulantes;
    }

    public void setNoPasajeros(int noPasajeros) {
        this.noPasajeros = noPasajeros;
    }

    public void setClases(String clases) {
        this.clases = clases;
    }

    public void setNoTripulantes(int noTripulantes) {
        this.noTripulantes = noTripulantes;
    }

    public int getNoPasajeros() {
        return noPasajeros;
    }

    public String getClases() {
        return clases;
    }

    public int getNoTripulantes() {
        return noTripulantes;
    }

    @Override
    public String getDatos() {
        return  super.getDatos()
                + "\nNo. de Pasajeros: " + getNoPasajeros()
                + "\nClases: " + getClases()
                + "\nNo. Tripulantes: " + getNoTripulantes();

    }
}