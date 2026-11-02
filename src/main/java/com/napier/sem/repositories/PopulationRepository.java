package com.napier.sem.repositories;

import com.napier.sem.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PopulationRepository {
    /**
     *
     */

    /**
     *
     */


    }


        try (Connection connect = DatabaseConnection.connect();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                }
            }
        } catch (SQLException e) {
    }

        }
    }