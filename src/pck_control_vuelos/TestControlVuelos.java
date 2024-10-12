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
//otras importaciones
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TestControlVuelos {

    public static void main(String args[]) {
        int i_aux = -1, encontrado = -1, dia_aux = -1, mes_aux = -1, anio_aux = -1;
        String s_aux = null;
        float f_aux = -1.0f;
        boolean correcto = true;

        ArrayList<Avion> aviones = new ArrayList<>();
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        ArrayList<Piloto> pilotos = new ArrayList<>();

        entradaListaAviones(aviones);
        entradaListaVuelos(vuelos);
        entradaListaPilotos(pilotos);

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
                    Avion pasajero_aux = darAltaAvion(aviones, 1);

                    //Guardando numero de pasajeros de avion
                    do {
                        try {
                            i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de pasajeros del Avion: ", 3));

                            if (i_aux < 0) {
                                JOptionPane.showMessageDialog(null, "El numero de pasajeros debe ser positivo", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            i_aux = -1;
                            JOptionPane.showMessageDialog(null, "El numero de pasajeros debe ser numerico", "ERROR", 0);
                        }
                    } while (i_aux < 0);

                    ((Pasajeros) pasajero_aux).setNoPasajeros(i_aux);

                    //Guardando las clases del avion
                    do {
                        s_aux = JOptionPane.showInputDialog(null, "Escriba las clases del avion: ", 3);

                        if (s_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar las clases del avion", "ERROR", 0);
                        }
                        if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "Las clases del avion no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }
                    } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    ((Pasajeros) pasajero_aux).setClases(s_aux);

                    //Guardando el numero del tripulantes del avion
                    do {
                        try {
                            i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de tripulantes del Avion: ", 3));

                            if (i_aux < 0) {
                                JOptionPane.showMessageDialog(null, "El numero de tripulantes debe ser positivo", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            i_aux = -1;
                            JOptionPane.showMessageDialog(null, "El numero de tripulantes debe ser numerico", "ERROR", 0);
                        }
                    } while (i_aux < 0);

                    ((Pasajeros) pasajero_aux).setNoTripulantes(i_aux);

                    aviones.add(pasajero_aux);

                    JOptionPane.showMessageDialog(null, "Avion de pasajeros dado de alta correctamente", "ENHORABUENA", 1);

                    break;

                case 2: //ALTA DE UN AVION DE CARGA
                    Avion carga_aux = darAltaAvion(aviones, 2);

                    //Guardando numero de pallets del avion
                    do {
                        try {
                            i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de pallets del Avion: ", 3));

                            if (i_aux < 0) {
                                JOptionPane.showMessageDialog(null, "El numero de pallets debe ser positivo", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            i_aux = -1;
                            JOptionPane.showMessageDialog(null, "El numero de pallets debe ser numerico", "ERROR", 0);
                        }
                    } while (i_aux < 0);

                    ((Carga) carga_aux).setNoPallets(i_aux);

                    //Guardando el volumen del avion
                    do {
                        try {
                            f_aux = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite el volumen del Avion: ", 3));

                            if (f_aux < 0) {
                                JOptionPane.showMessageDialog(null, "El volumen debe ser positivo", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            f_aux = -1.0f;
                            JOptionPane.showMessageDialog(null, "El volumen debe ser numerico", "ERROR", 0);
                        }
                    } while (f_aux < 0);

                    ((Carga) carga_aux).setVolumen(f_aux);

                    //Guardando la capacidad del avion
                    do {
                        try {
                            f_aux = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite la capacidad del Avion: ", 3));

                            if (f_aux < 0) {
                                JOptionPane.showMessageDialog(null, "La capacidad debe ser positiva", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            f_aux = -1.0f;
                            JOptionPane.showMessageDialog(null, "La capacidad debe ser numerica", "ERROR", 0);
                        }
                    } while (f_aux < 0);

                    ((Carga) carga_aux).setCapacidad(f_aux);

                    aviones.add(carga_aux);

                    JOptionPane.showMessageDialog(null, "Avion de carga dado de alta correctamente", "ENHORABUENA", 1);

                    break;

                case 3: //ALTA DE UN PILOTO
                    Piloto piloto_aux = new Piloto();

                    //Guardando Id del piloto
                    do {
                        s_aux = JOptionPane.showInputDialog(null, "Escriba el id del piloto: ", 3);

                        if (s_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar el id del piloto", "ERROR", 0);
                        }
                        if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "El id del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                        if (!s_aux.isBlank() && !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            encontrado = buscarIdPiloto(pilotos, s_aux);

                            if (encontrado != -1) {
                                JOptionPane.showMessageDialog(null, "Un piloto ya cuenta con ese Id, digite otro", "ERROR", 0);
                            }
                        }
                    } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+") || encontrado != -1);

                    piloto_aux.setIdPiloto(s_aux);

                    //Guardando nombre del piloto
                    do {
                        s_aux = JOptionPane.showInputDialog(null, "Escriba el nombre del piloto: ", 3);

                        if (s_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar el nombre del piloto", "ERROR", 0);
                        }
                        if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "El nombre del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                    } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    piloto_aux.setNombre(s_aux);

                    //Guardando categoria del piloto
                    do {
                        s_aux = JOptionPane.showInputDialog(null, "Escriba la categoria del piloto: ", 3);

                        if (s_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar la categoria del piloto", "ERROR", 0);
                        }
                        if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "La categoria del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                    } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    piloto_aux.setNombre(s_aux);

                    //Guardando licencia del piloto
                    do {
                        s_aux = JOptionPane.showInputDialog(null, "Escriba la licencia del piloto: ", 3);

                        if (s_aux.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar la licencia del piloto", "ERROR", 0);
                        }
                        if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                            JOptionPane.showMessageDialog(null, "La licencia del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                        }

                    } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                    piloto_aux.setNombre(s_aux);

                    //Guardando fecha de nacimiento del piloto
                    do {
                        //Guardando dia
                        do {
                            try {
                                dia_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el dia de nacimiento del piloto: ", 3));

                                if (dia_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El dia debe ser un valor positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                dia_aux = -1;
                                JOptionPane.showMessageDialog(null, "El dia debe ser numerico", "ERROR", 0);
                            }
                        } while (dia_aux < 0);

                        //Guardando mes
                        do {
                            try {
                                mes_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el mes de nacimiento del piloto: ", 3));

                                if (mes_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El mes debe ser un valor positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                mes_aux = -1;
                                JOptionPane.showMessageDialog(null, "El mes debe ser numerico", "ERROR", 0);
                            }
                        } while (mes_aux < 0);

                        //Guardando anio
                        do {
                            try {
                                anio_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el anio de nacimiento del piloto: ", 3));

                                if (anio_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El anio debe ser un valor positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                anio_aux = -1;
                                JOptionPane.showMessageDialog(null, "El anio debe ser numerico", "ERROR", 0);
                            }
                        } while (anio_aux < 0);

                        correcto = piloto_aux.setFechaNacimiento(dia_aux, mes_aux, anio_aux);

                        if (!correcto) {
                            JOptionPane.showMessageDialog(null, "La fecha de nacimiento ingresada es incorrecta, intente de nuevo", "ERROR", 0);
                        }
                    } while (!correcto);

                    //Guardando anios de experiencia del piloto
                    do {
                        try {
                            i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite los anios de experiencia del piloto: ", 3));

                            if (i_aux < 0) {
                                JOptionPane.showMessageDialog(null, "Los anios de experiencia debe ser un valor positivo", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            i_aux = -1;
                            JOptionPane.showMessageDialog(null, "Los anios de experiencia debe ser un valor numerico", "ERROR", 0);
                        }
                    } while (i_aux < 0);

                    piloto_aux.setAniosExperiencia(i_aux);

                    pilotos.add(piloto_aux);

                    JOptionPane.showMessageDialog(null, "Piloto dado de alta correctamente", "ENHORABUENA", 1);

                    break;

                case 4: //ALTA DE UN VUELO
                    Vuelo vuelo_aux = new Vuelo();

                    //Guardando id del Vuelo
                    do {
                        try {
                            i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Vuelo: ", 3));

                            if (i_aux < 0) {
                                JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                            }

                            encontrado = buscarIdAvion(aviones, i_aux);

                            if (encontrado != -1) {
                                JOptionPane.showMessageDialog(null, "Un vuelo ya cuenta con ese Id, digite otro", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            i_aux = -1;
                            JOptionPane.showMessageDialog(null, "El id debe ser numerico", "ERROR", 0);
                        }
                    } while (i_aux < 0 || encontrado != -1);

                    vuelo_aux.setIdVuelo(i_aux);

                    if (aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se puede asignar un avion al vuelo porque aun no hay aviones dados de alta", "ERROR", 0);
                    } else {
                        //Asignar avion al vuelo
                        do {
                            try {
                                i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Avion: ", 3));

                                if (i_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }

                                encontrado = buscarIdAvion(aviones, i_aux);

                                if (encontrado == -1) {
                                    JOptionPane.showMessageDialog(null, "No existe ningun avion con dicho id, asigne otro", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                i_aux = -1;
                                JOptionPane.showMessageDialog(null, "El id debe ser numerico", "ERROR", 0);
                            }
                        } while (i_aux < 0 || encontrado == -1);

                        vuelo_aux.setIdAvion(i_aux);

                        if (pilotos.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se puede asignar un piloto al vuelo porque aun no hay pilotos dados de alta", "ERROR", 0);
                        } else {
                            //Asignar piloto al vuelo
                            do {
                                s_aux = JOptionPane.showInputDialog(null, "Escriba el id del piloto: ", 3);

                                if (s_aux.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "Debe registrar el id del piloto", "ERROR", 0);
                                }
                                if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    JOptionPane.showMessageDialog(null, "El id del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                                }

                                if (s_aux.isBlank() && s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    encontrado = buscarIdPiloto(pilotos, s_aux);

                                    if (encontrado == -1) {
                                        JOptionPane.showMessageDialog(null, "No existe ningun piloto con dicho id, asigne otro", "ERROR", 0);
                                    }
                                }
                            } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+") || encontrado == -1);

                            vuelo_aux.setIdPiloto(s_aux);

                            //Guardar ciudad de origen
                            do {
                                s_aux = JOptionPane.showInputDialog(null, "Escriba la ciudad de origen: ", 3);

                                if (s_aux.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "Debe registrar la ciudad de origen", "ERROR", 0);
                                }
                                if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    JOptionPane.showMessageDialog(null, "La ciudad de origen no debe contener numeros ni caracteres especiales", "ERROR", 0);
                                }
                            } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                            vuelo_aux.setCdOrigen(s_aux);

                            //Guardar ciudad de destino
                            do {
                                s_aux = JOptionPane.showInputDialog(null, "Escriba la ciudad de destino: ", 3);

                                if (s_aux.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "Debe registrar la ciudad de destino", "ERROR", 0);
                                }
                                if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                    JOptionPane.showMessageDialog(null, "La ciudad de destino no debe contener numeros ni caracteres especiales", "ERROR", 0);
                                }
                            } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                            vuelo_aux.setCdDestino(s_aux);

                            //Guardando fecha de salida
                            do {
                                //Guardando dia
                                do {
                                    try {
                                        dia_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el dia de salida del vuelo: ", 3));

                                        if (dia_aux < 0) {
                                            JOptionPane.showMessageDialog(null, "El dia debe ser un valor positivo", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        dia_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El dia debe ser numerico", "ERROR", 0);
                                    }
                                } while (dia_aux < 0);

                                //Guardando mes
                                do {
                                    try {
                                        mes_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el mes de salida del vuelo: ", 3));

                                        if (mes_aux < 0) {
                                            JOptionPane.showMessageDialog(null, "El mes debe ser un valor positivo", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        mes_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El mes debe ser numerico", "ERROR", 0);
                                    }
                                } while (mes_aux < 0);

                                //Guardando anio
                                do {
                                    try {
                                        anio_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el anio de de salida del vuelo: ", 3));

                                        if (anio_aux < 0) {
                                            JOptionPane.showMessageDialog(null, "El anio debe ser un valor positivo", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        anio_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El anio debe ser numerico", "ERROR", 0);
                                    }
                                } while (anio_aux < 0);

                                correcto = vuelo_aux.setFechaSalida(dia_aux, mes_aux, anio_aux);

                                if (!correcto) {
                                    JOptionPane.showMessageDialog(null, "La fecha de de salida ingresada es incorrecta, intente de nuevo", "ERROR", 0);
                                }
                            } while (!correcto);

                            //Guardando fecha de llegada
                            do {
                                //Guardando dia
                                do {
                                    try {
                                        dia_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el dia de llegada del vuelo: ", 3));

                                        if (dia_aux < 0) {
                                            JOptionPane.showMessageDialog(null, "El dia debe ser un valor positivo", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        dia_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El dia debe ser numerico", "ERROR", 0);
                                    }
                                } while (dia_aux < 0);

                                //Guardando mes
                                do {
                                    try {
                                        mes_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el mes de llegada del vuelo: ", 3));

                                        if (mes_aux < 0) {
                                            JOptionPane.showMessageDialog(null, "El mes debe ser un valor positivo", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        mes_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El mes debe ser numerico", "ERROR", 0);
                                    }
                                } while (mes_aux < 0);

                                //Guardando anio
                                do {
                                    try {
                                        anio_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el anio de de llegada del vuelo: ", 3));

                                        if (anio_aux < 0) {
                                            JOptionPane.showMessageDialog(null, "El anio debe ser un valor positivo", "ERROR", 0);
                                        }
                                    } catch (NumberFormatException e) {
                                        anio_aux = -1;
                                        JOptionPane.showMessageDialog(null, "El anio debe ser numerico", "ERROR", 0);
                                    }
                                } while (anio_aux < 0);

                                correcto = vuelo_aux.setFechaLlegada(dia_aux, mes_aux, anio_aux);

                                if (!correcto) {
                                    JOptionPane.showMessageDialog(null, "La fecha de de llegada ingresada es incorrecta, intente de nuevo", "ERROR", 0);
                                }
                            } while (!correcto);

                            vuelos.add(vuelo_aux);

                            JOptionPane.showMessageDialog(null, "Vuelo dado de alta correctamente", "ENHORABUENA", 1);
                        }
                    }

                    break;

                case 5: //LISTAR AVIONES DE PASAJEROS
                    if (!aviones.isEmpty()) {
                        String mensaje = """
                                     ------------------------------------------------------------------------
                                       Id          Modelo         Marca         No. pasajeros         Clases
                                     ------------------------------------------------------------------------
                                     """;

                        for (Avion av_aux : aviones) {
                            if (av_aux instanceof Pasajeros) {
                                mensaje += "\n" + av_aux.getIdAvion() + "   " + av_aux.getModelo() + "   " + av_aux.getMarca() + "   "
                                        + ((Pasajeros) av_aux).getNoPasajeros() + "   " + ((Pasajeros) av_aux).getClases();
                            }
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE AVIONES DE PASAJEROS", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay aviones de pasajeros dados de alta", "ERROR", 0);
                    }
                    break;

                case 6: //LISTAR AVIONES DE CARGA
                    if (!aviones.isEmpty()) {
                        String mensaje = """
                                     ------------------------------------------------------------------------
                                       Id          Modelo         Marca         No. pallets         Capacidad
                                     ------------------------------------------------------------------------
                                     """;

                        for (Avion av_aux : aviones) {
                            if (av_aux instanceof Carga) {
                                mensaje += "\n" + av_aux.getIdAvion() + "   " + av_aux.getModelo() + "   " + av_aux.getMarca() + "   "
                                        + ((Carga) av_aux).getNoPallets() + "   " + ((Carga) av_aux).getCapacidad();
                            }
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE AVIONES DE CARGA", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay aviones de carga dados de alta", "ERROR", 0);
                    }

                    break;

                case 7: //LISTAR PILOTOS
                    if (!pilotos.isEmpty()) {
                        String mensaje = """
                                     ------------------------------------------------------------------------
                                       Id          Nombre         Categoria         Anios de experiencia
                                     ------------------------------------------------------------------------
                                     """;

                        for (Piloto pil_aux : pilotos) {
                            mensaje += "\n" + pil_aux.getIdPiloto() + "   " + pil_aux.getNombre() + "   " + pil_aux.getCategoria() + "   "
                                    + pil_aux.getAniosExperiencia();
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE PILOTOS", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay pilotos dados de alta", "ERROR", 0);
                    }

                    break;

                case 8: //LISTAR VUELOS
                    if (!vuelos.isEmpty()) {
                        String mensaje = """
                                     -------------------------------------------------------------------------
                                       Id vuelo    Id avion    Id piloto         Destino         Fecha Salida
                                     -------------------------------------------------------------------------
                                     """;

                        for (Vuelo vuel_aux : vuelos) {
                            mensaje += "\n" + vuel_aux.getIdVuelo() + "    " + vuel_aux.getIdAvion() + "    " + vuel_aux.getIdPiloto() + "   "
                                    + vuel_aux.getCdDestino() + "       " + vuel_aux.getFechaSalida();
                        }

                        JOptionPane.showMessageDialog(null, mensaje, "LISTA DE VUELOS", 1);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay vuelos dados de alta", "ERROR", 0);
                    }

                    break;

                case 9: //VER DETALLE DE UN AVION DE PASAJEROS
                    if (aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay aviones dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Avion de pasajeros a buscar: ", 3));

                                if (i_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                i_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerica", "ERROR", 0);
                            }
                        } while (i_aux < 0);

                        encontrado = buscarIdAvion(aviones, i_aux);

                        if (encontrado == -1) {
                            JOptionPane.showMessageDialog(null, "No existe avion con dicho id", "ERROR", 0);
                        } else {
                            Avion av_aux = aviones.get(encontrado);

                            if (av_aux instanceof Pasajeros) {
                                JOptionPane.showMessageDialog(null, av_aux.getDatos(), "DETALLES DE UN AVION DE PASAJEROS", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "El avion con dicho id no corresponde a un avion de pasajeros", "ERROR", 0);
                            }
                        }
                    }

                    break;

                case 10: //VER DETALLE DE UN AVION DE CARGA
                    if (aviones.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay aviones dados de alta", "ERROR", 0);
                    } else {
                        do {
                            try {
                                i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Avion de carga a buscar: ", 3));

                                if (i_aux < 0) {
                                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                                }
                            } catch (NumberFormatException e) {
                                i_aux = -1;
                                JOptionPane.showMessageDialog(null, "El Id debe ser numerica", "ERROR", 0);
                            }
                        } while (i_aux < 0);

                        encontrado = buscarIdAvion(aviones, i_aux);

                        if (encontrado == -1) {
                            JOptionPane.showMessageDialog(null, "No existe avion con dicho id", "ERROR", 0);
                        } else {
                            Avion av_aux = aviones.get(encontrado);

                            if (av_aux instanceof Carga) {
                                JOptionPane.showMessageDialog(null, av_aux.getDatos(), "DETALLES DE UN AVION DE CARGA", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "El avion con dicho id no corresponde a un avion de carga", "ERROR", 0);
                            }
                        }
                    }

                    break;

                case 11: //VER DETALLE DE UN PILOTO
                    if (pilotos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay pilotos dados de alta", "ERROR", 0);
                    } else {
                        do {
                            s_aux = JOptionPane.showInputDialog(null, "Escriba el id del piloto a buscar: ", 3);

                            if (s_aux.isBlank()) {
                                JOptionPane.showMessageDialog(null, "Debe registrar el id del piloto a buscar", "ERROR", 0);
                            }
                            if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                                JOptionPane.showMessageDialog(null, "El id del piloto no debe contener numeros ni caracteres especiales", "ERROR", 0);
                            }
                        } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

                        encontrado = buscarIdPiloto(pilotos, s_aux);

                        if (encontrado == -1) {
                            JOptionPane.showMessageDialog(null, "No existe piloto con dicho id", "ERROR", 0);
                        } else {
                            JOptionPane.showMessageDialog(null, pilotos.get(encontrado).getDatos(), "DETALLES DE UN AVION DE CARGA", 1);
                        }
                    }

                    break;

                case 12: //VER DETALLE DE UN VUELO
                    do {
                        try {
                            i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Vuelo a buscar: ", 3));

                            if (i_aux < 0) {
                                JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                            }
                        } catch (NumberFormatException e) {
                            i_aux = -1;
                            JOptionPane.showMessageDialog(null, "El id debe ser numerico", "ERROR", 0);
                        }
                    } while (i_aux < 0);
                    
                    encontrado = buscarIdVuelo(vuelos, i_aux);
                    
                    if(encontrado == -1){
                        JOptionPane.showMessageDialog(null, "No existe vuelo con dicho id", "ERROR", 0);
                    }
                    else{
                        Vuelo vue_aux = vuelos.get(encontrado);
                        
                        String mensaje ="Id vuelo: " +  vue_aux.getIdVuelo();
                        
                        encontrado = buscarIdAvion(aviones, vue_aux.getIdAvion());
                        
                        if(encontrado == -1){
                            JOptionPane.showMessageDialog(null, "No existe avion con dicho id", "ERROR", 0);
                        }
                        else{
                            Avion av_aux = aviones.get(encontrado);
                            
                            if(av_aux instanceof Pasajeros){
                                mensaje += "\n\nAVION DE PASAJEROS\n";
                            }
                            else{
                                mensaje += "\n\nAVION DE CARGA\n";
                            }
                            
                            mensaje += av_aux.getDatos();
                            
                            encontrado = buscarIdPiloto(pilotos, vue_aux.getIdPiloto());
                            
                            if(encontrado == -1){
                                JOptionPane.showMessageDialog(null, "No existe piloto con dicho id", "ERROR", 0);
                            }
                            else{
                                Piloto pil_aux = pilotos.get(encontrado);
                                
                                mensaje += "\n\nPILOTO\n" + pil_aux.getDatos();
                                
                                mensaje += "\n\nCd. Origen: " + vue_aux.getCdOrigen() +
                                            "\nCd. Destino: " + vue_aux.getCdOrigen() +
                                            "\nFecha de salida: " + vue_aux.getFechaSalida() +
                                            "\nFecha de llegada: " + vue_aux.getFechaLlegada();
                                
                                JOptionPane.showMessageDialog(null, mensaje, "DETALLES DE UN VUELO", 1);
                            }
                        }                        
                    }
                    
                    break;

                case 13: //ELIMINAR UN AVION DE PASAJEROS
                    
                    break;

                case 14: //ELIMINAR UN AVION DE CARGA
                    break;

                case 15: //ELIMINAR UN PILOTO
                    break;

                case 16: //ELIMINAR UN VUELO
                    break;

                case 17: //SALIR
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

//    public static int buscarId(ArrayList<Object> lista, int buscarXint, String buscarXstring) {
//        int pos = -1;
//
//        for (int i = 0; i < lista.size(); i++) {
//            Object obj_auxiliar = lista.get(i);
//
//            if (obj_auxiliar instanceof Pasajeros) { //Buscar id de avion de pasajeros
//                if (((Pasajeros) obj_auxiliar).getIdAvion() == buscarXint) {
//                    pos = i;
//                    break;
//                }
//            }
//            if (obj_auxiliar instanceof Carga) { //Buscar id de avion de carga
//                if (((Carga) obj_auxiliar).getIdAvion() == buscarXint) {
//                    pos = i;
//                    break;
//                }
//            }
//            if (obj_auxiliar instanceof Vuelo) { //Buscar id de vuelo
//                if (((Vuelo) obj_auxiliar).getIdVuelo() == buscarXint) {
//                    pos = i;
//                    break;
//                }
//            }
//            if (obj_auxiliar instanceof Piloto) { //Buscar id del piloto
//                if (buscarXstring.equalsIgnoreCase(((Piloto) obj_auxiliar).getIdPiloto())) {
//                    pos = i;
//                    break;
//                }
//            }
//        }
//
//        return pos;
//    }
    public static Avion darAltaAvion(ArrayList<Avion> lista, int opc) {
        Avion avion_aux;
        if (opc == 1) {
            avion_aux = new Pasajeros();
        } else {
            avion_aux = new Carga();
        }
        int i_aux, encontrado = 0;
        String s_aux;
        float f_aux;

        //Guardando Id del avion
        do {
            try {
                i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el Id del Avion: ", 3));

                if (i_aux < 0) {
                    JOptionPane.showMessageDialog(null, "El Id debe ser positivo", "ERROR", 0);
                }

                encontrado = buscarIdAvion(lista, i_aux);

                if (encontrado != -1) {
                    JOptionPane.showMessageDialog(null, "Un avion ya cuenta con ese Id, digite otro", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                i_aux = -1;
                JOptionPane.showMessageDialog(null, "El Id debe ser numerica", "ERROR", 0);
            }
        } while (i_aux < 0 || encontrado != -1);

        avion_aux.setIdAvion(i_aux);

        //Guardando modelo del avion
        do {
            s_aux = JOptionPane.showInputDialog(null, "Escriba el modelo del avion: ", 3);

            if (s_aux.isBlank()) {
                JOptionPane.showMessageDialog(null, "Debe registrar el modelo del avion", "ERROR", 0);
            }
            if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                JOptionPane.showMessageDialog(null, "El modelo del avion no debe contener numeros ni caracteres especiales", "ERROR", 0);
            }
        } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

        avion_aux.setModelo(s_aux);

        //Guardandno marca del avion
        do {
            s_aux = JOptionPane.showInputDialog(null, "Escriba la marca del avion: ", 3);

            if (s_aux.isBlank()) {
                JOptionPane.showMessageDialog(null, "Debe registrar la marca del avion", "ERROR", 0);
            }
            if (!s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+")) {
                JOptionPane.showMessageDialog(null, "La marca del avion no debe contener numeros ni caracteres especiales", "ERROR", 0);
            }
        } while (s_aux.isBlank() || !s_aux.matches("(([a-z]|[A-Z])+[ ]{0,1})+"));

        avion_aux.setMarca(s_aux);

        //Guardandno capacidad del tanque del avion
        do {
            try {
                i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la capacidad de tanque del Avion: ", 3));

                if (i_aux < 0) {
                    JOptionPane.showMessageDialog(null, "La capacidad del tanque debe ser positiva", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                i_aux = -1;
                JOptionPane.showMessageDialog(null, "La capacidad del tanque debe ser numerica", "ERROR", 0);
            }
        } while (i_aux < 0);

        avion_aux.setCapacidadTanque(i_aux);

        //Guardando el numero de motores del avion
        do {
            try {
                i_aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de motores del Avion: ", 3));

                if (i_aux < 0) {
                    JOptionPane.showMessageDialog(null, "El numero de motores debe ser positivo", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                i_aux = -1;
                JOptionPane.showMessageDialog(null, "El numero de motores debe ser numerico", "ERROR", 0);
            }
        } while (i_aux < 0);

        avion_aux.setNoMotores(i_aux);

        //Guardando la velocidad del avion
        do {
            try {
                f_aux = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite la velocidad del Avion: ", 3));

                if (f_aux < 0) {
                    JOptionPane.showMessageDialog(null, "La velocidad debe ser positiva", "ERROR", 0);
                }
            } catch (NumberFormatException e) {
                f_aux = -1.0f;
                JOptionPane.showMessageDialog(null, "La velocidad debe ser numerica", "ERROR", 0);
            }
        } while (f_aux < 0);

        avion_aux.setVelocidad(f_aux);

        return avion_aux;
    }
}
