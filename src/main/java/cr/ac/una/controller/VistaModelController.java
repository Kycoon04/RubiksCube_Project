package cr.ac.una.controller;

import cr.ac.una.proyecto.App;
import cr.ac.una.proyecto.model.Movements;
import cr.ac.una.util.FlowController;
import static cr.ac.una.util.FlowController.Cube;
import cr.ac.una.util.TextCSV;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class VistaModelController implements Initializable {

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
    private TextArea Movements;
    @FXML
    private TextArea stepsP;
    @FXML
    private Text Cronometro;
    @FXML
    private Text Jugador;
   private boolean isHelpVisible = false;
    int HEIGHT = 620;
    int WEIGHT = 1180;
    int Segundos = 0;
    int minutos = 0;

    private Movements Moviment = new Movements();
 
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    @FXML
    private AnchorPane AnchoPancho;
    @FXML
    private GridPane FaceMini0;
    @FXML
    private GridPane FaceMini1;
    @FXML
    private GridPane FaceMini2;
    @FXML
    private GridPane FaceMini3;
    @FXML
    private GridPane FaceMini4;
    @FXML
    private GridPane FaceMini5;
    @FXML
    private Button help;
    @FXML
    private ImageView imgHelp;
    @FXML
    private Text Jugador1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PintarFaces();
        PintarFacesMini();
        Contador();
        Jugador.setText("Jugador: " + FlowController.getInstance().getNombreJugador());
        Cube3D();
    }

    void Contador() {
        Timeline timeline = (new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            Segundos += 1;
            if (Segundos == 60) {
                Segundos = 0;
                minutos += 1;
            }
            if (Segundos < 10) {
                Cronometro.setText(minutos + ":0" + Segundos);
            } else {
                Cronometro.setText(minutos + ":" + Segundos);
            }
        })));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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

    public Rectangle Pintar(GridPane Face, int i, int j, int k) {
        Rectangle cell = new Rectangle(52, 52, getColorFromString(i, j, k));
        cell.setStroke(Color.BLACK);
        cell.setStrokeWidth(2);
        return cell;
    }

    public Rectangle PintarMini(GridPane Face, int i, int j, int k) {
        Rectangle cell = new Rectangle(26, 26, getColorFromString(i, j, k));
        cell.setStroke(Color.BLACK);
        cell.setStrokeWidth(2);
        return cell;
    }

    public void PintarFaces() {
        SetCubes();
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

    public void PintarFacesMini() {
        SetCubes();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FaceMini0.add(PintarMini(FaceMini0, 0, j, 2 - i), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FaceMini1.add(PintarMini(FaceMini1, i, 0, 2 - j), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FaceMini2.add(PintarMini(FaceMini2, i, j, 0), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FaceMini3.add(PintarMini(FaceMini3, i, 2, j), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FaceMini4.add(PintarMini(FaceMini4, i, 2 - j, 2), j, i);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                FaceMini5.add(PintarMini(FaceMini5, 2, j, i), j, i);
            }
        }
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

    @FXML
    private void R(ActionEvent event) {
        Movements.setText(Movements.getText() + "R ");
        Moviment.SpinR(FlowController.getInstance().Cube, false);
        PintarFaces();
        PintarFacesMini();
        FlowController.getInstance().numeros.add(1);
        GetPasos();
    }

    @FXML
    private void L(ActionEvent event) {
        Movements.setText(Movements.getText() + "L ");
        Moviment.SpinL(FlowController.getInstance().Cube, false);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(11);
        GetPasos();
    }

    @FXML
    private void F(ActionEvent event) {
        Movements.setText(Movements.getText() + "F ");
        Moviment.SpinF(FlowController.getInstance().Cube, false);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(9);
        GetPasos();
    }

    @FXML
    private void D(ActionEvent event) {
        Movements.setText(Movements.getText() + "D ");
        Moviment.SpinD(FlowController.getInstance().Cube, false);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(5);
        GetPasos();
    }

    @FXML
    private void B(ActionEvent event) {
        Movements.setText(Movements.getText() + "B ");
        Moviment.SpinB(FlowController.getInstance().Cube, false);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(7);
        GetPasos();
    }

    @FXML
    private void U(ActionEvent event) {
        Movements.setText(Movements.getText() + "U ");
        Moviment.SpinU(FlowController.getInstance().Cube, false);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(3);
        GetPasos();
    }

    @FXML
    private void RPrima(ActionEvent event) {
        Movements.setText(Movements.getText() + "R° ");
        Moviment.SpinR(FlowController.getInstance().Cube, true);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(2);
        GetPasos();
    }

    @FXML
    private void LPrima(ActionEvent event) {
        Movements.setText(Movements.getText() + "L° ");
        Moviment.SpinL(FlowController.getInstance().Cube, true);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(12);
        GetPasos();
    }

    @FXML
    private void FPrima(ActionEvent event) {
        Movements.setText(Movements.getText() + "F° ");
        Moviment.SpinF(FlowController.getInstance().Cube, true);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(10);
        GetPasos();
    }

    @FXML
    private void DPrima(ActionEvent event) {
        Movements.setText(Movements.getText() + "D° ");
        Moviment.SpinD(FlowController.getInstance().Cube, true);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(6);
        GetPasos();
    }

    @FXML
    private void BPrima(ActionEvent event) {
        Movements.setText(Movements.getText() + "B° ");
        Moviment.SpinB(FlowController.getInstance().Cube, true);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(8);
        GetPasos();
    }

    @FXML
    private void UPrima(ActionEvent event) {
        Movements.setText(Movements.getText() + "U° ");
        Moviment.SpinU(FlowController.getInstance().Cube, true);
        PintarFaces();
        PintarFacesMini();
         FlowController.getInstance().numeros.add(4);
        GetPasos();
    }

    @FXML
    private void unSolveCube(MouseEvent event) {
        ArrayList<Integer> numerosAuxiliar = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            numerosAuxiliar.add(i);
        }
        Collections.shuffle(numerosAuxiliar);
        getMoviments(numerosAuxiliar, FlowController.getInstance().pila1, true);
         FlowController.getInstance().numeros.addAll(numerosAuxiliar);
        metodo();
    }

    @FXML
    private void solveCube(MouseEvent event) {
        if (! FlowController.getInstance().numeros.isEmpty()) {
            metodo();
            getMoviments( FlowController.getInstance().numeros, FlowController.getInstance().pila1, false);
        } else {
            System.out.println("Alerta: Debes realizar algún movimiento.");
        }
    }

    public static <T> Stack<T> convertirListaEnPila(List<T> lista) {
        Stack<T> pila1 = new Stack<>();
        for (T elemento : lista) {
            pila1.push(elemento);

        }
        return pila1;
    }

    public void metodo() {
        FlowController.getInstance().pila1 = convertirListaEnPila( FlowController.getInstance().numeros);
    }

    public void getMoviments(ArrayList<Integer> listNum, Stack<Integer> pilaParametro, Boolean Solve) {
        int num = 0;
        float counter = 0;
        for (int i = 0; i < listNum.size(); i++) {
            if (Solve) {
                num = listNum.get(i);
            } else {
                num = pilaParametro.pop();
            }
            final int numFinal = num;
            PauseTransition delayAppearance = new PauseTransition(Duration.seconds(counter));
            delayAppearance.setOnFinished(event -> {
                switch (numFinal) {
                    case 1:
                        Moviment.SpinR(FlowController.getInstance().Cube, !Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 2:
                        Moviment.SpinR(FlowController.getInstance().Cube, Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 3:
                        Moviment.SpinU(FlowController.getInstance().Cube, !Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 4:
                        Moviment.SpinU(FlowController.getInstance().Cube, Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 5:
                        Moviment.SpinD(FlowController.getInstance().Cube, !Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 6:
                        Moviment.SpinD(FlowController.getInstance().Cube, Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 7:
                        Moviment.SpinB(FlowController.getInstance().Cube, !Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 8:
                        Moviment.SpinB(FlowController.getInstance().Cube, Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 9:
                        Moviment.SpinF(FlowController.getInstance().Cube, !Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 10:
                        Moviment.SpinF(FlowController.getInstance().Cube, Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 11:
                        Moviment.SpinL(FlowController.getInstance().Cube, !Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                    case 12:
                        Moviment.SpinL(FlowController.getInstance().Cube, Solve);
                        PintarFaces();
                        PintarFacesMini();
                        break;
                }
            });
            delayAppearance.play();
            counter += 0.30;
        }
        if (!Solve) {
            listNum.clear();
            pilaParametro.clear();
        }
    }

    @FXML
    private void actionPerformed(ActionEvent event) {
        metodo();
        GetPasos();
    }

    public void GetPasos() {
        if (!FlowController.getInstance().pila1.isEmpty()) {
            switch (FlowController.getInstance().pila1.pop()) {
                case 1:
                    stepsP.setText(" R° ");
                    break;
                case 2:
                    stepsP.setText(" R ");
                    break;
                case 3:
                    stepsP.setText(" U° ");
                    break;
                case 4:
                    stepsP.setText(" U ");
                    break;
                case 5:
                    stepsP.setText(" D° ");
                    break;
                case 6:
                    stepsP.setText(" D ");
                    break;
                case 7:
                    stepsP.setText(" B° ");
                    break;
                case 8:
                    stepsP.setText(" B ");
                    break;
                case 9:
                    stepsP.setText(" F° ");
                    break;
                case 10:
                    stepsP.setText(" F ");
                    break;
                case 11:
                    stepsP.setText(" L° ");
                    break;
                case 12:
                    stepsP.setText(" L ");
                    break;
            }
        }
    }

    void Cube3D() {
        Box box = prepareBox();
        box.setTranslateX(WEIGHT / 2);
        box.setTranslateY(HEIGHT / 2);
        SmartGroup group = new SmartGroup();
        group.getChildren().add(box);
        double angle = 0 * 90;
        double x = Math.cos(Math.toRadians(angle)) - 12;
        double z = Math.sin(Math.toRadians(angle));
        double rotationY = angle - 90;

        Face0.setTranslateX(x);
        Face0.setTranslateY(60);
        Face0.setTranslateZ(z);
        Face0.setRotationAxis(Rotate.X_AXIS);
        Face0.setRotate(rotationY);

        group.getChildren().add(Face0);

        //----------------------------------------------------------------------------------------------
        angle = 1 * 90;
        x = Math.cos(Math.toRadians(angle)) + 65;
        z = Math.sin(Math.toRadians(angle)) - 2;
        rotationY = angle - 360;

        Face1.setTranslateX(x);
        Face1.setTranslateY(-14);
        Face1.setTranslateZ(z);
        Face1.setRotationAxis(Rotate.Y_AXIS);
        Face1.setRotate(rotationY);

        group.getChildren().add(Face1);

        //----------------------------------------------------------------------------------------------
        angle = 2 * 90;
        x = Math.cos(Math.toRadians(angle)) - 10;
        z = Math.sin(Math.toRadians(angle)) - 82;
        rotationY = angle - 180;

        Face2.setTranslateX(x);
        Face2.setTranslateY(-14);
        Face2.setTranslateZ(z);
        Face2.setRotationAxis(Rotate.Y_AXIS);
        Face2.setRotate(rotationY);

        group.getChildren().add(Face2);

        //----------------------------------------------------------------------------------------------
        angle = 3 * 90;
        x = Math.cos(Math.toRadians(angle)) - 84;
        z = Math.sin(Math.toRadians(angle)) + 2;
        rotationY = angle - 360;

        Face3.setTranslateX(x);
        Face3.setTranslateY(-14);
        Face3.setTranslateZ(z);
        Face3.setRotationAxis(Rotate.Y_AXIS);
        Face3.setRotate(rotationY);

        group.getChildren().add(Face3);

        //----------------------------------------------------------------------------------------------
        angle = 2 * 90;
        x = Math.cos(Math.toRadians(angle)) - 116;
        z = Math.sin(Math.toRadians(angle)) + 81;
        rotationY = angle - 360;

        Face4.setTranslateX(x);
        Face4.setTranslateY(80);
        Face4.setTranslateZ(z);
        Face4.setRotationAxis(Rotate.Y_AXIS);
        Face4.setRotate(rotationY);

        group.getChildren().add(Face4);

        //----------------------------------------------------------------------------------------------
        angle = 0 * 90;
        x = Math.cos(Math.toRadians(angle)) - 12;
        z = Math.sin(Math.toRadians(angle));
        rotationY = angle - 270;

        Face5.setTranslateX(x);
        Face5.setTranslateY(-90);
        Face5.setTranslateZ(z);
        Face5.setRotationAxis(Rotate.X_AXIS);
        Face5.setRotate(rotationY);

        group.getChildren().add(Face5);

        Camera camera = new PerspectiveCamera();
        SubScene subScene = new SubScene(group, WEIGHT, HEIGHT, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        AnchoPancho.getChildren().add(subScene);
        initMouseControl(group, subScene.getScene());
    }

    private Box prepareBox() {
        Box box = new Box(156, 156, 156);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.BLACK);
        box.setMaterial(material);
        return box;
    }

    private void initMouseControl(SmartGroup group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;

        double centerX = WEIGHT / 2;
        double centerY = HEIGHT / 2;
        double centerZ = 0;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, centerX, centerY, centerZ, Rotate.X_AXIS),
                yRotate = new Rotate(0, centerX, centerY, centerZ, Rotate.Y_AXIS)
        );

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        AnchoPancho.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        AnchoPancho.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        FlowController.getInstance().Flow().addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta);
        });
    }

    @FXML
    private void help(MouseEvent event) {
        
      isHelpVisible = !isHelpVisible; 
      imgHelp.setVisible(isHelpVisible);
     
    }

    @FXML
    private void saveGame(MouseEvent event) {
        TextCSV guardar = new TextCSV() ; 
        
        guardar.saveList( FlowController.getInstance().numeros);
        guardar.saveStack(FlowController.getInstance().pila1);
        guardar.saveCube(Cube);
        
    }

    class SmartGroup extends Group {

        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }

}
