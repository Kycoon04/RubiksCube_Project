package cr.ac.una.proyecto.model;

public class Colors {
    
    private String Color_1;
    private String Color_2;
    private String Color_3;

    public Colors(String Color_1) {
        this.Color_1 = Color_1;
        this.Color_2 = "na";
        this.Color_3 = "na";
    }

    public Colors(String Color_1, String Color_2) {
        this.Color_1 = Color_1;
        this.Color_2 = Color_2;
        this.Color_3 = "na";
    }

    public Colors(String Color_1, String Color_2, String Color_3) {
        this.Color_1 = Color_1;
        this.Color_2 = Color_2;
        this.Color_3 = Color_3;
    }
    public String getColor_1() {
        return Color_1;
    }
    public void setColor_1(String Color_1) {
        this.Color_1 = Color_1;
    }
    public String getColor_2() {
        return Color_2;
    }
    public void setColor_2(String Color_2) {
        this.Color_2 = Color_2;
    }
    public String getColor_3() {
        return Color_3;
    }
    public void setColor_3(String Color_3) {
        this.Color_3 = Color_3;
    }
}
