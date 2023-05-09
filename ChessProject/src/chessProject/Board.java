package chessProject;

import java.util.ArrayList;

import chessProject.Pieces.Piece;

public class Board extends Runner{
	Piece[][] board = new Piece[8][8];
	
	public String toString() {
	    String outString = "[";
	    for(int row = 0; row < board.length; row++) {
	        for(int col = 0; col < board[row].length; col++) {
	            Piece piece = board[row][col];
	            if(piece == null) {
	            	outString += "■";
	            } else {
	                outString += piece.getIcon();
	            }
	            if(col != board[row].length - 1) {
	                outString += ",";
	            }
	        }
	        outString += "]\n[";
	    }
	    return outString.substring(0, outString.length() - 3) + "]";
	}
	
	public boolean isValid(int x, int y) {
		if(board[x][y].contains(null)) {
			return true;
		}
		return false;
	}
	
	public void setPiece(Piece newPiece) {
		board[newPiece.getX()][newPiece.getY()] = newPiece;
	}
	
}
