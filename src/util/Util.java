package util;

import java.sql.Date;
import java.time.LocalDate;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Util {


	public static Integer formatTelefone(TextField telefone) {
		String fone = telefone.getText();
		
		fone = fone.replace("(", "");
		fone = fone.replace(")", "");
		fone = fone.replace("-", "");

		System.out.println(fone);
		
		return Integer.parseInt(fone);
	}
	
	public static Integer formatCpfCnpj(TextField CpfCnpj) {
		String cod = CpfCnpj.getText();
		
		cod = cod.replace(".", "");
		cod = cod.replace(".", "");
		cod = cod.replace("/", "");
		cod = cod.replace("-", "");
		
		System.out.println(cod);
		
		return Integer.parseInt(cod);
	}
	
	
	/**
	 * Recebe um LocaDate e retorna um Date
	 * 
	 * @param localDate
	 * @return
	 */
	public static Date asDate(LocalDate localDate) {
		return java.sql.Date.valueOf(localDate);
	}
	
	/**
	 * Recebe um Date e retorna um LocalDate
	 * 
	 * @param localDate
	 * @return
	 */
	public static LocalDate asLocalDate(Date date) {
		return LocalDate.parse(date.toString());
	}

	/**
	 * Monta a mascara para Moeda.
	 *
	 * @param textField
	 *            TextField
	 */
	public static void monetaryField(final TextField textField) {
		textField.setAlignment(Pos.CENTER_RIGHT);
		textField.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String value = textField.getText();
				value = value.replaceAll("[^0-9]", "");
				value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
				value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
				value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
				value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
				value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
				textField.setText(value);
				positionCaret(textField);

				textField.textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observableValue, String oldValue,
							String newValue) {
						if (newValue.length() > 9)
							textField.setText(oldValue);
					}
				});
			}
		});
	}

	/**
	 * Retorna monetário formatado para o banco MySQL
	 */
	public static Double formatDouble(String valor) {
		String num = "";
		if (num != null) {
			num = valor.replace(".", "");
			num = num.replaceAll(",", ".");
		}
		return Double.parseDouble(num);
	}

	/**
	 * Formata o campo de texto para no máximo 250 caracteres.
	 *
	 * @param textArea
	 *            TextArea
	 */
	public static void textAreaField(final TextArea textArea) {
		textArea.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				textArea.textProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observableValue, String oldValue,
							String newValue) {
						if (newValue.length() > 250)
							textArea.setText(oldValue);
					}
				});
			}
		});
	}

	/**
	 * @param textField
	 *            TextField.
	 * @param length
	 *            Tamanho do campo.
	 */
	private static void maxField(final TextField textField, final Integer length) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				if (newValue.length() > length)
					textField.setText(oldValue);
			}
		});
	}

	/**
	 * Devido ao incremento dos caracteres das mascaras eh necessario que o cursor
	 * sempre se posicione no final da string.
	 *
	 * @param textField
	 *            TextField
	 */
	private static void positionCaret(final TextField textField) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Posiciona o cursor sempre a direita.
				textField.positionCaret(textField.getText().length());
			}
		});
	}
	/**
	 * Mostra uma mensagem de confirmação para a exclusao de algum ítem.
	 */
	public static ButtonType alertaExclusao() {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Confirmar Exclusão!?");
		alerta.setHeaderText("Tem certeza que deseja excluir?");
		alerta.setContentText("O processo a seguir é irreversível.");
		alerta.showAndWait();
		
		return alerta.getResult();
	}
}
