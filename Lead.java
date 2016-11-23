package methods_3;

/**
 * Created by PH on 03.11.2016.
 */
public class Lead extends Main {
    double Eps = 1e-5,   TauMin = 0.01;
    double Tau = 0;
    double  T = 1, TauMax = 0.5;
    double tk = 0,Tau1=TauMin;
    Double[][]Objectop=new Double[2000][4];


    public Double getObjectop(int i,int j) {

        return Objectop[i][j];



    }

    double ek[];
    double yk[] ;
    double yk_1[] ;
    double yk1[] ;
    double   Tau_1 = TauMin ;
    double  tk1 = 0;
    Lead(int[] y, int choose, int chooseFunction, Double[][] objectop){


        switch (choose) {
            case 1:
                if(choose==2)
                {choose=2;}
                if(choose==1)
                {choose=3;}
                if(choose==3)
                {choose=2;}
                Eiler_yavn A = new Eiler_yavn(y, choose, chooseFunction,objectop);
                for(int i=0;i<2000;i++)
                    for(int j=0;j<4;j++)
                    {Objectop[i][j]=A.getObjectop(i,j);
                }
                System.out.println("rg");
                break;
            case 2:
                if(choose==2)
                {choose=2;}
                if(choose==1)
                {choose=3;}
                if(choose==3)
                {choose=2;}
                Eiler_neyavn B  = new Eiler_neyavn(y, choose, chooseFunction,objectop);
                for(int i=0;i<2000;i++)
                    for(int j=0;j<4;j++)
                        Objectop[i][j]=B.getObjectop(i,j);
                System.out.println("rg");
                break;
            case 3:
                if(choose==2)
                {choose=2;}
                if(choose==1)
                {choose=3;}
                if(choose==3)
                {choose=2;}
                Shihman C = new Shihman(y, choose, chooseFunction,objectop);
                for(int i=0;i<2000;i++)
                    for(int j=0;j<4;j++)
                        Objectop[i][j]=C.getObjectop(i,j);
                System.out.println("rg");
        }

    }



    public double[] getEk() {
        return ek;
    }

    public void setEk(double[] ek,int choose) {
        ek = new double[choose];
        this.ek = ek;
    }

    public double[] getYk() {
        return yk;
    }

    public void setYk(double[] yk,int choose) {
        ek = new double[choose];
        this.yk = yk;
    }

    public double[] getYk_1() {
        return yk_1;
    }

    public void setYk_1(double[] yk_1,int choose) {
        ek = new double[choose];
        this.yk_1 = yk_1;
    }

    public double[] getYk1() {
        return yk1;
    }

    public void setYk1(double[] yk1,int choose) {
        ek = new double[choose];
        this.yk1 = yk1;
    }
}
