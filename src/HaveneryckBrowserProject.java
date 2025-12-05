import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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

	    // Botões
	    Button btnVoltar = new Button("← Voltar");
	    Button btnAvancar = new Button("Avançar →");
	    Button btnRecarregar = new Button("↻ Recarregar");

	    // Ações dos botões
	    btnVoltar.setOnAction(e -> {
	        if (motor.getHistory().getCurrentIndex() > 0) {
	            motor.getHistory().go(-1);
	        }
	    });

	    btnAvancar.setOnAction(e -> {
	        if (motor.getHistory().getCurrentIndex() < motor.getHistory().getEntries().size() - 1) {
	            motor.getHistory().go(1);
	        }
	    });

	    btnRecarregar.setOnAction(e -> motor.reload());

	    campoUrl.setOnAction(evento -> motor.load(formataUrl(campoUrl.getText())));


	    // Sincroniza o campo de URL com a navegação real
		motor.locationProperty().addListener((obs, oldUrl, newUrl) -> {
		    if (newUrl != null) {
		        campoUrl.setText(newUrl);
		    }
		});

	    // Layout
	    HBox barraBotoes = new HBox(5);
	    barraBotoes.getChildren().addAll(btnVoltar, btnAvancar, btnRecarregar);

	    VBox vBox = new VBox(10);
	    vBox.getChildren().addAll(campoUrl, barraBotoes, navegador);

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
