/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.proyecto.model;

/**
 *
 * @author ander
 */
public class User {
    private String name;
    private int movements;
    private String time;
    private int score;

    public User(){
        
    }
    public User(String name, int movements, String time, int score) {
        this.name = name;
        this.movements = movements;
        this.time = time;
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovements(int movements) {
        this.movements = movements;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getMovements() {
        return movements;
    }

    public String getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }
    
    
    
    
    
}
