package com.autoscuola.view;

import com.autoscuola.controller.LoginController;

import java.util.Scanner;

public class LoginView {
    private final LoginController loginController;

    public LoginView(LoginController loginController) {
        this.loginController = loginController;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (loginController.login(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed! Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView(loginController);
        loginView.show();
    }
}
