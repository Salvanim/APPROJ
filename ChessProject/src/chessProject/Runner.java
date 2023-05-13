package chessProject;

import chessProject.Pieces.Queen;
import chessProject.Pieces.King;
import chessProject.Pieces.Pawn;
import chessProject.Pieces.Knight;
import chessProject.Pieces.Rook;
import chessProject.Pieces.Bishop;
import chessProject.Pieces.Piece;


public class Runner {
	public static void main(String[] args) {
		Board gameBoard = new Board();
		King blackKing = new King("king", "white", "black", 0,3);
		King whiteKing = new King("king", "black", "white", 7,4);
		
		Queen whiteQueen = new Queen("queen", "white", "white", 7,3);
		Queen blackQueen = new Queen("queen", "black", "black", 0,4);
		
		Bishop blackBishop1 = new Bishop("bishop", "black", "black", 0,5);
		Bishop blackBishop2 = new Bishop("bishop", "black", "black", 0,2);
		
		Bishop whiteBishop1 = new Bishop("bishop", "white", "white", 7,5);
		Bishop whiteBishop2 = new Bishop("bishop", "white", "white", 7,2);
		
		Knight blackKnight1 = new Knight("knight", "black", "black", 0,6);
		Knight blackKnight2 = new Knight("knight", "black", "black", 0,1);
		
		Knight whiteKnight1 = new Knight("knight", "white", "white", 7,6);
		Knight whiteKnight2 = new Knight("knight", "white", "white", 7,1);
		
		Rook blackRook1 = new Rook("rook", "black", "black", 0,7);
		Rook blackRook2 = new Rook("rook", "black", "black", 0,0);
		
		Rook whiteRook1 = new Rook("rook", "white", "white", 7,7);
		Rook whiteRook2 = new Rook("rook", "white", "white", 7,0);
		
		Pawn[] blackPawns = new Pawn[8];
		for(int i = 0; i <= 7; i++) {
			blackPawns[i] = new Pawn("pawn", "white", "black", 1,i);
		}
		
		Pawn[] whitePawns = new Pawn[8];
		for(int i = 0; i <= 7; i++) {
			whitePawns[i] = new Pawn("pawn", "white", "white", 6,i);
		}
		
		Piece[] allPeices = {blackKing,whiteKing, blackQueen, whiteQueen, blackBishop1, blackBishop2, whiteBishop1, whiteBishop2, blackKnight1, blackKnight2, whiteKnight1, whiteKnight2, blackRook1, blackRook2, whiteRook1, whiteRook2};
		gameBoard.setPieces(allPeices,blackPawns,whitePawns);
		System.out.println(gameBoard);
	}
}
