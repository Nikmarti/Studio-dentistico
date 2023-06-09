package com.example.stden;



import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;


public class HelloController {

    @FXML
    private MenuBar menu;

    @FXML
    private Parent root;
    @FXML
    private Stage stage_paziente;
    private Scene scene_paziente;


    @FXML
    public Label stampa;


    @FXML
    public Pane pane;

    @FXML
    public TextField Nome;
    @FXML
    public TextField Cognome;
    @FXML
    public TextField Problema;
    @FXML
    public TextField CF;
    @FXML
    public TextField TEL;


    @FXML
    public TextField Data;


    public ListView<String> lstPazienti = new ListView<String>();


    @FXML
    ListView<String> pazientiListView = new ListView<>();


    Paziente paziente = new Paziente();

    GestioneFile gestioneFile = new GestioneFile();


    public void Registra() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage_paziente = (Stage) menu.getScene().getWindow();
        stage_paziente.setTitle("Registrazione");
        scene_paziente = new Scene(root);
        stage_paziente.setScene(scene_paziente);
        stage_paziente.show();
        stage_paziente.setResizable(false);
    }


    public void Lista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("visualizza.fxml"));
            Parent root = loader.load();
            stage_paziente = (Stage) menu.getScene().getWindow();

            stage_paziente.setScene(scene_paziente);
            scene_paziente = new Scene(root);
            stage_paziente.setScene(scene_paziente);
            stage_paziente.show();
            stage_paziente.setResizable(false);
            //newStage.show();


            pazientiListView = (ListView<String>) loader.getNamespace().get("pazientiListView");
            pazientiListView.setItems(lstPazienti.getItems());

            if (gestioneFile.apriInLettura() == true) {
                Paziente p = new Paziente();

                do {
                    p = gestioneFile.leggiPaziente();


                    if (p != null) {
                        String pazienteString = p.toString();
                        lstPazienti.getItems().add(pazienteString);
                        System.out.println(pazienteString);
                    }
                } while (p != null);


            } else {
                System.out.println("ERROR");
            }


            gestioneFile.chiudiFileInLettura();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void TastoInvio() {
        String nome = Nome.getText();
        String cognome = Cognome.getText();
        String cf = CF.getText();
        String problema = Problema.getText();
        String tel = TEL.getText();
        String data = Data.getText();



        if (nome.isEmpty() || cognome.isEmpty() || cf.isEmpty() || problema.isEmpty() || tel.isEmpty() || data.isEmpty()) {
            stampa.setText("Completa i campi");
        } else {
            paziente.setNome(nome);
            paziente.setCognome(cognome);
            paziente.setCf(cf);
            paziente.setProblema(problema);
            paziente.setTel(tel);
            paziente.setData(data);
            stampa.setText("Paziente registrato");


            if (gestioneFile.apriInScrittura()) {
                gestioneFile.writeFile(paziente);
                gestioneFile.chiudiFileScritt();
            } else {
                System.out.println("ERROR");
            }
        }
    }



    @FXML
    public void Cancella() throws IOException {
        ObservableList<String> selectedItems = pazientiListView.getSelectionModel().getSelectedItems();
        lstPazienti.getItems().removeAll(selectedItems);

        pazientiListView.getItems().removeAll(selectedItems);


        gestioneFile.Elimina();


        gestioneFile.visual();


    }




}

















































