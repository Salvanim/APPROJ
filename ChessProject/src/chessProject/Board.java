package chessProject;

import chessProject.Pieces.Piece;

public class Board {
	Piece[][] board = new Piece[8][8];
	public String toString() {
	    String outString = " ";
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
	                outString += " ";
	            }
	        }
	        outString += " \n ";
	    }
	    return outString.substring(0, outString.length() - 3) + " ";
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
	
	public Piece getPiece(int x, int y) {
			return board[x][y];
	}
	
	public boolean isMoveValid(int startX, int startY, int endX, int endY) {
		boolean isValid = false;
		Piece startPiece = board[startX][startY];
		Piece endPiece = board[endX][endY];
		boolean endIsNull = (endPiece == null);
		boolean generalValid = (startPiece != null && (endIsNull || endPiece.getPieceColor() != startPiece.getPieceColor()));
			String name = board[startX][startY].getDisplayName();
			boolean isOnBoard = (endX >= 0 && endX <= 7) && (endY >= 0 && endY <= 7);
			if(name.contains("king")) {
				if(isOnBoard && generalValid && ((endX <= startX+1) && (endY <= startY+1)) && ((endX >= startX-1) && (endY >= startY-1))) {
					return true;
				}
			} else if(name.contains("queen")) {
				if(isOnBoard && generalValid && (nullBetweenRook(startX, startY, endX, endY) || nullBetweenBishop(startX, startY, endX, endY))) {
					return true;
				}
			} else if(name.contains("knight")) {
				if(isOnBoard && generalValid && isInL(startX, startY, endX, endY)) {
					return true;
				}
			} else if(name.contains("rook")) {
				if(isOnBoard && generalValid && (startX == endX || startY == endY) && nullBetweenRook(startX, startY, endX, endY)) {
					return true;
				}
			} else if(name.contains("bishop")) {
				if(isOnBoard && generalValid && !(startX == endX || startY == endY) && nullBetweenBishop(startX, startY, endX, endY)) {
					return true;
				}
			} else if(name.contains("pawn")) {
				if(board[startX][startY].getPieceColor() == "black") {
					if(isOnBoard && generalValid && ((endX == startX+1 && endIsNull) || (!endIsNull && endY == startY+1))) {
						isValid = true;
					}
				} else if(board[startX][startY].getPieceColor() == "white"){
					if(isOnBoard && generalValid && ((endX == startX-1 && endIsNull) || (!endIsNull && endY == startY-1))) {
						isValid = true;
					}
				}
			}
		return isValid;
	}
	
	public boolean nullBetweenRook(int startX, int startY, int endX, int endY) {
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
	
	public boolean nullBetweenBishop(int startX, int startY, int endX, int endY) {
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
	
	public void movePiece(int startX, int startY, int endX, int endY) {
		if(isMoveValid(startX, startY, endX, endY)) {
			board[endX][endY] = board[startX][startY];
			setValid(startX, startY);
		} else {
			System.out.println("Invalid Move");
			System.exit(0);
		}
	}
	
	
}
