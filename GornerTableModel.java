package methods_3;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;

    public Double getObjectop(int i,int j) {

        return Objectop[i][j];



    }
    private Double from;
    private Double to;
    private Double step;
    public Double Objectop[][]=new Double[2000][4];
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
        double fr= from;
        double t= to;
        double st= step;
        double co= coefficients[0];
        double coe= coefficients[1];
        int[]y=new int[3];
        y[0]=(int)fr;
        y[1]= (int)t;
        y[2]= (int)st;
        int a= (int)co;
        int b= (int)coe;
        System.out.println(Math.round(a)+""+Math.round(b));
        Lead A=new Lead(y,Math.round(a)+1,Math.round(b),Objectop );
        for(int i=0;i<2000;i++)
            for(int j=0;j<4;j++)
                Objectop[i][j]=A.getObjectop(i,j);
    }
    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return 2000;
    }


    public Object getValueAt(int row, int col) {

        switch(col){
            case 0:
                return Objectop[row][col] ;
            case 1:
                return Objectop[row][col];
            case 2:
                return Objectop[row][col];
            case 3:
                return Objectop[row][col];
            default:
                break;
        }
        return null;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение T";
            case 1:
                return "U1";
            case 2:
                return "U2";
            case 3:
                return "U3";
            default:
                return "error";
        }
    }

    public Class<?> getColumnClass(int col) {
        return Double.class;
    }
}
