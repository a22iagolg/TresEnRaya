package motor3R;

import java.util.Random;

/**
 * Clase que contiene la lógica del juego 3 en Raya. 
 */
public class TresEnRaya {
    private char[][] tablero = new char[3][3];
    private char humano;
    private char maquina;
    private int dificultad;

    /**
     * Devuelve el Char del jugador.
     * 
     * @return Char del jugador.
     */

    public char getHumano() {
        return humano;
    }

    /**
     * Devuelve el tablero de juego.
     */
    public char[][] getTablero() {
        return tablero;
    }

    /**
     * Establecer la dificultad de la partida. Por ahora todas son la misma.
     *
     * @param difName Nombre de la dificultad, establece un int.
     */
    public void setDificultad(String difName) {
        dificultad = switch (difName) {
            case "facil" -> 1;
            case "normal" -> 2;
            case "dificil" -> 3;
            default -> 1;
        };
    }

    /**
     * Constructor de la clase con dos parámetros. Rellena el tablero de espacios vacíos "_"
     * 
     * @param humano  Char del jugador.
     * @param maquina Char de la CPU.
     */
    public TresEnRaya(char humano, char maquina) {

        // Rellenar el tablero de espacios vacíos
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '_';
            }
        }

        // Asignar chars a player y maquina
        this.humano = humano;
        this.maquina = maquina;
    }

    /**
     * Establece el caracter del player/CPU en el tablero.
     * 
     * @param c       Char que será incrustado en el tablero (player o CPU)
     * @param fila    Fila en la que se introducirá.
     * @param columna Columna en la que se introducirá.
     */
    public void actualizarTablero(char c, int fila, int columna) {
        tablero[fila][columna] = c;
    }

    /**
     * Método que gestiona el movimiento de la máquina en función de la dificultad.
     * Por ahora solamente una.
     */
    public void eleccionMaquina() {
        if (dificultad == 1 || dificultad == 2 || dificultad == 3) {
            Random random = new Random();
            int fila = random.nextInt(3);
            int columna = random.nextInt(3);

            while (tablero[fila][columna] != '_') {
                fila = random.nextInt(3);
                columna = random.nextInt(3);
            }
            actualizarTablero(maquina, fila, columna);
        }
    }

    /**
     * Verifica si el tablero tiene todas las filas llenas.
     */
    public boolean chequearFinal() {
        // Recorrer en busca de espacios vacíos
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica si ha habido ganador, es decir, si alguna de las filas o cualquiera de las diagonales del tablero son iguales.
     */
    public boolean comprobarGanador() {
        // comprobar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][0] != '_') {
                return true;
            }
        }

        // comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i] && tablero[0][i] != '_') {
                return true;
            }
        }

        // comprobar diagonales
        if ((tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != '_') ||
                (tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != '_')) {
            return true;
        }

        // ultimo caso: no se cumple ninguno
        return false;
    }

}