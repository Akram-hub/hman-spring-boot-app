package org.akram.Ahorcado.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Juego {

        private int numIntentos = 6;
        private HashMap<Integer,String> intentos = new HashMap<Integer, String>();

        private File fichero;
        private ArrayList<String> palabras = new ArrayList<String>();
        private String palabra = "";
        private BufferedReader buffer;
        private  int longitudPalabra;
        private  char[] palabraTroceada;
        private String palabraGuionada="";

        public Juego() throws IOException {

            fichero = new File("src/main/resources/ahorcado/palabras.txt");

            buffer = new BufferedReader(new FileReader(fichero));
            while ((palabra = buffer.readLine()) != null) {
                palabras.add(palabra);
            }
            palabra = palabras.get((int) (Math.random() * palabras.size()));
            buffer.close();

            longitudPalabra = palabra.length();
            palabraTroceada = new char[longitudPalabra];

            for (int i = 0; i < palabraTroceada.length; i++) {
                palabraTroceada[i] =  '-';
            }

            intentos.put(0,"cero");
            intentos.put(1,"uno");
            intentos.put(2,"dos");
            intentos.put(3,"tres");
            intentos.put(4,"cuatro");
            intentos.put(5,"cinco");
            intentos.put(6,"seis");
        }

    public void compruebaLetra(String letra) {

        if(palabra.contains(letra)) {
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra.charAt(0)) {
                    palabraTroceada[i] = palabra.charAt(i);
                }
            }
        } else{
            numIntentos--;
        }

    }

    public boolean compruebaGanador(){

        if (String.valueOf(palabraTroceada).equals(palabra)){
            return true;
        } else {
            return false;
        }

    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public void setNumIntentos(int numIntentos) {
        this.numIntentos = numIntentos;
    }

    public HashMap<Integer, String> getIntentos() {
        return intentos;
    }

    public void setIntentos(HashMap<Integer, String> intentos) {
        this.intentos = intentos;
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public BufferedReader getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedReader buffer) {
        this.buffer = buffer;
    }

    public int getLongitudPalabra() {
        return longitudPalabra;
    }

    public void setLongitudPalabra(int longitudPalabra) {
        this.longitudPalabra = longitudPalabra;
    }

    public char[] getPalabraTroceada() {
        return palabraTroceada;
    }

    public void setPalabraTroceada(char[] palabraTroceada) {
        this.palabraTroceada = palabraTroceada;
    }

    public String getPalabraGuionada() {
        return palabraGuionada;
    }

    public void setPalabraGuionada(String palabraGuionada) {
        this.palabraGuionada = palabraGuionada;
    }
}
