package chessProject;

import chessProject.Pieces.King;
import chessProject.Pieces.Piece;

public class Runner {
	public static void main(String[] args) {
		Board gameBoard = new Board();
		King newKing = new King("king", "white", "white", 1,0);
		gameBoard.setPieces(newKing);
		gameBoard.movePiece(1, 0, 1, 0);
		System.out.print(gameBoard);
	}
}
