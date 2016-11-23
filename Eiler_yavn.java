package methods_3;

/**
 * Created by PH on 31.10.2016.
 */
public class Eiler_yavn {
    double Eps = 1e-3;
    double Tau = 0;
    double  T = 1, TauMax = 0.1;
    double tk = 0;
    int a =0;
    Double[][]Objectop=new Double[2000][4];
    int p=0;

    public Double getObjectop(int i,int j) {

        return Objectop[i][j];
    }

    public void setObjectop(int p,int h,double r) {


        Objectop[p][h] = r;
    }


     public Eiler_yavn(int[] u, int N, int chooseFunction, Double[][] Objectop) {

         N = u.length;

         double yk[] = new double[N];


         for (int i = 0; i < N; i++)
             yk[i] = u[i];

         do {

             for (int i = 0; i < N; i++) {
                 if (i == 0)
                     System.out.println("t=" + tk);
                 System.out.println("u[" + i + 1 + "]=" + yk[i]);
                 if (i == N - 1)
                     System.out.println("\n");

             }

             setObjectop(p, 0, tk);
             setObjectop(p, 1, yk[0]);
             setObjectop(p, 2, yk[1]);
             setObjectop(p, 3, yk[2]);
             p++;


             double Normf = Math.abs(new function_1().function_1(yk, 0, chooseFunction));

             for (int t = 1; t < N; t++) {
                 double buf = Math.abs(new function_1().function_1(yk, t, chooseFunction));
                 if (buf > Normf) Normf = buf;
             }

             Tau = Eps/(Normf + Eps/TauMax);

             for (int i = 0; i < N; i++)
                 yk[i] = yk[i] + Tau * new function_1().function_1(yk, i, chooseFunction);

             tk += Tau;

             a++;
         } while (tk < T);

         System.out.println("Iterations	quantity is " + a);

         setObjectop(p, 0, a);
         setObjectop(p, 1, 0);
         setObjectop(p, 2, 0);
         setObjectop(p, 3, 0);

     }




}
