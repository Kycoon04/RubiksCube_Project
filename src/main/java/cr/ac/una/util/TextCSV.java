/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.util;

import cr.ac.una.proyecto.model.CubeRubik;
import cr.ac.una.proyecto.model.User;
import static cr.ac.una.util.FlowController.Players;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Dilan
 */
public class TextCSV {

    CubeRubik[][][] cubeArray = new CubeRubik[3][3][3];
    Stack<Integer> Stackdata = new Stack<>();
    ArrayList<Integer> datalist = new ArrayList<>();
    ArrayList<User> dataUser = new ArrayList<>();

    public String absolutePath(String filename) {

        String currentDirectory = System.getProperty("user.dir");
        String fullPath = currentDirectory + File.separator + filename;
        return fullPath;
    }

    public void saveList(List<Integer> data) {

        String ruta = absolutePath("lista.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Integer item : data) {
                writer.write(item.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void saveStack(Stack<Integer> data) {

        String ruta = absolutePath("pila.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Integer item : data) {
                writer.write(item.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadList() {
        String ruta = absolutePath("lista.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int intValue = Integer.parseInt(line);
                    datalist.add(intValue);
                } catch (NumberFormatException e) {

                    System.err.println("Error al convertir línea a entero: " + line);
                    e.printStackTrace();
                }
            }
            System.out.println("Lista de enteros cargada exitosamente.");
            System.out.println(datalist.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadStack() {
        String ruta = absolutePath("pila.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int intValue = Integer.parseInt(line);
                    Stackdata.add(intValue);
                } catch (NumberFormatException e) {

                    System.err.println("Error al convertir línea a entero: " + line);
                    e.printStackTrace();
                }
            }
            System.out.println("Pïlas de enteros cargada exitosamente.");
            System.out.println(Stackdata.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveCube(CubeRubik[][][] cubeArray) {
        String ruta = absolutePath("colors.txt");
        try (FileWriter writer = new FileWriter(ruta)) {
            for (int i = 0; i < cubeArray.length; i++) {
                for (int j = 0; j < cubeArray[i].length; j++) {
                    for (int k = 0; k < cubeArray[i][j].length; k++) {
                        CubeRubik cube = cubeArray[i][j][k];
                        if (cube != null) {
                            String identifier = cube.getIdentifier();
                            String color1 = cube.getColor_1();
                            String color2 = cube.getColor_2();
                            String color3 = cube.getColor_3();

                            String line = i + "," + j + "," + k + "," + identifier + "," + color1 + "," + color2 + "," + color3;
                            writer.write(line + "\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCube() {
        String ruta = absolutePath("colors.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int i = Integer.parseInt(parts[0]);
                int j = Integer.parseInt(parts[1]);
                int k = Integer.parseInt(parts[2]);
                String identifier = parts[3];
                String color1 = parts[4];
                String color2 = parts[5];
                String color3 = parts[6];

                CubeRubik cube = new CubeRubik(identifier);
                cube.setColor_1(color1);
                cube.setColor_2(color2);
                cube.setColor_3(color3);

                cubeArray[i][j][k] = cube;

                System.out.println("Cube at (" + i + "," + j + "," + k + "): ");
                System.out.println("Identifier: " + identifier);
                System.out.println("Color 1: " + color1);
                System.out.println("Color 2: " + color2);
                System.out.println("Color 3: " + color3);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        rellenarMatrizCube();
    }

    public void rellenarMatrizCube() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    FlowController.getInstance().Cube[i][j][k] = cubeArray[i][j][k];
                }
            }
        }
        FlowController.getInstance().pila1 = Stackdata;
        FlowController.getInstance().numeros = datalist;
    }

    public void saveUser(User player) {
        String ruta = absolutePath("currentPlayer.txt");
        try (FileWriter writer = new FileWriter(ruta)) {
            String line = player.getName();
            line += ";" + player.getMovements();
            line += ";" + player.getTime();
            line += ";" + player.getScore();

            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User loadUser() {
        User currentUser = new User();
        String ruta = absolutePath("currentPlayer.txt");
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                currentUser.setName(parts[0]);
                currentUser.setMovements(Integer.parseInt(parts[1]));
                currentUser.setTime(parts[2]);
                currentUser.setScore(Integer.parseInt(parts[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentUser;
    }

    public void addUser(User player) {
        FlowController.getInstance().Players.add(player);

        for (User user : Players) {
            System.out.println("" + user.getName());
        }

    }

    public void saveScoreUser(ArrayList<User> Userlist) {
        String ruta = absolutePath("Users.txt");
        try (FileWriter writer = new FileWriter(ruta)) {
            for (User user : Userlist) {
                //System.out.println(""+user.getName());
                String line = user.getName();
                line += ";" + user.getMovements();
                line += ";" + user.getTime();
                line += ";" + user.getScore();

                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadScoreUser() {

        FlowController.getInstance().Players.clear();
        User user;
        String ruta = absolutePath("Users.txt");
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                user = new User();
                user.setName(parts[0]);
                user.setMovements(Integer.parseInt(parts[1]));
                user.setTime(parts[2]);
                user.setScore(Integer.parseInt(parts[3]));
                FlowController.getInstance().Players.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sort(ArrayList list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Integer.valueOf(user2.getScore()).compareTo(user1.getScore());
            }
        });
    }

}
