package utep.cs3331.lab5.chess;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessboardAdapterTest {
	
	private ChessboardAdapter board;
	
	@BeforeEach
	void setUp() {
		ArrayList<Piece> pieces= new ArrayList<Piece>();
		pieces.add(new Queen("queen", "white", 'd', 1));
		board = new ChessboardAdapter(new Chessboard ("code123", 8, 8, pieces));
	}

	@Test
	void testGetPiece1() {
		assertTrue(board.getPiece("Qd1"));
	}
	@Test
	void testGetPiece2() {
		assertTrue(board.getPiece("qd1"));
	}
	
	@Test
	void testGetPiece3() {
		assertFalse(board.getPiece("Qd7"));
	}
	@Test
	void testGetPiece4() {
		assertFalse(board.getPiece("Kd1"));
	}
	
	@Test
	void testMove1() {
		assertTrue(board.move("Qd1","Qd2"));
	}
	
	@Test
	void testMove2() {
		assertTrue(board.move("Qd1","Qd5"));
	}
	
	@Test
	void testMove3() {
		assertFalse(board.move("Qd1","Qf6"));
	}
	
	@Test
	void testMove4() {
		assertFalse(board.move("Qd1","Qa5"));
	}
}
