package methods_3;

/**
 * Created by PH on 31.10.2016.
 */
public class function_1 {
    double koef_a=1; double koef_k=2;


    double  function_1(double u[], int n,int chooseFunction) {
        if (chooseFunction == 1) {
            if (n == 0)
                return (koef_k - koef_a) / koef_a * u[1] * u[2];
            else if (n == 1)
                return (koef_a + koef_k) / koef_k * u[0] * u[2];
            else if (n == 2)
                return (koef_a - koef_k) / koef_a * u[0] * u[1];
            else
                return 0;
        }
        if (chooseFunction == 2) {
            if (n == 0)
                return ((Math.sin(koef_a) / koef_a) + (-u[0] * u[1]));
            else if (n == 1)
                return ((koef_a * koef_k) / 1 + (koef_a * koef_a) + (-u[1] * u[1]));

            else
                return 0;

        }

        return 0;
    }


    double FN(double[] uk1, double[] uk, double Tau,int n,int chooseFunction) {

        if (chooseFunction == 1) {
            if (n == 0)
                return uk1[0] - uk[0] - Tau * ((koef_k - koef_a) / koef_a) * uk1[1] * uk1[2];
            if (n == 1)
                return uk1[1] - uk[1] - Tau * ((koef_k + koef_a) / koef_k) * uk1[2] * uk1[0];

            return uk1[2] - uk[2] - Tau * ((koef_a - koef_k) / koef_a) * uk1[0] * uk1[1];

        }

        if (chooseFunction == 2) {
            if (n == 0)
                return uk1[0] - uk[0] - Tau * (Math.sin(koef_a) / koef_a) + (-uk1[0] * uk1[1]);
            else if (n == 1)
                return uk1[1] - uk[1] - Tau * ((koef_a * koef_k) / 1 + (koef_a * koef_a) + (-uk1[1] * uk1[1]));
            else
                return 0;
        }
        return 0;

    }


    double FN(double[] uk1, double[] uk_1, double[] uk, double a0, double a1, double b0,int n,int chooseFunction) {
        if (n == 0)
            return uk1[0] - a1 * uk_1[0] - a0 * uk[0] - b0 * (((koef_k - koef_a) / koef_a) * uk1[1] * uk1[2]);
        if (n == 1)
            return uk1[1] - a1 * uk_1[1] - a0 * uk[1] - b0 * (((koef_a + koef_k) / koef_k) * uk1[0] * uk1[2]);

            return uk1[2] - a1 * uk_1[2] - a0 * uk[2] - b0 * (((koef_a - koef_k) / koef_a) * uk1[0] * uk1[1]);

    }
}


