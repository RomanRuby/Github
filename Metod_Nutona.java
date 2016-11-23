package methods_3;

/**
 * Created by PH on 31.10.2016.
 */
public class Metod_Nutona extends function_1 {

    double[] x;
    double[] yk; double Tau; int n; double []yk_1;
    public double getX(int i) {
        return x[i];
    }

    public double getYk(int i) {
        return yk[i];
    }



    public void setX(double[] x, int n) {

        this.x = new double[n];
        for (int i = 0; i<n; i++) {

        this.x[i]=x[i];}
    }

    public void setYk(double[] yk,int n) {

        this.yk = new double[n];
        for (int i = 0; i<n; i++) {
        this.yk[i]=yk[i];}
    }



    Metod_Nutona(double[] x, double[] yk, double Tau, int n, int chooseFunction) {


        double[] Fn = new double[n];
        double[][] Jf = new double[n][n + 1];

        double[] dx = new double[n];
        double d1, d2;
        double e1 = 1e-5;
        double e2 = 1e-5;
        double eps = 1e-5;

        int iter = 1;
        do {
            for (int i = 0; i < n; i++)
                Jf[i][n] = -FN(x, yk, Tau, i, chooseFunction);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double f1;
                    x[j] += eps;

                    f1 = FN(x, yk, Tau, i, chooseFunction);


                    x[j] -= eps;

                    Jf[i][j] = (f1 + Jf[i][n]) / eps;
                }
            }

            //for(int l = 0; l < n; l++,System.out.println())
            //for(int k = 0; k < n+1; k++)
            //  System.out.print(Jf[l][k] + "      ");

            double[][] buf = new double[n][n + 1];

            for (int l = 0; l < n; l++)
                for (int k = 0; k < n + 1; k++)
                    buf[l][k] = Jf[l][k];

            Metod_Gaussa S = new Metod_Gaussa(buf, n);


            for (int t = 0; t < n; t++) {
                dx[t] = S.getDx(t);
                // System.out.println(dx[t]);
            }


            for (int i = 0; i < n; i++)
                x[i] += dx[i];


            d1 = Math.abs(Jf[0][n]);
            for (int i = 1; i < n; i++)
                if (Math.abs(Jf[i][n]) > d1)
                    d1 = Math.abs(Jf[i][n]);

            //Norm(xk+1 - xk)
            d2 = Math.abs(dx[0]);
            for (int i = 1; i < n; i++)
                if (Math.abs(dx[i]) > d2) d2 = Math.abs(dx[i]);

            //Norm(xk+1)
            // double mx = Math.abs(x[0]); double mn = Math.abs(x[0]);
            //for(int i = 1; i < n; i++) {
            //  double buf = Math.abs(x[i]);
            //if (buf > mx) mx = buf;
            //if (mn > buf) mn = buf;
            // }

            //if(mx >= 1) d2 = d2/mn;

            iter++;
        }
        while ((d1 > e1) || (d2 > e2));

        setX(x, n);

    }





    Metod_Nutona(double[] x, double[] yk, double []yk_1, double a0, double a1, double b0 , int n, int chooseFunction) {
        double[] Fn = new double[n];


        double[][] Jf = new double[n][n + 1];

        double[] dx = new double[n];
        double d1, d2;
        double e1 = 1e-5;
        double e2 = 1e-5;
        double eps = 1e-5;

        int iter = 1;
        do {
            for (int i = 0; i < n; i++) {
                Jf[i][n] = -FN(x, yk, yk_1, a0, a1, b0,i, chooseFunction);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double f1;
                    x[j] += eps;

                    f1 = FN(x, yk, yk_1, a0, a1, b0, i, chooseFunction);

                    x[j] -= eps;


                    Jf[i][j] = (f1 + Jf[i][n]) / eps;
                }
            }

            double[][] buf = new double[n][n + 1];

            for (int l = 0; l < n; l++)
                for (int k = 0; k < n + 1; k++)
                    buf[l][k] = Jf[l][k];

            Metod_Gaussa S = new Metod_Gaussa(buf, n);


            for (int t = 0; t < n; t++) {
                dx[t] = S.getDx(t);
                // System.out.println(dx[t]);
            }


            for (int i = 0; i < n; i++)
                x[i] += dx[i];


            d1 = Math.abs(Jf[0][n]);
            for (int i = 1; i < n; i++)
                if (Math.abs(Jf[i][n]) > d1)
                    d1 = Math.abs(Jf[i][n]);

            //Norm(xk+1 - xk)
            d2 = Math.abs(dx[0]);
            for (int i = 1; i < n; i++)
                if (Math.abs(dx[i]) > d2) d2 = Math.abs(dx[i]);

            //Norm(xk+1)
            // double mx = Math.abs(x[0]); double mn = Math.abs(x[0]);
            //for(int i = 1; i < n; i++) {
            //  double buf = Math.abs(x[i]);
            //if (buf > mx) mx = buf;
            //if (mn > buf) mn = buf;
            // }

            //if(mx >= 1) d2 = d2/mn;

            iter++;
        }
        while ((d1 > e1) || (d2 > e2));


    }


}
        
        