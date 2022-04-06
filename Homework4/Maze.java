//Jiawei Lu
//20007605

import java.lang.module.FindException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class Maze implements GridColors {

    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    public boolean findMazePath() {
        return findMazePath(0, 0);
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORN ARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || (maze.getColor(x, y).equals(NON_BACKGROUND)  ==false)) {
            return false;
        }  else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        } else {
            maze.recolor(x, y, PATH);
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) ||
            findMazePath(x, y + 1) || findMazePath(x, y -1)) {
                return true;

            } else {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }
    }

    public void findMazePathStackBased(int x, int y,
                                       ArrayList<ArrayList<PairInt>> result,
                                       Stack<PairInt> trace){
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() ||
                (maze.getColor(x, y)  != NON_BACKGROUND)){
            return;
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> cur = new ArrayList<>(trace);
            result.add(cur);
            trace.pop(); 
            maze.recolor(x, y, NON_BACKGROUND); 
            return;
        } else {
            trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    }

    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);
        return result;
    }

    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        int index = 0;
        int glabalMin;
        ArrayList<ArrayList<PairInt>> allPaths;
        allPaths = findAllMazePaths(x, y);
        if (allPaths.size() == 1){
            return allPaths.get(0);
        }
        glabalMin = allPaths.get(0).size();

        for (int i = 1; i < allPaths.size(); i++) {
            if (allPaths.get(i).size() < glabalMin) {
                glabalMin = allPaths.get(i).size();
                index = i;
            }
        }
        return allPaths.get(index);
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/