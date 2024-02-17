import motor3R.*;
import java.util.Random;
import java.util.Scanner;

public class InterfaceConsola {
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    private TresEnRaya juego = new TresEnRaya('X', 'O');
    private char[][] tablero = juego.getTablero();
    private boolean ganador;
    private int fila, columna;

    public InterfaceConsola() {
        System.out.println("Empezando nueva partida, por favor, escoge dificultad: facil, media o dificil.");
        juego.setDificultad(sc.nextLine());
        System.out.println("Tablero vacío: ");
        imprimirTablero();

        int randomStart = random.nextInt(2);
        if (randomStart == 0)
            turnoJugador();
        else
            turnoMaquina();
    }

    public void imprimirTablero() {
        for (char[] fila : tablero) {
            for (char c : fila) {
                System.out.print(c + "|");
            }
            System.out.println();
        }
    }

    void escogerCords() {
        System.out.print("Fila: ");
        fila = sc.nextInt();
        sc.nextLine();
        System.out.print("Columna: ");
        columna = sc.nextInt();
        sc.nextLine();
    }

    public void turnoJugador() {
        if (!juego.chequearFinal() && !ganador) {
            System.out.println("Turno del jugador, introduce coordenadas: ");
            escogerCords();

            while (tablero[fila][columna] != '_') {
                System.out.println("Casilla ocupada, elije otra.");
                escogerCords();
            }

            juego.actualizarTablero(juego.getHumano(), fila, columna);
            imprimirTablero();

            ganador = juego.comprobarGanador();
            if (ganador)
                System.out.println("Enhorabuena, has ganado!");
            else if (!ganador && juego.chequearFinal())
                System.out.println("Tablero lleno, EMPATE!");
            else
                turnoMaquina();
        }
    }

    public void turnoMaquina() {
        if (!juego.chequearFinal() && !ganador) {
            System.out.println("Turno de la máquina...");

            juego.eleccionMaquina();

            imprimirTablero();
            ganador = juego.comprobarGanador();
            if (ganador)
                System.out.println("LA MAQUINA HA GANADO, PAQUETE");
            else if (!ganador && juego.chequearFinal())
                System.out.println("Tablero lleno, EMPATE!");
            else
                turnoJugador();

        }
    }

    public static void main(String[] args) {
        new InterfaceConsola();
    }
}
