package methods_3;

/**
 * Created by PH on 31.10.2016.
 */
public class Shihman {

    double Tau, Tau_1, Tau_2;
    double Eps = 1e-3, T = 1, TauMax = 0.1, TauMin = 0.01;
    double tk = 0;

    Double[][] Objectop = new Double[2000][4];
    int p = 0;

    public Double getObjectop(int i, int j) {

        return Objectop[i][j];


    }

    public void setObjectop(int p, int h, double r) {


        Objectop[p][h] = r;


    }

    public Shihman(int[] u, int n, int chooseFunction, Double[][] objectop) {
        n = 3;
        double[] ek = new double[n];
        double[] yk_2 = new double[n];
        double[] yk_1 = new double[n];
        double[] yk = new double[n];
        double[] yk1 = new double[n];

        Tau = Tau_1 = Tau_2 = TauMin;
        for (int i = 0; i < n; i++)
            yk1[i] = yk[i] = yk_1[i] = yk_2[i] = u[i];




        double a0, a1, b0;
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

            if (flag)
                continue;


            double tmp = ek[0];
            for (int k = 1; k < n; k++) {
                if (ek[k] > tmp) tmp = ek[k];
            }

            if (Math.abs(tmp) <= Eps / 4)
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

            if (shag != 1) Tau_1 = Tau;

            shag++;

        } while (shag <= 1);

        shag = 0;
        double Rk, kpp, kp, kc, kn;

        do {

            a0 = ((Tau + Tau_1) * (Tau + Tau_1)) / (Tau_1 * (2 * Tau + Tau_1));
            a1 = -(Tau * Tau) / (Tau_1 * (2 * Tau + Tau_1));
            b0 = (Tau * (Tau + Tau_1)) / (2 * Tau + Tau_1);

            tk = tk + Tau;

            Metod_Nutona R = new Metod_Nutona(yk1, yk_1, yk, a0, a1, b0, n, chooseFunction);

            Rk = (Tau * Tau * (Tau + Tau_1) * (Tau + Tau_1)) / (2 * Tau + Tau_1);
            kn = Tau * (Tau + Tau_1) * (Tau + Tau_1 + Tau_2);
            kc = Tau * Tau_1 * (Tau_1 + Tau_2);
            kp = Tau_1 * Tau_2 * (Tau + Tau_1);
            kpp = Tau_2 * (Tau_1 + Tau_2) * (Tau + Tau_1 + Tau_2);

            for (int k = 0; k < n; k++)     // Вычисляем  ek по формуле
                ek[k] = Math.abs((Rk) * (yk1[k] / kn - yk[k] / kc + yk_1[k] / kp - yk_2[k] / kpp));

            boolean flag = false;
            for (int k = 0; k < n; k++)
                if (ek[k] > Eps) {
                    tk = tk - Tau;
                    Tau /= 2;
                    for (int i = 0; i < n; i++) yk1[i] = yk[i];
                    flag = true;
                    break;
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

            for (int i = 0; i < n; i++) {
                if (i == 0)
                    System.out.println("t=" + tk);
                System.out.println("u[" + i + 1 + "]=" + yk1[i]);


            }
            setObjectop(p, 0, tk);
            setObjectop(p, 1, yk1[0]);
            setObjectop(p, 2, yk1[1]);
            setObjectop(p, 3, yk1[2]);
            p++;


            for (int s = 0; s < n; s++) {
                yk_2[s] = yk_1[s];
                yk_1[s] = yk[s];
                yk[s] = yk1[s];
            }
            Tau_2 = Tau_1;
            Tau_1 = Tau;

            shag++;


        } while (tk < T);

        System.out.println("Iterations quantity is " +p);
        setObjectop(p, 0, p);
        setObjectop(p, 1, 0);
        setObjectop(p, 2, 0);
        setObjectop(p, 3, 0);

    }
}
