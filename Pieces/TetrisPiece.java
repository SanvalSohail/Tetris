package Pieces;

import java.awt.Color;
import java.awt.Graphics2D;

import TetrisGame.Gameplay;
import TetrisGame.KeyHandler;

public class TetrisPiece {

    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int autoDropCounter = 0;
    public int direction = 1; //4 different rotations
    boolean leftCollision,rightCollision,bottomCollision;
    public boolean active = true;

    public void create(Color c){
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }

    public void setXY(int x, int y){}
    public void updateXY(int direction){
        checkRotationCollision();
        if(leftCollision == false && rightCollision == false && bottomCollision == false)
        {
        this.direction = direction;
        b[0].x = tempB[0].x;
        b[0].y = tempB[0].y;
        b[1].x = tempB[1].x;
        b[1].y = tempB[1].y;
        b[2].x = tempB[2].x;
        b[2].y = tempB[2].y;
        b[3].x = tempB[3].x;
        b[3].y = tempB[3].y;
        }
    }
    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}

    public void checkMovementCollision(){
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        //check collision with set blocks
        checkSetBlockCollision();

        //check collision with boundaries
        //left wall
        for(int i = 0; i < b.length; i++){
            if(b[i].x == Gameplay.left_x){
                leftCollision = true;
            }
        }
        //right wall
        for(int i = 0; i < b.length; i++){
            if(b[i].x + Block.SIZE == Gameplay.right_x){
                rightCollision = true;
            }
        }
        //bottom floor
        for(int i = 0; i < b.length; i++){
            if(b[i].y + Block.SIZE == Gameplay.bottom_y){
                bottomCollision = true;
            }
        }
    }
    public void checkRotationCollision(){
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        
        //check collision with set blocks
        checkSetBlockCollision();

        //check collision with boundaries
        //left wall
        for(int i = 0; i < b.length; i++){
            if(tempB[i].x < Gameplay.left_x){
                leftCollision = true;
            }
        }
        //right wall
        for(int i = 0; i < b.length; i++){
            if(tempB[i].x + Block.SIZE > Gameplay.right_x){
                rightCollision = true;
            }
        }
        //bottom floor
        for(int i = 0; i < b.length; i++){
            if(tempB[i].y + Block.SIZE > Gameplay.bottom_y){
                bottomCollision = true;
            }
        }
    }

    private void checkSetBlockCollision(){

        for (int i = 0; i < Gameplay.setBlocks.size(); i++ )
        {
            int checkX = Gameplay.setBlocks.get(i).x;
            int checkY = Gameplay.setBlocks.get(i).y;

            //check down
            for(int j = 0; j < b.length; j++){
                if (b[j].y + Block.SIZE == checkY && b[j].x == checkX){
                    bottomCollision = true;
                }
            }

            //check left
            for(int j = 0; j < b.length; j++){
                if (b[j].x - Block.SIZE == checkX && b[j].y == checkY){
                    leftCollision = true;
                }
            }
            //check right
            for(int j = 0; j < b.length; j++){
                if (b[j].x + Block.SIZE == checkX && b[j].y == checkY){
                    rightCollision = true;
                }
            }
        }
    }

    public void update(){
        if(KeyHandler.upPressed)
        {
            switch(direction){
                case 1: getDirection2();break;
                case 2: getDirection3();break;
                case 3: getDirection4();break;
                case 4: getDirection1();break;
            }
            KeyHandler.upPressed = false;
        }

        checkMovementCollision();

        if(KeyHandler.downPressed)
        {
            if(!bottomCollision)    {
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;

            autoDropCounter = 0;
            }
            KeyHandler.downPressed = false;
        }

        if(KeyHandler.leftPressed)
        {
            if(!leftCollision)  {
            b[0].x -= Block.SIZE;
            b[1].x -= Block.SIZE;
            b[2].x -= Block.SIZE;
            b[3].x -= Block.SIZE;
            }

            KeyHandler.leftPressed = false;
        }

        if(KeyHandler.rightPressed)
        {
            if(!rightCollision) {
            b[0].x += Block.SIZE;
            b[1].x += Block.SIZE;
            b[2].x += Block.SIZE;
            b[3].x += Block.SIZE;
            }

            KeyHandler.rightPressed = false;
        }

        if(bottomCollision) active = false;
        else{
        autoDropCounter++;
        if(autoDropCounter == Gameplay.dropInterval)
        {
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
            autoDropCounter = 0;
        }
    }
    }
    public void draw(Graphics2D g2){
        int margin = 2;
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
    }
}
