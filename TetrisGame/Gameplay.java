package TetrisGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;

import Pieces.Block;
import Pieces.Piece_L1;
import Pieces.Piece_L2;
import Pieces.Piece_Line;
import Pieces.Piece_Square;
import Pieces.Piece_T;
import Pieces.Piece_Z1;
import Pieces.Piece_Z2;
import Pieces.TetrisPiece;

public class Gameplay {

    //main play area variables
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;
    
    // Current Tetris Piece
    TetrisPiece currentPiece;
    final int PIECE_START_X;
    final int PIECE_START_Y;
    // Next Tetris Piece
    TetrisPiece nextPiece;
    final int NEXT_Piece_X;
    final int NEXT_Piece_Y;
    //List for inactive pieces
    public static ArrayList<Block> setBlocks = new ArrayList<>();
    //Drop timing = once/s
    public static int dropInterval = 60;

    public Gameplay(){
        // Main Play Area Frame
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;
        //Starting positions of blocks
        PIECE_START_X = left_x + (WIDTH/2) - Block.SIZE;
        PIECE_START_Y = top_y + Block.SIZE;

        NEXT_Piece_X = right_x + 175;
        NEXT_Piece_Y = top_y + 500;
        //Set the starting piece
        currentPiece = pickPiece();
        currentPiece.setXY(PIECE_START_X, PIECE_START_Y);
        nextPiece = pickPiece();
        nextPiece.setXY(NEXT_Piece_X, NEXT_Piece_Y);
    }

    private TetrisPiece pickPiece(){
        TetrisPiece piece = null;

        int i = new Random().nextInt(7);

        switch (i) {
            case 0: piece = new Piece_L1();break;
            case 1: piece = new Piece_L2();break;
            case 2: piece = new Piece_Square();break;
            case 3: piece = new Piece_Line();break;
            case 4: piece = new Piece_Z1();break;
            case 5: piece = new Piece_Z2();break;
            case 6: piece = new Piece_T();break;
        }

        return piece;
    }
     
    public void update(){
        if(currentPiece.active == false){
            setBlocks.add(currentPiece.b[0]);
            setBlocks.add(currentPiece.b[1]);
            setBlocks.add(currentPiece.b[2]);
            setBlocks.add(currentPiece.b[3]);

            currentPiece = nextPiece;
            currentPiece.setXY(PIECE_START_X, PIECE_START_Y);
            nextPiece = pickPiece();
            nextPiece.setXY(NEXT_Piece_X, NEXT_Piece_Y);
        }
        currentPiece.update();
    }

    public void draw(Graphics2D g2){

        //Border for play area
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x-4, top_y-4, WIDTH+8, HEIGHT+8);

        //next piece box
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Arial", Font.PLAIN,30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 60, y + 60);

        //Draw the currentPiece
        if(currentPiece != null){
            currentPiece.draw(g2);
        }
        
        //Draw the next piece in the next zone
        nextPiece.draw(g2);

        //Draw blocks that are no longer active
        for(int i = 0; i < setBlocks.size(); i++){
            setBlocks.get(i).draw(g2);
        }
        //Draw Pause Text
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
        if(KeyHandler.pausePressed){
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
        }
    }
}