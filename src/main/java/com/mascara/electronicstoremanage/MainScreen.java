package com.mascara.electronicstoremanage;

import com.mascara.electronicstoremanage.entities.Role;
import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.enums.general.SexEnum;
import com.mascara.electronicstoremanage.enums.staff.StaffStatusEnum;
import com.mascara.electronicstoremanage.services.role.RoleServiceImpl;
import com.mascara.electronicstoremanage.services.staff.StaffServiceImpl;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.role.RoleCreateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffCreateRequest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MainScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/form/login.fxml"));
        String urlIcon = getClass().getResource("/icon_app.png").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Đăng nhập");
        stage.getIcons().add(new Image(urlIcon));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        HibernateUtils.buildTable();
        initData();
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

            List<Staff> staffList = session.createQuery("select s from Staff s where s.deleted is false", Staff.class)
                    .getResultList();
            if (staffList.isEmpty()) {
                StaffCreateRequest staff1 = StaffCreateRequest.builder()
                        .fullName("Nguyễn Đức Thành")
                        .phoneNumber("0342293128")
                        .email("nv01@gmail.com")
                        .dateOfBirth(LocalDate.of(2002,12,23))
                        .sex(SexEnum.getEnumByDisplay("Nam"))
                        .userName("nv01")
                        .password("Dthanh123456#")
                        .status(StaffStatusEnum.getEnumByDisplay("Đang làm việc"))
                        .roleName("Admin")
                        .build();

                StaffCreateRequest staff2 = StaffCreateRequest.builder()
                        .fullName("Mai Bảo Huy")
                        .phoneNumber("0342293122")
                        .email("nv02@gmail.com")
                        .dateOfBirth(LocalDate.of(2002,02,05))
                        .sex(SexEnum.getEnumByDisplay("Nam"))
                        .userName("nv02")
                        .password("Bhuy123456#")
                        .status(StaffStatusEnum.getEnumByDisplay("Đang làm việc"))
                        .roleName("Admin")
                        .build();

                StaffServiceImpl.getInstance().insertStaff(staff1);
                StaffServiceImpl.getInstance().insertStaff(staff2);
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