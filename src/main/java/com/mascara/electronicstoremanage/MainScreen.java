package com.mascara.electronicstoremanage;

import com.mascara.electronicstoremanage.entities.Role;
import com.mascara.electronicstoremanage.services.role.RoleServiceImpl;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.role.RoleCreateRequest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class MainScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/form/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        HibernateUtils.buildTable();
//        initData();
        launch();
    }

    public static void initData() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Role> roleList = session.createQuery("select r from Role r where r.deleted is false", Role.class)
                    .getResultList();
            if (roleList.isEmpty()) {
                RoleCreateRequest adminRequest = RoleCreateRequest.builder()
                        .roleName("Admin").build();
                RoleCreateRequest staffRequest = RoleCreateRequest.builder()
                        .roleName("Staff").build();
                RoleServiceImpl.getInstance().insertRole(adminRequest);
                RoleServiceImpl.getInstance().insertRole(staffRequest);
            }
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}