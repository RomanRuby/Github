package methods_3;

/**
 * Created by PH on 31.10.2016.
 */
public  class Metod_Gaussa {

    double[][] Jac; int n; double[] dx;

    public void setJac(double[][] jac,int n) {
        Jac = new double[n][n+1];
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n+1; j++)
                this.Jac[i][j] = jac[i][j];
        }

    }

    public double getJac(int i,int j) {

              return  Jac[i][j];

    }



    public double getDx(int i) {
       return  dx[i];
    }



    public void setDx(double[] dx,int n) {
        this.dx = new double[n];
        for (int i = 0; i<n; i++) {
        this.dx[i] = dx[i];}
    }

    Metod_Gaussa(double[][] Jac, int n) {

        double[] dx = new double[n];

        for (int i = 0; i < n; i++)
        {
            double max = Math.abs(Jac[i][i]);
            int term = i;
            for (int t = i; t < n; t++)
                if (Math.abs(Jac[t][i]) > max) {
                    max = Math.abs(Jac[t][i]);
                    term = t;
                }

            if (term!= i)
            {
                double buffer[]=new double [n+1];
                for(int r=0;r<n+1;r++) {
                    {
                        buffer[r] = Jac[i][r];
                        Jac[i][r] = Jac[term][r];
                    }
                }
                for (int u = 0; u < n+1 ; u++) {
                    Jac[term][u] = buffer[u];
                }

            }

            double amain = Jac[i][i];
            for (int z = i; z < n + 1; z++) {
                Jac[i][z] = Jac[i][z] / amain;
            }


            for (int j = i + 1; j < n; j++) {
                double b = Jac[j][i];
                for (int z = i; z < n + 1; z++)
                    Jac[j][z] -= Jac[i][z] * b;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            dx[i] = Jac[i][n];
            for (int j = i + 1; j < n; j++) {
                dx[i] -= Jac[i][j] * dx[j];
            }
        }



        setDx(dx,n);
    }
}