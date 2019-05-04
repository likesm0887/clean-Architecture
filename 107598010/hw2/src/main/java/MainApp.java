/**
 * Copyright 2019 [Yu-Xin Chen]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import adapter.entryPoint.Controller;
import View.UI.MainView;
import adapter.gateway.Database;
import adapter.gateway.MemoryDatabase;
import adapter.gateway.SqliteDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import core.useCase.UseCaseFactory;

public class MainApp extends Application {
    private Controller controller;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main.fxml"));
        Parent root = loader.load();

        Database database = new SqliteDatabase();
        UseCaseFactory useCaseFactory =new UseCaseFactory(database);
        controller =new Controller(useCaseFactory);


        Scene scene = new Scene(root, 760, 500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
        MainView mainView = loader.getController();
        mainView.setController(controller);
        mainView.updateUI();


        primaryStage.setTitle("Clean Course");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
