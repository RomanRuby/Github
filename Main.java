package methods_3;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 * Created by PH on 31.10.2016.
 */
public class Main extends JFrame {
    static final int WIDTH=700;
    static final int HEIGHT=500;
    private JTextField u1;
    private JTextField u2;
    private JTextField u3;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private Double[]coefficients;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();
    private GornerTableModel data;
    private JButton btnCalc = null;
    private JButton btnClear = null;
    Double[][]Objectop=new Double[2000][4];
int x1=1;
    XYSeries series = new XYSeries(" u1");
    XYSeries series1 = new XYSeries("u2");
    XYSeries series2 = new XYSeries("u3");

    XYSeries series3 = new XYSeries("u1*");
    XYSeries series4 = new XYSeries("u2*");
    XYSeries series5 = new XYSeries("u3*");

    XYSeries series6 = new XYSeries("u1**");
    XYSeries series7 = new XYSeries("u2**");
    XYSeries series8 = new XYSeries("u3**");

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();
    private JFileChooser fileChoose;
    private JMenuItem menuHelpAbout;
    private JMenuItem menuFileToCsv;
    private JMenuItem menuFileGraphics;
    private JMenuItem menuFileGraphics1;
    private JMenuItem menuFileGraphics2;
    private int formulaId = 1;
    private Box hBox = Box.createHorizontalBox();;
    private AboutFrame Aframe=new AboutFrame();

