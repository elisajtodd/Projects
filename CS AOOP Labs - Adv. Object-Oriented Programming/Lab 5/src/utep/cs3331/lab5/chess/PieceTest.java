package utep.cs3331.lab5.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PieceTest {
	
	Queen q;
	King k;
	Pawn p;
	Knight n;
	Bishop b;
	Rook r;
	
	@BeforeEach
	void setUp(){
		q = new Queen("queen", "white", 'd', 1);
		k = new King("king", "white", 'e', 1);
		p = new Pawn("pawn", "white", 'd', 2);
		n = new Knight("knight", "white", 'g', 1);
		b = new Bishop("bishop", "white", 'c', 1);
		r = new Rook("rook", "white", 'a', 1);
	}
	@Test
	void testMoveQueen1(){
		assertTrue(q.validate('d', 3)); //test queen
	}
	
	void testMoveQueen2(){
		assertTrue(q.validate('b', 1));
	}
	
	void testMoveQueen3(){
		assertFalse(q.validate('c', 4));
	}
	
	void testMoveQueen4(){
	   assertFalse(q.validate('f', 2));
	}
	
	@Test
	void testMoveKing(){
	   assertTrue(k.validate('e',2)); //test king
	}
	
	@Test
	void testMoveKnight(){
	   assertTrue(n.validate('h', 3)); //test knight
	}
	@Test
	void testMoveBishop(){
	   assertTrue(b.validate('a', 3)); //test bishop
	}
	@Test
	void testMoveRook(){
	   assertTrue(r.validate('a', 8)); //test rook
	}
	@Test
	void testMovePawn(){
	   assertTrue(p.validate('d', 4)); //test pawn
	}



}
