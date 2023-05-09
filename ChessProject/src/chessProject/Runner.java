package chessProject;

import chessProject.Pieces.King;
import chessProject.Pieces.Piece;

public class Runner {
	public static void main(String[] args) {
		Board gameBoard = new Board();
		King newKing = new King("king", "white", "white", 0,0);
		gameBoard.setPiece(newKing);
		System.out.print(gameBoard);
	}
}
