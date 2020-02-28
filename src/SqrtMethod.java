public class SqrtMethod {

    private double[][] matrA;
    private double[] vectorB;
    private double[][] matrS;
    private double[] vectorX;
    private double[] vectorY;
    private double[] discrepancy;
    private double det;
    private int n;


    public SqrtMethod(double matrA[][], double vectorB[]){
        n = matrA.length;
        this.matrA = new double[n][n];
        this.matrS = new double[n][n];
        this.vectorB = new double[n];
        for(int i = 0; i < n; i++){
            this.vectorB[i] = vectorB[i];
            for(int j = 0; j < n; j++){
                this.matrA[i][j] = matrA[i][j];
            }
        }
    }

    public void createSymmetricMatr(){
        double symmetricMatr[][] = new double[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                symmetricMatr[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    symmetricMatr[i][j] += matrA[k][i] * matrA[k][j];
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrA[i][j] = symmetricMatr[i][j];
            }
        }
    }

    public void createNewVectorB(double matrA[][]){
        double newVector[] = new double[n];
        for(int i=0; i<n; i++)
        {
            newVector[i]=0;
            for(int j=0; j<n; j++)
            {
                newVector[i] += matrA[j][i]*vectorB[j];
            }
        }
        for(int i = 0; i < n; i++){
            vectorB[i] = newVector[i];
        }

    }


    public void createMatrixS(){
        double sum;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                sum = 0;
                if(i == j){
                    for(int k = 0; k < i; k++) {
                        sum += matrS[k][i]*matrS[k][i];
                    }
                    matrS[i][i] = Math.sqrt(matrA[i][i] - sum);
                }
                else{
                    for(int k = 0; k < i; k++) {
                        sum += matrS[k][i]*matrS[k][j];
                    }
                    matrS[i][j] = (matrA[i][j] - sum)/matrS[i][i];
                }
            }
        }
    }

    public void createVectorY(){
        vectorY = new double[n];
        double sum;
        for(int i = 0; i < n; i++){
            sum = 0;
            for(int k = 0; k  < i; k++){
                sum += matrS[k][i] * vectorY[k];
            }
            vectorY[i] = (vectorB[i] - sum) / matrS[i][i];
        }
    }

    public void createVectorX(){
        vectorX = new double[n];
        double sum;
        for(int i = n-1; i >= 0; i--){
            sum = 0;
            for(int k = i+1; k < n; k++){
                sum += matrS[i][k] * vectorX[k];
            }
            vectorX[i] = (vectorY[i] - sum) / matrS[i][i];
        }
    }

    public void createDiscrepancy(double matrA[][], double vectorB[]){
        discrepancy = new double[n];
        double[] res = new double[n];
        for(int i = 0; i < n; i++){
            res[i] = 0;
            for(int j = 0; j < n; j++){
                res[i] += matrA[i][j]*vectorX[j];
            }
            discrepancy[i] = vectorB[i] - res[i];
        }
    }

    public void createDet(){
        det = 1;
        for(int i = 0; i < n; i++){
            det *= matrS[i][i] * matrS[i][i];
        }
    }

    public void printDet(){
        System.out.println("Det = " + det);
    }

    public void print(){
        System.out.println("Symmetrick matrix with vector B");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.format("%25s", matrA[i][j] + "    ");
            }
            System.out.format("%25s", vectorB[i] + "\n");
        }
        System.out.println();
    }

    public void printMatrS(){
        System.out.println("Matrix S");
        for(double row[]: matrS){
            for(double item: row){
                System.out.format("%25s", item + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printVectorX(){
        System.out.println("vector X");
        for(double item: vectorX){
            System.out.format("%25s", item + "    ");
        }
        System.out.println("\n");
    }

    public void printDiscrepancy(){
        System.out.println("Discrepancy");
        for(double item: discrepancy){
            System.out.format("%25s", item + "    ");
        }
        System.out.println();
    }

}

