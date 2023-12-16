


package Dao;

import Entity.Policy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


public class InsuranceServiceImplements implements PolicyService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Insurance";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql#25@067788@";

    @Override
    public boolean createPolicy(Policy policy) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO policy (policyId, policyName, policyType, coverageAmount) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, policy.getPolicyId());
                statement.setString(2, policy.getPolicyType()); 
                statement.setString(3, policy.getPolicyDescription());  
                statement.setBigDecimal(4, policy.getCoverageAmount());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM policy WHERE policyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, policyId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Policy(
                                resultSet.getInt("policyId"),
                                resultSet.getString("policyName"),
                                resultSet.getString("policyType"),
                                resultSet.getBigDecimal("coverageAmount")  
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Policy> getAllPolicies() {
        Collection<Policy> policies = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM policy";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        Policy policy = new Policy(
                                resultSet.getInt("policyId"),
                                resultSet.getString("policyType"),
                                resultSet.getString("policyName"),
                                resultSet.getBigDecimal("coverageAmount")  
                        );
                        policies.add(policy);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "UPDATE policy SET policyType = ?, policyName = ?, coverageAmount = ? WHERE policyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, policy.getPolicyType());
                statement.setString(2, policy.getPolicyDescription());
                statement.setBigDecimal(3, policy.getCoverageAmount());  
                statement.setInt(4, policy.getPolicyId());

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "DELETE FROM policy WHERE policyId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, policyId);

                int rowsDeleted = statement.executeUpdate();
                return rowsDeleted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