    private void addRadioButton(String buttonName, final int formulaId)
    { JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev)
            {Main.this.formulaId = formulaId; } });
        radioButtons.add(button);

        hboxFormulaType.add(button);


    }

    public Main()
    {
        super("3 Methods");
        setSize(WIDTH,HEIGHT);
        Toolkit kit=Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Файл");
        JMenu menuHelp = new JMenu("Справка");
        JMenu menuGraphics = new JMenu("График");
        fileChoose=new JFileChooser();

        menuFileGraphics=new JMenuItem("Graphics");
        menuFileGraphics.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent v) {


            final XYSeriesCollection dataset = new XYSeriesCollection();





            dataset.addSeries(series);

            dataset.addSeries(series3);

            dataset.addSeries(series6);


            JFreeChart chart = ChartFactory
                    .createXYLineChart("Eiler", "t", "u",
                            dataset,
                            PlotOrientation.VERTICAL,
                            true, true, true);





            JFrame frame1 = new JFrame("MinimalStaticChart1");
            // Помещаем график на фрейм
            frame1.getContentPane()
                    .add(new ChartPanel(chart));
            frame1.setSize(400,300);


            frame1.show();
    }});


        menuFileGraphics1=new JMenuItem("Graphics1");
        menuFileGraphics1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent v) {


                final XYSeriesCollection dataset1 = new XYSeriesCollection();



                dataset1.addSeries(series1);

                dataset1.addSeries(series4);

                dataset1.addSeries(series7);




                JFreeChart chart1 = ChartFactory
                        .createXYLineChart("Eiler2", "t", "u",
                                dataset1,
                                PlotOrientation.VERTICAL,
                                true, true, true);


                JFrame frame2 = new JFrame("MinimalStaticChart2");
                // Помещаем график на фрейм
                frame2.getContentPane()
                        .add(new ChartPanel(chart1));
                frame2.setSize(400,300);
                frame2.show();

            }});


        menuFileGraphics2=new JMenuItem("Graphics2");
        menuFileGraphics2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent v) {


                final XYSeriesCollection dataset2 = new XYSeriesCollection();



                dataset2.addSeries(series2);

                dataset2.addSeries(series5);

                dataset2.addSeries(series8);




                JFreeChart chart1 = ChartFactory
                        .createXYLineChart("Eiler2", "t", "u",
                                dataset2,
                                PlotOrientation.VERTICAL,
                                true, true, true);


                JFrame frame2 = new JFrame("MinimalStaticChart2");
                // Помещаем график на фрейм
                frame2.getContentPane()
                        .add(new ChartPanel(chart1));
                frame2.setSize(400,300);
                frame2.show();

            }});


        menuFileToCsv =new JMenuItem("Сохранить в CSV-файл");
        menuFileToCsv.addActionListener(new ActionListener(){
                                            public void actionPerformed(ActionEvent ev) {
                                                fileChoose.setCurrentDirectory(new File("."));
                                                if(fileChoose.showSaveDialog(Main.this)==JFileChooser.APPROVE_OPTION){
                                                    File file=fileChoose.getSelectedFile();
                                                    try {
                                                        PrintStream out=new PrintStream(file);
                                                        for(int y=0;y<data.getRowCount();y++){
                                                            for(int u=0;u<data.getColumnCount();u++){
                                                                out.print(formatter.format(data.getValueAt(y, u)));
                                                                if(u!=data.getColumnCount()-1)
                                                                    out.print(";");
                                                                else
                                                                    out.print("\n");
                                                            }
                                                        }
                                                    } catch (FileNotFoundException e) {
                                                        System.out.println("Не удалось создать файл");
                                                    }
                                                }
                                            }
                                        }
        );
        menuHelpAbout =new JMenuItem("О программе");
        menuHelpAbout.addActionListener(new ActionListener(){
                                            public void actionPerformed(ActionEvent ev) {
                                                Aframe.setVisible(true);
                                            }
                                        }
        );


        menuFileToCsv.setEnabled(false);


        menuFile.add(menuFileToCsv);
        menuGraphics.add(menuFileGraphics);
        menuGraphics.add(menuFileGraphics1);
        menuGraphics.add(menuFileGraphics2);

        menuHelp.add(menuHelpAbout);
        menuBar.add(menuFile);
        menuBar.add(menuGraphics);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);

        l1= new JLabel("U1:");
        l2 = new JLabel("U2:");
        l3 = new JLabel("U3:");
        u1 =new JTextField("1.0",12);

        u1.setMaximumSize(l1.getPreferredSize());
        u2 =new JTextField("1.0",12);
        u2.setMaximumSize(l2.getPreferredSize());
        u3 =new JTextField("1.0",12);
        u3.setMaximumSize(l3.getPreferredSize());
        Box hBoxUp=Box.createHorizontalBox();

        hBoxUp.add(Box.createHorizontalGlue());
        l1.getHorizontalAlignment();
        hBoxUp.add(l1);
        hBoxUp.add(Box.createHorizontalStrut(10));
        hBoxUp.add(u1);
        hBoxUp.add(Box.createHorizontalStrut(10));
        hBoxUp.add(l2);
        hBoxUp.add(Box.createHorizontalStrut(10));
        hBoxUp.add(u2);
        hBoxUp.add(Box.createHorizontalStrut(10));
        hBoxUp.add(l3);
        hBoxUp.add(Box.createHorizontalStrut(10));
        hBoxUp.add(u3);
        hBoxUp.add(Box.createHorizontalGlue());


        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        addRadioButton("Формула 3", 3);
        radioButtons.setSelected( radioButtons.getElements().nextElement().getModel(), true);

        Box hBoxUp1=Box.createHorizontalBox();

        hBoxUp1.add(Box.createHorizontalGlue());

        hBoxUp1.add( hboxFormulaType);


        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Method 1",new method(1));
        jtp.addTab("Method 2",new method(2));
        jtp.addTab("Method 3",new method(3));
        Box hBoxUpt=Box.createHorizontalBox();
        hBoxUpt.add(jtp);


        btnCalc=new JButton("Вычислить");
        btnCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                coefficients=new Double[2];
    int selectedIndex = jtp.getSelectedIndex();
    coefficients[0]= Double.parseDouble(String.valueOf(selectedIndex));
                coefficients[1]= (double) formulaId;


                data = new GornerTableModel(Double.parseDouble(u1.getText()),
                        Double.parseDouble(u2.getText()),Double.parseDouble(u3.getText()),coefficients);


                if(x1==1)
                    for(int i=0;i<1863;i++)
                    {
                        series.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,1));
                        series1.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,2));
                        series2.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,3));

                    }
                if(x1==2)
                    for(int i=0;i<400;i++)
                    {

                        series3.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,1));
                        series4.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,2));
                        series5.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,3));

                    }
                if(x1==3)
                    for(int i=0;i<399;i++)
                    {

                        series6.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,1));
                        series7.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,2));
                        series8.add((Double) data.getValueAt(i,0), (Double) data.getValueAt(i,3));

                    }
                x1++;
                JTable table = new JTable( data);

                table.setRowHeight(30);
                hBox.removeAll();
                hBox.add(new JScrollPane(table));

                menuFileToCsv.setEnabled(true);

                getContentPane().validate();
            }
        });


        btnClear=new JButton("Очистить поля");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                u1.setText("0.0");
                u2.setText("0.0");
                u3.setText("0.0");

                menuFileToCsv.setEnabled(false);

                hBox.removeAll();
                hBox.add(new JPanel());
                getContentPane().validate();
            }
        });


        Box hBoxDn=Box.createHorizontalBox();
        hBoxDn.add(Box.createHorizontalGlue());
        hBoxDn.add(btnCalc);
        hBoxDn.add(Box.createHorizontalGlue());
        hBoxDn.add(btnClear);
        hBoxDn.add(Box.createHorizontalGlue());
        hBoxDn.add(Box.createVerticalGlue());

        Box contentBox = Box.createVerticalBox();

        contentBox.add(hBoxUp);

        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);

        contentBox.add(Box.createHorizontalGlue());
        contentBox.add(hBoxUp1);


        Box contentBox1 = Box.createVerticalBox();
        contentBox1.add(hBoxUpt);
        contentBox1.add(hBox);







        getContentPane().add(contentBox1, BorderLayout.CENTER);


        getContentPane().add(hBoxDn, BorderLayout.SOUTH);

        getContentPane().add(contentBox, BorderLayout.NORTH);



    }




    public static void main(String[] args) {


        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }



}

