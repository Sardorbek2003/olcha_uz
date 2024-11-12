package dasturlash.uz.dao;

import dasturlash.uz.config.PostgresqlConfig;
import dasturlash.uz.entity.Category_;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<Category_> getAllCategory() {
        List<Category_> categories = new ArrayList<>();
        String query = "SELECT id, name, parent_id, activ, created_date, updated_date FROM category";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Category_ category = new Category_(resultSet);
                categories.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public void addCategory(Category_ category) {
        String query = "INSERT INTO category (name, parent_id, activ, created_date, updated_date,createdBy,updatedBy) VALUES (?, ?, ?, NOW(), NOW(),?, ?)";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getParentId());
            preparedStatement.setBoolean(3, category.isActiv());
            preparedStatement.setString(4, category.getCreatedBy());
            preparedStatement.setString(5, category.getUpdatedBy());


            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Category_ category) {
        String query = "UPDATE category SET name = ?, parent_id = ?, activ = ?, updated_by, updated_date = NOW() WHERE id = ?";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getParentId());
            preparedStatement.setBoolean(3, category.isActiv());
            preparedStatement.setString(4, category.getUpdatedBy());
            preparedStatement.setInt(5, category.getId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCategoryById(int id) {
        String query = "DELETE FROM category WHERE id = ?";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
