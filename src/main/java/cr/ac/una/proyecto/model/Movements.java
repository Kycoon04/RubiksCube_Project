package cr.ac.una.proyecto.model;

public class Movements {
    private CubeRubik[][][] CubeCopy = new CubeRubik[3][3][3];
    public Movements() {
    }
    private void Copy(CubeRubik[][][] Cube) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    CubeCopy[i][j][k] = Cube[i][j][k];
                }
            }
        }
    }
    private void rotateLayerDandU(CubeRubik[][][] Cube, int layer, boolean clockwise) {
        Copy(Cube);
        if (!clockwise) {
            for (int i = 0; i < 3; i++) {
                Cube[layer][0][i] = CubeCopy[layer][2-i][0];
                Cube[layer][1][i] = CubeCopy[layer][2-i][1];
                Cube[layer][2][i] = CubeCopy[layer][2-i][2];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                Cube[layer][i][0] = CubeCopy[layer][0][2-i];
                Cube[layer][i][1] = CubeCopy[layer][1][2-i];
                Cube[layer][i][2] = CubeCopy[layer][2][2-i];
            }
        }
    }
    public void SpinU(CubeRubik[][][] Cube, boolean Prima ){//Bueno
        if(!Prima) {
            for (int i = 0; i < 3; i++) {
                rotateLayerDandU(Cube, 0,true);
                Cube[0][0][2].swap2Color3();
                Cube[0][0][0].swap2Color3();
            }
        }else{
            for (int i = 0; i < 3; i++) {
                rotateLayerDandU(Cube, 0,false);
                Cube[0][0][2].swap2Color3();
                Cube[0][2][2].swap2Color3();
            }
        }
    }
    public void SpinD(CubeRubik[][][] Cube, boolean Prima){
        Copy(Cube);
        if(!Prima) {
            for (int i = 0; i < 3; i++) {
                rotateLayerDandU(Cube, 2,false);
                Cube[2][0][2].swap1Color2();
                Cube[2][2][2].swap1Color2();
            }
        }else{
            for (int i = 0; i < 3; i++) {
                rotateLayerDandU(Cube, 2,true);
                Cube[2][0][2].swap1Color2();
                Cube[2][0][0].swap1Color2();
            }
        }
    }
    private void rotateLayerRandL(CubeRubik[][][] Cube, int layer, boolean clockwise, int orientation) {
        Copy(Cube);
        if (!clockwise) {
            for (int i = 0; i < 3; i++) {
                Cube[i][layer][2-orientation] = CubeCopy[2-orientation][layer][2-i];
                Cube[i][layer][1] = CubeCopy[1][layer][2-i];
                Cube[i][layer][orientation] = CubeCopy[orientation][layer][2-i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                Cube[i][layer][2-orientation] = CubeCopy[orientation][layer][i];
                Cube[i][layer][1] = CubeCopy[1][layer][i];
                Cube[i][layer][orientation] = CubeCopy[2-orientation][layer][i];
            }
        }
    }
    public void SpinL(CubeRubik[][][] Cube, boolean Prima) {
        Copy(Cube);
        if (!Prima) {
            Cube[0][0][1].swap1Color2();
            Cube[1][0][2].swap1Color2();
            Cube[0][0][0].swap1Color2();
            Cube[0][0][2].swap1Color3();
            Cube[2][0][0].swap2Color3();
            Cube[2][0][2].swap1Color2();
            for (int i = 0; i < 3; i++) {
                rotateLayerRandL(Cube, 0, true, 0);
            }
        } else {
            Cube[1][0][0].swap1Color2();
            Cube[0][0][1].swap1Color2();
            Cube[0][0][0].swap1Color3();
            Cube[0][0][2].swap1Color2();
            Cube[2][0][0].swap1Color2();
            Cube[2][0][2].swap2Color3();
            for (int i = 0; i < 3; i++) {
                rotateLayerRandL(Cube, 0, false, 0);
            }
        }
    }
    public void SpinR(CubeRubik[][][] Cube, boolean Prima){ //Bueno
        Copy(Cube);
        if(!Prima){
            Cube[0][2][1].swap1Color2();
            Cube[2][2][1].swap1Color2();
            Cube[0][2][0].swapThreeColor();
            Cube[2][2][0].swap2Color3();
            Cube[0][2][2].swap1Color2();
            Cube[2][2][2].swapF();
            for (int i = 0; i < 3; i++) {
                rotateLayerRandL(Cube, 2, false, 2);
            }
        }else{
            Cube[1][2][2].swap1Color2();
            Cube[1][2][0].swap1Color2();
            Cube[0][2][0].swap2Color3();
            Cube[2][2][0].swapThreeColor();
            Cube[0][2][2].swapF();
            Cube[2][2][2].swap1Color2();
            for (int i = 0; i < 3; i++) {
                rotateLayerRandL(Cube, 2, true, 0);
            }
        }
    }
    public void SpinF(CubeRubik[][][] Cube, boolean Prima){ //Bueno
        Copy(Cube);
        if(!Prima){
            Cube[2][1][0].swap1Color2();
            Cube[0][1][0].swap1Color2();
            Cube[0][0][0].swapThreeColor();
            Cube[2][0][0].swap2Color3();
            Cube[2][2][0].swapF();
            Cube[0][2][0].swap1Color2();
            for (int i = 0; i < 3; i++) {
                Cube[i][0][0] = CubeCopy[2][i][0];
                Cube[i][1][0] = CubeCopy[1][i][0];
                Cube[i][2][0] = CubeCopy[0][i][0];
            }
        }else{
            Cube[1][0][0].swap1Color2();
            Cube[1][2][0].swap1Color2();
            Cube[0][0][0].swap2Color3();
            Cube[2][0][0].swapThreeColor();
            Cube[2][2][0].swap1Color2();
            Cube[0][2][0].swapF();
            for (int i = 0; i < 3; i++) {
                Cube[i][0][0] = CubeCopy[0][2-i][0];
                Cube[i][1][0] = CubeCopy[1][2-i][0];
                Cube[i][2][0] = CubeCopy[2][2-i][0];
            }
        }
    }
    public void SpinB(CubeRubik[][][] Cube, boolean Prima){
        Copy(Cube);
        if(!Prima){
            Cube[1][0][2].swap1Color2();
            Cube[2][1][2].swap1Color2();
            Cube[0][2][2].swap1Color2();
            Cube[0][0][2].swap2Color3();
            Cube[2][0][2].swap1Color3();
            Cube[2][2][2].swap2Color3();
            for (int i = 0; i < 3; i++) {
                Cube[i][0][2] = CubeCopy[0][2-i][2];
                Cube[i][1][2] = CubeCopy[1][2-i][2];
                Cube[i][2][2] = CubeCopy[2][2-i][2];
            }
        }else{
            Cube[1][2][2].swap1Color2();
            Cube[2][1][2].swap1Color2();
            Cube[0][2][2].swap2Color3();
            Cube[0][0][2].swap1Color2();
            Cube[2][0][2].swap2Color3();
            Cube[2][2][2].swap1Color3();
            for (int i = 0; i < 3; i++) {
                Cube[0][i][2] = CubeCopy[2-i][0][2];
                Cube[1][i][2] = CubeCopy[2-i][1][2];
                Cube[2][i][2] = CubeCopy[2-i][2][2];
            }
        }
    }
}
