package methods_3;

/**
 * Created by PH on 31.10.2016.
 */
public class Eiler_neyavn {

    double Eps = 1e-3, TauMin = 0.01;
    double Tau = TauMin;
    double T = 1, TauMax = 0.1;
    double tk = 0;


    double ek[];
    double yk[];
    double yk_1[];
    double yk1[];
    double Tau_1 = TauMin;

    public void setYk(int n) {

        this.yk = new double[n];
    }

    public void setYk_1(int n) {

        this.yk_1 = new double[n];
    }

    public void setYk1(int n) {

        this.yk1 = new double[n];
    }

    public void setEk(int n) {
        this.ek = new double[n];

    }

    Double[][] Objectop = new Double[2000][4];
    int p = 0;

    public Double getObjectop(int i, int j) {

        return Objectop[i][j];

    }

    public void setObjectop(int p, int h, double r) {

        Objectop[p][h] = r;

    }

    Eiler_neyavn(int[] u, int n, int chooseFunction, Double[][] Objectop) {

        n = 3;
        setYk(n);
        setYk1(n);
        setYk_1(n);
        setEk(n);

        for (int i = 0; i < n; i++) {
            yk[i] = u[i];
            yk_1[i] = u[i];
            yk1[i] = u[i];
        }




        int shag = 0;

        do {


            tk = tk + Tau;

            Metod_Nutona A = new Metod_Nutona(yk1, yk, Tau, n, chooseFunction);

            boolean flag = false;

            for (int k = 0; k < n; k++) {

                ek[k] = Math.abs((-Tau / (Tau + Tau_1)) * (yk1[k] - yk[k] - (Tau / Tau_1) * (yk[k] - yk_1[k])));

                if (ek[k] > Eps) {
                    tk = tk - Tau;
                    Tau /= 2;
                    for (int i = 0; i < n; i++) yk1[i] = yk[i];
                    flag = true;
                    break;
                }

            }
            if (flag) {
                shag++;
                continue;
            }


            double tmp = ek[0];
            for (int k = 1; k < n; k++) {
                if (ek[k] > tmp) tmp = ek[k];
            }


            if ((Math.abs(tmp) <= Eps / 4))
                Tau = 2 * Tau;

            if (Tau > TauMax)
                Tau = TauMax;

            //for (int i = 0; i < n; i++) {
            //  if (i == 0)
            //   System.out.println("t=" + tk);
            // System.out.println("u[" + i + 1 + "]=" + yk1[i]);


            //}

            setObjectop(p, 0, tk);
            setObjectop(p, 1, yk1[0]);
            setObjectop(p, 2, yk1[1]);
            setObjectop(p, 3, yk1[2]);
            p++;


            for (int k = 0; k < n; k++) {
                yk_1[k] = yk[k];
                yk[k] = yk1[k];
            }

            Tau_1 = Tau;

            shag++;

        } while (tk < T);

        System.out.println("Iterations	quantity is " + shag);
        setObjectop(p, 0, shag);
        setObjectop(p, 1, 0);
        setObjectop(p, 2, 0);
        setObjectop(p, 3, 0);
    }

    public void Show(double[] T, int n) {
        for (int i = 0; i < n; i++)
            System.out.println(T[i]);
        System.out.println();
    }

}

