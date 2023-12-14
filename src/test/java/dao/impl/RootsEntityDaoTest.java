package dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import dao.EquationDao;
import dao.RootsDao;
import equation.Equation;
import exception.DaoException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConnectionManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RootsEntityDaoTest {

	private static RootsEntityDao dao;

	@BeforeAll
	static void setUp() {
		dao = RootsEntityDao.getInstance();
	}

	@Test
	void testSaveAndFindEquationsByRoot() {
		Equation testEquation = new Equation("2*x+5", 3.0);
		dao.save(testEquation);
		dao.findEquationsByRoot(testEquation);
		boolean deleted = dao.delete(testEquation.getId());
		assertFalse(deleted);
	}

}