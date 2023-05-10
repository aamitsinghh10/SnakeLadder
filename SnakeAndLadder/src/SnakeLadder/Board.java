package SnakeLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;
    Board(int boardSize, int numberOfSnakes, int numberOfLadders){
        initializeCells(boardSize);
        addSnakesAndLadders(cells, numberOfSnakes, numberOfLadders);
    }
    private void initializeCells(int boardSize){
        cells = new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                Cell cellobj = new Cell();
                cells[i][j] = cellobj;
            }
        }
    }
    private void addSnakesAndLadders(Cell[][] cells,int numberOfSnakes, int numberOfLadders){
        while(numberOfSnakes>0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);

            if(snakeTail>=snakeHead){
                continue;
            }
            Jump snakeObj = new Jump();
            snakeObj.start = snakeHead;
            snakeObj.end = snakeTail;

            Cell cell = getCell(snakeHead);
            cell.jump = snakeObj;

            numberOfSnakes--;
        }

        while(numberOfLadders>0){
            int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);

            if(ladderTail>=ladderHead){
                continue;
            }
            Jump snakeObj = new Jump();
            snakeObj.start = ladderHead;
            snakeObj.end = ladderTail;

            Cell cell = getCell(ladderHead);
            cell.jump = snakeObj;

            numberOfLadders--;
        }
    }
    Cell getCell(int playerPosition) {
        int boardRow = playerPosition / cells.length;
        int boardColumn = (playerPosition % cells.length);
        return cells[boardRow][boardColumn];
    }

}
