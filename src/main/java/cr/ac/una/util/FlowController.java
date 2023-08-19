    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.util;

import cr.ac.una.controller.Controller;
import cr.ac.una.proyecto.App;
import cr.ac.una.proyecto.model.Colors;
import cr.ac.una.proyecto.model.CubeRubik;
import cr.ac.una.proyecto.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlowController {

    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static ResourceBundle idioma;
    private static HashMap<String, FXMLLoader> loaders = new HashMap<>();
    private static String NombreJugador = "Jugador 1";
    public static CubeRubik[][][] Cube = new CubeRubik[3][3][3];
    public static CubeRubik[][][] CubeCopy = new CubeRubik[3][3][3];
    public static Stack<Integer> pila1 = new Stack<>();
    public static Stack<Colors> Centro = new Stack<>();
    public static ArrayList<Integer> numeros = new ArrayList<>();
    public static Stack<Colors> Aristas = new Stack<>();
    public static Stack<Colors> Esquinas = new Stack<>();
    public static ArrayList<User> Players=new ArrayList<>();
    

    private FlowController() {
        initializeCube();
        initializeCubeCopy();
    }

    private static void CargarPilas() {
        String[] colors = {"white", "orange", "blue", "red", "green", "yellow"};
        String[][] aristas = {
            {"white", "orange"}, {"white", "green"}, {"white", "blue"}, {"white", "red"},
            {"orange", "yellow"}, {"blue", "yellow"}, {"red", "yellow"}, {"green", "yellow"},
            {"orange", "green"}, {"orange", "blue"}, {"red", "blue"}, {"green", "red"}
        };
        String[][] esquinas = {
            {"white", "orange", "green"}, {"white", "orange", "blue"},
            {"white", "green", "red"}, {"white", "red", "blue"},
            {"orange", "green", "yellow"}, {"orange", "blue", "yellow"},
            {"green", "red", "yellow"}, {"red", "blue", "yellow"}
        };
        for (String color : colors) {
            Centro.add(new Colors(color));
        }
        for (String[] arista : aristas) {
            Aristas.add(new Colors(arista[0], arista[1]));
        }
        for (String[] esquina : esquinas) {
            Esquinas.add(new Colors(esquina[0], esquina[1], esquina[2]));
        }
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FlowController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FlowController();
                }
            }
        }
    }

        public static FlowController getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    public static String getNombreJugador() {
        return NombreJugador;
    }

    public static void setNombreJugador(String NombreJugador) {
        FlowController.NombreJugador = NombreJugador;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void InitializeFlow(Stage stage, ResourceBundle idioma) {
        getInstance();
        this.mainStage = stage;
        this.idioma = idioma;
    }
    public Stage Flow() {
        return mainStage;
    }
    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = loaders.get(name);
        if (loader == null) {
            synchronized (FlowController.class) {
                if (loader == null) {
                    try {
                        loader = new FXMLLoader(App.class.getResource(name + ".fxml"), this.idioma);
                        loader.load();
                        loaders.put(name, loader);
                    } catch (Exception ex) {
                        loader = null;
                    }
                }
            }
        }
        return loader;
    }

    public void goMain(String vista) {
        try {
            this.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource(vista + ".fxml"), this.idioma)));
            this.mainStage.show();
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }
    public void goView(String viewName) {
        goView(viewName, "Center", null);
    }

    public void goView(String viewName, String accion) {
        goView(viewName, "Center", accion);
    }

    public void goView(String viewName, String location, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = this.mainStage;
            controller.setStage(stage);
        }
        switch (location) {
            case "Center":
                ((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().clear();
                ((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().add(loader.getRoot());
                break;
            case "Top":
                break;
            case "Bottom":
                break;
            case "Right":
                break;
            case "Left":
                break;
            default:
                break;
        }
    }

    public void goViewInStage(String viewName, Stage stage) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setStage(stage);
        stage.getScene().setRoot(loader.getRoot());
    }

    public Controller getController(String viewName) {
        return getLoader(viewName).getController();
    }

    public void initialize() {
        this.loaders.clear();
    }

    public void salir() {
        this.mainStage.close();
    }
    public void initializeCube() {
        Cube[1][1][1] = new CubeRubik("white", "Centro");
        Cube[0][1][1] = new CubeRubik("white", "Centro");
        Cube[1][0][1] = new CubeRubik("orange", "Centro");
        Cube[1][1][0] = new CubeRubik("green", "Centro");
        Cube[2][1][1] = new CubeRubik("yellow", "Centro");
        Cube[1][2][1] = new CubeRubik("red", "Centro");
        Cube[1][1][2] = new CubeRubik("blue", "Centro");

        Cube[0][0][1] = new CubeRubik("white", "orange", "Arista");
        Cube[0][1][0] = new CubeRubik("white", "green", "Arista");
        Cube[0][1][2] = new CubeRubik("white", "blue", "Arista");
        Cube[0][2][1] = new CubeRubik("white", "red", "Arista");
        Cube[2][0][1] = new CubeRubik("orange", "yellow", "Arista");
        Cube[2][1][2] = new CubeRubik("blue", "yellow", "Arista");
        Cube[2][2][1] = new CubeRubik("red", "yellow", "Arista");
        Cube[2][1][0] = new CubeRubik("green", "yellow", "Arista");
        Cube[1][0][0] = new CubeRubik("orange", "green", "Arista");
        Cube[1][0][2] = new CubeRubik("orange", "blue", "Arista");
        Cube[1][2][2] = new CubeRubik("red", "blue", "Arista");
        Cube[1][2][0] = new CubeRubik("green", "red", "Arista");

        Cube[0][0][0] = new CubeRubik("white", "orange", "green", "Esquina");
        Cube[0][0][2] = new CubeRubik("white", "orange", "blue", "Esquina");
        Cube[0][2][0] = new CubeRubik("white", "green", "red", "Esquina");
        Cube[0][2][2] = new CubeRubik("white", "red", "blue", "Esquina");
        Cube[2][0][0] = new CubeRubik("orange", "green", "yellow", "Esquina");
        Cube[2][0][2] = new CubeRubik("orange", "blue", "yellow", "Esquina");
        Cube[2][2][0] = new CubeRubik("green", "red", "yellow", "Esquina");
        Cube[2][2][2] = new CubeRubik("red", "blue", "yellow", "Esquina");
    }

    public void initializeCubeCustume() {
        CargarPilas();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                   Cube[i][j][k].setColor_1("na");
                   Cube[i][j][k].setColor_2("na");
                   Cube[i][j][k].setColor_3("na");
                }
            }
        }
        Cube[1][1][1] = new CubeRubik("white", "Centro");
        Cube[0][1][1] = new CubeRubik("white", "Centro");
        Cube[1][0][1] = new CubeRubik("orange", "Centro");
        Cube[1][1][0] = new CubeRubik("green", "Centro");
        Cube[2][1][1] = new CubeRubik("yellow", "Centro");
        Cube[1][2][1] = new CubeRubik("red", "Centro");
        Cube[1][1][2] = new CubeRubik("blue", "Centro");
    }
    public void initializeCubeCopy() {
        CubeCopy[1][1][1] = new CubeRubik("white", "Centro");
        CubeCopy[0][1][1] = new CubeRubik("white", "Centro");
        CubeCopy[1][0][1] = new CubeRubik("orange", "Centro");
        CubeCopy[1][1][0] = new CubeRubik("green", "Centro");
        CubeCopy[2][1][1] = new CubeRubik("yellow", "Centro");
        CubeCopy[1][2][1] = new CubeRubik("red", "Centro");
        CubeCopy[1][1][2] = new CubeRubik("blue", "Centro");

        CubeCopy[0][0][1] = new CubeRubik("white", "orange", "Arista");
        CubeCopy[0][1][0] = new CubeRubik("white", "green", "Arista");
        CubeCopy[0][1][2] = new CubeRubik("white", "blue", "Arista");
        CubeCopy[0][2][1] = new CubeRubik("white", "red", "Arista");
        CubeCopy[2][0][1] = new CubeRubik("orange", "yellow", "Arista");
        CubeCopy[2][1][2] = new CubeRubik("blue", "yellow", "Arista");
        CubeCopy[2][2][1] = new CubeRubik("red", "yellow", "Arista");
        CubeCopy[2][1][0] = new CubeRubik("green", "yellow", "Arista");
        CubeCopy[1][0][0] = new CubeRubik("orange", "green", "Arista");
        CubeCopy[1][0][2] = new CubeRubik("orange", "blue", "Arista");
        CubeCopy[1][2][2] = new CubeRubik("red", "blue", "Arista");
        CubeCopy[1][2][0] = new CubeRubik("green", "red", "Arista");

        CubeCopy[0][0][0] = new CubeRubik("white", "orange", "green", "Esquina");
        CubeCopy[0][0][2] = new CubeRubik("white", "orange", "blue", "Esquina");
        CubeCopy[0][2][0] = new CubeRubik("white", "green", "red", "Esquina");
        CubeCopy[0][2][2] = new CubeRubik("white", "red", "blue", "Esquina");
        CubeCopy[2][0][0] = new CubeRubik("orange", "green", "yellow", "Esquina");
        CubeCopy[2][0][2] = new CubeRubik("orange", "blue", "yellow", "Esquina");
        CubeCopy[2][2][0] = new CubeRubik("green", "red", "yellow", "Esquina");
        CubeCopy[2][2][2] = new CubeRubik("red", "blue", "yellow", "Esquina");
    }
}
