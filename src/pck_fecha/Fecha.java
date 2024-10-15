package pck_fecha;

import java.io.Serializable;

public class Fecha implements Serializable{
    private int dia;
    private int mes;
    private int anio;
    
    public Fecha(){
        dia = 1;
        mes = 1;
        anio = 1900;
    }
    
    public Fecha(int d, int m, int a){
        dia = d;
        mes = m;
        anio = a;
    }
    
    public void setFecha(int d, int m, int a){
        dia = d;
        mes = m;
        anio = a;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }
    
    public int getAnio() {
        return anio;
    }
    
    //Este metodo es privado porque a una clase externa no le importara si es a;o bisiesto o no, solo quiere manejar fechas
    protected boolean bisiesto(){
        return ((anio%100!=0 && anio%4==0) || anio%400==0);
    }
    
    public boolean fechaCorrecta(){
        boolean diaCorrecto = false, mesCorrecto = false, anioCorrecto = false;
        
        anioCorrecto = anio >= 1940 && anio <= 2030;
        mesCorrecto = mes >= 1 && mes <= 12;
        
        int auxMes = 0;
        
        if(mes % 2 == 1) auxMes = 1; //Impares
        if(mes % 2 == 0) auxMes = 2; //Pares
        if(mes == 2) auxMes = 3; //Febrero
        
        switch(auxMes){
            case 1:
                    diaCorrecto = dia>=1 && dia<=31;
                break;
                
            case 2:
                    diaCorrecto = dia>=1 && dia<=30;
                break;
                
            case 3:
                if(bisiesto()){
                    diaCorrecto = dia>=1 && dia<=29;
                }
                else{
                    diaCorrecto = dia>=1 && dia<=28;
                }
        }
        
        return diaCorrecto && mesCorrecto && anioCorrecto;
    }
    
    public String getFecha(){
        return dia + "/" + mes + "/" + anio;
    }
}
