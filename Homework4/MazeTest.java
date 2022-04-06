import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.util.Arrays;


public class MazeTest extends JFrame implements GridColors {

    private TwoDimGrid theGrid; 

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                String reply =
                        JOptionPane.showInputDialog("Please enter the number of rows");
                int nRows = Integer.parseInt(reply);
                reply =
                        JOptionPane.showInputDialog("Please enter the number of columns");
                int nCols = Integer.parseInt(reply);
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                new MazeTest(aGrid);
            } else {
                BufferedReader br =
                        new BufferedReader(new FileReader(args[0]));

                ArrayList<char[]> gridArrayList = new ArrayList<char[]>();
                String line;
                while ((line = br.readLine()) != null) {
                    char[] row = line.toCharArray();
                    gridArrayList.add(row);
                }

                char[][] bitMap =
                        gridArrayList.toArray(new char[gridArrayList.size()][]);
                int nRows = bitMap.length;
                int nCols = bitMap[0].length;

                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                aGrid.recolor(bitMap, NON_BACKGROUND);
                new MazeTest(aGrid);


            }
        } catch (Exception ex) {
            System.err.println("Exception " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }


    private MazeTest(TwoDimGrid aGrid) {
        theGrid = aGrid;
        getContentPane().add(aGrid, BorderLayout.CENTER);

        JTextArea instruct = new JTextArea(2, 20);
        instruct.setText("Toggle a button to change its color"
                + "\nPress SOLVE when ready");
        getContentPane().add(instruct, BorderLayout.NORTH);
        JButton solveButton = new JButton("SOLVE");
        solveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });
        JButton resetButton = new JButton("RESET");
        resetButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                (new Maze(theGrid)).restore();
            }
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(solveButton);
        bottomPanel.add(resetButton);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public void solve() {
        Maze m = new Maze(theGrid);

        if (m.findAllMazePaths(0,0).size() > 0 ) {
        int size = m.findAllMazePaths(0,0).size();
        ArrayList[] array = new ArrayList[m.findAllMazePaths(0,0).size()];
        for (int i = 0; i < m.findAllMazePaths(0,0).size(); i++) {
            array[i] =  m.findAllMazePaths(0,0).get(i);
        }
        System.out.println(Arrays.toString(array));

        System.out.println("The shortest path is: " + m.findMazePathMin(0,0));

        boolean found = m.findMazePath();
            JOptionPane.showMessageDialog(null, "Successfuly found " + size +" path. Please reset maze and try again");
        } else {
            System.out.println("[[]]");
            JOptionPane.showMessageDialog(null, "Successfuly found " + 0 +" path. Please reset maze and try again");
        }
    }
}
