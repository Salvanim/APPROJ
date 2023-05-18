package chessProject;

import java.util.ArrayList;
import java.util.Scanner;

import chessProject.Pieces.Queen;
import chessProject.Pieces.King;
import chessProject.Pieces.Pawn;
import chessProject.Pieces.Knight;
import chessProject.Pieces.Rook;
import chessProject.Pieces.Bishop;
import chessProject.Pieces.Piece;

public class Board {
	private Piece[][] board = new Piece[8][8];
	private ArrayList<Piece> blackCaptured = new ArrayList<Piece>();
	private ArrayList<Piece> whiteCaptured = new ArrayList<Piece>();
	private boolean kingBeingAttacked = false;
	public String toString() {
	    String outString = " ";
	    for(int row = 0; row < board.length; row++) {
	        for(int col = 0; col < board[row].length; col++) {
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
	                outString += " ";
	            }
	        }
	        outString += " \n ";
	    }
	    return outString.substring(0, outString.length() - 3) + " ";
	}
	
	private void setPiecesByArray(Piece[][] board) {
		this.board = board;
	}
	
	private void createBoardWithPieces() {
		King blackKing = new King("king", "black", 0,3);
		King whiteKing = new King("king", "white", 7,4);
		Queen whiteQueen = new Queen("queen", "white", 7,3);
		Queen blackQueen = new Queen("queen", "black", 0,4);
		Bishop blackBishop1 = new Bishop("bishop", "black", 0,5);
		Bishop blackBishop2 = new Bishop("bishop", "black", 0,2);
		Bishop whiteBishop1 = new Bishop("bishop", "white", 7,5);
		Bishop whiteBishop2 = new Bishop("bishop", "white", 7,2);
		Knight blackKnight1 = new Knight("knight", "black", 0,6);
		Knight blackKnight2 = new Knight("knight", "black", 0,1);
		Knight whiteKnight1 = new Knight("knight", "white", 7,6);
		Knight whiteKnight2 = new Knight("knight", "white", 7,1);
		Rook blackRook1 = new Rook("rook", "black", 0,7);
		Rook blackRook2 = new Rook("rook", "black", 0,0);
		Rook whiteRook1 = new Rook("rook", "white", 7,7);
		Rook whiteRook2 = new Rook("rook", "white", 7,0);
		Pawn[] blackPawns = new Pawn[8];
		for(int i = 0; i <= 7; i++) {
			blackPawns[i] = new Pawn("pawn" , "black", 1,i);
		}
		Pawn[] whitePawns = new Pawn[8];
		for(int i = 0; i <= 7; i++) {
			whitePawns[i] = new Pawn("pawn" , "white", 6,i);
		}
		Piece[] allPeices = {blackKing,whiteKing, blackQueen, whiteQueen, blackBishop1, blackBishop2, whiteBishop1, whiteBishop2, blackKnight1, blackKnight2, whiteKnight1, whiteKnight2, blackRook1, blackRook2, whiteRook1, whiteRook2};
		setPieces(allPeices,blackPawns,whitePawns);
	}
	
	public Board() {
		createBoardWithPieces();
		System.out.println(this);
	}
	
	private void Update(){
		System.out.println("\n" + this);
	}
	
	private void setValid(int x, int y) {
		board[x][y] = null;
	}
	
	private void setPieces(Piece... newPieceS) {
		for(Piece newPiece : newPieceS) {
			board[newPiece.getX()][newPiece.getY()] = newPiece;
		}
	}
	
	private void setPieces(Piece[]... newPieces) {
		for(Piece[] pieces : newPieces) {
			for(Piece newPiece : pieces) {
				board[newPiece.getX()][newPiece.getY()] = newPiece;
			}
		}
	}
	
	private Piece getPiece(int x, int y) {
		return board[x][y];
	}
	
	private boolean addCapture(int startX, int startY, int endX, int endY) {
		boolean isCapture = false;
		Piece startPiece = board[startX][startY];
		Piece endPiece = board[endX][endY];
		if(endPiece != null) {
			if(endPiece.getPieceColor() != startPiece.getPieceColor() && endPiece != null) {
				isCapture = true;
				if(startPiece.getPieceColor() == "black") {
					whiteCaptured.add(endPiece);
				} else if(startPiece.getPieceColor() == "white") {
					blackCaptured.add(endPiece);
				} 
			}
		}
		return isCapture;
	}
	
	private String inverseColor(String color) {
		String returnColor = "black";
		if(color == "black") {
			returnColor = "white";
		}
		return returnColor;
	}
	
	private boolean isMoveValid(int startX, int startY, int endX, int endY) {
		boolean isValid = false;
		Piece startPiece = board[startX][startY];
		Piece endPiece = board[endX][endY];
		boolean endIsNull = (endPiece == null);
		boolean generalValid = (startPiece != null && (endIsNull || endPiece.getPieceColor() != startPiece.getPieceColor()));
		String name = board[startX][startY].getDisplayName();
		boolean isOnBoard = (endX >= 0 && endX <= 7) && (endY >= 0 && endY <= 7);
		int direction = startPiece.getCurrentDireciton();	
			if(name.contains("king")) {
				if(isOnBoard && generalValid && ((endX <= startX+1) && (endY <= startY+1)) && ((endX >= startX-1) && (endY >= startY-1))) {
					isValid = true;
				}
			} else if(name.contains("queen")) {
				if(isOnBoard && generalValid && (nullBetweenRook(startX, startY, endX, endY) || nullBetweenBishop(startX, startY, endX, endY))) {
					isValid = true;
				}
			} else if(name.contains("knight")) {
				if(isOnBoard && generalValid && isInL(startX, startY, endX, endY)) {
					isValid = true;
				}
			} else if(name.contains("rook")) {
				if(isOnBoard && generalValid && (startX == endX || startY == endY) && nullBetweenRook(startX, startY, endX, endY)) {
					isValid = true;
				}
			} else if(name.contains("bishop")) {
				if(isOnBoard && generalValid && !(startX == endX || startY == endY) && nullBetweenBishop(startX, startY, endX, endY)) {
					isValid = true;
				}
			} else if(name.contains("pawn")) {
				if(board[startX][startY].getPieceColor() == "black") {
					if(isOnBoard && generalValid && ((endX == startX+(1*direction) && endIsNull) || (!endIsNull && endY == startY+(1*direction)))) {
						isValid = true;
					}
				} else if(board[startX][startY].getPieceColor() == "white"){
					if(isOnBoard && generalValid && ((endX == startX-(1*direction) && endIsNull) || (!endIsNull && endY == startY-(1*direction)))) {
						isValid = true;
					}
				}
			}
		return isValid;
	}
	
	private boolean nullBetweenRook(int startX, int startY, int endX, int endY) {
		boolean nullBetween = true;
		if(startX != endX && startY != endY) {
			nullBetween = false;
		} else if(startX != endX && startY == endY) {
			if(startX > endX) {
				for(int i = endX+1; i < startX; i++) {
					if(board[i][startY] != null) {
						nullBetween = false;
						break;
					}
				}
			} else if(startX < endX) {
				for(int i = startX+1; i < endX; i++) {
					if(board[i][endY] != null) {
						nullBetween = false;
						break;
					}
				}
			}
		} else if(startX == endX && startY != endY){
			if(startY > endY) {
				for(int i = endY+1; i < startY; i++) {
					if(board[startX][i] != null) {
						nullBetween = false;
						break;
					}
				}
			} else if(startY < endY) {
				for(int i = startY+1; i < endY; i++) {
					if(board[endX][i] != null) {
						nullBetween = false;
						break;
					}
				}
			}
		}
		return nullBetween;
    }
	
	private boolean nullBetweenBishop(int startX, int startY, int endX, int endY) {
		boolean nullBetween = true;
		if(startX == endX || startY == endY) {
			nullBetween = false;
		} else if(startX != endX && startY != endY) {
			int checkX = endX;
			int checkY = endY;
			int xDirection = 0;
			int yDirection = 0;
			if(startX < endX) {
				xDirection = -1;
			} else if(startX > endX) {
				xDirection = 1;
			}
			
			if(startY < endY) {
				yDirection = -1;
			} else if (startY > endY) {
				yDirection = 1;
			}
			
			while(checkX != startX && checkY != startY) {
				if(board[checkX][checkY] != null) {
					nullBetween = false;
					break;
				} else {
					checkX += xDirection;
					checkY += yDirection;
				}
			}
		}
		return nullBetween;
    }
	
	private boolean isInL(int startX, int startY, int endX, int endY) {
		boolean isInL = false;
		int[][] moves = {{startX+2,startY+1}, {startX+2, startY-1},{startX-2,startY+1}, {startX-2, startY-1}, {startX+1,startY+2}, {startX+1, startY-2},{startX-1,startY+2}, {startX-1, startY-2}};
		for (int[] move : moves) {
	        if(endX == move[0] && endY == move[1]) {
	        	isInL = true;
	        }
		}
		return isInL;
	}
	
	public void moveMany(int... movements) {
		for(int i = 0; i < movements.length;) {
			if(movements.length%4 == 0) {
				movePiece(movements[i],movements[i+1],movements[i+2],movements[i+3]);
				i+=4;
			}
		}
	}
	
	private void moveByList(int[] movements) {
		for(int i = 0; i < movements.length;) {
			movePiece(movements[i],movements[i+1],movements[i+2],movements[i+3]);
			i+=4;
		}
	}
	
	private int getTotalBlackCapturedValue() {
		int totalValue = 0;
		for(Piece blackPiece : blackCaptured) {
			totalValue += blackPiece.getValue();
		}
		return totalValue;
	}
	
	private int getTotalWhiteCapturedValue() {
		int totalValue = 0;
		for(Piece whitePiece : whiteCaptured) {
			totalValue += whitePiece.getValue();
		}
		return totalValue;
	}
	
	private Piece getMaxBlackCpaturedPiece() {
		Piece max = blackCaptured.get(0);
		for(Piece blackPiece : blackCaptured) {
			if(blackPiece.getValue() > max.getValue()) {
				max = blackPiece;
			}
		}
		return max;
	}
	
	private Piece getMaxWhiteCpaturedPiece() {
		Piece max = whiteCaptured.get(0);
		for(Piece whitePiece : whiteCaptured) {
			if(whitePiece.getValue() > max.getValue()) {
				max = whitePiece;
			}
		}
		return max;
	}
	
	private void removeWhiteCapturedPiece(Piece whitePiece) {
		whiteCaptured.remove(whiteCaptured.indexOf(whitePiece));
	}
	
	private void removeBlackCapturedPiece(Piece blackPiece) {
		blackCaptured.remove(blackCaptured.indexOf(blackPiece));
	}
	
	private void changePawnDireciton(int startX, int startY, int endX, int endY) {
		if(endX == 7 || endX == 0) {
			board[startX][startY].swapCurrentDireciton();
		}
	}
	
	private int getKingX(String color) {
		int kingX = 0;
		for(Piece[] x : board) {
			for(Piece y : x) {
				if(y != null) {
					if(y.getPieceColor() == color && y.getDisplayName() == "king") {
						kingX = y.getX();
					}
				}
			}
		}
		return kingX;
	}
	
	private int getKingY(String color) {
		int kingY = 0;
		for(Piece[] x : board) {
			for(Piece y : x) {
				if(y != null) {
					if(y.getPieceColor() == color && y.getDisplayName() == "king") {
						kingY = y.getY();
					}
				}
			}
		}
		return kingY;
	}
	
	private void changePawnAtEnd(int startX, int startY, int endX, int endY) {
		if(endX == 7 || endX == 0) {
			if(board[startX][startY].getPieceColor() == "black") {
				board[startX][startY] = getMaxBlackCpaturedPiece();
			} else if(board[startX][startY].getPieceColor() == "white") {
				board[startX][startY] = getMaxWhiteCpaturedPiece();
			}
			
		}
	}
	
	public void movePiece(int startX, int startY, int endX, int endY) {
		if(isMoveValid(startX, startY, endX, endY)) {
			addCapture(startX, startY, endX, endY);
			changePawnDireciton(startX, startY, endX, endY);
			board[endX][endY] = board[startX][startY];
			setValid(startX, startY);
		} else {
			System.out.println("\n(" + startX + "," + startY + ")" + " to (" + endX + "," + endY + ")" + " is a invalid move!");
			System.exit(0);
		}
		Update();
	}
	
	
}
