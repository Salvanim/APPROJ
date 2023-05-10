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
		
		Queen blackQueen = new Queen("queen", "white", "white", 0,4);
		Queen whiteQueen = new Queen("queen", "black", "black", 7,3);
		
		Bishop blackBishop1 = new Bishop("queen", "white", "black", 0,5);
		Bishop blackBishop2 = new Bishop("queen", "white", "black", 0,2);
		
		Bishop whiteBishop1 = new Bishop("queen", "white", "white", 7,5);
		Bishop whiteBishop2 = new Bishop("queen", "white", "white", 7,2);
		
		Knight blackKnight1 = new Knight("queen", "white", "black", 0,6);
		Knight blackKnight2 = new Knight("queen", "white", "black", 0,1);
		
		Knight whiteKnight1 = new Knight("queen", "white", "white", 7,6);
		Knight whiteKnight2 = new Knight("queen", "white", "white", 7,1);
		
		Rook blackRook1 = new Rook("queen", "white", "black", 0,7);
		Rook blackRook2 = new Rook("queen", "white", "black", 0,0);
		
		Rook whiteRook1 = new Rook("queen", "white", "white", 7,7);
		Rook whiteRook2 = new Rook("queen", "white", "white", 7,0);
	
		Pawn blackPawn1 = new Pawn("queen", "white", "black", 1,0);
		Pawn blackPawn2 = new Pawn("queen", "white", "black", 1,1);
		Pawn blackPawn3 = new Pawn("queen", "white", "black", 1,2);
		Pawn blackPawn4 = new Pawn("queen", "white", "black", 1,3);
		Pawn blackPawn5 = new Pawn("queen", "white", "black", 1,4);
		Pawn blackPawn6 = new Pawn("queen", "white", "black", 1,5);
		Pawn blackPawn7 = new Pawn("queen", "white", "black", 1,6);
		Pawn blackPawn8 = new Pawn("queen", "white", "black", 1,7);
		
		Pawn whitePawn1 = new Pawn("queen", "white", "white", 6,0);
		Pawn whitePawn2 = new Pawn("queen", "white", "white", 6,1);
		Pawn whitePawn3 = new Pawn("queen", "white", "white", 6,2);
		Pawn whitePawn4 = new Pawn("queen", "white", "white", 6,3);
		Pawn whitePawn5 = new Pawn("queen", "white", "white", 6,4);
		Pawn whitePawn6 = new Pawn("queen", "white", "white", 6,5);
		Pawn whitePawn7 = new Pawn("queen", "white", "white", 6,6);
		Pawn whitePawn8 = new Pawn("queen", "white", "white", 6,7);
		
		Piece[] allPeices = {blackKing,whiteKing, blackQueen, whiteQueen, blackBishop1, blackBishop2, whiteBishop1, whiteBishop2, blackKnight1, blackKnight2, whiteKnight1, whiteKnight2, blackRook1, blackRook2, whiteRook1, whiteRook2, blackPawn1, blackPawn2, blackPawn3, blackPawn4, blackPawn5, blackPawn6, blackPawn7, blackPawn8, whitePawn1, whitePawn2, whitePawn3, whitePawn4, whitePawn5, whitePawn6, whitePawn7, whitePawn8};
		
		gameBoard.setPieces(allPeices);
		
		System.out.print(gameBoard);
	}
}
