package pck_control_vuelos;

//import para archivos
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import para objetos
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//excepciones
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.time.LocalDateTime;
//otras importaciones
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TestControlVuelos {

    public static void main(String args[]) {
        int idAvion_aux = -1, idVuelo_aux = -1, numPallets_aux = -1;
        int numPasaj_aux = -1, numTripul_aux = -1, aniosExp_aux = -1;
        int dia_aux = -1, mes_aux = -1, anio_aux = -1, pos_encontrad = -1, opc_confirm;
        
        float volum_aux = -1.0f, capCarga_aux = -1.0f;
        
        String clasesAv_aux = null, idPil_aux = null;
        String nomPil_aux = null, categPil_aux = null, licenPil_aux = null, cdOrigen_aux = null;
        String cdDestino_aux = null;
        
        LocalDateTime fecha_actual = LocalDateTime.now();
        //Lo anterior es un API que retorna el dia, el mes, el anio y los minutos
        //en un momento dado sin tener en cuenta la hora o la zona horaria
        boolean fech_correcta;

        ArrayList<Avion> lista_aviones = new ArrayList<>();
        ArrayList<Vuelo> lista_vuelos = new ArrayList<>();
        ArrayList<Piloto> lista_pilotos = new ArrayList<>();

        entradaListaAviones(lista_aviones);
        entradaListaVuelos(lista_vuelos);
        entradaListaPilotos(lista_pilotos);

        String menu = """
                      --- MENU CONTROL DE VUELOS ---
                  1)    Alta de un avion de pasajeros
                  2)    Alta de un avion de carga
                  3)    Alta de un piloto
                  4)    Alta de un vuelo
                  5)    Listar aviones de pasajeros
                  6)    Listar aviones de carga
                  7)    Listar pilotos
                  8)    Listar vuelos
                  9)    Ver detalle de un avion de pasajeros
                  10)   Ver detalle de un avion de carga
                  11)   Ver detalle de un piloto
                  12)   Ver detalle de un vuelo
                  13)   Eliminar un avion de pasajeros
                  14)   Eliminar un avion de carga
                  15)   Eliminar un piloto
                  16)   Elliminar un vuelo
                  17)   Salir
                        Digite una opcion:\n
                  """;

        int opc;

        do {
            do {
                try {
                    opc = Integer.parseInt(JOptionPane.showInputDialog(null, menu, 3));

                    if (opc < 0) {
                        JOptionPane.showMessageDialog(null, "No hay opciones negativas", "ERROR", 0);
                    }
                } catch (NumberFormatException e) {
                    opc = -1;
                    JOptionPane.showMessageDialog(null, "La opcion debe ser numerica", "ERROR", 0);
                }
            } while (opc < 0);

            switch (opc) {
                case 1: //ALTA DE UN AVION DE PASAJEROS
                    Avion avPasajero_aux = darAltaAvion(lista_aviones, 1);

                    //Guardando numero de pasajeros de avion
                    do {
                        try {
                            numPasaj_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de pasajeros del avion: ", 3));

                            if (numPasaj_aux <= 0) {
                                JOptionPane.showMessageDialog(null, "El numero de pasajeros debe ser mayor a 0", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            numPasaj_aux = -1;
                            JOptionPane.showMessageDialog(null, "El numero de pasajeros debe ser numerico", "ERROR", 0);
                        }
                    } while (numPasaj_aux <= 0);

                    ((Pasajeros) avPasajero_aux).setNoPasajeros(numPasaj_aux);

                    //Guardando las clases del avion
                    do {
                        clasesAv_aux = JOptionPane.showInputDialog(null, "Escriba las clases del avion de pasajeros: ", 3);

                        if (clasesAv_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar las clases del avion de pasajeros", "ERROR", 0);
                        }
                        if (!clasesAv_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "Las clases del avion de pasajeros no deben contener numeros ni caracteres especiales", "ERROR", 0);
                        }
                    } while (clasesAv_aux.isBlank() || !clasesAv_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    ((Pasajeros) avPasajero_aux).setClases(clasesAv_aux);

                    //Guardando el numero del tripulantes del avion
                    do {
                        try {
                            numTripul_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de tripulantes del avion de pasajeros: ", 3));

                            if (numTripul_aux <= 0) {
                                JOptionPane.showMessageDialog(null, "El numero de tripulantes debe ser mayor a 0", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            numTripul_aux = -1;
                            JOptionPane.showMessageDialog(null, "El numero de tripulantes debe ser numerico", "ERROR", 0);
                        }
                    } while (numTripul_aux <= 0);

                    ((Pasajeros) avPasajero_aux).setNoTripulantes(numTripul_aux);

                    lista_aviones.add(avPasajero_aux);

                    JOptionPane.showMessageDialog(null, "Avion de pasajeros dado de alta correctamente", "ENHORABUENA", 1);

                    break;

                case 2: //ALTA DE UN AVION DE CARGA
                    Avion avCarga_aux = darAltaAvion(lista_aviones, 2);

                    //Guardando numero de pallets del avion
                    do {
                        try {
                            numPallets_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de pallets del avion de carga: ", 3));

                            if (numPallets_aux <= 0) {
                                JOptionPane.showMessageDialog(null, "El numero de pallets debe ser mayor a 0", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            numPallets_aux = -1;
                            JOptionPane.showMessageDialog(null, "El numero de pallets debe ser numerico", "ERROR", 0);
                        }
                    } while (numPallets_aux <= 0);

                    ((Carga) avCarga_aux).setNoPallets(numPallets_aux);

                    //Guardando el volumen del avion
                    do {
                        try {
                            volum_aux = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite el volumen del avion de carga: ", 3));

                            if (volum_aux <= 0) {
                                JOptionPane.showMessageDialog(null, "El volumen debe ser mayor a 0", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            volum_aux = -1.0f;
                            JOptionPane.showMessageDialog(null, "El volumen debe ser numerico", "ERROR", 0);
                        }
                    } while (volum_aux < 0);

                    ((Carga) avCarga_aux).setVolumen(volum_aux);

                    //Guardando la capacidad del avion
                    do {
                        try {
                            capCarga_aux = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite la capacidad del avion de carga: ", 3));

                            if (capCarga_aux <= 0) {
                                JOptionPane.showMessageDialog(null, "La capacidad debe ser mayor a 0", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            capCarga_aux = -1.0f;
                            JOptionPane.showMessageDialog(null, "La capacidad debe ser numerica", "ERROR", 0);
                        }
                    } while (capCarga_aux < 0);

                    ((Carga) avCarga_aux).setCapacidad(capCarga_aux);

                    lista_aviones.add(avCarga_aux);

                    JOptionPane.showMessageDialog(null, "Avion de carga dado de alta correctamente", "ENHORABUENA", 1);

                    break;

                case 3: //ALTA DE UN PILOTO
                    Piloto piloto_aux = new Piloto();

                    //Guardando Id del piloto
                    do {
                        idPil_aux = JOptionPane.showInputDialog(null, "Escriba el Id del piloto: ", 3);

                        if (idPil_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar el Id del piloto", "ERROR", 0);
                        }
                        if (!idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "El Id del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                        if (!idPil_aux.isBlank() && idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            pos_encontrad = buscarIdPiloto(lista_pilotos, idPil_aux);

                            if (pos_encontrad != -1) {
                                JOptionPane.showMessageDialog(null, "Un piloto ya cuenta con ese Id, digite otro", "ERROR", 0);
                            }
                        }
                    } while (idPil_aux.isBlank() || !idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+") || pos_encontrad != -1);

                    piloto_aux.setIdPiloto(idPil_aux);

                    //Guardando nombre del piloto
                    do {
                        nomPil_aux = JOptionPane.showInputDialog(null, "Escriba el nombre del piloto: ", 3);

                        if (nomPil_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar el nombre del piloto", "ERROR", 0);
                        }
                        if (!nomPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "El nombre del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                    } while (nomPil_aux.isBlank() || !nomPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    piloto_aux.setNombre(nomPil_aux);

                    //Guardando categoria del piloto
                    do {
                        categPil_aux = JOptionPane.showInputDialog(null, "Escriba la categoria del piloto: ", 3);

                        if (categPil_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar la categoria del piloto", "ERROR", 0);
                        }
                        if (!categPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "La categoria del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                    } while (categPil_aux.isBlank() || !categPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    piloto_aux.setNombre(categPil_aux);

                    //Guardando licencia del piloto
                    do {
                        licenPil_aux = JOptionPane.showInputDialog(null, "Escriba la licencia del piloto: ", 3);

                        if (licenPil_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar la licencia del piloto", "ERROR", 0);
                        }
                        if (!licenPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "La licencia del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                    } while (licenPil_aux.isBlank() || !licenPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    piloto_aux.setNombre(licenPil_aux);

                    //Guardando fecha de nacimiento del piloto
                    do {
                        //Guardando dia
                        do {
                            try {
                                dia_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el dia de nacimiento del piloto: ", 3));

                                if (dia_aux <= 0) {
                                    JOptionPane.showMessageDialog(null, "El dia debe ser un valor mayor a 0", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                dia_aux = -1;
                                JOptionPane.showMessageDialog(null, "El dia debe ser numerico", "ERROR", 0);
                            }
                        } while (dia_aux <= 0);

                        //Guardando mes
                        do {
                            try {
                                mes_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el mes de nacimiento del piloto: ", 3));

                                if (mes_aux <= 0) {
                                    JOptionPane.showMessageDialog(null, "El mes debe ser un valor mayor a 0", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                mes_aux = -1;
                                JOptionPane.showMessageDialog(null, "El mes debe ser numerico", "ERROR", 0);
                            }
                        } while (mes_aux <= 0);

                        //Guardando anio
                        do {
                            try {
                                anio_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el anio de nacimiento del piloto: ", 3));

                                if (anio_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El anio debe ser un valor mayor a 0", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                anio_aux = -1;
                                JOptionPane.showMessageDialog(null, "El anio debe ser numerico", "ERROR", 0);
                            }
                        } while (anio_aux <= 0);

                        fech_correcta = piloto_aux.setFechaNacimiento(dia_aux, mes_aux, anio_aux);

                        if (!fech_correcta) {
                            JOptionPane.showMessageDialog(null, "La fecha de nacimiento ingresada es incorrecta, intente de nuevo", "ERROR", 0);
                        }
                    } while (!fech_correcta);

                    //Guardando anios de experiencia del piloto
                    do {
                        try {
                            aniosExp_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite los anios de experiencia del piloto: ", 3));

                            if (aniosExp_aux <= 0) {
                                JOptionPane.showMessageDialog(null, "Los anios de experiencia debe ser un valor mayor a 0", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            aniosExp_aux = -1;
                            JOptionPane.showMessageDialog(null, "Los anios de experiencia debe ser un valor numerico", "ERROR", 0);
                        }
                    } while (aniosExp_aux <= 0);

                    piloto_aux.setAniosExperiencia(aniosExp_aux);

                    lista_pilotos.add(piloto_aux);

                    JOptionPane.showMessageDialog(null, "Piloto dado de alta correctamente", "ENHORABUENA", 1);

                    break;

                case 4: //ALTA DE UN VUELO
                    Vuelo vuelo_aux = new Vuelo();

                    //Guardando id del Vuelo
                    do {
                        try {
                            idVuelo_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Vuelo: ", 3));

                            if (idVuelo_aux < 0) {
                                JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                            }

                            pos_encontrad = buscarIdAvion(lista_aviones, idVuelo_aux);

                            if (pos_encontrad != -1) {
                                JOptionPane.showMessageDialog(null, "Un vuelo ya cuenta con ese Id, digite otro", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            idVuelo_aux = -1;
                            JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
                        }
                    } while (idVuelo_aux < 0 || pos_encontrad != -1);

                    vuelo_aux.setIdVuelo(idVuelo_aux);

                    if (lista_aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se puede asignar un avion al vuelo porque aun no hay aviones dados de alta", "ERROR", 0);
                    } else {
                        //Asignar avion al vuelo
                        do {
                            try {
                                idAvion_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del avion a asignar: ", 3));

                                if (idAvion_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }

                                pos_encontrad = buscarIdAvion(lista_aviones, idAvion_aux);

                                if (pos_encontrad == -1) {
                                    JOptionPane.showMessageDialog(null, "No existe ningun avion con dicho Id, asigne otro", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                idAvion_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
                            }
                        } while (idAvion_aux < 0 || pos_encontrad == -1);

                        vuelo_aux.setIdAvion(idAvion_aux);

                        if (lista_pilotos.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se puede asignar un piloto al vuelo porque aun no hay pilotos dados de alta", "ERROR", 0);
                        } else {
                            //Asignar piloto al vuelo
                            do {
                                idPil_aux = JOptionPane.showInputDialog(null, "Escriba el Id del piloto: ", 3);

                                if (idPil_aux.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "Debe registrar el Id del piloto", "ERROR", 0);
                                }
                                if (!idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    JOptionPane.showMessageDialog(null, "El Id del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                                }

                                if (idPil_aux.isBlank() && idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    pos_encontrad = buscarIdPiloto(lista_pilotos, idPil_aux);

                                    if (pos_encontrad == -1) {
                                        JOptionPane.showMessageDialog(null, "No existe ningun piloto con dicho id, asigne otro", "ERROR", 0);
                                    }
                                }
                            } while (idPil_aux.isBlank() || !idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+") || pos_encontrad == -1);

                            vuelo_aux.setIdPiloto(idPil_aux);

                            //Guardar ciudad de origen
                            do {
                                cdOrigen_aux = JOptionPane.showInputDialog(null, "Escriba la ciudad de origen del vuelo: ", 3);

                                if (cdOrigen_aux.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "Debe registrar la ciudad de origen del vuelo", "ERROR", 0);
                                }
                                if (!cdOrigen_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    JOptionPane.showMessageDialog(null, "La ciudad de origen del vuelo no debe contener numeros ni caracteres especiales", "ERROR", 0);
                                }
                            } while (cdOrigen_aux.isBlank() || !cdOrigen_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                            vuelo_aux.setCdOrigen(cdOrigen_aux);

                            //Guardar ciudad de destino
                            do {
                                cdDestino_aux = JOptionPane.showInputDialog(null, "Escriba la ciudad de destino del vuelo: ", 3);

                                if (cdDestino_aux.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "Debe registrar la ciudad de destino del vuelo", "ERROR", 0);
                                }
                                if (!cdDestino_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    JOptionPane.showMessageDialog(null, "La ciudad de destino del vuelo no debe contener numeros ni caracteres especiales", "ERROR", 0);
                                }
                            } while (cdDestino_aux.isBlank() || !cdDestino_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                            vuelo_aux.setCdDestino(cdDestino_aux);

                            //Guardando fecha de salida
                            do {
                                //Guardando dia
                                do {
                                    try {
                                        dia_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el dia de salida del vuelo: ", 3));

                                        if (dia_aux <= 0) {
                                            JOptionPane.showMessageDialog(null, "El dia debe ser un valor mayor a 0", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        dia_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El dia debe ser numerico", "ERROR", 0);
                                    }
                                } while (dia_aux <= 0);

                                //Guardando mes
                                do {
                                    try {
                                        mes_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el mes de salida del vuelo: ", 3));

                                        if (mes_aux <= 0) {
                                            JOptionPane.showMessageDialog(null, "El mes debe ser un valor mayor a 0", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        mes_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El mes debe ser numerico", "ERROR", 0);
                                    }
                                } while (mes_aux <= 0);

                                //Guardando anio
                                do {
                                    try {
                                        anio_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el anio de de salida del vuelo: ", 3));

                                        if (anio_aux <= 0) {
                                            JOptionPane.showMessageDialog(null, "El anio debe ser un valor mayor a 0", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        anio_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El anio debe ser numerico", "ERROR", 0);
                                    }
                                } while (anio_aux <= 0);

                                fech_correcta = vuelo_aux.setFechaSalida(dia_aux, mes_aux, anio_aux);

                                if (!fech_correcta) {
                                    JOptionPane.showMessageDialog(null, "La fecha de salida ingresada es incorrecta, intente de nuevo", "ERROR", 0);
                                }
                            } while (!fech_correcta);

                            //Guardando fecha de llegada
                            do {
                                //Guardando dia
                                do {
                                    try {
                                        dia_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el dia de llegada del vuelo: ", 3));

                                        if (dia_aux <= 0) {
                                            JOptionPane.showMessageDialog(null, "El dia debe ser un valor mayor a 0", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        dia_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El dia debe ser numerico", "ERROR", 0);
                                    }
                                } while (dia_aux <= 0);

                                //Guardando mes
                                do {
                                    try {
                                        mes_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el mes de llegada del vuelo: ", 3));

                                        if (mes_aux <= 0) {
                                            JOptionPane.showMessageDialog(null, "El mes debe ser un valor mayor a 0", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        mes_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El mes debe ser numerico", "ERROR", 0);
                                    }
                                } while (mes_aux <= 0);

                                //Guardando anio
                                do {
                                    try {
                                        anio_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el anio de de llegada del vuelo: ", 3));

                                        if (anio_aux <= 0) {
                                            JOptionPane.showMessageDialog(null, "El anio debe ser un valor mayor a 0", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        anio_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El anio debe ser numerico", "ERROR", 0);
                                    }
                                } while (anio_aux <= 0);

                                fech_correcta = vuelo_aux.setFechaLlegada(dia_aux, mes_aux, anio_aux);

                                if (!fech_correcta) {
                                    JOptionPane.showMessageDialog(null, "La fecha de de llegada ingresada es incorrecta, intente de nuevo", "ERROR", 0);
                                }
                            } while (!fech_correcta);

                            lista_vuelos.add(vuelo_aux);

                            JOptionPane.showMessageDialog(null, "Vuelo dado de alta correctamente", "ENHORABUENA", 1);
                        }
                    }

                    break;

                case 5: //LISTAR AVIONES DE PASAJEROS
                    if (!lista_aviones.isEmpty()) {
                        String mensaje = """
                                     ------------------------------------------------------------------------
                                       Id          Modelo         Marca         No. pasajeros         Clases
                                     ------------------------------------------------------------------------
                                     """;

                        for (Avion avPas_aux : lista_aviones) {
                            if (avPas_aux instanceof Pasajeros) {
                                mensaje += "\n" + avPas_aux.getIdAvion() + "   " + avPas_aux.getModelo() + "   " + avPas_aux.getMarca() + "   "
                                        + ((Pasajeros) avPas_aux).getNoPasajeros() + "   " + ((Pasajeros) avPas_aux).getClases();
                            }
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE AVIONES DE PASAJEROS", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay aviones de pasajeros dados de alta", "ERROR", 0);
                    }
                    break;

                case 6: //LISTAR AVIONES DE CARGA
                    if (!lista_aviones.isEmpty()) {
                        String mensaje = """
                                     ------------------------------------------------------------------------
                                       Id          Modelo         Marca         No. pallets         Capacidad
                                     ------------------------------------------------------------------------
                                     """;

                        for (Avion avCar_aux : lista_aviones) {
                            if (avCar_aux instanceof Carga) {
                                mensaje += "\n" + avCar_aux.getIdAvion() + "   " + avCar_aux.getModelo() + "   " + avCar_aux.getMarca() + "   "
                                        + ((Carga) avCar_aux).getNoPallets() + "   " + ((Carga) avCar_aux).getCapacidad();
                            }
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE AVIONES DE CARGA", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay aviones de carga dados de alta", "ERROR", 0);
                    }

                    break;

                case 7: //LISTAR PILOTOS
                    if (!lista_pilotos.isEmpty()) {
                        String mensaje = """
                                     ------------------------------------------------------------------------
                                       Id          Nombre         Categoria         Anios de experiencia
                                     ------------------------------------------------------------------------
                                     """;

                        for (Piloto pil_aux : lista_pilotos) {
                            mensaje += "\n" + pil_aux.getIdPiloto() + "   " + pil_aux.getNombre() + "   " + pil_aux.getCategoria() + "   "
                                    + pil_aux.getAniosExperiencia();
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE PILOTOS", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay pilotos dados de alta", "ERROR", 0);
                    }

                    break;

                case 8: //LISTAR VUELOS
                    if (!lista_vuelos.isEmpty()) {
                        String mensaje = """
                                     -------------------------------------------------------------------------
                                       Id vuelo    Id avion    Id piloto         Destino         Fecha Salida
                                     -------------------------------------------------------------------------
                                     """;

                        for (Vuelo vuel_aux : lista_vuelos) {
                            mensaje += "\n" + vuel_aux.getIdVuelo() + "    " + vuel_aux.getIdAvion() + "    " + vuel_aux.getIdPiloto() + "   "
                                    + vuel_aux.getCdDestino() + "       " + vuel_aux.getFechaSalida();
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE VUELOS", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay vuelos dados de alta", "ERROR", 0);
                    }

                    break;

                case 9: //VER DETALLE DE UN AVION DE PASAJEROS
                    if (lista_aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay aviones dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                idAvion_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del avion de pasajeros a buscar: ", 3));

                                if (idAvion_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                idAvion_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerica", "ERROR", 0);
                            }
                        } while (idAvion_aux < 0);

                        pos_encontrad = buscarIdAvion(lista_aviones, idAvion_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe avion con dicho id", "ERROR", 0);
                        } else {
                            Avion av_aux = lista_aviones.get(pos_encontrad);

                            if (av_aux instanceof Pasajeros) {
                                JOptionPane.showMessageDialog(null, av_aux.getDatos(), "DETALLES DE UN AVION DE PASAJEROS", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "El avion con dicho Id no corresponde a un avion de pasajeros", "ERROR", 0);
                            }
                        }
                    }

                    break;

                case 10: //VER DETALLE DE UN AVION DE CARGA
                    if (lista_aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay aviones dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                idAvion_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Avion de carga a buscar: ", 3));

                                if (idAvion_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                idAvion_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
                            }
                        } while (idAvion_aux < 0);

                        pos_encontrad = buscarIdAvion(lista_aviones, idAvion_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe avion con dicho id", "ERROR", 0);
                        } else {
                            Avion av_aux = lista_aviones.get(pos_encontrad);

                            if (av_aux instanceof Carga) {
                                JOptionPane.showMessageDialog(null, av_aux.getDatos(), "DETALLES DE UN AVION DE CARGA", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "El avion con dicho Id no corresponde a un avion de carga", "ERROR", 0);
                            }
                        }
                    }

                    break;

                case 11: //VER DETALLE DE UN PILOTO
                    if (lista_pilotos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pilotos dados de alta", "ERROR", 0);
                    } else {
                        do {
                            idPil_aux = JOptionPane.showInputDialog(null, "Escriba el Id del piloto a buscar: ", 3);

                            if (idPil_aux.isBlank()) {
                                JOptionPane.showMessageDialog(null, "Debe registrar el Id del piloto a buscar", "ERROR", 0);
                            }
                            if (!idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                JOptionPane.showMessageDialog(null, "El Id del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                            }
                        } while (idPil_aux.isBlank() || !idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                        pos_encontrad = buscarIdPiloto(lista_pilotos, idPil_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe piloto con dicho Id", "ERROR", 0);
                        } else {
                            JOptionPane.showMessageDialog(null, lista_pilotos.get(pos_encontrad).getDatos(), "DETALLES DE PILOTO", 1);
                        }
                    }

                    break;

                case 12: //VER DETALLE DE UN VUELO
                    if (lista_vuelos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                idVuelo_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Vuelo a buscar: ", 3));

                                if (idVuelo_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                idVuelo_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
                            }
                        } while (idVuelo_aux < 0);

                        pos_encontrad = buscarIdVuelo(lista_vuelos, idVuelo_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe vuelo con dicho Id", "ERROR", 0);
                        } else {
                            Vuelo vue_aux = lista_vuelos.get(pos_encontrad);

                            String mensaje = "Id vuelo: " + vue_aux.getIdVuelo();

                            pos_encontrad = buscarIdAvion(lista_aviones, vue_aux.getIdAvion());

                            if (pos_encontrad == -1) {
                                JOptionPane.showMessageDialog(null, "No existe avion con dicho id", "ERROR", 0);
                            } else {
                                Avion av_aux = lista_aviones.get(pos_encontrad);

                                if (av_aux instanceof Pasajeros) {
                                    mensaje += "\n\nAVION DE PASAJEROS\n";
                                } else {
                                    mensaje += "\n\nAVION DE CARGA\n";
                                }

                                mensaje += av_aux.getDatos();

                                pos_encontrad = buscarIdPiloto(lista_pilotos, vue_aux.getIdPiloto());

                                if (pos_encontrad == -1) {
                                    JOptionPane.showMessageDialog(null, "No existe piloto con dicho Id", "ERROR", 0);
                                } else {
                                    Piloto pil_aux = lista_pilotos.get(pos_encontrad);

                                    mensaje += "\n\nPILOTO\n" + pil_aux.getDatos();

                                    mensaje += "\n\nCd. Origen: " + vue_aux.getCdOrigen()
                                            + "\nCd. Destino: " + vue_aux.getCdOrigen()
                                            + "\nFecha de salida: " + vue_aux.getFechaSalida()
                                            + "\nFecha de llegada: " + vue_aux.getFechaLlegada();

                                    JOptionPane.showMessageDialog(null, mensaje, "DETALLES DE UN VUELO", 1);
                                }
                            }
                        }
                    }

                    break;

                case 13: //ELIMINAR UN AVION DE PASAJEROS
                    if (lista_aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay aviones dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                idAvion_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Avion de pasajeros a eliminar: ", 3));

                                if (idAvion_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                idAvion_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
                            }
                        } while (idAvion_aux < 0);

                        pos_encontrad = buscarIdAvion(lista_aviones, idAvion_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe avion con dicho Id", "ERROR", 0);
                        } else {
                            Avion av_aux = lista_aviones.get(pos_encontrad);

                            if (av_aux instanceof Pasajeros) {
                                opc_confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el avion de pasajeros con Id " + av_aux.getIdAvion() + "?", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);

                                if (opc_confirm == JOptionPane.YES_OPTION) {
                                    lista_aviones.remove(pos_encontrad);
                                    JOptionPane.showMessageDialog(null, "Avion de pasajeros eliminado correctamente", "ELIMINACION COMPLETADA", 1);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Regresando al menu principal...", "ELIMINACION CANCELADA", 1);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El avion con dicho Id no corresponde a un avion de pasajeros", "ERROR", 0);
                            }
                        }
                    }

                    break;

                case 14: //ELIMINAR UN AVION DE CARGA
                    if (lista_aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay aviones dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                idAvion_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Avion de carga a eliminar: ", 3));

                                if (idAvion_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                idAvion_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
                            }
                        } while (idAvion_aux < 0);

                        pos_encontrad = buscarIdAvion(lista_aviones, idAvion_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe avion con dicho id", "ERROR", 0);
                        } else {
                            Avion av_aux = lista_aviones.get(pos_encontrad);

                            if (av_aux instanceof Carga) {
                                opc_confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el avion de carga con Id " + av_aux.getIdAvion() + "?", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);

                                if (opc_confirm == JOptionPane.YES_OPTION) {
                                    lista_aviones.remove(pos_encontrad);
                                    JOptionPane.showMessageDialog(null, "Avion de carga eliminado correctamente", "ELIMINACION COMPLETADA", 1);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Regresando al menu principal...", "ELIMINACION CANCELADA", 1);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El avion con dicho Id no corresponde a un avion de carga", "ERROR", 0);
                            }
                        }
                    }

                    break;

                case 15: //ELIMINAR UN PILOTO
                    if (lista_pilotos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pilotos dados de alta", "ERROR", 0);
                    } else {
                        do {
                            idPil_aux = JOptionPane.showInputDialog(null, "Escriba el Id del piloto a eliminar: ", 3);

                            if (idPil_aux.isBlank()) {
                                JOptionPane.showMessageDialog(null, "Debe registrar el Id del piloto", "ERROR", 0);
                            }
                            if (!idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                JOptionPane.showMessageDialog(null, "El Id del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                            }
                        } while (idPil_aux.isBlank() || !idPil_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                        pos_encontrad = buscarIdPiloto(lista_pilotos, idPil_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe piloto con dicho Id", "ERROR", 0);
                        } else {
                            Piloto pil_aux = lista_pilotos.get(pos_encontrad);

                            opc_confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al piloto con Id " + pil_aux.getIdPiloto() + "?", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);

                            if (opc_confirm == JOptionPane.YES_OPTION) {
                                lista_pilotos.remove(pos_encontrad);
                                JOptionPane.showMessageDialog(null, "Piloto eliminado correctamente", "ELIMINACION COMPLETADA", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "Regresando al menu principal...", "ELIMINACION CANCELADA", 1);
                            }

                        }
                    }

                    break;

                case 16: //ELIMINAR UN VUELO
                    if (lista_vuelos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                idVuelo_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del vuelo a eliminar: ", 3));

                                if (idVuelo_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                idVuelo_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
                            }
                        } while (idVuelo_aux < 0);

                        pos_encontrad = buscarIdVuelo(lista_vuelos, idVuelo_aux);

                        if (pos_encontrad == -1) {
                            JOptionPane.showMessageDialog(null, "No existe vuelo con dicho Id", "ERROR", 0);
                        } else {
                            Vuelo vue_aux = lista_vuelos.get(pos_encontrad);

                            opc_confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el vuelo con Id " + vue_aux.getIdAvion() + "?", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);

                            if (opc_confirm == JOptionPane.YES_OPTION) {
                                lista_aviones.remove(pos_encontrad);
                                JOptionPane.showMessageDialog(null, "Vuelo eliminado correctamente", "ELIMINACION COMPLETADA", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "Regresando al menu principal...", "ELIMINACION CANCELADA", 1);
                            }

                        }
                    }

                    break;

                case 17: //SALIR
                    String mensaje = """
                                     Tercer semestre, grupo uno
                                     Programacion Orientada a Objetos
                                     Proyecto final segundo parcial
                                     
                                                Integrantes:
                                     Hernandez Franco Brandom Galder
                                     Lozada Alfaro Mario Andre
                                     Diaz Covarrubias Escudero Diego Antonio
                                     Monterrubio Lara Jesus Alberto                                     
                                     """;
                    
                    salidaListaAviones(lista_aviones);
                    salidaListaVuelos(lista_vuelos);
                    salidaListaPilotos(lista_pilotos);
                    
                    JOptionPane.showMessageDialog(null, mensaje, "SALIENDO...", 1);
                    
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Digito una opcion inexistente", "ERROR", 0);
                    break;
            }
        } while (opc != 17);

    }

    public static void entradaListaAviones(ArrayList<Avion> lista) {
        FileInputStream fin = null;

        try {
            fin = new FileInputStream("ListadoAviones.txt");
            ObjectInputStream entrada = new ObjectInputStream(fin);
            while (true) {
                lista.add((Avion) entrada.readObject());
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro la clase\n\t" + e.getMessage(), "CLASE NO ENCONTRADA", 2);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo\n" + e.getMessage(), "ARCHIVO NO ENCONTRADO", 2);
        } catch (EOFException e) {
            System.out.println("Lectura del archivo ListadoAviones completada");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de entrada/salida\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 2);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 2);
                }
            }
        }
    }

    public static void entradaListaVuelos(ArrayList<Vuelo> lista) {
        FileInputStream fin = null;

        try {
            fin = new FileInputStream("ListadoVuelos.txt");
            ObjectInputStream entrada = new ObjectInputStream(fin);
            while (true) {
                lista.add((Vuelo) entrada.readObject());
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro la clase\n\t" + e.getMessage(), "CLASE NO ENCONTRADA", 2);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo\n" + e.getMessage(), "ARCHIVO NO ENCONTRADO", 2);
        } catch (EOFException e) {
            System.out.println("Lectura del archivo ListadoVuelos completada");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de entrada/salida\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 2);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 2);
                }
            }
        }
    }

    public static void entradaListaPilotos(ArrayList<Piloto> lista) {
        FileInputStream fin = null;

        try {
            fin = new FileInputStream("ListadoPilotos.txt");
            ObjectInputStream entrada = new ObjectInputStream(fin);
            while (true) {
                lista.add((Piloto) entrada.readObject());
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro la clase\n\t" + e.getMessage(), "CLASE NO ENCONTRADA", 2);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo\n" + e.getMessage(), "ARCHIVO NO ENCONTRADO", 2);
        } catch (EOFException e) {
            System.out.println("Lectura del archivo ListadoPilotos completada");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de entrada/salida\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 2);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 2);
                }
            }
        }
    }

    public static int buscarIdAvion(ArrayList<Avion> lista, int buscar) {
        int pos = -1;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdAvion() == buscar) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    public static int buscarIdVuelo(ArrayList<Vuelo> lista, int buscar) {
        int pos = -1;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdVuelo() == buscar) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    public static int buscarIdPiloto(ArrayList<Piloto> lista, String buscar) {
        int pos = -1;

        for (int i = 0; i < lista.size(); i++) {
            if (buscar.equalsIgnoreCase(lista.get(i).getIdPiloto())) {
                pos = i;
                break;
            }
        }

        return pos;
    }
    
    public static Avion darAltaAvion(ArrayList<Avion> lista, int opc) {
        Avion avion_aux;
        if (opc == 1) {
            avion_aux = new Pasajeros();
        } else {
            avion_aux = new Carga();
        }
        int idAvion_aux = -1, capTanq_aux  = -1, numMotor_aux = -1, pos_encontrad = -1;
        String modelAv_aux = null, marcaAv_aux = null;
        float veloc_aux = -1.0f;

        //Guardando Id del avion
        do {
            try {
                idAvion_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del avion: ", 3));

                if (idAvion_aux < 0) {
                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                }

                pos_encontrad = buscarIdAvion(lista, idAvion_aux);

                if (pos_encontrad != -1) {
                    JOptionPane.showMessageDialog(null, "Un avion ya cuenta con ese Id, digite otro", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                idAvion_aux = -1;
                JOptionPane.showMessageDialog(null, "El Id debe ser numerico", "ERROR", 0);
            }
        } while (idAvion_aux < 0 || pos_encontrad != -1);

        avion_aux.setIdAvion(idAvion_aux);

        //Guardando modelo del avion
        do {
            modelAv_aux = JOptionPane.showInputDialog(null, "Escriba el modelo del avion: ", 3);

            if (modelAv_aux.isBlank()) {
                JOptionPane.showMessageDialog(null, "Debe registrar el modelo del avion", "ERROR", 0);
            }
            if (!modelAv_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                JOptionPane.showMessageDialog(null, "El modelo del avion no debe contener numeros ni caracteres especiales", "ERROR", 0);
            }
        } while (modelAv_aux.isBlank() || !modelAv_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

        avion_aux.setModelo(modelAv_aux);

        //Guardandno marca del avion
        do {
            marcaAv_aux = JOptionPane.showInputDialog(null, "Escriba la marca del avion: ", 3);

            if (marcaAv_aux.isBlank()) {
                JOptionPane.showMessageDialog(null, "Debe registrar la marca del avion", "ERROR", 0);
            }
            if (!marcaAv_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                JOptionPane.showMessageDialog(null, "La marca del avion no debe contener numeros ni caracteres especiales", "ERROR", 0);
            }
        } while (marcaAv_aux.isBlank() || !marcaAv_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

        avion_aux.setMarca(marcaAv_aux);

        //Guardandno capacidad del tanque del avion
        do {
            try {
                capTanq_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la capacidad de tanque del avion: ", 3));

                if (capTanq_aux <= 0) {
                    JOptionPane.showMessageDialog(null, "La capacidad del tanque debe ser mayor a 0", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                capTanq_aux = -1;
                JOptionPane.showMessageDialog(null, "La capacidad del tanque debe ser numerica", "ERROR", 0);
            }
        } while (capTanq_aux <= 0);

        avion_aux.setCapacidadTanque(capTanq_aux);

        //Guardando el numero de motores del avion
        do {
            try {
                numMotor_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de motores del avion: ", 3));

                if (numMotor_aux < 2) {
                    JOptionPane.showMessageDialog(null, "El avion debe tener minimo 2 motores", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                numMotor_aux = -1;
                JOptionPane.showMessageDialog(null, "El numero de motores debe ser numerico", "ERROR", 0);
            }
        } while (numMotor_aux < 2);

        avion_aux.setNoMotores(numMotor_aux);

        //Guardando la velocidad del avion
        do {
            try {
                veloc_aux = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite la velocidad del avion: ", 3));

                if (veloc_aux <= 0) {
                    JOptionPane.showMessageDialog(null, "La velocidad debe ser mayor a 0", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                veloc_aux = -1.0f;
                JOptionPane.showMessageDialog(null, "La velocidad debe ser numerica", "ERROR", 0);
            }
        } while (veloc_aux <= 0);

        avion_aux.setVelocidad(veloc_aux);

        return avion_aux;
    }

    public static void salidaListaAviones(ArrayList<Avion> lista) {
        FileOutputStream fout = null;
        
        try {
            fout = new FileOutputStream("ListadoAviones.txt");
            ObjectOutputStream salida = new ObjectOutputStream(fout);
            for (Avion av_aux : lista) {
                salida.writeObject(av_aux);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo no pos_encontrad\n" + e.getMessage(), "ARCHIVO NO ENCONTRADO", 0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de entrada/salida\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 0);
        } finally {
            if (fout != null) {
                try {
                    fout.close(); //Por si se intenta cerrar algo que null
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 0);
                }
            }
        }
    }
    
    public static void salidaListaVuelos(ArrayList<Vuelo> lista) {
        FileOutputStream fout = null;
        
        try {
            fout = new FileOutputStream("ListadoVuelos.txt");
            ObjectOutputStream salida = new ObjectOutputStream(fout);
            for (Vuelo vue_aux : lista) {
                salida.writeObject(vue_aux);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo no pos_encontrad\n" + e.getMessage(), "ARCHIVO NO ENCONTRADO", 0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de entrada/salida\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 0);
        } finally {
            if (fout != null) {
                try {
                    fout.close(); //Por si se intenta cerrar algo que null
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 0);
                }
            }
        }
    }
    
    public static void salidaListaPilotos(ArrayList<Piloto> lista) {
        FileOutputStream fout = null;
        
        try {
            fout = new FileOutputStream("ListadoPilotos.txt");
            ObjectOutputStream salida = new ObjectOutputStream(fout);
            for (Piloto pil_aux : lista) {
                salida.writeObject(pil_aux);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo no pos_encontrad\n" + e.getMessage(), "ARCHIVO NO ENCONTRADO", 0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de entrada/salida\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 0);
        } finally {
            if (fout != null) {
                try {
                    fout.close(); //Por si se intenta cerrar algo que null
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo\n" + e.getMessage(), "ERROR DE ENTRADA/SALIDA", 0);
                }
            }
        }
    }
}
