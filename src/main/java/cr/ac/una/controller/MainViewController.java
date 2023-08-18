package cr.ac.una.controller;

import cr.ac.una.proyecto.model.Colors;
import cr.ac.una.proyecto.model.User;
import cr.ac.una.util.FlowController;
import cr.ac.una.util.TextCSV;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainViewController implements Initializable {

    @FXML
    private BorderPane ViewMain;
    private BorderPane ViewName;
    private TextField TextField;
    @FXML
    private GridPane Face2;
    @FXML
    private GridPane Face3;
    @FXML
    private GridPane Face4;
    @FXML
    private GridPane Face1;
    @FXML
    private GridPane Face0;
    @FXML
    private GridPane Face5;
    @FXML
    private BorderPane ViewCustome;
    @FXML
    private GridPane SetColor;
    @FXML
    private BorderPane ViewScores;
    @FXML
    private TableView<User> tabView_Scores= new TableView<>();
    @FXML
    private TableColumn tabCol_User= new TableColumn<>();
    @FXML
    private TableColumn tabCol_Movements= new TableColumn<>();
    @FXML
    private TableColumn tabCol_Time= new TableColumn<>();
    @FXML
    private TableColumn tabCol_Score= new TableColumn<>();
    private Colors colors;
    private Boolean Bandera = true;
    private int t = 0;
    private Color ColorSelected = null;
    Stack<Colors> Filtro = new Stack<>();
     public static ObservableList<User> users;
    public static List<User> userlist = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tabCol_User.setCellValueFactory(new PropertyValueFactory("name"));
        this.tabCol_Movements.setCellValueFactory(new PropertyValueFactory("movements"));
        this.tabCol_Time.setCellValueFactory(new PropertyValueFactory("time"));
        this.tabCol_Score.setCellValueFactory(new PropertyValueFactory("score"));
        ViewMain.toFront();
        
        
    }

    @FXML
    private void play(ActionEvent event) { 
        FlowController.getInstance().goMain("VistaModel");
    }
   
    @FXML
    private void Volver(MouseEvent event) {
        ViewMain.toFront();
    }

    @FXML
    private void CustomizeCube(ActionEvent event) {
        FlowController.getInstance().initializeCubeCustume();
        PintarFaces();
        initCenter();
        ViewCustome.toFront();
    }
    void initCenter() {
        for (int i = 0; i < 6; i++) {
            Rectangle cell = new Rectangle(52, 52, RellenarSet(i));
            cell.setStroke(Color.BLACK);
            cell.setStrokeWidth(2);
            cell.setOnMouseClicked(event -> {
                ColorSelected = (Color) cell.getFill();
            });
            SetColor.add(cell, i, 0);
        }
    }
    public void PintarFaces() {
        SetCubes();
        Face0.getChildren().clear();
        Face1.getChildren().clear();
        Face2.getChildren().clear();
        Face3.getChildren().clear();
        Face4.getChildren().clear();
        Face5.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Face0.add(Pintar(Face0, 0, j, 2 - i), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Face1.add(Pintar(Face1, i, 0, 2 - j), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Face2.add(Pintar(Face2, i, j, 0), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Face3.add(Pintar(Face3, i, 2, j), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Face4.add(Pintar(Face4, i, 2 - j, 2), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Face5.add(Pintar(Face5, 2, j, i), j, i);
            }
        }
    }

    public Rectangle Pintar(GridPane Face, int i, int j, int k) {
        Rectangle cell = new Rectangle(52, 52, getColorFromString(i, j, k));
        cell.setStroke(Color.GRAY);
        cell.setStrokeWidth(2);
        cell.setOnMouseClicked(event -> {
            if (ColorSelected != null) {
                if (FlowController.getInstance().Cube[i][j][k].getIdentifier().equals("Arista")) {
                    if (!FlowController.getInstance().Aristas.isEmpty()) {
                        while (Bandera) {
                            if (t != FlowController.getInstance().Aristas.size()) {
                                if (FlowController.getInstance().Aristas.get(t).getColor_1().equals(RellenarSetString(ColorSelected))
                                        || FlowController.getInstance().Aristas.get(t).getColor_2().equals(RellenarSetString(ColorSelected))
                                        || FlowController.getInstance().Aristas.get(t).getColor_3().equals(RellenarSetString(ColorSelected)) && t < FlowController.getInstance().Aristas.size()) {
                                    colors = FlowController.getInstance().Aristas.get(t);
                                    FlowController.getInstance().Aristas.remove(t);
                                    Bandera = false;
                                }
                            } else {
                                Bandera = false;
                            }
                            t++;
                        }
                        t = 0;
                        Bandera = true;
                        FlowController.getInstance().Cube[i][j][k].setColor_1(colors.getColor_1());
                        FlowController.getInstance().Cube[i][j][k].setColor_2(colors.getColor_2());
                        colors.setColor_1("na");
                        colors.setColor_2("na");
                    }
                } else {
                    if (!FlowController.getInstance().Cube[i][j][k].getIdentifier().equals("Centro")) {
                        if (!FlowController.getInstance().Esquinas.isEmpty()) {
                            while (Bandera) {
                                if (t != FlowController.getInstance().Esquinas.size()) {
                                    if (FlowController.getInstance().Esquinas.get(t).getColor_1().equals(RellenarSetString(ColorSelected))
                                            || FlowController.getInstance().Esquinas.get(t).getColor_2().equals(RellenarSetString(ColorSelected))
                                            || FlowController.getInstance().Esquinas.get(t).getColor_3().equals(RellenarSetString(ColorSelected)) && t < FlowController.getInstance().Esquinas.size()) {
                                        colors = FlowController.getInstance().Esquinas.get(t);
                                        FlowController.getInstance().Esquinas.remove(t);
                                        Bandera = false;
                                    }
                                } else {
                                    Bandera = false;
                                }
                                t++;
                            }
                            t = 0;
                            Bandera = true;
                            FlowController.getInstance().Cube[i][j][k].setColor_1(colors.getColor_1());
                            FlowController.getInstance().Cube[i][j][k].setColor_2(colors.getColor_2());
                            FlowController.getInstance().Cube[i][j][k].setColor_3(colors.getColor_3());
                            colors.setColor_1("na");
                            colors.setColor_2("na");
                            colors.setColor_3("na");
                        }
                    }
                }
                PintarFaces();
            }
        });
        return cell;
    }

    private Color getColorFromString(int i, int j, int k) {
        String colorStr = null;
        if (FlowController.getInstance().Cube[i][j][k].getIdentifier().equals("Centro")) {
            colorStr = FlowController.getInstance().Cube[i][j][k].getColor_1();
        } else {
            if (FlowController.getInstance().Cube[i][j][k].getIdentifier().equals("Arista")) {
                if (!FlowController.getInstance().Cube[i][j][k].getPrimerColor()) {
                    colorStr = FlowController.getInstance().Cube[i][j][k].getColor_1();
                    FlowController.getInstance().Cube[i][j][k].setPrimerColor(true);
                } else {
                    colorStr = FlowController.getInstance().Cube[i][j][k].getColor_2();
                }
            } else {
                if (!FlowController.getInstance().Cube[i][j][k].getPrimerColor()) {
                    colorStr = FlowController.getInstance().Cube[i][j][k].getColor_1();
                    FlowController.getInstance().Cube[i][j][k].setPrimerColor(true);
                } else {
                    if (!FlowController.getInstance().Cube[i][j][k].getSegundoColor()) {
                        colorStr = FlowController.getInstance().Cube[i][j][k].getColor_2();
                        FlowController.getInstance().Cube[i][j][k].setSegundoColor(true);
                    } else {
                        colorStr = FlowController.getInstance().Cube[i][j][k].getColor_3();
                    }
                }
            }
        }
        switch (colorStr) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "orange":
                return Color.ORANGE;
            case "white":
                return Color.WHITE;
            case "yellow":
                return Color.YELLOW;
            default:
                return Color.TRANSPARENT;
        }
    }

    Color RellenarSet(int i) {
        switch (i) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.ORANGE;
            case 4:
                return Color.WHITE;
            case 5:
                return Color.YELLOW;
            default:
                return Color.TRANSPARENT;
        }
    }

    String RellenarSetString(Color i) {
        switch (i.toString()) {
            case "0xff0000ff":
                return "red";
            case "0x0000ffff":
                return "blue";
            case "0x008000ff":
                return "green";
            case "0xffa500ff":
                return "orange";
            case "0xffffffff":
                return "white";
            case "0xffff00ff":
                return "yellow";
            default:
                return "transparent";
        }
    }

    public void SetCubes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    FlowController.getInstance().Cube[i][j][k].setPrimerColor(false);
                    FlowController.getInstance().Cube[i][j][k].setSegundoColor(false);
                }
            }
        }
    }

    @FXML
    private void Reset(ActionEvent event) {
        FlowController.getInstance().initializeCubeCustume();
        PintarFaces();
        initCenter();
    }

    @FXML
    private void ResetCuboBase(ActionEvent event) {
        FlowController.getInstance().initializeCube();
        PintarFaces();
    }

    @FXML
    private void viewScores(ActionEvent event) {
        TextCSV load=new TextCSV();
        load.loadScoreUser();
        listUsersScores();
        ViewScores.toFront();
    }
    public void listUsersScores(){
        userlist.clear();
        List<User> list=new ArrayList<>();
        list.clear();
        list=FlowController.getInstance().Players;
        Import(list);
        
        users=FXCollections.observableArrayList(userlist);
        this.tabView_Scores.refresh();
        this.tabView_Scores.setItems(users);
    }
    public void Import(List<User> Users) {
        for (int i = 0; i < Users.size(); i++) {
            userlist.add(Users.get(i));
            System.out.println(""+userlist.get(i).getName());
        }
    }

}
