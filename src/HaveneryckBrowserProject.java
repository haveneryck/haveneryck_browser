import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HaveneryckBrowserProject extends Application {
	
	@Override
	public void start(Stage palco) {
		TextField campoUrl = new TextField();
		WebView navegador = new WebView();
		WebEngine motor = navegador.getEngine();

		campoUrl.setOnAction(evento -> motor.load( formataUrl(campoUrl.getText())));
		VBox vBox = new VBox();
		vBox.getChildren().addAll(campoUrl, navegador);
		Scene cena = new Scene(vBox);
		palco.setTitle("Meu Browser Java");
		palco.setScene(cena);
		palco.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	public String formataUrl(String url) {
    // Trata entrada nula ou vazia
    if (url == null || url.trim().isEmpty()) {
        return "https://www.google.com";
    }
    url = url.trim(); // Remove espaços extras no início/fim
    // Adiciona protocolo se necessário
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        url = "http://" + url;
    }
    return url;
	}
}
