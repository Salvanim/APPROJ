package chessProject;

import chessProject.Pieces.Piece;

public class Board extends Runner{
	Piece[][] board = new Piece[8][8];
	public String toString() {
	    String outString = "[";
	    for(int row = 0; row < board[0].length; row++) {
	        for(int col = 0; col < board.length; col++) {
	            Piece piece = board[row][col];
	            if(piece == null) {
	            	if(col % 2 == 0 && row %2 == 0) {
	            		outString += "□";
	            	} else if (col % 2 == 0 && row %2 == 1){
	            		outString += "■";
	            	} else if (col % 2 == 1 && row %2 == 0){
	            		outString += "■";
	            	} else if (col % 2 == 1 && row %2 == 1){
	            		outString += "□";
	            	}
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
		if(board[x][y] == null) {
			return true;
		}
		return false;
	}
	
	public void setValid(int x, int y) {
		board[x][y] = null;
	}
	
	public void setPieces(Piece... newPieceS) {
		for(Piece newPiece : newPieceS) {
			board[newPiece.getX()][newPiece.getY()] = newPiece;
		}
	}
	
	public void setPieces(Piece[]... newPieces) {
		for(Piece[] pieces : newPieces) {
			for(Piece newPiece : pieces) {
				board[newPiece.getX()][newPiece.getY()] = newPiece;
			}
		}
	}
	
	public void movePiece(int startX, int startY, int endX, int endY) {
		if(isValid(endX, endY)) {
			board[endX][endY] = board[startX][startY];
			setValid(startX, startY);
		} else {
			System.out.println("Invalid Move");
			System.exit(0);
		}
	}
	
	
}
