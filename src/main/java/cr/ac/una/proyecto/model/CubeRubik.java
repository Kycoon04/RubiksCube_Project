package cr.ac.una.proyecto.model;

public class CubeRubik {

    String Color_1;
    String Color_2;
    String Color_3;
    String identifier;
    Boolean primerColor = false;
    Boolean segundoColor=false;

    public CubeRubik(String identifier) {
        this.identifier = identifier;
        this.Color_1 = "na";
        this.Color_2 = "na";
        this.Color_3 = "na";
    }

    public CubeRubik(String color_1, String identifierP) {
        Color_1 = color_1;
        identifier = identifierP;
    }
    public CubeRubik(String Color_1, String Color_2, String identifierP) {
        this.Color_1 = Color_1;
        this.Color_2 = Color_2;
        identifier = identifierP;
    }
    public CubeRubik(String Color_1, String Color_2, String Color_3, String identifierP) {
        this.Color_1 = Color_1;
        this.Color_2 = Color_2;
        this.Color_3 = Color_3;
        identifier = identifierP;
    }
    public void swap1Color2(){
        String aux = Color_2;
        Color_2 = Color_1;
        Color_1 = aux;
    }
    public void swap2Color3(){
        String aux = Color_2;
        Color_2 = Color_3;
        Color_3 = aux;
    }
    public void swap1Color3(){
        String aux = Color_1;
        Color_1 = Color_3;
        Color_3 = aux;
    }
    public void swapThreeColor(){
        String aux = Color_3;
        Color_3 = Color_1;
        Color_1 =Color_2;
        Color_2 = aux;
    }
    public void swapF(){
        swap2Color3();
        swap1Color2();
    }
    public void setPrimerColor(Boolean primerColor) {
        this.primerColor = primerColor;
    }

    public void setSegundoColor(Boolean segundoColor) {
        this.segundoColor = segundoColor;
    }

    public Boolean getPrimerColor() {
        return primerColor;
    }

    public Boolean getSegundoColor() {
        return segundoColor;
    }

    public String getColor_2() {
        return Color_2;
    }

    public String getColor_3() {
        return Color_3;
    }
    
    public String getColor_1() {
        return Color_1;
    }
    public String getIdentifier() {
        return identifier;
    }

    public void setColor_1(String Color_1) {
        this.Color_1 = Color_1;
    }

    public void setColor_2(String Color_2) {
        this.Color_2 = Color_2;
    }

    public void setColor_3(String Color_3) {
        this.Color_3 = Color_3;
    }
    
}
