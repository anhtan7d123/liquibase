package com.example.liquibase;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class LiquibaseApplication {

	public static void main(String[] args) throws SQLException, LiquibaseException {
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/liquibase1",
					"postgres", "05042001aA");

		Database database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(new JdbcConnection(c));

		Liquibase liquibase = new Liquibase("classpath:database/migration/master-changelog.xml", new ClassLoaderResourceAccessor(), database);

		liquibase.update("test");
	}
}
