package com.example.stden;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GestioneFile {
    String nFile = "ListaPaz.txt";
    static final String rimozione = "Rimossi.txt";//quelli che devo ancora cancellare
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    FileReader fileReader;
    FileWriter fileWriter;


    public static ArrayList<String> Pazienti = new ArrayList<String>();



    public boolean apriInScrittura() {//Appartenente al file ListaPaz.txt
        boolean esito = false;
        try {
            fileWriter = new FileWriter(nFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            esito = true;
        } catch (IOException error2) {
            System.out.println("Non è possibile aprire in scrittura il file");
        }
        return esito;
    }




    public boolean apriInLettura() {//Appartenente al file ListaPaz.txt
        boolean esito = false;
        try {
            fileReader = new FileReader(nFile);
            bufferedReader = new BufferedReader(fileReader);
            esito = true;
        } catch (FileNotFoundException error) {
            System.out.println("Il file non è stato trovato");
            try {
                //se il file non esiste, lo creiamo
                fileWriter = new FileWriter(nFile);
                esito = true;
            } catch (IOException error2) {
                System.out.println("Non è possibile creare il file");
                esito = false;
            }
        }
        return esito;
    }

    public boolean chiudiFileInLettura() {//Appartenente al file ListaPaz.txt
        boolean esito = false;
        try {
            bufferedReader.close();
            esito = true;
        } catch (IOException error) {
            System.out.println("Non è possibile chiudere il file");
        }
        return esito;
    }


    public boolean chiudiFileInScrittura() {
        boolean esito = false;
        try {
            bufferedWriter.close();
            esito = true;
        } catch (IOException error) {
            System.out.println("Non è possibile chiudere il file");
        }
        return esito;
    }



    public static void writeFile(Paziente p) {//Appartenente al file ListaPaz.txt
        String path = "ListaPaz.txt";
        boolean esito = false;

        String dati = p.getNome() + ";" + p.getCognome() + ";" + p.getCf() + ";" + p.getProblema() + ";" + p.getTel() + ";" + p.getData() + " ";
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(dati);
            bufferedWriter.newLine(); // Aggiungi una nuova riga dopo ogni paziente
            bufferedWriter.flush();
            esito = true;

            bufferedWriter.close();

            System.out.println("Dati scritti correttamente su " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void visual() {
        for (int i = 0; i < Pazienti.size(); i++) {
            System.out.println(Pazienti.get(i));
        }
    }

    public Paziente leggiPaziente() {
        Paziente p = new Paziente();
        try {
            String stringaInput = bufferedReader.readLine();

            if (stringaInput == null) p = null;
            else {
                p = trasformaStringInPaziente(stringaInput);
            }


        } catch (IOException e) {
            System.out.println("Errore di lettura");
        }
        return p;
    }

    public Paziente trasformaStringInPaziente(String stringaInput) {
        StringTokenizer st = new StringTokenizer(stringaInput, ";");

        String nome = st.nextToken();
        String cognome = st.nextToken();
        String cf = st.nextToken();
        String problema = st.nextToken();
        String tel = st.nextToken();
        String data = st.nextToken();
        Paziente p = new Paziente();
        p.setNome(nome);
        p.setCognome(cognome);
        p.setProblema(problema);
        p.setCf(cf);
        p.setTel(tel);
        p.setData(data);


        return p;

    }


    public boolean apriScritt() {//Appartenente al file Rimossi.txt
        boolean esito = false;
        try {
            fileWriter = new FileWriter(rimozione, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            esito = true;
        } catch (IOException error2) {
            System.out.println("Non è possibile aprire in scrittura il file");
        }
        return esito;
    }

    public boolean apriLett() {//Appartenente al file Rimossi.txt
        boolean esito = false;
        try {
            fileReader = new FileReader(rimozione);
            bufferedReader = new BufferedReader(fileReader);
            esito = true;
        } catch (FileNotFoundException error) {
            System.out.println("Il file non è stato trovato");
            try {
                //se il file non esiste, lo creiamo
                fileWriter = new FileWriter(rimozione);
                esito = true;
            } catch (IOException error2) {
                System.out.println("Non è possibile creare il file");
                esito = false;
            }
        }
        return esito;
    }

    public boolean chiudiFileLett() {//Appartenente al file Rimossi.txt
        boolean esito = false;
        try {
            bufferedReader.close();
            esito = true;
        } catch (IOException error) {
            System.out.println("Non è possibile chiudere il file");
        }
        return esito;
    }

    public boolean chiudiFileScritt() {//Appartenente al file Rimossi.txt
        boolean esito = false;
        try {
            bufferedWriter.close();
            esito = true;
        } catch (IOException error) {
            System.out.println("Non è possibile chiudere il file");
        }
        return esito;
    }


    /*public static void writeFile_rimossi(Paziente p) {
        String path = "Rimossi.txt";

        boolean esito = false;

        String dati = p.getNome() + ";" + p.getCognome() + ";" + p.getCf() + ";" + p.getProblema() + ";" + p.getTel() + ";" + p.getData() + " ";
        try {
            FileWriter fileWriter = new FileWriter(rimozione, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(dati);
            bufferedWriter.flush();
            esito = true;

            bufferedWriter.close();

            System.out.println("Dati scritti correttamente su " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/


    public void Elimina(){//Il metodo legge i dati dal file, li scrive su un altro file, eliminando l'originale e riscrive nuovamente i dati sul file originale
        String path = "ListaPaz.txt";
        boolean esito = false;
        apriInLettura();
        apriScritt();
        for (int i = 0; i < nFile.length() - 11; i++) {
            try {
                FileWriter fileWriter = new FileWriter(rimozione, true);//FileWriter consente di scrivere dati sul file
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String stringaInput = bufferedReader.readLine();
                stringaInput = bufferedReader.readLine();
                bufferedWriter.write(stringaInput);
                bufferedWriter.newLine(); // Aggiunge una nuova riga dopo ogni paziente
                bufferedWriter.flush();//svuota il flusso
                esito = true;

                bufferedWriter.close();

                System.out.println("Dati scritti correttamente su " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        chiudiFileScritt();
        chiudiFileInLettura();

        File file = new File(nFile);

        boolean result = file.delete();


        apriInScrittura();
        apriLett();
        for (int i = 0; i < rimozione.length() - 1; i++) {
            try {
                FileWriter fileWriter = new FileWriter(nFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String stringaInput = bufferedReader.readLine();
                bufferedWriter.write(stringaInput);
                bufferedWriter.newLine(); // Aggiunge una nuova riga dopo ogni paziente
                bufferedWriter.flush();
                esito = true;

                bufferedWriter.close();

                System.out.println("Dati scritti correttamente su " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        chiudiFileLett();
        chiudiFileInScrittura();
    }


}


//per cancellare devo usare un file di appoggio



















