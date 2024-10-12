/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pck_control_vuelos;

import java.io.Serializable;
import pck_fecha.Fecha;

/**
 *
 * @author Anddy
 */
public class Vuelo implements Serializable {
    private int idVuelo;
    private int idAvion;
    private String idPiloto;
    private String cdOrigen;
    private String cdDestino;
    private Fecha fechaSalida;
    private Fecha fechaLlegada;
    
    public Vuelo(int idVuelo, int idAvion, String idPiloto, String cdOrigen, String cdDestino, Fecha fechaSalida, Fecha fechaLlegada){
        this.idVuelo = idVuelo;
        this.idAvion = idAvion;
        this.idPiloto = idPiloto;
        this.cdOrigen = cdOrigen;
        this.cdDestino = cdDestino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
    }
    
   public Vuelo(int idVuelo, int idAvion, String idPiloto, String cdOrigen, String cdDestino, int diaS, int mesS, int anioS, int diaL, int mesL, int anioL){
        this.idVuelo = idVuelo;
        this.idAvion = idAvion;
        this.idPiloto = idPiloto;
        this.cdOrigen = cdOrigen;
        this.cdDestino = cdDestino;
        this.fechaSalida = new Fecha (diaS,mesS,anioS);
        this.fechaLlegada = new Fecha (diaL,mesL,anioL);
    }
   
    public Vuelo (){
        this(0,0,null,null,null,1,1,1999,1,1,1999);
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }

    public void setCdOrigen(String cdOrigen) {
        this.cdOrigen = cdOrigen;
    }

    public void setCdDestino(String cdDestino) {
        this.cdDestino = cdDestino;
    }

    public void setFechaSalida(Fecha fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    public boolean setFechaSalida(int dS, int mS, int aS){
        this.fechaSalida.setFecha(dS,mS,aS);
        return fechaSalida.fechaCorrecta();
    }

    public void setFechaLlegada(Fecha fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
    
    public boolean setFechaLlegada(int dL, int mL, int aL){
        this.fechaLlegada.setFecha(dL,mL,aL);
        return fechaLlegada.fechaCorrecta();
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public String getCdOrigen() {
        return cdOrigen;
    }

    public String getCdDestino() {
        return cdDestino;
    }

    public Fecha getFechaSalida() {
        return fechaSalida;
    }

    public Fecha getFechaLlegada() {
        return fechaLlegada;
    }
    
    public String getDatos(){
        return  "\n Id del Vuelo: "+getIdVuelo()+
                "\n Id del Avion: "+getIdAvion()+
                "\n Id del Piloto: "+getIdPiloto()+
                "\n Cd de Origen: "+getCdOrigen()+
                "\n Cd de Destino: "+getCdDestino()+
                "\n Fecha de Salida: "+getFechaSalida()+
                "\n Fecha de Llegada: "+getFechaLlegada();
    }
    
    
}
